package tech.noahgeren.projecteuler.problems;

import java.io.BufferedReader;
import java.io.IOException;

import tech.noahgeren.projecteuler.resources.ResourceLoader;
import tech.noahgeren.projecteuler.utils.Problem;

public class Problem096 extends Problem {

	@Override
	public String solve() {
		int sum = 0;
		int[][] puzzle = null;
		try(BufferedReader reader = ResourceLoader.getResource("p096_sudoku.txt")) {
			int i = 0;
			for(String line; (line = reader.readLine()) != null; i++) {
				if(line.startsWith("Grid")) {
					if(puzzle != null) {
						solveSudoku(puzzle, 0, 0);
						sum += puzzle[0][0] * 100 + puzzle[0][1] * 10 + puzzle[0][2];
					}
					puzzle = new int[9][9];
					i = -1;
					continue;
				}
				String[] rowValues = line.split("");
				for(int j = 0; j < 9; j++) {
					puzzle[i][j] = Integer.parseInt(rowValues[j]);
				}
			}
			solveSudoku(puzzle, 0, 0);
			sum += puzzle[0][0] * 100 + puzzle[0][1] * 10 + puzzle[0][2];
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Sum: " + sum;
		
	}
	
	private boolean solveSudoku(int[][] puzzle, int row, int col) {
		if(row > 8) return true;
		if(puzzle[row][col] != 0) {
			return solveSudoku(puzzle, row + (col / 8), (col + 1) % 9);
		}
		for(int i = 1; i < 10; i++) {
			if(canPlay(puzzle, row, col, i)) {
				puzzle[row][col] = i;
				boolean solved = solveSudoku(puzzle, row + (col / 8), (col + 1) % 9);
				if(solved) {
					return true;
				} else {
					puzzle[row][col] = 0;
				}
			}
		}
		return false;
	}
	
	private boolean canPlay(int[][] puzzle, int row, int col, int value) {
		int boxRow = (row / 3) * 3, boxCol = (col / 3) * 3;
		for(int i = 0; i < 9; i++) {
			if(puzzle[row][i] == value || // Check row
					puzzle[i][col] == value || // Check column
					puzzle[boxRow + (i / 3)][boxCol + (i % 3)] == value) { // Check box
				return false;
			}
		}
		return true;
	}

}
