package com.padapav.notification.event;
import java.time.Instant;
public record OrderConfirmedEvent(Long orderId, Long customerId, Instant occurredAt) {}
