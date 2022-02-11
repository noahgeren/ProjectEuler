package tech.noahgeren.projecteuler.problems;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import tech.noahgeren.projecteuler.utils.Problem;

public class Problem205 extends Problem {

	@Override
	public String solve() {
		BigDecimal avg = BigDecimal.ZERO;
		int trials = 100;
		for(int j = 0; j < trials; j++) {
			BigInteger tests = BigInteger.valueOf(1_000_000);
			BigInteger wins = BigInteger.ZERO;
			for(BigInteger i = BigInteger.ZERO; i.compareTo(tests) < 0; i = i.add(BigInteger.ONE)) {
				int peter = 0, colin = 0;
				for(int p = 0; p < 9; p++) {
					peter += Math.random() * 4 + 1;
				}
				for(int c = 0; c < 6; c++) {
					colin += Math.random() * 6 + 1;
				}
				if(peter > colin) {
					wins = wins.add(BigInteger.ONE);
				}
			}
			BigDecimal bdTests = new BigDecimal(tests), bdWins = new BigDecimal(wins);
			avg = avg.add(bdWins.divide(bdTests, 10, RoundingMode.HALF_UP));
		}
		return "Answer: " + avg.divide(BigDecimal.valueOf(trials), 7, RoundingMode.HALF_UP);
	}

}
