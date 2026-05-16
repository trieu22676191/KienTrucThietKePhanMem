package com.fooddelivery.payment.listener;

import com.fooddelivery.payment.config.RabbitConfig;
import com.fooddelivery.payment.event.EventType;
import com.fooddelivery.payment.event.OrderCreatedEvent;
import com.fooddelivery.payment.event.PaymentResultEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class OrderCreatedListener {
    private static final Logger log = LoggerFactory.getLogger(OrderCreatedListener.class);
    private final RabbitTemplate rabbitTemplate;
    private final double successRate;

    public OrderCreatedListener(RabbitTemplate rabbitTemplate,
                                @Value("${payment.success-rate}") double successRate) {
        this.rabbitTemplate = rabbitTemplate;
        this.successRate = successRate;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "payment.order.created.queue", durable = "true"),
            exchange = @Exchange(value = "food-delivery.exchange", type = "topic"),
            key = EventType.ORDER_CREATED
    ))
    public void handleOrderCreated(OrderCreatedEvent event) {
        log.info("[PAYMENT] Processing payment for order #{} amount {}", event.getOrderId(), event.getTotalAmount());

        boolean success = ThreadLocalRandom.current().nextDouble() < successRate;

        if (success) {
            PaymentResultEvent result = new PaymentResultEvent(
                    EventType.PAYMENT_SUCCESS,
                    event.getOrderId(),
                    event.getUserId(),
                    event.getTotalAmount(),
                    "Thanh toán thành công"
            );
            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, EventType.PAYMENT_SUCCESS, result);
            log.info("[PAYMENT] Published PAYMENT_SUCCESS for order #{}", event.getOrderId());
        } else {
            PaymentResultEvent result = new PaymentResultEvent(
                    EventType.PAYMENT_FAILED,
                    event.getOrderId(),
                    event.getUserId(),
                    event.getTotalAmount(),
                    "Thanh toán thất bại (random simulate)"
            );
            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, EventType.PAYMENT_FAILED, result);
            log.info("[PAYMENT] Published PAYMENT_FAILED for order #{}", event.getOrderId());
        }
    }
}
