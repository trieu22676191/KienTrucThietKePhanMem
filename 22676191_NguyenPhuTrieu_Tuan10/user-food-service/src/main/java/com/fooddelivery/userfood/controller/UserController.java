package com.fooddelivery.userfood.controller;

import com.fooddelivery.userfood.dto.AuthResponse;
import com.fooddelivery.userfood.dto.LoginRequest;
import com.fooddelivery.userfood.dto.RegisterRequest;
import com.fooddelivery.userfood.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
}
