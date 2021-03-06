package io.barreeyentos.algorithms.minCoins.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import io.barreeyentos.algorithms.minCoins.ChangeFinder;
import io.barreeyentos.algorithms.util.Pair;

/*
 * Brute Force
 *
 * tl;dr: find all ways to make the sum then find optimal solution
 */
public class BruteForceImpl implements ChangeFinder {

    @Override
    public List<Pair<Integer, Integer>> solve(int sum, int... coinValues) {
        Set<List<Pair<Integer, Integer>>> allSolutions = new HashSet<>();
        List<Pair<Integer, Integer>> coinStates = Arrays.stream(coinValues).mapToObj(v -> Pair.of(0, v))
                .collect(Collectors.toList());

        solutionHelper(sum, allSolutions, coinStates);
        return allSolutions.stream().collect(Collectors.reducing(this::optimalSolution)).orElse(new ArrayList<>());
    }

    public void solutionHelper(int sum, Set<List<Pair<Integer, Integer>>> allSolutions,
            List<Pair<Integer, Integer>> currentSolution) {
        int currentSum = calculateSum(currentSolution);
        if (currentSum == sum) {
            allSolutions.add(currentSolution.stream().filter(p -> p.getLeft() != 0).collect(Collectors.toList()));
            return;
        }

        if (currentSum > sum) {
            return;
        }
        for (int i = 0; i < currentSolution.size(); ++i) {
            List<Pair<Integer, Integer>> updatedSolution = new ArrayList<>(currentSolution);
            Pair<Integer, Integer> updateCoin = currentSolution.get(i);
            updatedSolution.set(i, Pair.of(updateCoin.getLeft() + 1, updateCoin.getRight()));
            solutionHelper(sum, allSolutions, updatedSolution);
        }

    }

    private int calculateSum(List<Pair<Integer, Integer>> coinStates) {
        return coinStates.stream().mapToInt(p -> p.getLeft() * p.getRight()).sum();
    }

    private List<Pair<Integer, Integer>> optimalSolution(List<Pair<Integer, Integer>> left,
            List<Pair<Integer, Integer>> right) {
        int numCoinsLeft = left.stream().mapToInt(Pair<Integer, Integer>::getLeft).sum();
        int numCoinsRight = right.stream().mapToInt(Pair<Integer, Integer>::getLeft).sum();
        return (numCoinsLeft < numCoinsRight) ? left : right;

    }

}
