package com.ecomerce.orders_service.services;

import com.ecomerce.orders_service.model.dtos.OrderRequest;

public interface OrderService {
    void placeOrder(OrderRequest orderRequest);
}
