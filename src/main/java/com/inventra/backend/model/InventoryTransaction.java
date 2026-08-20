package com.inventra.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "inventory_transactions")
public class InventoryTransaction {

    @Id
    private String id;

    private String productId;

    private InventoryTransactionType type;

    private Integer quantity;

    private String reason;

    private LocalDateTime createdAt;

    public InventoryTransaction() {
    }

    public String getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public InventoryTransactionType getType() {
        return type;
    }

    public void setType(InventoryTransactionType type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}