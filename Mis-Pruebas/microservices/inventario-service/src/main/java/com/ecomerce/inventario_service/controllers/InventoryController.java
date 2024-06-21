package com.ecomerce.inventario_service.controllers;

import com.ecomerce.inventario_service.model.dtos.BaseResponse;
import com.ecomerce.inventario_service.model.dtos.OrderItemRequest;
import com.ecomerce.inventario_service.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    //verificar si un producto esá en el inventario
    @GetMapping("/sku/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sku") String sku){
        return inventoryService.isInStock(sku);
    }

    //Este segundo método es el que vamos a invocar desde el servicio de ordenes
    @PostMapping("/in-stock")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse areInStock(@RequestBody List<OrderItemRequest> orderItems){
        return inventoryService.areInStock(orderItems);
    }
}
