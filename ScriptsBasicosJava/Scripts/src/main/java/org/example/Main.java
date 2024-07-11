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
        if (resultado == -1) {
            System.out.println("El número no se encuentra en el arreglo.");
        } else {
            System.out.println("El número se encuentra en la posición " + resultado);
        }
        // Ordenación de array
        // Método de la burbuja
        int[] arr2 = {64, 34, 25, 12, 22, 11, 90};
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
    }
}