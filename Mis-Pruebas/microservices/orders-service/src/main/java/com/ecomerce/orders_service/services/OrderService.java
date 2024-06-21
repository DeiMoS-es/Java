package com.ecomerce.orders_service.services;

import com.ecomerce.orders_service.model.dtos.OrderRequest;
import com.ecomerce.orders_service.model.dtos.OrderResponse;
import com.ecomerce.orders_service.model.entities.Order;

import java.util.List;

public interface OrderService {
    void placeOrder(OrderRequest orderRequest);
    List<OrderResponse> getAllOrders();
}
