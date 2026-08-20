package com.inventra.backend.service;

import com.inventra.backend.dto.DailySalesResponse;
import com.inventra.backend.dto.DemandForecastResponse;
import com.inventra.backend.forecast.ForecastEngine;
import org.springframework.stereotype.Service;
import com.inventra.backend.forecast.ForecastEngine;
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
                    "Not enough sales data to generate forecast"
            );
        }

        double predictedDemand =
                forecastEngine.forecast(dailySales);

        return new DemandForecastResponse(
                productId,
                to.plusDays(1),
                dailySales.size(),
                predictedDemand
        );
    }
}