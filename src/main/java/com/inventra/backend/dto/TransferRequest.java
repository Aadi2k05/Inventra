package com.inventra.backend.dto;
import jakarta.validation.constraints.Min; import jakarta.validation.constraints.NotBlank;
public record TransferRequest(@NotBlank String productId,@NotBlank String toWarehouseId,@Min(1) int quantity,String reason) {}
