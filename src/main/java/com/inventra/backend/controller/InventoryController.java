package com.inventra.backend.controller;

import com.inventra.backend.dto.InventoryTransactionRequest;
import com.inventra.backend.dto.InventoryTransactionResponse;
import com.inventra.backend.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * Create a purchase, sale, return, damage or adjustment transaction.
     */
    @PostMapping("/transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryTransactionResponse createTransaction(
            @Valid @RequestBody InventoryTransactionRequest request
    ) {
        return inventoryService.createTransaction(request);
    }

    /**
     * Get all inventory transactions.
     *
     * This endpoint is used by the Inventory page.
     */
    @GetMapping("/transactions")
    public List<InventoryTransactionResponse> getAllTransactions() {
        return inventoryService.getAllTransactions();
    }

    /**
     * Get transaction history for a specific product.
     */
    @GetMapping("/products/{productId}/transactions")
    public List<InventoryTransactionResponse> getTransactionsByProductId(
            @PathVariable String productId
    ) {
        return inventoryService.getTransactionsByProductId(productId);
    }
}