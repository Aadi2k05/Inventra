package com.inventra.backend.dto;

public class SalesAnalyticsResponse {

    private String productId;
    private long totalUnitsSold;
    private long transactionCount;

    public SalesAnalyticsResponse(
            String productId,
            long totalUnitsSold,
            long transactionCount
    ) {
        this.productId = productId;
        this.totalUnitsSold = totalUnitsSold;
        this.transactionCount = transactionCount;
    }

    public String getProductId() {
        return productId;
    }

    public long getTotalUnitsSold() {
        return totalUnitsSold;
    }

    public long getTransactionCount() {
        return transactionCount;
    }
}