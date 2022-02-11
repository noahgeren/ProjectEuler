package tech.noahgeren.projecteuler.utils;

import java.math.BigInteger;

public class Numbers {
	
	public static BigInteger gcd(BigInteger a, BigInteger b) {
		if(a.compareTo(b) < 0) {
			return gcd(b, a);
		}
		while(b.compareTo(BigInteger.ZERO) != 0) {
			final BigInteger temp = b;
			b = a.mod(b);
			a = temp;
		}
		return a;
	}
	
	public static BigInteger lcm(BigInteger a, BigInteger b) {
		return a.multiply(b).divide(gcd(a, b));
	}

}
