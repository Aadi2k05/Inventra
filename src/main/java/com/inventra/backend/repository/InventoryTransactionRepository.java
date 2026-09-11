package com.inventra.backend.repository;

import com.inventra.backend.model.InventoryTransaction;
import com.inventra.backend.model.InventoryTransactionType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface InventoryTransactionRepository
        extends MongoRepository<InventoryTransaction, String> {

    /**
     * Get all transactions, newest first.
     */
    List<InventoryTransaction> findAllByOrderByCreatedAtDesc();

    /**
     * Get transactions for a specific product.
     */
    List<InventoryTransaction> findByProductIdOrderByCreatedAtDesc(
            String productId
    );

    /**
     * Get transactions for a product by transaction type.
     */
    List<InventoryTransaction> findByProductIdAndType(
            String productId,
            InventoryTransactionType type
    );

    /**
     * Get transactions for a product by type, oldest first.
     */
    List<InventoryTransaction>
    findByProductIdAndTypeOrderByCreatedAtAsc(
            String productId,
            InventoryTransactionType type
    );

    /**
     * Get transactions for a product and type
     * within a date range.
     */
    List<InventoryTransaction>
    findByProductIdAndTypeAndCreatedAtBetweenOrderByCreatedAtAsc(
            String productId,
            InventoryTransactionType type,
            LocalDateTime from,
            LocalDateTime to
    );
}