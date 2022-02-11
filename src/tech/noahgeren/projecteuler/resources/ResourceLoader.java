package tech.noahgeren.projecteuler.resources;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ResourceLoader {
	
	public static BufferedReader getResource(String name) {
		return new BufferedReader(new InputStreamReader(ResourceLoader.class.getResourceAsStream(name)));
	}

}
