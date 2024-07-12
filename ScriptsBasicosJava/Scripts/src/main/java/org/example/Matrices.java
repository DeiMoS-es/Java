
package org.example;

/**
 * Clase que realiza operaciones con matrices.
 */
public class Matrices {
    int[][] matrizA = {{1, 2, 3}, {4, 5, 6}};
    int[][] matrizB = {{7, 8}, {9, 10}, {11, 12}};

    public static int[][] multiplicaciónMatrices(int[][] matrizA, int [][] matrizB){
        int filasMatrizA = matrizA.length;
        int columnasMatrizA = matrizA[0].length;
        int columnasMatrizB = matrizB[0].length;
        int[][] matrizResultado = new int[filasMatrizA][columnasMatrizB];
        for(int i = 0; i < filasMatrizA; i++){
            for(int j = 0; j< columnasMatrizB; j++){
                for(int k = 0; k < columnasMatrizA; k++){
                    matrizResultado[i][j] += matrizA[i][k] * matrizB[k][j];
                }
            }
        }
        return matrizResultado;
    };

    public static void imprimirMatriz(int[][] matriz){
        int filas = matriz.length;
        int columnas = matriz[0].length;
        for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                System.out.println(matriz[i][j] + " ");
            }
            System.out.println();
        }
    };
}
