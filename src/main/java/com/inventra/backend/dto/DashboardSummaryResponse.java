package com.inventra.backend.dto;

public class DashboardSummaryResponse {

    private long totalProducts;
    private long lowStockProducts;
    private long outOfStockProducts;
    private double totalInventoryValue;

    public DashboardSummaryResponse(
            long totalProducts,
            long lowStockProducts,
            long outOfStockProducts,
            double totalInventoryValue
    ) {
        this.totalProducts = totalProducts;
        this.lowStockProducts = lowStockProducts;
        this.outOfStockProducts = outOfStockProducts;
        this.totalInventoryValue = totalInventoryValue;
    }

    public long getTotalProducts() {
        return totalProducts;
    }

    public long getLowStockProducts() {
        return lowStockProducts;
    }

    public long getOutOfStockProducts() {
        return outOfStockProducts;
    }

    public double getTotalInventoryValue() {
        return totalInventoryValue;
    }
}