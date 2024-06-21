package com.ecomerce.orders_service.model.dtos;

public record OrderItemsResponse(Long id, String sku, double price, Long quantity) {
}
