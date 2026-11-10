package com.padapav.order.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
public record OrderLineRequest(@NotNull @Positive Long menuItemId, @NotBlank String menuItemName,
    @NotNull @Positive Integer quantity, @NotNull @Positive Long unitPrice) {}
