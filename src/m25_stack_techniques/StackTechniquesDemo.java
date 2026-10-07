package m25_stack_techniques;

import java.util.Arrays;

public class StackTechniquesDemo {
    public static void main(String[] args) {
        demoNextGreaterElement();
    }

    // eight arrays: each one printed with the stack result and checked against brute force,
    // then the error for null
    private static void demoNextGreaterElement() {
        System.out.println("=== NextGreaterElement ===");
        int[][] arrays = {
                {4, 5, 2, 25},
                {13, 7, 6, 12},
                {-3, -1, -5},
                {1, 2, 3},
                {3, 2, 1},
                {2, 2},
                {7},
                {}
        };
        for (int[] values : arrays) {
            int[] nextIndices = NextGreaterElement.nextGreaterIndices(values);
            printNextGreater(values, nextIndices);
            int[] bruteForceIndices = NextGreaterElement.nextGreaterIndicesBruteForce(values);
            System.out.println("brute force agrees: " + Arrays.equals(nextIndices, bruteForceIndices));
            System.out.println();
        }
        try {
            NextGreaterElement.nextGreaterIndices(null);
        } catch (NullPointerException e) {
            System.out.println("nextGreaterIndices(null) = " + e.getMessage());
        }
        System.out.println();
    }

    // prints "values = [4, 5, 2, 25]", "next greater indices = [1, 3, 3, -1]",
    // then one line per element: "4 -> 5", ..., "25 -> none"
    private static void printNextGreater(int[] values, int[] nextIndices) {
        System.out.println("values = " + Arrays.toString(values));
        System.out.println("next greater indices = " + Arrays.toString(nextIndices));
        for (int i = 0; i < values.length; i++) {
            if (nextIndices[i] == NextGreaterElement.NONE) {
                System.out.println("  " + values[i] + " -> none");
            } else {
                System.out.println("  " + values[i] + " -> " + values[nextIndices[i]]);
            }
        }
    }
}
