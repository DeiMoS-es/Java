package microservices.book.multiplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
* This class represents a Multiplication in our application.
*/
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Multiplication {

	// Both factors
	private final int factorA;
	private final int factorB;

	// The result of the operation A * B
	private final int result;
	// Empty constructor for JSON (de)serialization
	Multiplication() {
		this(0, 0);
	}

	public Multiplication(int factorA, int factorB) {
		this.factorA = factorA;
		this.factorB = factorB;
		this.result = factorA * factorB;
	}
	public int getFactorA() {
		return factorA;
	}
	public int getFactorB() {
		return factorB;
	}
	public int getResult() {
		return result;
	}
	@Override
	public String toString() {
		return "Multiplication{" + "factorA=" + factorA + ", factorB=" + factorB +", result(A*B)=" + result +'}';
	}
}
