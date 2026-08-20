package com.inventra.backend.service;

import com.inventra.backend.dto.DashboardSummaryResponse;
import com.inventra.backend.model.Product;
import com.inventra.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    private final ProductRepository productRepository;

    public DashboardService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public DashboardSummaryResponse getSummary() {

        List<Product> products = productRepository.findAll();

        long totalProducts = products.size();

        long lowStockProducts = products.stream()
                .filter(product ->
                        product.getStockQuantity()
                                <= product.getReorderLevel()
                )
                .count();

        long outOfStockProducts = products.stream()
                .filter(product ->
                        product.getStockQuantity() == 0
                )
                .count();

        double totalInventoryValue = products.stream()
                .mapToDouble(product ->
                        product.getPrice()
                                * product.getStockQuantity()
                )
                .sum();

        return new DashboardSummaryResponse(
                totalProducts,
                lowStockProducts,
                outOfStockProducts,
                totalInventoryValue
        );
    }
}