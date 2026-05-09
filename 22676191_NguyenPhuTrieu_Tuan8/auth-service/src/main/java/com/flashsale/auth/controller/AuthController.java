package com.flashsale.auth.controller;

import com.flashsale.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body("Username and password are required");
        }

        String userKey = "user:" + user.getUsername() + ":password";
        if (Boolean.TRUE.equals(redisTemplate.hasKey(userKey))) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        redisTemplate.opsForValue().set(userKey, user.getPassword());
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body("Username and password are required");
        }

        String userKey = "user:" + user.getUsername() + ":password";
        String storedPassword = redisTemplate.opsForValue().get(userKey);

        if (storedPassword != null && storedPassword.equals(user.getPassword())) {
            String token = UUID.randomUUID().toString();
            String sessionKey = "session:" + token;
            // Store session in redis for 24 hours
            redisTemplate.opsForValue().set(sessionKey, user.getUsername(), 24, TimeUnit.HOURS);
            
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("username", user.getUsername());
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No token provided");
        }
        String token = authHeader.substring(7);
        String sessionKey = "session:" + token;
        String username = redisTemplate.opsForValue().get(sessionKey);
        
        if (username != null) {
            Map<String, String> response = new HashMap<>();
            response.put("username", username);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
    }
}
