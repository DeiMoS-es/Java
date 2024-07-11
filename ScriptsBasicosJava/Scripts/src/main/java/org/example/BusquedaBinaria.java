package org.example;

/**
 * Clase que realiza la búsqueda binaria de un número en un arreglo ordenado.
 */
public class BusquedaBinaria {
    /**
     *
     * @param arr Arreglo de enteros.
     * @param l Índice izquierdo. En que posición comienza la búsqueda.
     * @param r Índice derecho. En que posición termina la búsqueda.
     * @param x Número a buscar.
     * @return Devuelve la posición del número en el arreglo. Si no lo encuentra devuelve -1.
     */
    public static int busquedaBinaria(int arr[], int l, int r, int x){
        if (r >= l){
            int mid = l + (r - l) / 2; // Calcula el índice medio del arreglo.
            if (arr[mid] == x){ // Si el número en la posición media es igual al número a buscar. Se ha terminado la búsqueda. Y devolvemos la posición.
                return mid;
            } if(arr[mid] > x){ // Si el número en la posición media es mayor al número a buscar. Se busca en la mitad izquierda del arreglo.
                return busquedaBinaria(arr, l, mid - 1, x); // Se llama a la función recursivamente.
            } else { // Si el número en la posición media es menor al número a buscar. Se busca en la mitad derecha del arreglo.
                return busquedaBinaria(arr, mid + 1, r, x);

            }
        }
        return -1; // Si no se encuentra el número en el arreglo. Devolvemos -1.
    };

    public static int busquedaBinariaV2(int arr[]){
        int posicionInicial = 0;
        int posicionFinal = arr.length - 1;
        int numeroABuscar = 5;
        if (posicionFinal > posicionInicial) {
            int mitad = posicionInicial + (posicionFinal - posicionInicial) / 2;
            if (arr[mitad] == numeroABuscar) {
                return mitad;
            } else if (arr[mitad] > numeroABuscar) {
                return busquedaBinariaV2(arr);
            } else {
                return busquedaBinariaV2(arr);
            }
        }
        return -1;
    };
}
