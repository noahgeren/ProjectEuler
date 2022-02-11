package tech.noahgeren.projecteuler.problems;

import java.math.BigInteger;

import tech.noahgeren.projecteuler.utils.Problem;

public class Problem719 extends Problem {
	
	private static final long MAX = pow(10l, 6);

	@Override
	public String solve() {
		BigInteger sum = BigInteger.ZERO;
		long s;
		for(long i = MAX; i >= 4; i--) {
			if(i % 1000l == 0l) {
				System.out.println(i);
			}
			s = pow(i, 2);
			if(sNumber(i, s)) {
				sum = sum.add(BigInteger.valueOf(s));
			}
		}
		return sum.toString();
	}
	
	private boolean sNumber(long i, long s) {
		final int numberOfBits = String.valueOf(s).length() - 1;
		final int maxBreakPoints = (int) Math.pow(2, numberOfBits);
		for(int breakPoints = 1; breakPoints < maxBreakPoints; breakPoints++) {
			long sum = 0, current = 0;
			for(int bit = 0; bit <= numberOfBits; bit++) {
				current = current * 10l + ((s / pow(10l, numberOfBits - bit)) % 10l);
				if(((breakPoints >>> bit) & 1) != 0) {
					sum += current;
					current = 0;
				}
			}
			sum += current;
			if(sum == i) {
				return true;
			}
		}
//		String str = s.toString();
//		int numberOfBits = str.length() - 1;
//		final short maxBreakPoints = (short) Math.pow(2, numberOfBits);
//		outer:
//		for(short breakPoints = 1; breakPoints < maxBreakPoints; breakPoints++) {
//			BigInteger sum = BigInteger.ZERO;
//			BigInteger current = BigInteger.ZERO;
//			for(int bit = 0; bit <= numberOfBits; bit++) {
//				current = current.multiply(BigInteger.TEN).add(new BigInteger(str.substring(bit, bit + 1)));
//				if(((breakPoints >>> bit) & 1) != 0) {
//					sum = sum.add(current);
//					current = BigInteger.ZERO;
//					if(sum.compareTo(i) > 0) continue outer;
//				}
//				if(current.compareTo(i) > 0) continue outer;
//			}
//			sum = sum.add(current);
//			if(sum.compareTo(i) == 0) {
//				return true;
//			}
//		}
		return false;
	}
	
	private static long pow(long base, int exp) {
		if(exp == 0) return 1l;
		return base * pow(base, exp - 1);
	}

}
