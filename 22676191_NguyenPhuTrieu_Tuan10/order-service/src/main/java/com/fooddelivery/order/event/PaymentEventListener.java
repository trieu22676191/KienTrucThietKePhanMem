package com.fooddelivery.order.event;

import com.fooddelivery.order.model.Order;
import com.fooddelivery.order.model.OrderStatus;
import com.fooddelivery.order.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventListener {
    private static final Logger log = LoggerFactory.getLogger(PaymentEventListener.class);
    private final OrderRepository orderRepository;

    public PaymentEventListener(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "order.payment.success.queue", durable = "true"),
            exchange = @Exchange(value = "food-delivery.exchange", type = "topic"),
            key = EventType.PAYMENT_SUCCESS
    ))
    public void onPaymentSuccess(PaymentResultEvent event) {
        updateStatus(event.getOrderId(), OrderStatus.PAID);
        log.info("[ORDER] Order #{} -> PAID", event.getOrderId());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "order.payment.failed.queue", durable = "true"),
            exchange = @Exchange(value = "food-delivery.exchange", type = "topic"),
            key = EventType.PAYMENT_FAILED
    ))
    public void onPaymentFailed(PaymentResultEvent event) {
        updateStatus(event.getOrderId(), OrderStatus.PAYMENT_FAILED);
        log.info("[ORDER] Order #{} -> PAYMENT_FAILED", event.getOrderId());
    }

    private void updateStatus(Long orderId, OrderStatus status) {
        orderRepository.findById(orderId).ifPresent(order -> {
            order.setStatus(status);
            orderRepository.save(order);
        });
    }
}
