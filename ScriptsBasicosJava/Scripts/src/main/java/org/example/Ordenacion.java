package org.example;

/**
 * Clase que realiza la ordenación de un arreglo de enteros.
 */
public class Ordenacion {
    /**
     * Método que realiza la ordenación de un arreglo de enteros mediante el algoritmo de ordenación burbuja.
     */
    static void bubbleSort(int[] arr){
        int n = arr.length;
        for (int i = 0; i < n-1; i++){
            for (int j = 0; j < n-i-1; j++){
                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    };

    /**
     * Método que realiza la ordenación de un arreglo de enteros mediante el algoritmo de ordenación por selección.
     */
    static void selectionSort(int[] arr){
        int n = arr.length;
        for (int i = 0; i < n-1; i++){ // Comenzamos desde el primer elemento
            int min_idx = i; // "Suponomenos" que el primer elemento es el mínimo y lo almacenamos temporalmente
            for (int j = i+1; j < n; j++){ // Recorremos el arreglo desde el siguiente elemento
                if (arr[j] < arr[min_idx]){ // Si encontramos un elemento menor que el mínimo actual lo actualizamos
                    min_idx = j;
                }
            }
            int temp = arr[min_idx]; // Intercambiamos el mínimo con el primer elemento
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * Método que realiza la ordenación de un arreglo de enteros mediante el algoritmo de ordenación por inserción.
     */
    static void insertionSort(int[] arr){
        int n = arr.length;
        for (int i = 0; i < n; i++){
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j+1] = arr[j];
                j = j - 1;
            }
            arr[j+1] = key;
        }
    }

    /**
     * Método que realiza la ordenación de un arreglo de enteros mediante el algoritmo de ordenación rápida.
     * @param arr Arreglo de enteros
     * @param low Índice más bajo
     * @param high Índice más alto
     * @return Índice del pivote
     */
    static int partition(int[] arr, int low, int high){
        int pivot = arr[high]; // Tomamos el último elemento como pivote
        int i = (low-1); // Índice del elemento más pequeño
        for (int j = low; j < high; j++){
            if (arr[j] < pivot){
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    void quickSort(int[] arr, int low, int high){
        if (low < high){
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }

    static void printArray(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
    }
}
