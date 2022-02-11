package com.noahgeren.projecteuler.problems;

import java.util.List;

import com.noahgeren.projecteuler.utils.Primes;
import com.noahgeren.projecteuler.utils.Problem;

public class Problem087 extends Problem {

	@Override
public String solve() {
	final int LIMIT = 50000000;
	final boolean[] primeTriples = new boolean[LIMIT];
	final List<Integer> primes = Primes.sieveOfEratosthenes(10000);
	for(int i = 0, a = 0; (a = primes.get(i) * primes.get(i)) < LIMIT; i++) {
		for(int j = 0, b = 0; (b = a + primes.get(j) * primes.get(j) * primes.get(j)) < LIMIT; j++) {
			for(int k = 0, c = 0; (c = b + primes.get(k) * primes.get(k) * primes.get(k) * primes.get(k)) < LIMIT; k++) {
				primeTriples[c] = true;
			}
		}
	}
	int answer = 0;
	for(int i = 0; i < LIMIT; i++) {
		if(primeTriples[i]) answer++;
	}
	return "Answer: " + answer;
}

}
