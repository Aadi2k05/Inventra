package com.inventra.backend.controller;

import com.inventra.backend.dto.DemandForecastResponse;
import com.inventra.backend.service.ForecastService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/forecast")
public class ForecastController {

    private final ForecastService forecastService;

    public ForecastController(
            ForecastService forecastService
    ) {
        this.forecastService = forecastService;
    }

    @GetMapping("/products/{productId}")
    public DemandForecastResponse forecast(
            @PathVariable String productId,
            @RequestParam(defaultValue = "30") int days
    ) {

        return forecastService.forecast(
                productId,
                days
        );
    }
}