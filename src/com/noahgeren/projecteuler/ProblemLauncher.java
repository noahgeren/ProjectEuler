package com.noahgeren.projecteuler;

import com.noahgeren.projecteuler.utils.Problem;

public class ProblemLauncher {
	
	private static final int PROBLEM = 83;
	
	public static void main(String[] args) {
		try {
			@SuppressWarnings("deprecation")
			final Problem problem = (Problem) Class.forName(
					"com.noahgeren.projecteuler.problems.Problem"
							+ String.format("%03d", PROBLEM)).newInstance();
			final long startTime = System.currentTimeMillis();
			try {
				System.out.println("Starting to solve problem " + PROBLEM + ".");
				System.out.println(problem.solve());
				System.out.println("Solve time: " + (System.currentTimeMillis() - startTime) + "ms");
			} catch(Exception e) {
				System.err.println("Error trying to solve problem " + PROBLEM);
				e.printStackTrace();
			}
		} catch (NumberFormatException | InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			System.err.println("Could not load problem " + PROBLEM);
			e.printStackTrace();
		}
	}

}
