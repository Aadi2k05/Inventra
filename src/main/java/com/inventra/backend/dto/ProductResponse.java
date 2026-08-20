package com.inventra.backend.dto;

public class ProductResponse {

    private String id;
    private String sku;
    private String name;
    private String category;
    private Double price;
    private Integer stockQuantity;
    private Integer reorderLevel;

    public ProductResponse() {
    }

    public ProductResponse(
            String id,
            String sku,
            String name,
            String category,
            Double price,
            Integer stockQuantity,
            Integer reorderLevel
    ) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.reorderLevel = reorderLevel;
    }

    public String getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public Integer getReorderLevel() {
        return reorderLevel;
    }
}