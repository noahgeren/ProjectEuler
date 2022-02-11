package com.noahgeren.projecteuler.problems;

import java.math.BigInteger;

import com.noahgeren.projecteuler.utils.Problem;

public class Problem700 extends Problem {

	@Override
	public String solve() {
		BigInteger min = new BigInteger("1504170715041708");
		BigInteger m = new BigInteger("1504170715041707");
		BigInteger modulus = new BigInteger("4503599627370517");
		BigInteger sum = BigInteger.ZERO;
		for(BigInteger n = m; min.compareTo(BigInteger.ONE) >= 0; n = n.add(m).mod(modulus)) {
			if(n.compareTo(min) < 0) {
				System.out.println(n.toString());
				min = n;
				if(min.compareTo(new BigInteger("15806432")) == 0) break;
				sum = sum.add(n);
			}
		}
		BigInteger diff = new BigInteger("409165");
		while(min.compareTo(BigInteger.ZERO) > 0) {
			sum = sum.add(min);
			min = min.subtract(diff);
		}
		return sum.toString();
	}

}
