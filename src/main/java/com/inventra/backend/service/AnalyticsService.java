package com.inventra.backend.service;

import com.inventra.backend.dto.DailySalesResponse;
import com.inventra.backend.dto.SalesAnalyticsResponse;
import com.inventra.backend.exception.ProductNotFoundException;
import com.inventra.backend.model.InventoryTransaction;
import com.inventra.backend.model.InventoryTransactionType;
import com.inventra.backend.repository.InventoryTransactionRepository;
import com.inventra.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    private final ProductRepository productRepository;
    private final InventoryTransactionRepository inventoryTransactionRepository;

    public AnalyticsService(
            ProductRepository productRepository,
            InventoryTransactionRepository inventoryTransactionRepository
    ) {
        this.productRepository = productRepository;
        this.inventoryTransactionRepository = inventoryTransactionRepository;
    }

    public SalesAnalyticsResponse getSalesAnalytics(String productId) {

        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException(
                    "Product with id '" + productId + "' not found"
            );
        }

        List<InventoryTransaction> sales =
                inventoryTransactionRepository.findByProductIdAndType(
                        productId,
                        InventoryTransactionType.SALE
                );

        long totalUnitsSold = sales.stream()
                .mapToLong(InventoryTransaction::getQuantity)
                .sum();

        long transactionCount = sales.size();

        return new SalesAnalyticsResponse(
                productId,
                totalUnitsSold,
                transactionCount
        );
    }

    public List<DailySalesResponse> getDailySales(String productId) {

        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException(
                    "Product with id '" + productId + "' not found"
            );
        }

        List<InventoryTransaction> sales =
                inventoryTransactionRepository
                        .findByProductIdAndTypeOrderByCreatedAtAsc(
                                productId,
                                InventoryTransactionType.SALE
                        );

        Map<LocalDate, Long> dailySales = sales.stream()
                .collect(Collectors.groupingBy(
                        transaction -> transaction.getCreatedAt().toLocalDate(),
                        TreeMap::new,
                        Collectors.summingLong(
                                InventoryTransaction::getQuantity
                        )
                ));

        return dailySales.entrySet()
                .stream()
                .map(entry ->
                        new DailySalesResponse(
                                entry.getKey(),
                                entry.getValue()
                        )
                )
                .toList();
    }
}