package tech.noahgeren.projecteuler.utils;

import java.util.ArrayList;
import java.util.List;

public class Primes {
	
	public static List<Integer> sieveOfEratosthenes(int limit){
		boolean[] sieve = new boolean[limit];
		List<Integer> primes = new ArrayList<>();
		for(int i = 2; i < limit; i++) {
			if(sieve[i]) continue;
			primes.add(i);
			for(int j = i * 2; j < limit; j += i) {
				sieve[j] = true;
			}
		}
		return primes;
	}

}
