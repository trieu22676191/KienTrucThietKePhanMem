package com.fooddelivery.userfood.service;

import com.fooddelivery.userfood.dto.AuthResponse;
import com.fooddelivery.userfood.dto.LoginRequest;
import com.fooddelivery.userfood.dto.RegisterRequest;
import com.fooddelivery.userfood.model.User;
import com.fooddelivery.userfood.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username đã tồn tại");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFullName(request.getFullName());
        user = userRepository.save(user);
        return toAuthResponse(user);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .filter(u -> u.getPassword().equals(request.getPassword()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sai username hoặc password"));
        return toAuthResponse(user);
    }

    private AuthResponse toAuthResponse(User user) {
        String token = "token-" + user.getId() + "-" + UUID.randomUUID();
        return new AuthResponse(user.getId(), user.getUsername(), user.getFullName(), token);
    }
}
