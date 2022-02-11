package com.noahgeren.projecteuler.problems;

import java.awt.Point;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.IOException;

import com.noahgeren.projecteuler.resources.ResourceLoader;
import com.noahgeren.projecteuler.utils.Problem;

public class Problem102 extends Problem {

	@Override
	public String solve() {
		int originTriangles = 0;
		final Point origin = new Point(0, 0);
		try(BufferedReader reader = ResourceLoader.getResource("p102_triangles.txt")){
			for(String line; (line = reader.readLine()) != null; ) {
				String[] tokens = line.split(",");
				final int[] xPoints = { 
						Integer.parseInt(tokens[0]),
						Integer.parseInt(tokens[2]),
						Integer.parseInt(tokens[4])
				};
				final int[] yPoints = {
						Integer.parseInt(tokens[1]),
						Integer.parseInt(tokens[3]),
						Integer.parseInt(tokens[5])
				};
				final Polygon triangle = new Polygon(xPoints, yPoints, 3);
				if(triangle.contains(origin)) originTriangles++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Triangles: " + originTriangles;
	}

}
