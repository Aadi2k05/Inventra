package com.inventra.backend.dto;

public class ReorderRecommendationResponse {

    private String productId;
    private String sku;
    private String productName;

    private Integer currentStock;
    private Integer reorderLevel;
    private Integer leadTimeDays;

    private double predictedDailyDemand;
    private double leadTimeDemand;
    private Integer safetyStock;
    private Integer recommendedOrderQuantity;

    private boolean reorderRequired;
    private String recommendation;
    private boolean dataSufficient;
    private String confidence;

    public ReorderRecommendationResponse(
            String productId,
            String sku,
            String productName,
            Integer currentStock,
            Integer reorderLevel,
            Integer leadTimeDays,
            double predictedDailyDemand,
            double leadTimeDemand,
            Integer safetyStock,
            Integer recommendedOrderQuantity,
            boolean reorderRequired,
            String recommendation,
            boolean dataSufficient,
            String confidence
    ) {
        this.productId = productId;
        this.sku = sku;
        this.productName = productName;
        this.currentStock = currentStock;
        this.reorderLevel = reorderLevel;
        this.leadTimeDays = leadTimeDays;
        this.predictedDailyDemand = predictedDailyDemand;
        this.leadTimeDemand = leadTimeDemand;
        this.safetyStock = safetyStock;
        this.recommendedOrderQuantity = recommendedOrderQuantity;
        this.reorderRequired = reorderRequired;
        this.recommendation = recommendation;
        this.dataSufficient = dataSufficient;
        this.confidence = confidence;
    }

    public String getProductId() {
        return productId;
    }

    public String getSku() {
        return sku;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getCurrentStock() {
        return currentStock;
    }

    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public Integer getLeadTimeDays() {
        return leadTimeDays;
    }

    public double getPredictedDailyDemand() {
        return predictedDailyDemand;
    }

    public double getLeadTimeDemand() {
        return leadTimeDemand;
    }

    public Integer getSafetyStock() {
        return safetyStock;
    }

    public Integer getRecommendedOrderQuantity() {
        return recommendedOrderQuantity;
    }

    public boolean isReorderRequired() {
        return reorderRequired;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public boolean isDataSufficient() {
        return dataSufficient;
    }

    public String getConfidence() {
        return confidence;
    }
}