package microservices.book.multiplication.Mservice;

import microservices.book.multiplication.service.RandomGeneratorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)//Anotación para JUnit 5
public class RandomGeneratorServiceImplTest {

    private RandomGeneratorServiceImpl randomGeneratorServiceImpl;

    /**
     * Dentro del método setUp(), se crea una instancia de RandomGeneratorServiceImpl y se asigna al campo randomGeneratorServiceImpl.
     * Esto significa que antes de cada prueba, se creará un nuevo objeto RandomGeneratorServiceImpl.
     * Esto se hace para asegurar que cada prueba se ejecute de forma aislada y que el estado de una prueba no afecte el estado de otra prueba.
     */
    @BeforeEach
    public void setUp() {
        randomGeneratorServiceImpl = new RandomGeneratorServiceImpl();
    }

    @Test
    public void generateRandomFactorIsBetweenExpectedLimits() throws Exception {
        List<Integer> randomFactors = IntStream.range(0, 1000)
                            .map(i -> randomGeneratorServiceImpl.generateRandomFactor())
                            .boxed()
                            .collect(Collectors.toList());
        assertThat(randomFactors).allMatch(factor -> factor >= 11 && factor <= 100);
    }
}
