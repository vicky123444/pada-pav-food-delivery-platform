package com.padapav.payment.model;
import java.time.Instant;
import java.util.UUID;
public record PaymentResponse(UUID paymentId, Long orderId, Long amountMinor, String currency,
    String method, PaymentStatus status, Instant createdAt) {}
