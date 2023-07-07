package com.example.tienda.exception;

public class ImagenNotFoundException extends RuntimeException {

    public ImagenNotFoundException(String message) {
        super(message);
    }

    public ImagenNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
