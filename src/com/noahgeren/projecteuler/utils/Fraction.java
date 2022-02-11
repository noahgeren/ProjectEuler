package com.noahgeren.projecteuler.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Fraction extends java.lang.Number implements Comparable<Fraction> {
	
	private static final long serialVersionUID = 2621401547350826669L;
	
	public static final Fraction ONE = new Fraction(1, 1),
			TEN = new Fraction(10, 1), 
			ONE_HALF = new Fraction(1, 2), 
			ONE_THIRD = new Fraction(1, 3), 
			ONE_FOURTH = new Fraction(1, 4);

	private static final BigInteger NEGATIVE_ONE = BigInteger.valueOf(-1);
	
	private BigInteger num, den;
	
	public Fraction(int num, int den) {
		this.num = BigInteger.valueOf(num);
		this.den = BigInteger.valueOf(den);
	}
	
	public Fraction(BigInteger num, BigInteger den) {
		this.num = num;
		this.den = den;
	}
	
	public Fraction add(Fraction o) {
		return new Fraction(num.multiply(o.den).add(den.multiply(o.num)), den.multiply(o.den));
	}
	
	public Fraction subtract(Fraction o) {
		return add(new Fraction(o.num.multiply(NEGATIVE_ONE), o.den));
	}
	
	public Fraction multiply(Fraction o) {
		return new Fraction(num.multiply(o.num), den.multiply(o.den));
	}
	
	public Fraction multiply(int a) {
		return multiply(BigInteger.valueOf(a));
	}
	
	public Fraction multiply(BigInteger a) {
		return new Fraction(num.multiply(a), den);
	}
	
	public Fraction divide(Fraction o) {
		return multiply(o.reciprical());
	}
	
	public Fraction divide(int a) {
		return divide(BigInteger.valueOf(a));
	}
	
	public Fraction divide(BigInteger a) {
		return new Fraction(num, den.multiply(a));
	}
	
	public Fraction reciprical() {
		return new Fraction(den, num);
	}
	
	public Fraction simplified() {
		final BigInteger gcd = Numbers.gcd(num, den);
		return new Fraction(num.divide(gcd), den.divide(gcd));
	}

	@Override
	public int compareTo(Fraction o) {
		return num.multiply(o.den).subtract(den.multiply(o.num)).compareTo(den.multiply(o.den));
		
	}
	
	public void setNum(BigInteger num) {
		this.num = num;
	}
	
	public void setNum(int num) {
		this.num = BigInteger.valueOf(num);
	}
	
	public void setDen(BigInteger den) {
		this.den = den;
	}
	
	public void setDen(int den) {
		this.den = BigInteger.valueOf(den);
	}
	
	public BigInteger getNum() {
		return num;
	}
	
	public BigInteger getDen() {
		return den;
	}
	
	public BigDecimal getValue(int scale) {
		return new BigDecimal(num).divide(new BigDecimal(den), scale, BigDecimal.ROUND_HALF_EVEN);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((den == null) ? 0 : den.hashCode());
		result = prime * result + ((num == null) ? 0 : num.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fraction other = (Fraction) obj;
		if (den == null) {
			if (other.den != null)
				return false;
		}
		if (num == null) {
			if (other.num != null)
				return false;
		}
		return compareTo(other) == 0;
	}
	
	@Override
	public String toString() {
		return num.toString() + "/" + den.toString();
	}

	@Override
	public int intValue() {
		return num.divide(den).intValue();
	}

	@Override
	public long longValue() {
		return num.divide(den).longValue();
	}

	@Override
	public float floatValue() {
		return getValue(5).floatValue();
	}

	@Override
	public double doubleValue() {
		return getValue(10).doubleValue();
	}

}
