package com.ecomerce.inventario_service.services;

import com.ecomerce.inventario_service.model.dtos.BaseResponse;
import com.ecomerce.inventario_service.model.dtos.OrderItemRequest;

import java.util.List;

public interface InventoryService {
    boolean isInStock(String sku);

    BaseResponse areInStock(List<OrderItemRequest> orderItems);
}
