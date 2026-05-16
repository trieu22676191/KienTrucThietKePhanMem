package com.fooddelivery.userfood.repository;

import com.fooddelivery.userfood.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
