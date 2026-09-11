package com.inventra.backend.forecast;

import com.inventra.backend.dto.DailySalesResponse;

import java.util.List;

public interface ForecastStrategy {

    double forecast(List<DailySalesResponse> dailySales);
}