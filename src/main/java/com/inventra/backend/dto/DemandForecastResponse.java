package com.inventra.backend.dto;

import java.time.LocalDate;

public class DemandForecastResponse {

    private String productId;
    private LocalDate forecastDate;
    private int historicalDays;
    private double predictedDailyDemand;
    private boolean dataSufficient;
    private String confidence;
    private double demandStandardDeviation;

    public DemandForecastResponse(
            String productId,
            LocalDate forecastDate,
            int historicalDays,
            double predictedDailyDemand,
            boolean dataSufficient,
            String confidence,
            double demandStandardDeviation
    ) {
        this.productId = productId;
        this.forecastDate = forecastDate;
        this.historicalDays = historicalDays;
        this.predictedDailyDemand = predictedDailyDemand;
        this.dataSufficient = dataSufficient;
        this.confidence = confidence;
        this.demandStandardDeviation = demandStandardDeviation;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }

    public int getHistoricalDays() {
        return historicalDays;
    }

    public double getPredictedDailyDemand() {
        return predictedDailyDemand;
    }

    public boolean isDataSufficient() {
        return dataSufficient;
    }

    public String getConfidence() {
        return confidence;
    }

    public double getDemandStandardDeviation() {
        return demandStandardDeviation;
    }
}