package com.noahgeren.projecteuler.problems;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.noahgeren.projecteuler.utils.Problem;

public class Problem719 extends Problem {

	@Override
	public String solve() {
		long[] breakpoints = {4l, 400_000l, 700_000l, 900_000l, 1_000_001l};
		List<Solver> threads = new ArrayList<>();
		for(int i = 0; i < breakpoints.length - 1; i++) {
			Solver t = new Solver(breakpoints[i], breakpoints[i + 1] - 1l);
			threads.add(t);
			t.start();
		}
		BigInteger sum = BigInteger.ZERO;
		for(Solver t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.exit(1);
			}
			sum = sum.add(t.sum);
		}
		return sum.toString();
	}
	
	class Solver extends Thread {
		private long min, max;
		
		BigInteger sum = BigInteger.ZERO;
		
		public Solver(long min, long max) {
			this.min = min;
			this.max = max;
		}
		
		@Override
		public void run() {
			for(long i = min; i <= max; i++) {
				if(i % 1000l == 0) {
					System.out.println(i);
				}
				long s = pow(i, 2);
				if(sNumber(i, s)) {
					sum = sum.add(BigInteger.valueOf(s));
				}
			}
		}
		
		private boolean sNumber(long i, long s) {
			int bits = String.valueOf(s).length() - 1;
			int max = (int) Math.pow(2, bits);
			for(int splits = 1; splits < max; splits++) {
				long sum = 0, current = 0;
				for(int bit = 0; bit <= bits; bit++) {
					current = current * 10l + ((s / pow(10l, bits - bit)) % 10l);
					if(((splits >>> bit) & 1) != 0) {
						sum += current;
						current = 0l;
					}
				}
				sum += current;
				if(sum == i) {
					return true;
				}
			}
			return false;
		}
		
		private long pow(long base, int exp) {
			if(exp == 0) return 1l;
			return base * pow(base, exp - 1);
		}
	}
}
