package com.noahgeren.projecteuler.problems;

import com.noahgeren.projecteuler.utils.Problem;

public class Problem145 extends Problem {
	
	private static final long ONE_BILLION = 1_000_000_000l;

	@Override
	public String solve() {
		long count = 0;
		for(long i = 12; i < ONE_BILLION; i += 2) {
			final short tensDigit = (short) (i % 10);
			if(tensDigit == 0 || tensDigit == 1) {
				i++;
			}
			if(oddDigits(i + reverse(i))) {
				count++;
			}
		}
		return count + " reversible numbers.";
	}
	
	private boolean oddDigits(long n) {
		while(n > 0) {
			if(n % 2 == 0) return false;
			n /= 10;
		}
		return true;
	}
	
	private long reverse(long n) {
		long reverse = 0;
		while(n > 0) {
			reverse *= 10;
			reverse += n % 10;
			n /= 10;
		}
		return reverse;
	}

}
