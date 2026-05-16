package com.fooddelivery.payment.event;

import java.math.BigDecimal;
import java.time.Instant;

public class OrderCreatedEvent {
    private String eventType;
    private Long orderId;
    private Long userId;
    private Long foodId;
    private String foodName;
    private int quantity;
    private BigDecimal totalAmount;
    private Instant createdAt;

    public String getEventType() { return eventType; }
    public Long getOrderId() { return orderId; }
    public Long getUserId() { return userId; }
    public Long getFoodId() { return foodId; }
    public String getFoodName() { return foodName; }
    public int getQuantity() { return quantity; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public Instant getCreatedAt() { return createdAt; }
}
