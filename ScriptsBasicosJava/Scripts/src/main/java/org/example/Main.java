package org.example;

public class Main {

    public static void main(String[] args) {

        int suma = Suma.sumar(3, 5);
        System.out.println("La suma de es " + suma);
        // Búsqueda binaria en un arreglo ordenado
        int[] arr = {2, 3, 4, 5, 6, 10, 40};
        int n = arr.length;
        int x = 40;
        int resultado = BusquedaBinaria.busquedaBinaria(arr, 0, n - 1, x);
        int resultado2 = BusquedaBinaria.busquedaBinariaV2(arr);
        if (resultado == -1) {
            System.out.println("El número no se encuentra en el arreglo.");
        } else {
            System.out.println("El número se encuentra en la posición " + resultado);
        }
        if (resultado2 == -1) {
            System.out.println("El número no se encuentra en el arreglo.");
        } else {
            System.out.println("El número se encuentra en la posición " + resultado2);
        }
        // Ordenación de array
        // Método de la burbuja
        int[] arr2 = {64, 34, 25, 12, 22, 90, 11};
        Ordenacion.bubbleSort(arr2);
        System.out.println("//////////////////////////////////////////");
        System.out.println("Arreglo ordenado por método de la burbuja");
        Ordenacion.printArray(arr2);
        // Método de selección
        int[] arr3 = {64, 34, 25, 12, 22, 11, 90};
        Ordenacion.selectionSort(arr3);
        System.out.println("//////////////////////////////////////////");
        System.out.println("Arreglo ordenado por método de selección");
        Ordenacion.printArray(arr3);
        System.out.println("//////////////////////////////////////////");
        System.out.println("Cambiando el inicio de las palabras");
        CambiarInicioPalabras.cambiarInicioPalabras("hola mundo desde java");
        System.out.println("//////////////////////////////////////////");
        System.out.println("Contando letras");
        System.out.println(OperacionesString.contarLetra("e"));
        System.out.println("//////////////////////////////////////////");
        System.out.println("Palabra más repetida");
        System.out.println(OperacionesString.palabraMasRepetida("hola mundo hola hola mundo mundo mundo hola hola"));
        System.out.println("//////////////////////////////////////////");
        System.out.println("Ordenando alfabéticamente");
        OperacionesString.ordenarAlfabeticamente("hola mundo desde java");
        System.out.println("//////////////////////////////////////////");
        System.out.println("Factorial de un número");
        System.out.println(FactorialEntero.factorial(5));
        System.out.println(FactorialEntero.factorialV2(5));
        System.out.println("//////////////////////////////////////////");
        System.out.println("Matrices");
        int[][] matrizA = {{1, 2, 3}, {4, 5, 6}};
        int[][] matrizB = {{7, 8}, {9, 10}, {11, 12}};
        int[][] matrizResultado = Matrices.multiplicaciónMatrices(matrizA, matrizB);
        Matrices.imprimirMatriz(matrizResultado);
    }
}