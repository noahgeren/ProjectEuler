package tech.noahgeren.projecteuler.problems;

import java.io.BufferedReader;
import java.io.IOException;

import tech.noahgeren.projecteuler.resources.ResourceLoader;
import tech.noahgeren.projecteuler.utils.Problem;

public class Problem081 extends Problem {

	@Override
	public String solve() {
		try(BufferedReader reader = ResourceLoader.getResource("p081_matrix.txt")){
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}