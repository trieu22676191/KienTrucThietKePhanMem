package com.fooddelivery.userfood.config;

import com.fooddelivery.userfood.model.Food;
import com.fooddelivery.userfood.model.User;
import com.fooddelivery.userfood.repository.FoodRepository;
import com.fooddelivery.userfood.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initData(UserRepository userRepository, FoodRepository foodRepository) {
        return args -> {
            User demo = new User();
            demo.setUsername("demo");
            demo.setPassword("123456");
            demo.setFullName("Người dùng Demo");
            userRepository.save(demo);

            foodRepository.save(createFood("Phở Bò", "Phở bò tái chín", "45000"));
            foodRepository.save(createFood("Cơm Tấm", "Cơm tấm sườn bì chả", "55000"));
            foodRepository.save(createFood("Bún Chả", "Bún chả Hà Nội", "50000"));
            foodRepository.save(createFood("Bánh Mì", "Bánh mì thịt nướng", "30000"));
            foodRepository.save(createFood("Trà Sữa", "Trà sữa trân châu", "35000"));
        };
    }

    private Food createFood(String name, String desc, String price) {
        Food food = new Food();
        food.setName(name);
        food.setDescription(desc);
        food.setPrice(new BigDecimal(price));
        food.setImageUrl("https://placehold.co/200x150?text=" + name.replace(" ", "+"));
        return food;
    }
}
