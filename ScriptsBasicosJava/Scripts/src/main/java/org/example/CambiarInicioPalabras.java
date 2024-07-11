package org.example;

public class CambiarInicioPalabras {
    /**
     * Cambia el inicio de las palabras de una cadena a mayúsculas y añade la primera letra de la palabra.
     */
    static void cambiarInicioPalabras(String cadena) {
        String[] palabras = cadena.split(" ");
        for (int i = 0; i < palabras.length; i++) {
            palabras[i] = palabras[i].substring(0, 1).toUpperCase() + palabras[i].substring(0, 1) + palabras[i].substring(1);
        }
        System.out.println(String.join(" ", palabras));
    }
}
