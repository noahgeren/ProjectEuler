package com.noahgeren.projecteuler.problems;

import java.math.BigInteger;

import com.noahgeren.projecteuler.utils.Problem;

public class Problem104 extends Problem {

	@Override
	public String solve() {
		BigInteger[] fib = {BigInteger.ONE, BigInteger.ONE};
		BigInteger k = BigInteger.ONE;
		for( ; isNotPandigital(fib[0]); k = k.add(BigInteger.ONE)) {
			BigInteger temp = fib[0].add(fib[1]);
			fib[0] = fib[1];
			fib[1] = temp;
			System.out.println(k.toString());
		}
		return "Answer: " + k.toString();
	}

	private boolean isNotPandigital(BigInteger n) {
		String value = n.toString();
		if(value.length() < 9) return true;
		boolean[] digits = new boolean[9];
		for(char c : value.substring(value.length() - 9).toCharArray()) {
			if(c == '0') return true;
			digits[c - '1'] = true;
		}
		for(boolean digit : digits) {
			if(!digit) return true;
		}
		digits = new boolean[9];
		for(char c : value.substring(0, 9).toCharArray()) {
			if(c == '0') return true;
			digits[c - '1'] = true;
		}
		for(boolean digit : digits) {
			if(!digit) return true;
		}
		return false;
	}

}
