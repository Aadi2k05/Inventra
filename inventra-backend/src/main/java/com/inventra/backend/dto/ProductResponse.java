package com.inventra.backend.dto;
public record ProductResponse(String id,String sku,String name,String category,Double price,Integer stockQuantity,Integer reorderLevel,Integer leadTimeDays) {}
