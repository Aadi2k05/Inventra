package com.inventra.backend.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record InventoryAdjustmentRequest(@NotBlank String productId,@NotNull Integer quantity,String reason) {}
