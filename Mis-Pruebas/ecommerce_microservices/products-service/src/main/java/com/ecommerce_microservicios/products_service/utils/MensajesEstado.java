package com.ecommerce_microservicios.products_service.utils;

public class MensajesEstado {
    public static void mensajeConfirmacion(String mensaje){
        System.out.println("Confirmación: " + mensaje);
    }
    public static void mensajeError(String mensaje){
        System.out.println("Error: " + mensaje);
    }
}
