package com.noahgeren.projecteuler.problems;

import java.util.List;

import com.noahgeren.projecteuler.utils.Primes;
import com.noahgeren.projecteuler.utils.Problem;

public class Problem800 extends Problem {

	@Override
	public String solve() {
		final double LIMIT = 800_800d * Math.log(800_800d);
		final List<Integer> primes = Primes.sieveOfEratosthenes((int) (LIMIT / Math.log(2)));
		long count = 0;
		for(int i = 0; i < primes.size(); i++) {
			for(int j = i + 1; j < primes.size(); j++) {
				double p = primes.get(i), q = primes.get(j);
				double logVal = q * Math.log(p) + p * Math.log(q);
				if(logVal <= LIMIT) {
					count++;
				} else if(i + 1 == j) {
					return String.valueOf(count);
				} else {
					break;
				}
			}
		}
		return String.valueOf(count);
	}

}
