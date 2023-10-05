package com.apiRest.pedido.exception;

public class PedidoException extends RuntimeException{

    public PedidoException(String mensaje){
        super(mensaje);
    }
}
