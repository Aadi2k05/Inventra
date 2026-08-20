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

    @PostMapping("/transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryTransactionResponse createTransaction(
            @Valid @RequestBody InventoryTransactionRequest request
    ) {
        return inventoryService.createTransaction(request);
    }

    @GetMapping("/products/{productId}/transactions")
    public List<InventoryTransactionResponse> getTransactionsByProductId(
            @PathVariable String productId
    ) {
        return inventoryService.getTransactionsByProductId(productId);
    }
}