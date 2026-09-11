package com.inventra.backend.dto; public record InventoryResponse(String id,String warehouseId,String productId,int quantity,int reservedQuantity,int availableQuantity) {}
