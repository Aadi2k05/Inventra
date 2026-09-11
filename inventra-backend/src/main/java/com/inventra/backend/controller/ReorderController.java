package com.inventra.backend.controller;

import com.inventra.backend.dto.ReorderRecommendationResponse;
import com.inventra.backend.service.ReorderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reorder")
public class ReorderController {

    private final ReorderService reorderService;

    public ReorderController(ReorderService reorderService) {
        this.reorderService = reorderService;
    }

    @GetMapping("/products/{productId}")
    public ReorderRecommendationResponse getRecommendation(
            @PathVariable String productId
    ) {
        return reorderService.getRecommendation(productId);
    }
}