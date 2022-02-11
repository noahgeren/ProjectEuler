package tech.noahgeren.projecteuler.problems;

import java.math.BigInteger;

import tech.noahgeren.projecteuler.utils.Problem;

public class Problem684 extends Problem {
	
	private static final BigInteger NINE = BigInteger.valueOf(9);
	
	private static final BigInteger ONE_BILLION_SEVEN = BigInteger.valueOf(1000000007);
	
	private static final long k = 35;

	@Override
	public String solve() {
		BigInteger solution = BigInteger.ZERO;
		BigInteger[] fib = {BigInteger.ONE, BigInteger.ONE.add(BigInteger.ONE)};
		for(long i = 2; i <= k; i++) {
			System.out.print(i + ", ");
			solution = solution.add(s(fib[0])).mod(ONE_BILLION_SEVEN);
			fib = swap(fib);
			fib[1] = fib[0].add(fib[1]);
		}
		return "Solution: " + solution.toString();
	}
	
	private BigInteger[] swap(BigInteger[] arr) {
		BigInteger a = arr[0];
		arr[0] = arr[1];
		arr[1] = a;
		return arr;
	}
	
	private BigInteger s(BigInteger n) {
		BigInteger[] quotientAndRemainder = n.divideAndRemainder(NINE);
		StringBuilder result = new StringBuilder(quotientAndRemainder[1].toString());
		for(BigInteger i = BigInteger.ZERO; i.compareTo(quotientAndRemainder[0]) < 0; i=i.add(BigInteger.ONE)) {
			result.append("9");
		}
		return new BigInteger(result.toString());
	}

}
