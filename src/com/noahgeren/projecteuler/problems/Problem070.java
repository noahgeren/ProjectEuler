package com.noahgeren.projecteuler.problems;

import java.util.List;

import com.noahgeren.projecteuler.utils.Fraction;
import com.noahgeren.projecteuler.utils.Primes;
import com.noahgeren.projecteuler.utils.Problem;

public class Problem070 extends Problem {
	
	private static final int TEN_MILLION = 10_000_000;
	
	private List<Integer> primes = Primes.sieveOfEratosthenes(TEN_MILLION);

	@Override
	public String solve() {
		Fraction min = Fraction.TEN;
		int min_n = 0;
		for(int n = 2; n < TEN_MILLION; n++) {
			Fraction phi = Fraction.ONE.multiply(n);
			for(int i = 0, p = 0;  (p = primes.get(i)) <= n; i++) {
				if(n % p == 0) {
					phi = phi.multiply(Fraction.ONE.subtract(new Fraction(1, p))).simplified();
				}
			}
		}
		return "Min N: " + min_n;
	}

}
