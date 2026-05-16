package com.fooddelivery.userfood.dto;

public class AuthResponse {
    private Long userId;
    private String username;
    private String fullName;
    private String token;

    public AuthResponse(Long userId, String username, String fullName, String token) {
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.token = token;
    }

    public Long getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getFullName() { return fullName; }
    public String getToken() { return token; }
}
