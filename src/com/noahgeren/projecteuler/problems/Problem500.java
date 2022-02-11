package com.noahgeren.projecteuler.problems;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.noahgeren.projecteuler.utils.Primes;
import com.noahgeren.projecteuler.utils.Problem;

public class Problem500 extends Problem {

	@Override
	public String solve() {
		final int LIMIT = 7370030; // Value found from trial and error
		// Generate the first 500500 Fermi-Dirac primes
		List<Integer> fermiDiracPrimes = new ArrayList<>(500500);
		// Start with all the primes below LIMIT
		fermiDiracPrimes.addAll(Primes.sieveOfEratosthenes(LIMIT));
		// Add every value p^(2^k) < LIMIT where p is prime and k >= 1
		final int SQUARE_LIMIT = (int) Math.sqrt(Integer.MAX_VALUE);
		for (int i = 0; fermiDiracPrimes.get(i) < SQUARE_LIMIT; i++) {
			final int PRIME = fermiDiracPrimes.get(i);
			int square = PRIME * PRIME;
			if (square > LIMIT)
				break;
			while (square < LIMIT) {
				fermiDiracPrimes.add(square);
				if (square >= SQUARE_LIMIT)
					break;
				square *= square;
			}
		}
		final BigInteger MODULUS = BigInteger.valueOf(500500507l);
		BigInteger answer = BigInteger.ONE;
		// Multiply the first 500500 Fermi-Dirac primes to obtain the smallest value
		// with 2^500500 divisors
		for (int prime : fermiDiracPrimes) {
			// Use of modular multiplication property
			// (A * B) mod C = (A mod C * B mod C) mod C
			answer = answer.multiply(BigInteger.valueOf(prime)).mod(MODULUS);
		}
		return answer.toString();
	}

}
