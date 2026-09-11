package com.inventra.backend.repository;

import com.inventra.backend.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    boolean existsBySku(String sku);

    @Query("{ '$expr': { '$lte': ['$stockQuantity', '$reorderLevel'] } }")
    List<Product> findLowStockProducts();
}