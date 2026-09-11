package com.inventra.backend.service;

import com.inventra.backend.dto.DashboardSummaryResponse;
import com.inventra.backend.dto.ProductResponse;
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

        long totalUnitsInStock = products.stream()
                .mapToLong(Product::getStockQuantity)
                .sum();

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
                totalUnitsInStock,
                totalInventoryValue
        );
    }

    public List<ProductResponse> getLowStockProducts() {

        List<Product> products =
                productRepository.findLowStockProducts();

        return products.stream()
                .map(product ->
                        new ProductResponse(
                                product.getId(),
                                product.getSku(),
                                product.getName(),
                                product.getCategory(),
                                product.getPrice(),
                                product.getStockQuantity(),
                                product.getReorderLevel(),
                                product.getLeadTimeDays()
                        )
                )
                .toList();
    }
}