package com.fooddelivery.order.controller;

import com.fooddelivery.order.dto.CreateOrderRequest;
import com.fooddelivery.order.model.Order;
import com.fooddelivery.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@RequestBody CreateOrderRequest request) {
        return orderService.createOrder(request);
    }

    @GetMapping
    public List<Order> getOrders(@RequestParam(required = false) Long userId) {
        return orderService.getOrders(userId);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }
}
