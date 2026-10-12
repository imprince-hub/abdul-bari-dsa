package m25_stack_techniques;

import java.util.Arrays;
import java.util.List;

public class StackTechniquesDemo {
    public static void main(String[] args) {
        demoNextGreaterElement();
        demoTowerOfHanoi();
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

    // 0 to 3 disks: the moves from both versions and whether they agree,
    // 10 disks: only the number of moves and whether they agree, then the errors for -1 and 21 disks
    private static void demoTowerOfHanoi() {
        System.out.println("=== TowerOfHanoi ===");
        for (int disks = 0; disks <= 3; disks++) {
            List<String> recursiveMoves = TowerOfHanoi.movesRecursive(disks);
            List<String> iterativeMoves = TowerOfHanoi.movesIterative(disks);
            System.out.println(disks + " disks, recursive: " + recursiveMoves);
            System.out.println(disks + " disks, iterative: " + iterativeMoves);
            System.out.println("recursive and iterative agree: " + recursiveMoves.equals(iterativeMoves));
            System.out.println();
        }
        List<String> recursiveMoves = TowerOfHanoi.movesRecursive(10);
        List<String> iterativeMoves = TowerOfHanoi.movesIterative(10);
        System.out.println("10 disks: " + recursiveMoves.size() + " moves");
        System.out.println("recursive and iterative agree: " + recursiveMoves.equals(iterativeMoves));
        System.out.println();
        int[] invalidDisks = {-1, 21};
        for (int disks : invalidDisks) {
            try {
                TowerOfHanoi.movesRecursive(disks);
            } catch (IllegalArgumentException e) {
                System.out.println("movesRecursive(" + disks + ") = " + e.getMessage());
            }
            try {
                TowerOfHanoi.movesIterative(disks);
            } catch (IllegalArgumentException e) {
                System.out.println("movesIterative(" + disks + ") = " + e.getMessage());
            }
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
