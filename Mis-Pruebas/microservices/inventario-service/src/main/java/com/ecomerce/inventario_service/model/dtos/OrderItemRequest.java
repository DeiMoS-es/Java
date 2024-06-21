package com.ecomerce.inventario_service.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {
    private Long orderItemId;
    private String sku;
    private Double price;
    private Long quantity;
}
