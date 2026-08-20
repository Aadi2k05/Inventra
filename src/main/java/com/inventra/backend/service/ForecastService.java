package com.inventra.backend.service;

import com.inventra.backend.dto.DailySalesResponse;
import com.inventra.backend.dto.DemandForecastResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ForecastService {

    private final AnalyticsService analyticsService;

    public ForecastService(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
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
                    "Not enough sales data to generate forecast"
            );
        }

        double averageDemand = dailySales.stream()
                .mapToLong(DailySalesResponse::getUnitsSold)
                .average()
                .orElse(0.0);

        return new DemandForecastResponse(
                productId,
                to.plusDays(1),
                dailySales.size(),
                averageDemand
        );
    }
}