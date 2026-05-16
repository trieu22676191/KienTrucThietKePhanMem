package com.fooddelivery.order.service;

import com.fooddelivery.order.client.FoodServiceClient;
import com.fooddelivery.order.dto.CreateOrderRequest;
import com.fooddelivery.order.dto.FoodDto;
import com.fooddelivery.order.event.EventPublisher;
import com.fooddelivery.order.event.OrderCreatedEvent;
import com.fooddelivery.order.model.Order;
import com.fooddelivery.order.repository.OrderRepository;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final FoodServiceClient foodServiceClient;
    private final EventPublisher eventPublisher;

    public OrderService(OrderRepository orderRepository,
                        FoodServiceClient foodServiceClient,
                        EventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.foodServiceClient = foodServiceClient;
        this.eventPublisher = eventPublisher;
    }

    @RateLimiter(name = "createOrder", fallbackMethod = "createOrderRateLimitFallback")
    public Order createOrder(CreateOrderRequest request) {
        if (request.getUserId() == null || request.getFoodId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId và foodId là bắt buộc");
        }
        if (request.getQuantity() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "quantity phải >= 1");
        }

        FoodDto food = foodServiceClient.getFoodById(request.getFoodId());
        if (food == null || food.getPrice() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy món ăn");
        }

        BigDecimal total = food.getPrice().multiply(BigDecimal.valueOf(request.getQuantity()));

        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setFoodId(food.getId());
        order.setFoodName(food.getName());
        order.setQuantity(request.getQuantity());
        order.setTotalAmount(total);
        order.setCreatedAt(Instant.now());
        order = orderRepository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent(
                order.getId(),
                order.getUserId(),
                order.getFoodId(),
                order.getFoodName(),
                order.getQuantity(),
                order.getTotalAmount(),
                order.getCreatedAt()
        );
        eventPublisher.publishOrderCreated(event);

        return order;
    }

    @SuppressWarnings("unused")
    private Order createOrderRateLimitFallback(CreateOrderRequest request, Throwable ex) {
        throw new ResponseStatusException(
                HttpStatus.TOO_MANY_REQUESTS,
                "Quá nhiều đơn hàng trong thời gian ngắn. Vui lòng thử lại sau."
        );
    }

    public List<Order> getOrders(Long userId) {
        if (userId != null) {
            return orderRepository.findByUserIdOrderByCreatedAtDesc(userId);
        }
        return orderRepository.findAll();
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy đơn hàng"));
    }
}
