package com.example.tienda.exception;

public class ImagenException extends RuntimeException {

    public ImagenException(String message) {
        super(message);
    }

    public ImagenException(String message, Throwable cause) {
        super(message, cause);
    }
}

