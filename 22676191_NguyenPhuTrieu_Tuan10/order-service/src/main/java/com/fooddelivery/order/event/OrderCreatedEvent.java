package com.fooddelivery.order.event;

import java.math.BigDecimal;
import java.time.Instant;

public class OrderCreatedEvent {
    private String eventType = EventType.ORDER_CREATED;
    private Long orderId;
    private Long userId;
    private Long foodId;
    private String foodName;
    private int quantity;
    private BigDecimal totalAmount;
    private Instant createdAt;

    public OrderCreatedEvent() {}

    public OrderCreatedEvent(Long orderId, Long userId, Long foodId, String foodName,
                             int quantity, BigDecimal totalAmount, Instant createdAt) {
        this.orderId = orderId;
        this.userId = userId;
        this.foodId = foodId;
        this.foodName = foodName;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getFoodId() { return foodId; }
    public void setFoodId(Long foodId) { this.foodId = foodId; }
    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
