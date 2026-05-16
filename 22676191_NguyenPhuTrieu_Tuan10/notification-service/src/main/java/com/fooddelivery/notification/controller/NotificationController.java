package com.fooddelivery.notification.controller;

import com.fooddelivery.notification.store.NotificationStore;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationStore notificationStore;

    public NotificationController(NotificationStore notificationStore) {
        this.notificationStore = notificationStore;
    }

    @GetMapping
    public List<NotificationStore.NotificationRecord> getNotifications(@RequestParam Long userId) {
        return notificationStore.getByUser(userId);
    }
}
