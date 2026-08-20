package com.inventra.backend.dto;

import java.time.LocalDate;

public class DemandForecastResponse {

    private String productId;
    private LocalDate forecastDate;
    private int historicalDays;
    private double predictedDailyDemand;

    public DemandForecastResponse(
            String productId,
            LocalDate forecastDate,
            int historicalDays,
            double predictedDailyDemand
    ) {
        this.productId = productId;
        this.forecastDate = forecastDate;
        this.historicalDays = historicalDays;
        this.predictedDailyDemand = predictedDailyDemand;
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
}