package com.padapav.payment.service;
import com.padapav.payment.model.*;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PaymentService {
    private final Map<UUID, PaymentResponse> payments = new ConcurrentHashMap<>();
    private final Map<Long, UUID> paymentIdsByOrder = new ConcurrentHashMap<>();
    public PaymentResponse createPayment(PaymentRequest request) {
        UUID id = paymentIdsByOrder.computeIfAbsent(request.orderId(), orderId -> {
            PaymentStatus status = "CASH_ON_DELIVERY".equalsIgnoreCase(request.method())
                ? PaymentStatus.PENDING : PaymentStatus.SIMULATED_AUTHORIZED;
            PaymentResponse response = new PaymentResponse(UUID.randomUUID(), orderId, request.amountMinor(),
                request.currency(), request.method().toUpperCase(), status, Instant.now());
            payments.put(response.paymentId(), response);
            return response.paymentId();
        });
        return payments.get(id);
    }
    public PaymentResponse getPayment(UUID id) {
        PaymentResponse response = payments.get(id);
        if (response == null) throw new NoSuchElementException("Payment not found: " + id);
        return response;
    }
    public PaymentResponse getPaymentForOrder(Long orderId) {
        UUID id = paymentIdsByOrder.get(orderId);
        if (id == null) throw new NoSuchElementException("Payment not found for order: " + orderId);
        return getPayment(id);
    }
}
