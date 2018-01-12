package io.barreeyentos.algorithms.minCoins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.barreeyentos.algorithms.util.Pair;

/*
 * Dynamic Programming solution to MinCoins
 *
 * tl;dr solve smaller problems and add on to them for a larger solution
 */
public class DynamicProgrammingImpl implements MinCoins {

    class CoinSumState {
        int minNumCoins;
        int coinValue;
        CoinSumState previousState;

        public CoinSumState(int minNumCoins, int coinValue, CoinSumState prevState) {
            this.minNumCoins = minNumCoins;
            this.coinValue = coinValue;
            this.previousState = prevState;
        }
    }

    @Override
    public List<Pair<Integer, Integer>> solve(int sum, int... coinValues) {
        Map<Integer, CoinSumState> dpSumSolutions = new HashMap<>();
        dpSumSolutions.put(0, new CoinSumState(0, 0, null));
        IntStream.rangeClosed(1, sum).forEach(s -> {
            int minCoins = Integer.MAX_VALUE;
            int coinValue = 0;
            CoinSumState prevState = null;
            for (int c : coinValues) {
                if (dpSumSolutions.containsKey(s - c) && minCoins > (dpSumSolutions.get(s - c).minNumCoins + 1)) {
                    prevState = dpSumSolutions.get(s - c);
                    minCoins = prevState.minNumCoins + 1;
                    coinValue = c;
                }
            }
            if (minCoins != Integer.MAX_VALUE) {
                dpSumSolutions.put(s, new CoinSumState(minCoins, coinValue, prevState));
            }
        });

        List<Pair<Integer, Integer>> result = new ArrayList<>();
        CoinSumState sumState = dpSumSolutions.get(sum);
        while (sumState != null) {
            if (sumState.coinValue > 0) {
                result.add(Pair.of(1, sumState.coinValue));
            }
            sumState = sumState.previousState;

        }
        return result.stream().collect(Collectors.groupingBy(p -> p.getRight())).entrySet().stream()
                .map(e -> Pair.of(e.getValue().size(), e.getKey())).collect(Collectors.toList());
    }

}
