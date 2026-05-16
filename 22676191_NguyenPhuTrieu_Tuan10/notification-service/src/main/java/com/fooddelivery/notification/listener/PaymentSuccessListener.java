package com.fooddelivery.notification.listener;

import com.fooddelivery.notification.event.EventType;
import com.fooddelivery.notification.event.PaymentResultEvent;
import com.fooddelivery.notification.store.NotificationStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentSuccessListener {
    private static final Logger log = LoggerFactory.getLogger(PaymentSuccessListener.class);
    private final NotificationStore notificationStore;

    public PaymentSuccessListener(NotificationStore notificationStore) {
        this.notificationStore = notificationStore;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "notification.payment.success.queue", durable = "true"),
            exchange = @Exchange(value = "food-delivery.exchange", type = "topic"),
            key = EventType.PAYMENT_SUCCESS
    ))
    public void handlePaymentSuccess(PaymentResultEvent event) {
        String message = String.format("Đơn hàng #%d đã thanh toán thành công!", event.getOrderId());
        notificationStore.add(event.getUserId(), message);
        log.info("[NOTIFICATION] {}", message);
    }
}
