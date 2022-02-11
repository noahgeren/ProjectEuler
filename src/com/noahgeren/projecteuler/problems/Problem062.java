package com.noahgeren.projecteuler.problems;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import com.noahgeren.projecteuler.utils.Problem;

public class Problem062 extends Problem {

	@Override
	public String solve() {
		int capacity = 10;
		ArrayList<BigInteger> cubes = new ArrayList<>(capacity);
		int digitCount = (int) Math.log10(1);
		for(int i = 1; i < Integer.MAX_VALUE ; i++) {
			final int log10 = (int) Math.log10(i);
			if(log10 > digitCount) {
				digitCount = log10;
				cubes = new ArrayList<>(capacity *= 10);
			}
			BigInteger cube = BigInteger.valueOf(i).pow(3);
			cubes.add(cube);
			BigInteger[] perms = cubes
					.parallelStream()
					.filter(c -> isPermutation(cube, c))
					.sorted()
					.toArray(BigInteger[]::new);
			if(perms.length == 5) {
				return "Answer:" + perms[0].toString();
			}
		}
		return "No answer";
	}
	
	private boolean isPermutation(BigInteger x, BigInteger y) {
		char[] xStr = x.toString().toCharArray(), yStr = y.toString().toCharArray();
		Arrays.sort(xStr);
		Arrays.sort(yStr);
		return Arrays.equals(xStr, yStr);
	}

}
