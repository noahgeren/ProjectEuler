package tech.noahgeren.projecteuler.problems;

import java.util.Arrays;

import tech.noahgeren.projecteuler.utils.Problem;

public class Problem091 extends Problem {

	@Override
	public String solve() {
		final int LIMIT = 50;
		int rightTriangles = 0;
		for(int x1 = 0; x1 <= LIMIT; x1++) {
			for(int x2 = 0; x2 <= LIMIT; x2++) {
				for(int y1 = 0; y1 <= LIMIT; y1++) {
					for(int y2 = 0; y2 <= LIMIT; y2++) {
						if(isRightTriangle(x1, y1, x2, y2)) {
							rightTriangles++;
						}
					}
				}
			}
		}
		return "Answer: " + (rightTriangles / 2);
	}
	
	private boolean isRightTriangle(int x1, int y1, int x2, int y2) {
		final double epsilon = 0.0000001d;
		if(x1 == 0 && y1 == 0 || x2 == 0 && y2 == 0) return false;
		if(x1 == x2 && y1 == y2) return false;
		double[] sides = {
				dist(0, 0, x1, y1),
				dist(0, 0, x2, y2),
				dist(x1, y1, x2, y2)
		};
		Arrays.sort(sides);
		boolean isRight = Math.abs(Math.pow(sides[0], 2) + Math.pow(sides[1], 2) - Math.pow(sides[2], 2)) < epsilon;
		if(isRight) {
			//System.out.println(String.format("(%d, %d) (%d, %d) a=%f b=%f c=%f", x1, y1, x2, y2, sides[0], sides[1], sides[2]));
		}
		return isRight;
	}
	
	private double dist(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}

}
