package com.inventra.backend.repository;

import com.inventra.backend.model.InventoryTransaction;
import com.inventra.backend.model.InventoryTransactionType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InventoryTransactionRepository
        extends MongoRepository<InventoryTransaction, String> {

    List<InventoryTransaction> findByProductIdOrderByCreatedAtDesc(
            String productId
    );

    List<InventoryTransaction> findByProductIdAndType(
            String productId,
            InventoryTransactionType type
    );

    List<InventoryTransaction> findByProductIdAndTypeOrderByCreatedAtAsc(
            String productId,
            InventoryTransactionType type
    );
}