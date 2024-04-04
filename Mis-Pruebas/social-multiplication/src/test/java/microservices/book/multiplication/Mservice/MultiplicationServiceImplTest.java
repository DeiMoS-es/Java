package microservices.book.multiplication.Mservice;

import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.service.MultiplicationServiceImpl;
import microservices.book.multiplication.service.RandomGeneratorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
/**
 * Clase de pruebas para MultiplicationServiceImpl.
 * Es una prueba unitaria. Por eso no lleva las anotaciones
 */
public class MultiplicationServiceImplTest {

    private MultiplicationServiceImpl multiplicationServiceImpl;

    @Mock//Con esta anotación se crea una instancia de RandomGeneratorService.
    private RandomGeneratorService randomGeneratorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);//Anotación para JUnit 5 que permite la inicialización de los mocks.
        multiplicationServiceImpl = new MultiplicationServiceImpl(randomGeneratorService);
    }

    @Test
    public void createRandomMultiplicationTest() {
        // given (our mocked Random Generator service will return first 50, then 30)
        // Aquí se simula el comportamiento del servicio RandomGeneratorService.
        // Cuando se llame al método generateRandomFactor(), primero devolverá 50, y luego 30.
        given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);
        // when
        Multiplication multiplication = multiplicationServiceImpl.createRandomMultiplication();
        // then
        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorB()).isEqualTo(30);
        assertThat(multiplication.getResult()).isEqualTo(1500);
    }
}
