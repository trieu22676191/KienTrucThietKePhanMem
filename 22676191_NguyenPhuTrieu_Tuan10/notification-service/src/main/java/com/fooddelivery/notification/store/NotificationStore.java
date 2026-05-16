package com.fooddelivery.notification.store;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class NotificationStore {
    private final Map<Long, List<NotificationRecord>> store = new ConcurrentHashMap<>();

    public void add(Long userId, String message) {
        store.computeIfAbsent(userId, k -> Collections.synchronizedList(new ArrayList<>()))
                .add(new NotificationRecord(message, Instant.now()));
    }

    public List<NotificationRecord> getByUser(Long userId) {
        return store.getOrDefault(userId, List.of());
    }

    public record NotificationRecord(String message, Instant sentAt) {}
}
