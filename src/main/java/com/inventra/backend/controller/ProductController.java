package com.inventra.backend.controller;

import com.inventra.backend.dto.ProductRequest;
import com.inventra.backend.dto.ProductResponse;
import com.inventra.backend.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(
            @Valid @RequestBody ProductRequest productRequest
    ) {
        return productService.createProduct(productRequest);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/low-stock")
    public List<ProductResponse> getLowStockProducts() {
        return productService.getLowStockProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(
            @PathVariable String id
    ) {
        return productService.getProductById(id);
    }
}