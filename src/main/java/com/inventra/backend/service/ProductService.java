package com.inventra.backend.service;

import com.inventra.backend.dto.ProductRequest;
import com.inventra.backend.dto.ProductResponse;
import com.inventra.backend.exception.DuplicateSkuException;
import com.inventra.backend.exception.ProductNotFoundException;
import com.inventra.backend.model.Product;
import com.inventra.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(ProductRequest productRequest) {

        if (productRepository.existsBySku(productRequest.getSku())) {
            throw new DuplicateSkuException(
                    "Product with SKU '" + productRequest.getSku() + "' already exists"
            );
        }

        Product product = new Product();

        product.setSku(productRequest.getSku());
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setReorderLevel(productRequest.getReorderLevel());

        Product savedProduct = productRepository.save(product);

        return mapToResponse(savedProduct);
    }
    private ProductResponse mapToResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getSku(),
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getReorderLevel()
        );
    }
    public ProductResponse getProductById(String id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product with id '" + id + "' not found"
                        )
                );

        return mapToResponse(product);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<ProductResponse> getLowStockProducts() {

        return productRepository.findLowStockProducts()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
}