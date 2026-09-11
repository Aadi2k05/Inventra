package com.inventra.backend.controller;

import com.inventra.backend.dto.DashboardSummaryResponse;
import com.inventra.backend.dto.ProductResponse;
import com.inventra.backend.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService
    ) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/summary")
    public DashboardSummaryResponse getSummary() {
        return dashboardService.getSummary();
    }

    @GetMapping("/low-stock")
    public List<ProductResponse> getLowStockProducts() {
        return dashboardService.getLowStockProducts();
    }
}