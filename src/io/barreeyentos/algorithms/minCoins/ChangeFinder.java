package io.barreeyentos.algorithms.minCoins;

import java.util.List;

import io.barreeyentos.algorithms.util.Pair;

/*
 * Problem Statement:
 * Given a list of N coins, their values (V1, V2, … , VN), and the total sum S.
 * Find the minimum number of coins the sum of which is S (we can use as many coins of one type as we want),
 * or report that it’s not possible to select coins in such a way that they sum up to S.
 * 
 * Return value:
 * 	- empty list if sum is not possible
 *  - a list of pairs that represent Pair.of( numberOfCoins, coinValue)
 *  
 *  Example:
 *  
 *  S = 11 
 *  N = 3
 *  values = (1, 5, 10)
 *  
 *  solve(11, 1, 5, 10) returns
 *  
 *  [ (1, 10), (1, 1)]
 *  
 *  which means 1 coin of value 10, and 1 coin of value 1
 */
@FunctionalInterface
public interface ChangeFinder {
	List<Pair<Integer, Integer>> solve(int sum, int... coinValues);
}
