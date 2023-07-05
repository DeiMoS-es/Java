package com.example.tienda.util;

public class MensajesUtil {
    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    public static void mostrarMensajeConfirmacion(String mensaje) {
        System.out.println("Confirmación: " + mensaje);
    }
    public static void mostrarMensajeError(String mensaje){
        System.out.println("Error: " + mensaje);
    }

}
