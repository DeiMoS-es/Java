package microservices.book.multiplication.Mservice;
import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.service.MultiplicationService;
import microservices.book.multiplication.service.RandomGeneratorService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Clase de pruebas para MultiplicationService.
 * Es una prueba de integración.
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)//Anotación para JUnit 5
public class MultiplicationServiceTest {
	/*
	* @MockBean
	* Doble de prueba para el servicio RandomGeneratorService.
	* Esto permite simular su comportamiento sin necesidad de instanciar la clase real.
	* En esta primera fase del proyecto no tenemos creado la implementación de RandomGeneratorService. Por eso necesitamos un MockBean
	* */
	@MockBean
	private RandomGeneratorService randomGeneratorService;
	@Autowired
	private MultiplicationService multiplicationService;
	@Test
	public void createRandomMultiplicationTest() {
		// given (our mocked Random Generator service will return first 50, then 30)
		// Aquí se simula el comportamiento del servicio RandomGeneratorService.
		// Cuando se llame al método generateRandomFactor(), primero devolverá 50, y luego 30.
		given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);
		// when
		Multiplication multiplication = multiplicationService.createRandomMultiplication();
		// then
		assertThat(multiplication.getFactorA()).isEqualTo(50);
		assertThat(multiplication.getFactorB()).isEqualTo(30);
		assertThat(multiplication.getResult()).isEqualTo(1500);
	}
}

