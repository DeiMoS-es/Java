package org.example;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SumaTest {
    @Test
    public void testSumar() {
        int resultado = Suma.sumar(3, 5);
        assertEquals(8, resultado);
    }
}
