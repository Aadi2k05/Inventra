package com.inventra.backend.dto;

import com.inventra.backend.model.InventoryTransactionType;

import java.time.LocalDateTime;

public class InventoryTransactionResponse {

    private String id;
    private String productId;
    private InventoryTransactionType type;
    private Integer quantity;
    private String reason;
    private LocalDateTime createdAt;

    public InventoryTransactionResponse() {
    }

    public InventoryTransactionResponse(
            String id,
            String productId,
            InventoryTransactionType type,
            Integer quantity,
            String reason,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.productId = productId;
        this.type = type;
        this.quantity = quantity;
        this.reason = reason;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public InventoryTransactionType getType() {
        return type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getReason() {
        return reason;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}