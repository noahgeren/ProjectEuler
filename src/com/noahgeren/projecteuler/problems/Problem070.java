package com.noahgeren.projecteuler.problems;

import java.util.Arrays;
import java.util.List;

import com.noahgeren.projecteuler.utils.Fraction;
import com.noahgeren.projecteuler.utils.Primes;
import com.noahgeren.projecteuler.utils.Problem;

public class Problem070 extends Problem {
	
	private static final int TEN_MILLION = 10_000_000;
//	private static final int TEN_MILLION = 100_000; // used for testing
	
	private List<Integer> primes = Primes.sieveOfEratosthenes(TEN_MILLION);

	@Override
	public String solve() {
		Fraction[] fractions = new Fraction[TEN_MILLION + 1];
		Fraction min = Fraction.TEN;
		int min_n = 0;
		for(int prime : primes) {
			for(int i = prime; i <= TEN_MILLION; i += prime) {
				if(fractions[i] == null) {
					fractions[i] = new Fraction(i, 1);
				}
				fractions[i] = fractions[i].multiply(Fraction.ONE.subtract(new Fraction(1, prime)));
			}
		}
		int phi;
		Fraction nOverPhi;
		for(int n = 2; n <= TEN_MILLION; n++) {
			phi = fractions[n].intValue();
			if(isPermutation(n, phi) && (nOverPhi = new Fraction(n, phi)).compareTo(min) <= 0) {
				System.out.println(nOverPhi);
				min = nOverPhi;
				min_n = n;
			}
		}
		return "Min N: " + min_n;
	}
	
	public boolean isPermutation(int n, int p) {
		return Arrays.equals(getDigitFrequency(n), getDigitFrequency(p));
	}
	
	public int[] getDigitFrequency(int n) {
		int[] freq = new int[10];
		while(n != 0) {
			freq[n % 10]++;
			n /= 10;
		}
		return freq;
	}

}
