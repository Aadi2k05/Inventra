package com.inventra.backend.repository;

import com.inventra.backend.model.InventoryTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InventoryTransactionRepository
        extends MongoRepository<InventoryTransaction, String> {

    List<InventoryTransaction> findByProductIdOrderByCreatedAtDesc(String productId);
}