package com.padapav.notification.event;
import java.time.Instant;
public record OrderPaymentPendingEvent(Long orderId, Long customerId, Instant occurredAt) {}
