package com.fooddelivery.order.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE = "food-delivery.exchange";

    @Bean
    TopicExchange foodDeliveryExchange() {
        return new TopicExchange(EXCHANGE, true, false);
    }
}
