package com.fooddelivery.payment.event;

public final class EventType {
    public static final String ORDER_CREATED = "ORDER_CREATED";
    public static final String PAYMENT_SUCCESS = "PAYMENT_SUCCESS";
    public static final String PAYMENT_FAILED = "PAYMENT_FAILED";

    private EventType() {}
}
