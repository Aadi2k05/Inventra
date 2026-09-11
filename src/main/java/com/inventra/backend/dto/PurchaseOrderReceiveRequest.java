package com.inventra.backend.dto; import jakarta.validation.constraints.Min; public record PurchaseOrderReceiveRequest(@Min(1) int quantity) {}
