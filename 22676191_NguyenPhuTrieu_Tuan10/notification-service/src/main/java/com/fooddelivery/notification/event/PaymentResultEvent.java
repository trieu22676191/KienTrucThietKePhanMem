package com.fooddelivery.notification.event;

import java.math.BigDecimal;

public class PaymentResultEvent {
    private String eventType;
    private Long orderId;
    private Long userId;
    private BigDecimal amount;

    public String getEventType() { return eventType; }
    public Long getOrderId() { return orderId; }
    public Long getUserId() { return userId; }
    public BigDecimal getAmount() { return amount; }
}
