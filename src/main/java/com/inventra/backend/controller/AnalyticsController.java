package com.inventra.backend.controller;

import com.inventra.backend.dto.SalesAnalyticsResponse;
import com.inventra.backend.service.AnalyticsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/products/{productId}/sales")
    public SalesAnalyticsResponse getSalesAnalytics(
            @PathVariable String productId
    ) {
        return analyticsService.getSalesAnalytics(productId);
    }
}