package com.padapav.payment.controller;
import com.padapav.payment.model.*;
import com.padapav.payment.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentService service;
    public PaymentController(PaymentService service) { this.service = service; }
    @PostMapping
    public ResponseEntity<PaymentResponse> create(@Valid @RequestBody PaymentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createPayment(request));
    }
    @GetMapping("/{id}")
    public PaymentResponse get(@PathVariable UUID id) { return service.getPayment(id); }
    @GetMapping("/order/{orderId}")
    public PaymentResponse getForOrder(@PathVariable Long orderId) { return service.getPaymentForOrder(orderId); }
}
