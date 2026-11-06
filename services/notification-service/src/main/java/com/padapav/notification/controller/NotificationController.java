package com.padapav.notification.controller;

import com.padapav.notification.model.NotificationMessage;
import com.padapav.notification.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/notifications")
    public List<NotificationMessage> getNotifications() {
        return notificationService.getNotifications();
    }

    @PostMapping("/notifications")
    public NotificationMessage sendNotification(@RequestBody NotificationMessage request) {
        return notificationService.sendNotification(request);
    }
}
