package com.noahgeren.projecteuler.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import com.noahgeren.projecteuler.resources.ResourceLoader;
import com.noahgeren.projecteuler.utils.Point;
import com.noahgeren.projecteuler.utils.Problem;

public class Problem082 extends Problem {
	
	private static final int SIZE = 80;
	
	private static final int MIN_ROW_MOVE = -1, MIN_COL_MOVE = 1;

	@Override
	public String solve() {
		final Node[][] matrix = new Node[SIZE][SIZE];
		try(BufferedReader reader = ResourceLoader.getResource("p081_matrix.txt")){
			for(int row = 0; row < SIZE; row++) {
				String[] numbers = reader.readLine().split(",");
				for(int col = 0; col < SIZE; col++) {
					matrix[row][col] = new Node(row, col, Integer.valueOf(numbers[col]));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		int min = Integer.MAX_VALUE;
		for(int row = 0; row < SIZE; row++) {
			int dist = solveFromRow(matrix, row);
			if(dist < min) {
				System.out.println(row);
				min = dist;
			}
		}
		return "Min Distance: " + min;
	}
	
	public int solveFromRow(Node[][] matrix, int row) {
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				matrix[i][j].minDistance = Integer.MAX_VALUE;
			}
		}
		matrix[row][0].minDistance = matrix[row][0].weight;
		ArrayList<Node> unsettled = new ArrayList<>();
		HashSet<Point> settled = new HashSet<>();
		unsettled.add(matrix[row][0]);
		while(!unsettled.isEmpty()) {
			Collections.sort(unsettled);
			Node current = unsettled.get(0);
			unsettled.remove(0);
			if(current.y == SIZE - 1) {
				return current.minDistance;
			}
			for(int rowMove = MIN_ROW_MOVE; rowMove <= 1; rowMove += 2) {
				Node move;
				if(current.x + rowMove < 0 || current.x + rowMove >= SIZE || settled.contains((move = matrix[current.x + rowMove][current.y]))) {
					continue;
				}
				unsettled.remove(move);
				move.setMinDistance(current.minDistance + move.weight);
				unsettled.add(move);
			}
			for(int colMove = MIN_COL_MOVE; colMove <= 1; colMove += 2) {
				Node move;
				if(current.y + colMove < 0 || current.y + colMove >= SIZE || settled.contains((move = matrix[current.x][current.y + colMove]))) {
					continue;
				}
				unsettled.remove(move);
				move.setMinDistance(current.minDistance + move.weight);
				unsettled.add(move);
			}
			settled.add(current);
		}
		System.err.println("Something went wrong");
		return Integer.MAX_VALUE;
	}
	
	static class Node extends Point implements Comparable<Node> {
		
		int weight;
		private int minDistance = Integer.MAX_VALUE;
		
		public Node(int row, int col, int weight) {
			super(row, col);
			this.weight = weight;
		}
		
		public void setMinDistance(int minDistance) {
			if(minDistance < this.minDistance) {
				this.minDistance = minDistance;
			}
		}

		@Override
		public int compareTo(Node o) {
			return minDistance - o.minDistance;
		}
		
	}

}
