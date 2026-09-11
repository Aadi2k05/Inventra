package com.inventra.backend.forecast;

import com.inventra.backend.dto.DailySalesResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SimpleMovingAverageStrategy implements ForecastStrategy {

    @Override
    public double forecast(List<DailySalesResponse> dailySales) {

        if (dailySales == null || dailySales.isEmpty()) {
            throw new IllegalArgumentException(
                    "Sales data cannot be empty"
            );
        }

        return dailySales.stream()
                .mapToLong(DailySalesResponse::getUnitsSold)
                .average()
                .orElse(0.0);
    }
}