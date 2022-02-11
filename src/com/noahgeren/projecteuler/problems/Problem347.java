package com.noahgeren.projecteuler.problems;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.noahgeren.projecteuler.utils.Primes;
import com.noahgeren.projecteuler.utils.Problem;

public class Problem347 extends Problem {

	@Override
	public String solve() {
		final BigInteger TEN_MILLION = BigInteger.valueOf(100);
		final List<Integer> primes = Primes.sieveOfEratosthenes(50);
		final List<Integer> distinctProducts = new ArrayList<>();
		outer:
		for(int i = 0; i < primes.size(); i++) {
			final BigInteger p = BigInteger.valueOf(primes.get(i));
			for(int j = i + 1; j < primes.size(); j++) {
				final BigInteger q = BigInteger.valueOf(primes.get(j));
				if(p.multiply(q).compareTo(TEN_MILLION) > 0) continue outer;
				BigInteger MAX = BigInteger.ZERO;
				final int maxExp = (int) log(p, TEN_MILLION.divide(q));
				for(int pExp = 1; pExp <= maxExp; pExp++) {
					BigInteger product = p.pow(pExp);
					final int qExp = (int) log(q, TEN_MILLION.divide(product));
					product = product.multiply(q.pow(qExp));
					if(product.compareTo(TEN_MILLION) <= 0 && product.compareTo(MAX) > 0) {
						MAX = product;
					}
					System.out.println(p + "^" + pExp + " * " + q + "^" + qExp + " = " + product);
				}
				distinctProducts.add(MAX.intValueExact());
			}
		}
		BigInteger answer = BigInteger.ZERO;
		for(Integer distinctProduct : distinctProducts) {
			answer = answer.add(BigInteger.valueOf(distinctProduct));
		}
		return "Answer: " + answer.toString();
	}
	
	private double log(BigInteger base, BigInteger n) {
		return Math.log(n.doubleValue()) / Math.log(base.doubleValue());
	}

}
