package com.example.user.util;

public class MensajeUtil {
    public static void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }
    public static void mensajeConfirmacion(String mensaje){
        System.out.println("Confirmación: " + mensaje);
    }
    public static void mensajeError(String mensaje){
        System.out.println("Error: " + mensaje);
    }
}
