package com.ecommerce_microservicios.products_service.utils;

import com.ecommerce_microservicios.products_service.model.dtos.ProductRequest;

public class ValidationProduct {
    public static void validarProducto(ProductRequest productRequest){
        validarNombre(productRequest.getProductName());
        validarDescripcion(productRequest.getProductDescription());
        validarPrecio(productRequest.getProductPrice());
        validarSku(productRequest.getProductSku());
    }

    private static void validarSku(String productSku) {
        if(productSku == null){
            throw new RuntimeException("El productSku no puede ser nulo");
        }
        if(productSku.isEmpty()){
            throw new RuntimeException("El sku no puede estar vacío");
        }
        if(productSku.length() < 4 || productSku.length() > 10){
            throw new RuntimeException("El sku debe tener entre 4 y 10 caracteres");
        }
    }

    private static void validarPrecio(Double productPrice) {
        if(productPrice == null){
            throw new RuntimeException("El precio del producto no puede ser nulo");
        }
        if(Double.isNaN(productPrice)){
            throw new RuntimeException("El precio del producto debe ser un número");
        }
        if(productPrice <= 0){
            throw new RuntimeException("El precio del producto debe ser mayor que cero");
        }
    }

    private static void validarDescripcion(String productDescription) {
        if(productDescription == null){
            throw new RuntimeException("La descripción del producto no puede ser nula");
        }
        if (productDescription.isEmpty()){
            throw new RuntimeException("La descripción del producto no puede estar vacía");
        }
        if(productDescription.length() < 4 || productDescription.length() > 255){
            throw new RuntimeException("La descripción del producto debe tener entre 4 y 255 caracteres");
        }
    }

    private static void validarNombre(String productName) {
        if(productName == null){
            throw new RuntimeException("El nombre del producto no puede ser nulo");
        }
        if(productName.isEmpty()){
            throw new RuntimeException("El nombre del producto no puede estar vacío");
        }
        if(productName.length() < 4 || productName.length() > 50){
            throw new RuntimeException("El nombre del producto debe tener entre 4 y 50 caracteres");
        }
    }

}
