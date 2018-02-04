package io.barreeyentos.algorithms.strings;

import java.util.Objects;

public class LongestIncreasingSequence {

    class State {
        int longestLength = 0;
        char currentChar;
        State prevState;
    }

    public String solve(String string) {
        char[] digits = string.toCharArray();
        State states[] = new State[digits.length];

        State result = new State();

        for (int r = 0; r < digits.length; ++r) {
            states[r] = new State();
            states[r].currentChar = digits[r];
            states[r].longestLength = 1;
            for (int c = r - 1; c >= 0; --c) {
                if (digits[c] < digits[r] && ((states[c].longestLength + 1) > states[r].longestLength)) {
                    states[r].longestLength = states[c].longestLength + 1;
                    states[r].prevState = states[c];
                }

            }

            if (states[r].longestLength > result.longestLength) {
                result = states[r];
            }
        }

        return recreateString(result);
    }

    private String recreateString(State result) {
        StringBuilder str = new StringBuilder("");
        State currState = result;
        while (Objects.nonNull(currState) && currState.longestLength > 0) {
            str.insert(0, currState.currentChar);
            currState = currState.prevState;
        }
        return str.toString();
    }

    public static void main(String... args) {
        LongestIncreasingSequence solver = new LongestIncreasingSequence();
        test(solver, "12321");
        test(solver, "132567459");
        test(solver, "14253647567894895689");

    }

    public static void test(LongestIncreasingSequence solver, String string1) {
        System.out.println("String: " + string1);

        System.out.println("Longest common substring: " + solver.solve(string1));
        System.out.println();
    }

}
