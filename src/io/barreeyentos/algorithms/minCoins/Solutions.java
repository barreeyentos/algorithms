package io.barreeyentos.algorithms.minCoins;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import io.barreeyentos.algorithms.util.Pair;

/*
 * Benchmark each solution
 */
public class Solutions {

    public static void main(String... args) {
        System.out.println("Coin sum algorithms.");
        /*
         * brute force takes a long time if you go more than 50ish
         */
        int sumToFind = 37;

        int[] coins = { 1, 5, 10, 25 };

        System.out.println("Sum to find: " + sumToFind);
        System.out.print("Available coins: ");
        Arrays.stream(coins).forEach(c -> System.out.print(c + ","));
        System.out.println("\n");

        long start, end = 0;
        long bruteForceTime, dynamicProgrammingTime, greedyTime = 0;

        BruteForceImpl brute = new BruteForceImpl();
        start = Instant.now().toEpochMilli();
        List<Pair<Integer, Integer>> bruteForceCoins = brute.solve(sumToFind, coins);
        end = Instant.now().toEpochMilli();
        bruteForceTime = end - start;

        printSolution("Brute Force", bruteForceTime, bruteForceCoins);

        GreedyImpl greedy = new GreedyImpl();
        start = Instant.now().toEpochMilli();
        List<Pair<Integer, Integer>> greedyCoins = greedy.solve(sumToFind, coins);
        end = Instant.now().toEpochMilli();
        greedyTime = end - start;

        printSolution("Greedy", greedyTime, greedyCoins);

        DynamicProgrammingImpl dp = new DynamicProgrammingImpl();
        start = Instant.now().toEpochMilli();
        List<Pair<Integer, Integer>> dynamicProgrammingCoins = dp.solve(sumToFind, coins);
        end = Instant.now().toEpochMilli();
        dynamicProgrammingTime = end - start;

        printSolution("Dynamic Programming Solution", dynamicProgrammingTime, dynamicProgrammingCoins);

    }

    public static void printSolution(String algorithm, long duration, List<Pair<Integer, Integer>> solution) {
        System.out.println(algorithm);
        solution.forEach(p -> System.out.println("\t" + p.getLeft() + " of value " + p.getRight()));
        System.out.println("Found solution in " + duration + "ms");
        System.out.println("\n");
    }

}
