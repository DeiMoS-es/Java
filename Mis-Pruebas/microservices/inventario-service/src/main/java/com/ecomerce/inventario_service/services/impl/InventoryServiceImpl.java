package com.ecomerce.inventario_service.services.impl;

import com.ecomerce.inventario_service.model.dtos.BaseResponse;
import com.ecomerce.inventario_service.model.dtos.OrderItemRequest;
import com.ecomerce.inventario_service.model.entities.Inventory;
import com.ecomerce.inventario_service.repositories.InventoryRepository;
import com.ecomerce.inventario_service.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;


    @Override
    public boolean isInStock(String sku) {
        var inventory = inventoryRepository.findBySku(sku);
        return inventory.filter(value -> value.getQuantity() > 0).isPresent(); // retorna true si el producto está en stock y la cantidad es mayor a 0
    }

    // Esste método será accesible desde el servicio de ordenes
    @Override
    public BaseResponse areInStock(List<OrderItemRequest> orderItems) {
        var errorList = new ArrayList<String>(); // Lista de errores
        List<String> skus =  orderItems.stream().map(OrderItemRequest::getSku).toList(); // Extraer los skus de la lista de items
        List<Inventory> inventoryList = inventoryRepository.findBySkuIn(skus); // Buscar los productos en el inventario
        orderItems.forEach(orderItem -> {
            var inventory = inventoryList.stream().filter(value -> value.getSku().equals(orderItem.getSku())).findFirst(); // Buscar el producto en la lista de inventario
            if (inventory.isEmpty()) { // Si no se encuentra el producto o la cantidad es menor a la solicitada
                errorList.add("El producto con sku: " + orderItem.getSku() + " no existe."); // Agregar mensaje de error
            } else if (inventory.get().getQuantity() < orderItem.getQuantity()) {
                errorList.add("El producto con sku: " + orderItem.getSku() + " no tiene suficiente stock."); // Agregar mensaje de error

            }
        });
        return errorList.size() > 0 ? new BaseResponse(errorList.toArray(new String[0])) : new BaseResponse(null);
    }
}
