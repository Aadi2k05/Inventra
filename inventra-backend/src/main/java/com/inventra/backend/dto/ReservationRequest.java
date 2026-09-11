package com.inventra.backend.dto;
import jakarta.validation.constraints.Min; import jakarta.validation.constraints.NotBlank;
public record ReservationRequest(@NotBlank String orderId,@NotBlank String productId,@Min(1) int quantity) {}
