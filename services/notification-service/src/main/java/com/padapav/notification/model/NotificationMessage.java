package com.padapav.notification.model;

import lombok.Data;

@Data
public class NotificationMessage {

    private String id;
    private String type;
    private String channel;
    private String recipient;
    private String message;
}
