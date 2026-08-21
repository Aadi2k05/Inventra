package com.inventra.backend.dto;

public class DashboardSummaryResponse {

    private long totalProducts;
    private long lowStockProducts;
    private long outOfStockProducts;
    private long totalUnitsInStock;
    private double totalInventoryValue;

    public DashboardSummaryResponse(
            long totalProducts,
            long lowStockProducts,
            long outOfStockProducts,
            long totalUnitsInStock,
            double totalInventoryValue
    ) {
        this.totalProducts = totalProducts;
        this.lowStockProducts = lowStockProducts;
        this.outOfStockProducts = outOfStockProducts;
        this.totalUnitsInStock = totalUnitsInStock;
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

    public long getTotalUnitsInStock() {
        return totalUnitsInStock;
    }

    public double getTotalInventoryValue() {
        return totalInventoryValue;
    }
}