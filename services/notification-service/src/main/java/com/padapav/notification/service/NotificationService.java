package com.padapav.notification.service;

import com.padapav.notification.model.NotificationMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    private final List<NotificationMessage> notifications = new ArrayList<>();

    public List<NotificationMessage> getNotifications() {
        return notifications;
    }

    public NotificationMessage sendNotification(NotificationMessage request) {
        if (request.getId() == null || request.getId().isBlank()) {
            request.setId("NOTIF-" + System.currentTimeMillis());
        }
        notifications.add(request);
        return request;
    }
}
