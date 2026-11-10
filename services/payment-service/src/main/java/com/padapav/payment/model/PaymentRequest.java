package com.padapav.payment.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
public record PaymentRequest(@NotNull @Positive Long orderId, @NotNull @Positive Long amountMinor,
    @NotBlank @Pattern(regexp = "[A-Z]{3}") String currency, @NotBlank String method) {}
