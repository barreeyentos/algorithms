package io.barreeyentos.algorithms.minCoins.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.barreeyentos.algorithms.minCoins.ChangeFinder;
import io.barreeyentos.algorithms.util.Pair;

public class GreedyImpl implements ChangeFinder {

    @Override
    public List<Pair<Integer, Integer>> solve(int sum, int... coinValues) {
        int remainder = sum;
        List<Pair<Integer, Integer>> change = new ArrayList<>();
        for (int value : reverseSort(coinValues)) {
            if (remainder == 0)
                return change;
            int numCoins = remainder / value;
            remainder %= value;
            if (numCoins > 0) {
                change.add(Pair.of(numCoins, value));
            }
        }

        return (remainder == 0) ? change : new ArrayList<>();
    }

    int[] reverseSort(int... coinValues) {
        if (coinValues.length <= 1) {
            return coinValues;
        }
        int[] left = reverseSort(Arrays.copyOfRange(coinValues, 0, coinValues.length / 2));
        int[] right = reverseSort(Arrays.copyOfRange(coinValues, coinValues.length / 2, coinValues.length));

        int[] sorted = new int[coinValues.length];
        int leftIndex = 0;
        int rightIndex = 0;
        for (int i = 0; i < sorted.length; i++) {
            if (leftIndex < left.length) {
                if (rightIndex < right.length) {
                    if (left[leftIndex] > right[rightIndex]) {
                        sorted[i] = left[leftIndex++];
                    } else {
                        sorted[i] = right[rightIndex++];
                    }
                } else {
                    sorted[i] = left[leftIndex++];
                }
            } else {
                sorted[i] = right[rightIndex++];
            }
        }

        return sorted;
    }

}
