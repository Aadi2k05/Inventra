package com.inventra.backend.forecast;

import com.inventra.backend.dto.DailySalesResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ForecastEngine {

    private final ForecastStrategy forecastStrategy;

    public ForecastEngine(ForecastStrategy forecastStrategy) {
        this.forecastStrategy = forecastStrategy;
    }

    public double forecast(List<DailySalesResponse> dailySales) {

        return forecastStrategy.forecast(dailySales);
    }
}