package com.fooddelivery.userfood.controller;

import com.fooddelivery.userfood.model.Food;
import com.fooddelivery.userfood.service.FoodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {
    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public List<Food> getFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("/{id}")
    public Food getFood(@PathVariable Long id) {
        return foodService.getFoodById(id);
    }
}
