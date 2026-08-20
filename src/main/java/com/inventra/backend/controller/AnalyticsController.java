package com.inventra.backend.controller;

import com.inventra.backend.dto.SalesAnalyticsResponse;
import com.inventra.backend.service.AnalyticsService;
import org.springframework.web.bind.annotation.*;
import com.inventra.backend.dto.DailySalesResponse;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

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

    @GetMapping("/products/{productId}/daily-sales")
    public List<DailySalesResponse> getDailySales(
            @PathVariable String productId,
            @RequestParam LocalDate from,
            @RequestParam LocalDate to
    ) {
        return analyticsService.getDailySales(
                productId,
                from,
                to
        );
    }
}