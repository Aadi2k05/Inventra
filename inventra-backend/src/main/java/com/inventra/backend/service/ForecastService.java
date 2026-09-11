package com.inventra.backend.service;

import com.inventra.backend.dto.DailySalesResponse;
import com.inventra.backend.dto.DemandForecastResponse;
import com.inventra.backend.forecast.ForecastEngine;
import com.inventra.backend.model.Product;
import com.inventra.backend.exception.ProductNotFoundException;
import com.inventra.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ForecastService {

    private final AnalyticsService analyticsService;
    private final ForecastEngine forecastEngine;
    private final ProductRepository productRepository;

    public ForecastService(
            AnalyticsService analyticsService,
            ForecastEngine forecastEngine,
            ProductRepository productRepository
    ) {
        this.analyticsService = analyticsService;
        this.forecastEngine = forecastEngine;
        this.productRepository = productRepository;
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

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product with id '" +
                                        productId +
                                        "' not found"
                        )
                );

        LocalDate to = LocalDate.now();

        LocalDate from =
                to.minusDays(days - 1L);

        List<DailySalesResponse> dailySales =
                analyticsService.getDailySales(
                        productId,
                        from,
                        to
                );

        if (dailySales.isEmpty()) {

            throw new IllegalArgumentException(
                    "No historical sales data available for this product"
            );
        }

        int historicalDays =
                dailySales.size();

        boolean dataSufficient =
                historicalDays >= 7;

        String confidence;

        if (historicalDays >= 30) {

            confidence = "HIGH";

        } else if (historicalDays >= 14) {

            confidence = "MEDIUM";

        } else if (historicalDays >= 7) {

            confidence = "LOW";

        } else {

            confidence = "INSUFFICIENT";
        }

        /*
         * ForecastEngine calculates the predicted demand.
         */
        double predictedDemand =
                forecastEngine.forecast(dailySales);

        /*
         * Average daily demand from historical sales.
         */
        double averageDailyDemand =
                dailySales.stream()
                        .mapToLong(
                                DailySalesResponse::getUnitsSold
                        )
                        .average()
                        .orElse(0.0);

        /*
         * Demand variation.
         */
        double demandStandardDeviation =
                calculateStandardDeviation(
                        dailySales
                );

        /*
         * Product supplier lead time.
         */
        int leadTimeDays =
                product.getLeadTimeDays() == null
                        ? 7
                        : product.getLeadTimeDays();

        /*
         * Expected demand while waiting for
         * the supplier.
         */
        double leadTimeDemand =
                averageDailyDemand *
                        leadTimeDays;

        /*
         * Safety stock.
         *
         * Using 1.65 gives approximately
         * a 95% service-level style buffer.
         */
        double safetyStock =
                1.65 *
                        demandStandardDeviation *
                        Math.sqrt(
                                Math.max(
                                        leadTimeDays,
                                        1
                                )
                        );

        /*
         * Reorder point:
         *
         * Lead-time demand
         * +
         * Safety stock
         */
        double reorderPoint =
                leadTimeDemand +
                        safetyStock;

        /*
         * Current inventory.
         */
        int currentStock =
                product.getStockQuantity();

        /*
         * Order enough to reach the
         * calculated reorder point.
         */
        int recommendedOrderQuantity =
                (int) Math.ceil(
                        Math.max(
                                0,
                                reorderPoint -
                                        currentStock
                        )
                );

        /*
         * Determine inventory risk.
         */
        String status;

        if (currentStock == 0) {

            status = "CRITICAL";

        } else if (
                currentStock <= reorderPoint
        ) {

            status = "REORDER";

        } else if (
                currentStock <=
                        reorderPoint * 1.5
        ) {

            status = "WATCH";

        } else {

            status = "HEALTHY";
        }

        return new DemandForecastResponse(

                productId,

                product.getSku(),

                product.getName(),

                to.plusDays(1),

                historicalDays,

                predictedDemand,

                averageDailyDemand,

                demandStandardDeviation,

                currentStock,

                product.getReorderLevel(),

                leadTimeDays,

                leadTimeDemand,

                safetyStock,

                reorderPoint,

                recommendedOrderQuantity,

                dataSufficient,

                confidence,

                status
        );
    }


    private double calculateStandardDeviation(
            List<DailySalesResponse> dailySales
    ) {

        if (dailySales.size() <= 1) {
            return 0.0;
        }

        double mean =
                dailySales.stream()
                        .mapToLong(
                                DailySalesResponse::getUnitsSold
                        )
                        .average()
                        .orElse(0.0);

        double variance =
                dailySales.stream()
                        .mapToDouble(day ->
                                Math.pow(
                                        day.getUnitsSold() -
                                                mean,
                                        2
                                )
                        )
                        .sum()
                        /
                        (dailySales.size() - 1);

        return Math.sqrt(variance);
    }
}