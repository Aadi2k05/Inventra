package com.inventra.backend.service;

import com.inventra.backend.dto.InventoryTransactionRequest;
import com.inventra.backend.dto.InventoryTransactionResponse;
import com.inventra.backend.exception.InsufficientStockException;
import com.inventra.backend.exception.ProductNotFoundException;
import com.inventra.backend.model.InventoryTransaction;
import com.inventra.backend.model.Product;
import com.inventra.backend.repository.InventoryTransactionRepository;
import com.inventra.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventoryService {

    private final ProductRepository productRepository;
    private final InventoryTransactionRepository inventoryTransactionRepository;

    public InventoryService(
            ProductRepository productRepository,
            InventoryTransactionRepository inventoryTransactionRepository
    ) {
        this.productRepository = productRepository;
        this.inventoryTransactionRepository = inventoryTransactionRepository;
    }

    /**
     * Create an inventory transaction and automatically update stock.
     */
    public InventoryTransactionResponse createTransaction(
            InventoryTransactionRequest request
    ) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product with id '" +
                                        request.getProductId() +
                                        "' not found"
                        )
                );

        int quantity = request.getQuantity();

        switch (request.getType()) {

            case PURCHASE, RETURN, ADJUSTMENT -> {

                product.setStockQuantity(
                        product.getStockQuantity() + quantity
                );
            }

            case SALE, DAMAGE -> {

                if (product.getStockQuantity() < quantity) {

                    throw new InsufficientStockException(
                            "Insufficient stock for product '" +
                                    product.getSku() +
                                    "'. Available stock: " +
                                    product.getStockQuantity()
                    );
                }

                product.setStockQuantity(
                        product.getStockQuantity() - quantity
                );
            }
        }

        // Save updated stock.
        productRepository.save(product);

        // Create transaction record.
        InventoryTransaction transaction =
                new InventoryTransaction();

        transaction.setProductId(product.getId());
        transaction.setType(request.getType());
        transaction.setQuantity(quantity);
        transaction.setReason(request.getReason());
        transaction.setCreatedAt(LocalDateTime.now());

        InventoryTransaction savedTransaction =
                inventoryTransactionRepository.save(transaction);

        return mapToResponse(savedTransaction);
    }

    /**
     * Get every inventory transaction.
     *
     * New endpoint:
     * GET /api/inventory/transactions
     */
    public List<InventoryTransactionResponse> getAllTransactions() {

        return inventoryTransactionRepository
                .findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    /**
     * Get transactions belonging to one product.
     *
     * Endpoint:
     * GET /api/inventory/products/{productId}/transactions
     */
    public List<InventoryTransactionResponse> getTransactionsByProductId(
            String productId
    ) {

        if (!productRepository.existsById(productId)) {

            throw new ProductNotFoundException(
                    "Product with id '" +
                            productId +
                            "' not found"
            );
        }

        return inventoryTransactionRepository
                .findByProductIdOrderByCreatedAtDesc(productId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    /**
     * Convert MongoDB transaction entity into API response.
     */
    private InventoryTransactionResponse mapToResponse(
            InventoryTransaction transaction
    ) {

        return new InventoryTransactionResponse(
                transaction.getId(),
                transaction.getProductId(),
                transaction.getType(),
                transaction.getQuantity(),
                transaction.getReason(),
                transaction.getCreatedAt()
        );
    }
}