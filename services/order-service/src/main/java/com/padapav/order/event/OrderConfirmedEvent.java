package com.padapav.order.event;

import java.time.Instant;

public record OrderConfirmedEvent(
    String eventType,
    String eventId,
    Long orderId,
    Long customerId,
    Instant occurredAt,
    String correlationId
) {
}
