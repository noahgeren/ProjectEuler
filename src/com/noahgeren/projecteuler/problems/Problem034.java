package com.noahgeren.projecteuler.problems;

import com.noahgeren.projecteuler.utils.Numbers;
import com.noahgeren.projecteuler.utils.Problem;

public class Problem034 extends Problem {
	
	private static int[] FACTORIALS = new int[10];
	
	static {
		for(int i = 0; i < FACTORIALS.length; i++) {
			FACTORIALS[i] = Numbers.factorial(i);
		}
	}

	@Override
	public String solve() {
		int result = 0;
		for(int i = 10; i < 100_000; i++) {
			int n = i, sum = 0;
			while(n > 0) {
				sum += FACTORIALS[n % 10];
				n /= 10;
			}
			if(sum == i) {
				result += i;
			}
		}
		return String.valueOf(result);
	}

}
