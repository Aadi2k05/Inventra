package com.inventra.backend.forecast;

import com.inventra.backend.dto.DailySalesResponse;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Primary;
import java.util.List;

@Component
@Primary
public class WeightedMovingAverageStrategy implements ForecastStrategy {

    @Override
    public double forecast(List<DailySalesResponse> dailySales) {

        if (dailySales == null || dailySales.isEmpty()) {
            throw new IllegalArgumentException(
                    "Sales data cannot be empty"
            );
        }

        long weightedSum = 0;
        long totalWeight = 0;

        for (int i = 0; i < dailySales.size(); i++) {

            long weight = i + 1;

            weightedSum +=
                    dailySales.get(i).getUnitsSold() * weight;

            totalWeight += weight;
        }

        return (double) weightedSum / totalWeight;
    }
}