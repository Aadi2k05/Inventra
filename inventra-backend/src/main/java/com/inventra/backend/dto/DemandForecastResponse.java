package com.inventra.backend.dto;

import java.time.LocalDate;

public class DemandForecastResponse {

    private String productId;
    private String sku;
    private String productName;

    private LocalDate forecastDate;

    private int historicalDays;

    private double predictedDemand;
    private double averageDailyDemand;
    private double demandStandardDeviation;

    private int currentStock;
    private int reorderLevel;
    private int leadTimeDays;

    private double leadTimeDemand;
    private double safetyStock;
    private double reorderPoint;

    private int recommendedOrderQuantity;

    private boolean dataSufficient;
    private String confidence;
    private String status;

    public DemandForecastResponse(
            String productId,
            String sku,
            String productName,
            LocalDate forecastDate,
            int historicalDays,
            double predictedDemand,
            double averageDailyDemand,
            double demandStandardDeviation,
            int currentStock,
            int reorderLevel,
            int leadTimeDays,
            double leadTimeDemand,
            double safetyStock,
            double reorderPoint,
            int recommendedOrderQuantity,
            boolean dataSufficient,
            String confidence,
            String status
    ) {

        this.productId = productId;
        this.sku = sku;
        this.productName = productName;
        this.forecastDate = forecastDate;
        this.historicalDays = historicalDays;
        this.predictedDemand = predictedDemand;
        this.averageDailyDemand = averageDailyDemand;
        this.demandStandardDeviation = demandStandardDeviation;
        this.currentStock = currentStock;
        this.reorderLevel = reorderLevel;
        this.leadTimeDays = leadTimeDays;
        this.leadTimeDemand = leadTimeDemand;
        this.safetyStock = safetyStock;
        this.reorderPoint = reorderPoint;
        this.recommendedOrderQuantity = recommendedOrderQuantity;
        this.dataSufficient = dataSufficient;
        this.confidence = confidence;
        this.status = status;
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

    public LocalDate getForecastDate() {
        return forecastDate;
    }

    public int getHistoricalDays() {
        return historicalDays;
    }

    public double getPredictedDemand() {
        return predictedDemand;
    }

    public double getAverageDailyDemand() {
        return averageDailyDemand;
    }

    /*
     * Compatibility method.
     *
     * ReorderService already uses:
     *
     * forecast.getPredictedDailyDemand()
     *
     * Keep this method so existing code continues
     * to compile.
     */
    public double getPredictedDailyDemand() {
        return averageDailyDemand;
    }

    public double getDemandStandardDeviation() {
        return demandStandardDeviation;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public int getLeadTimeDays() {
        return leadTimeDays;
    }

    public double getLeadTimeDemand() {
        return leadTimeDemand;
    }

    public double getSafetyStock() {
        return safetyStock;
    }

    public double getReorderPoint() {
        return reorderPoint;
    }

    public int getRecommendedOrderQuantity() {
        return recommendedOrderQuantity;
    }

    public boolean isDataSufficient() {
        return dataSufficient;
    }

    public String getConfidence() {
        return confidence;
    }

    public String getStatus() {
        return status;
    }
}