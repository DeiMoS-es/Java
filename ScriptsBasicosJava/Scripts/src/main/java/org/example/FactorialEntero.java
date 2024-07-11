package org.example;

/**
 * Clase que realiza el cálculo del factorial de un número entero.
 */
public class FactorialEntero {

    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static int factorialV2(int n){
        int factorial = 1;
        for (int i = 1; i <= n; i++){
            factorial *= i;
        }
        return factorial;
    }
}
