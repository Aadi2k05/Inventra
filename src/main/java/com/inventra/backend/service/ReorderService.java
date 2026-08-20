package com.inventra.backend.service;

import com.inventra.backend.dto.DemandForecastResponse;
import com.inventra.backend.dto.ReorderRecommendationResponse;
import com.inventra.backend.exception.ProductNotFoundException;
import com.inventra.backend.model.Product;
import com.inventra.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ReorderService {

    private final ProductRepository productRepository;
    private final ForecastService forecastService;

    public ReorderService(
            ProductRepository productRepository,
            ForecastService forecastService
    ) {
        this.productRepository = productRepository;
        this.forecastService = forecastService;
    }

    public ReorderRecommendationResponse getRecommendation(
            String productId
    ) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product with id '" +
                                        productId +
                                        "' not found"
                        )
                );

        int leadTimeDays =
                product.getLeadTimeDays() == null
                        ? 7
                        : product.getLeadTimeDays();

        DemandForecastResponse forecast =
                forecastService.forecast(productId, 7);

        if (!forecast.isDataSufficient()) {

            return new ReorderRecommendationResponse(
                    product.getId(),
                    product.getSku(),
                    product.getName(),
                    product.getStockQuantity(),
                    product.getReorderLevel(),
                    leadTimeDays,
                    forecast.getPredictedDailyDemand(),
                    0,
                    product.getReorderLevel(),
                    0,
                    false,
                    "Insufficient historical sales data for a reliable reorder recommendation.",
                    false,
                    forecast.getConfidence()
            );
        }

        double predictedDailyDemand =
                forecast.getPredictedDailyDemand();

        double leadTimeDemand =
                predictedDailyDemand * leadTimeDays;

        int safetyStock =
                Math.max(
                        product.getReorderLevel(),
                        (int) Math.ceil(
                                predictedDailyDemand * 2
                        )
                );

        int targetStock =
                (int) Math.ceil(
                        leadTimeDemand + safetyStock
                );

        int recommendedOrderQuantity =
                Math.max(
                        0,
                        targetStock - product.getStockQuantity()
                );

        boolean reorderRequired =
                product.getStockQuantity()
                        <= product.getReorderLevel()
                        || recommendedOrderQuantity > 0;

        String recommendation;

        if (recommendedOrderQuantity > 0) {
            recommendation =
                    "Reorder " +
                            recommendedOrderQuantity +
                            " units.";
        } else {
            recommendation =
                    "Stock level is currently sufficient.";
        }

        return new ReorderRecommendationResponse(
                product.getId(),
                product.getSku(),
                product.getName(),
                product.getStockQuantity(),
                product.getReorderLevel(),
                leadTimeDays,
                predictedDailyDemand,
                leadTimeDemand,
                safetyStock,
                recommendedOrderQuantity,
                reorderRequired,
                recommendation,
                forecast.isDataSufficient(),
                forecast.getConfidence()
        );
    }
}