package com.inventra.backend.service;

import com.inventra.backend.dto.DailySalesResponse;
import com.inventra.backend.dto.DemandForecastResponse;
import com.inventra.backend.forecast.ForecastEngine;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ForecastService {

    private final AnalyticsService analyticsService;
    private final ForecastEngine forecastEngine;

    public ForecastService(
            AnalyticsService analyticsService,
            ForecastEngine forecastEngine
    ) {
        this.analyticsService = analyticsService;
        this.forecastEngine = forecastEngine;
    }

    public DemandForecastResponse forecast(
            String productId,
            int days
    ) {

        if (days <= 0) {
            throw new IllegalArgumentException(
                    "Forecast days must be greater than 0"
            );
        }

        LocalDate to = LocalDate.now();

        LocalDate from = to.minusDays(days - 1L);

        List<DailySalesResponse> dailySales =
                analyticsService.getDailySales(
                        productId,
                        from,
                        to
                );

        if (dailySales.isEmpty()) {
            throw new IllegalArgumentException(
                    "No historical sales data available for this product"
            );
        }

        boolean dataSufficient =
                dailySales.size() >= 7;

        String confidence;

        if (dailySales.size() >= 30) {
            confidence = "HIGH";
        } else if (dailySales.size() >= 14) {
            confidence = "MEDIUM";
        } else if (dailySales.size() >= 7) {
            confidence = "LOW";
        } else {
            confidence = "INSUFFICIENT";
        }

        double predictedDemand =
                forecastEngine.forecast(dailySales);

        double demandStandardDeviation =
                calculateStandardDeviation(dailySales);

        return new DemandForecastResponse(
                productId,
                to.plusDays(1),
                dailySales.size(),
                predictedDemand,
                dataSufficient,
                confidence,
                demandStandardDeviation
        );
    }

    private double calculateStandardDeviation(
            List<DailySalesResponse> dailySales
    ) {

        if (dailySales.size() <= 1) {
            return 0.0;
        }

        double mean = dailySales.stream()
                .mapToLong(DailySalesResponse::getUnitsSold)
                .average()
                .orElse(0.0);

        double variance = dailySales.stream()
                .mapToDouble(day ->
                        Math.pow(
                                day.getUnitsSold() - mean,
                                2
                        )
                )
                .sum()
                / (dailySales.size() - 1);

        return Math.sqrt(variance);
    }
}