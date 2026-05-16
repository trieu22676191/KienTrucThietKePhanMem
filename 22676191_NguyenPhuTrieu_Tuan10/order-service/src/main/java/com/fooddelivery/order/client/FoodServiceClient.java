package com.fooddelivery.order.client;

import com.fooddelivery.order.dto.FoodDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FoodServiceClient {
    private final RestTemplate restTemplate;
    private final String foodServiceUrl;

    public FoodServiceClient(RestTemplate restTemplate,
                             @Value("${food-service.url}") String foodServiceUrl) {
        this.restTemplate = restTemplate;
        this.foodServiceUrl = foodServiceUrl;
    }

    @CircuitBreaker(name = "foodService", fallbackMethod = "getFoodFallback")
    @Retry(name = "foodService")
    public FoodDto getFoodById(Long foodId) {
        return restTemplate.getForObject(
                foodServiceUrl + "/api/foods/" + foodId,
                FoodDto.class
        );
    }

    @SuppressWarnings("unused")
    private FoodDto getFoodFallback(Long foodId, Throwable ex) {
        throw new IllegalStateException(
                "Food Service không khả dụng (Circuit Breaker). foodId=" + foodId + ": " + ex.getMessage()
        );
    }
}
