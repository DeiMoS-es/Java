package com.ecomerce.orders_service.services.impl;


import com.ecomerce.orders_service.model.dtos.*;
import com.ecomerce.orders_service.model.entities.Order;
import com.ecomerce.orders_service.model.entities.OrderItems;
import com.ecomerce.orders_service.repositories.OrderRepository;
import com.ecomerce.orders_service.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

   @Override
    public void placeOrder(OrderRequest orderRequest){
       // Antes de guardar la orden (pedido) debemos verificar que los productos cuenten con sotck
       // Para eso llamamos a un método en el microservicio de inventario
       BaseResponse result = this.webClientBuilder.build()
               .post()
                .uri("http://localhost:8080/api/inventory/in-stock")
                .bodyValue(orderRequest.getOrderItems())// Pasar la lista de OrderItemRequest
                .retrieve()// Realizar la llamada
                .bodyToMono(BaseResponse.class)// Convertir la respuesta a un objeto BaseResponse, contendrá los errores que se puedan producir en la llamada al servicio
                .block(); // Bloquear la ejecución hasta que se complete la llamada
       if(result != null && !result.hasErrors()) {
           // Si no hay errores, procedemos a guardar la orden
           // Crear una nueva instancia de la entidad Order
           Order order = new Order();
           // Generar un número de orden aleatorio y asignarlo a la orden
           order.setOrderNumber(UUID.randomUUID().toString());
           // Mapear cada OrderItemRequest del OrderRequest a una entidad OrderItems y asignar la lista resultante a la orden
           order.setOrderItems(orderRequest.getOrderItems().stream()
                   .map(orderItemRequest -> mapOrderItemRequestToOrderItem(orderItemRequest, order))
                   .toList());
           // Guardar la orden en la base de datos utilizando el repositorio
           this.orderRepository.save(order);
       }else{
           // Obtener los mensajes de error y lanzar una excepción con el primer mensaje de error
           String[] errors = result.getErrorMessages();
           if (errors != null && errors.length > 0) {
               throw new IllegalArgumentException(errors[0]);
           }
       }
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        // Recuperar todas las órdenes de la base de datos utilizando el repositorio
        List<Order> orders = this.orderRepository.findAll();
        // Mapear cada entidad Order a un objeto OrderResponse y devolver la lista resultante
        return orders.stream().map(this::mapToOrderResponse).toList();
    }

    private OrderResponse mapToOrderResponse(Order order) {
        // Crear una nueva instancia de OrderResponse utilizando los valores de la entidad Order
        // Mapear cada entidad OrderItems en la lista de OrderItems de la entidad Order a un objeto OrderItemsResponse
        return new OrderResponse(order.getOrderId(), order.getOrderNumber(),
                order.getOrderItems().stream().map(this::mapToOrderItemRequest).toList());
    }

    private OrderItemsResponse mapToOrderItemRequest(OrderItems orderItems) {
        // Crear una nueva instancia de OrderItemsResponse utilizando los valores de la entidad OrderItems
        return new OrderItemsResponse(orderItems.getOrderItemId(), orderItems.getSku(),
                orderItems.getPrice(), orderItems.getQuantity());
    }

    private OrderItems mapOrderItemRequestToOrderItem(OrderItemRequest orderItemRequest, Order order) {
       // Crear una nueva instancia de la entidad OrderItems utilizando el patrón de diseño Builder
       // Asignar los valores de OrderItemRequest a la entidad OrderItems
       // Asignar la entidad Order a la entidad OrderItems
       return OrderItems.builder()
                .orderItemId(orderItemRequest.getOrderItemId())
                .sku(orderItemRequest.getSku())
                .price(orderItemRequest.getPrice())
                .quantity(orderItemRequest.getQuantity())
                .order(order)
                .build();
    }
}
