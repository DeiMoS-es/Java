package microservices.book.multiplication.Mservice;
import microservices.book.multiplication.service.RandomGeneratorService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)//Anotación para JUnit 5
public class RandomGeneratorServiceTest {

	@Autowired
	private RandomGeneratorService randomGeneratorService;

	@Test
	public void generateRandomFactorIsBetweenExpectedLimits() throws Exception {
	
		// when a good sample of randomly generated factors is generated
		List<Integer> randomFactors = IntStream.range(0, 1000)
							.map(i -> {
								int randomFactor = randomGeneratorService.generateRandomFactor();
								System.out.println(randomFactor);
								return randomFactor;
							})
							.boxed()
							.collect(Collectors.toList());

		// then all of them should be between 11 and 100
		// because we want a middle-complexity calculation
		assertThat(randomFactors).allMatch(factor -> factor >= 11 && factor <= 100);
	}
}
