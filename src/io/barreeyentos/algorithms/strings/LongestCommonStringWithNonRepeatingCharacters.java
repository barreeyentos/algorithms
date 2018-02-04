package io.barreeyentos.algorithms.strings;

import java.util.Objects;

public class LongestCommonStringWithNonRepeatingCharacters {

    class State {
        int longestLength;
        char currentChar;
        State prevState;
    }

    public String solve(String left, String right) {
        char[] string1 = left.toCharArray();
        char[] string2 = right.toCharArray();
        State states[][] = new State[string1.length][string2.length];

        State result = new State();

        for (int r = 0; r < string1.length; ++r) {
            for (int c = 0; c < string2.length; ++c) {
                if (string1[r] == string2[c]) {
                    int prevLen = 0;
                    State prevState = null;
                    if (r > 0 && c > 0) {
                        if (Objects.nonNull(states[r - 1][c - 1]) && string2[c] != states[r - 1][c - 1].currentChar) {
                            prevState = states[r - 1][c - 1];
                            prevLen = prevState.longestLength;
                        }
                    }

                    State currentState = new State();
                    currentState.longestLength = prevLen + 1;
                    currentState.currentChar = string2[c];
                    currentState.prevState = prevState;
                    states[r][c] = currentState;

                    if (currentState.longestLength > result.longestLength) {
                        result = currentState;
                    }

                }
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
        LongestCommonStringWithNonRepeatingCharacters solver = new LongestCommonStringWithNonRepeatingCharacters();
        test(solver, "geaksforgeeks", "geeksquizaksf");
        test(solver, "geeksforgeeks", "geeksquiz");
        test(solver, "geekforgreeks", "geeksquiz");
        test(solver, "abcdefghij", "ab123bcd345cdefgzoi");

    }

    public static void test(LongestCommonStringWithNonRepeatingCharacters solver, String string1, String string2) {
        System.out.println("String 1: " + string1);
        System.out.println("String 2: " + string2);

        System.out.println("Longest common substring: " + solver.solve(string1, string2));
        System.out.println();
    }

}
