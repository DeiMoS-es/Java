package com.ecommerce_microservicios.products_service.exception;

public class ProductException extends RuntimeException{
    public ProductException(String message){
        super(message);
    }
}
