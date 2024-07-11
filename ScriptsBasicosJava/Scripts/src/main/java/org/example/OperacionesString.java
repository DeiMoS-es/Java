package org.example;

/**
 * Clase que realiza operaciones con cadenas de texto.
 */
public class OperacionesString {
    /**
     * Método que cuenta el número de veces que se repite una letra en una cadena.
     * @param letra Letra a contar.
     * @return Número de veces que se repite la letra.
     */
    public static int contarLetra(String letra){
        String cadena = "lorem ipsum dolor sit amet consectetur adipiscing elit";
        int n = cadena.length();
        int contador = 0;
        for(int i = 0; i < n; i++){
            if (cadena.substring(i, i + 1).equals(letra)){
                contador++;
            }
        }
        return contador;
    };

    public static String palabraMasRepetida(String cadena){
        String[] palabras = cadena.split(" ");
        int n = palabras.length;
        int contador = 0;
        String palabraMasRepetida = "";
        for(int i = 0; i < n; i++){
            int contadorPalabra = 0;
            for(int j = 0; j < n; j++){
                if (palabras[i].equals(palabras[j])){
                    contadorPalabra++;
                }
            }
            if (contadorPalabra > contador){
                contador = contadorPalabra;
                palabraMasRepetida = palabras[i];
            }
        }
        return palabraMasRepetida;
    };

}
