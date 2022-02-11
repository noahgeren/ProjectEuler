package tech.noahgeren.projecteuler.problems;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tech.noahgeren.projecteuler.utils.Problem;

public class Problem068 extends Problem {
	
	private BigInteger max = BigInteger.ZERO;

	@Override
	public String solve() {
		allPermutations(9, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
		return "Max: " + max.toString();
	}
	
	private void allPermutations(int n, int[] order) {
		if(n == 1) {
			if(10 + order[1] != order[6] + order[7]) return;
			
			for(int i = 0; i <= 4; i += 2) {
				if(order[i] + order[i + 1] != order[i + 3] + order[i + 4]) return;
			}
			
			final List<Integer> outerValues = Arrays.asList(order[0], order[3], order[5], order[7]);
			
			final int startingSide = outerValues.indexOf(Collections.min(outerValues));
			
			final StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i < 5; i++) {
				appendRow(sb, order, (startingSide + i) % 5);
			}
			
			final BigInteger val = new BigInteger(sb.toString());
			
			if(val.compareTo(max) > 0) {
				max = val;
			}

		} else {
			for(int i = 0; i < n - 1; i++) {
				allPermutations(n - 1, order);
				if(n % 2 == 0) {
					swap(order, i, n - 1);
				} else {
					swap(order, 0, n - 1);
				}
			}
			allPermutations(n - 1, order);
		}
	}
	
	private void appendRow(StringBuilder sb, int[] order, int side) {
		switch(side) {
		case 0:
			sb.append(order[0]);
			sb.append(order[1]);
			sb.append(order[2]);
			break;
		case 1:
			sb.append(order[3]);
			sb.append(order[2]);
			sb.append(order[4]);
			break;
		case 2:
			sb.append(order[5]);
			sb.append(order[4]);
			sb.append(order[6]);
			break;
		case 3:
			sb.append(order[7]);
			sb.append(order[6]);
			sb.append(order[8]);
			break;
		case 4:
			sb.append(10);
			sb.append(order[8]);
			sb.append(order[1]);
			break;
		}
	}
	
	private void swap(int[] order, int i, int j) {
		int temp = order[i];
		order[i] = order[j];
		order[j] = temp;
	}

}
