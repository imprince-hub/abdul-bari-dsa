package m25_stack_techniques;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class StackTechniquesDemo {
    public static void main(String[] args) {
        demoNextGreaterElement();
        demoTowerOfHanoi();
        demoBuiltInStack();
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

    // lecture 4, java.util.Stack<Integer>: push, print (bottom to top), peek, size, search, pop until empty,
    // then pop() and peek() on the empty stack, which throw EmptyStackException
    // (our m23 stacks throw NoSuchElementException("Stack underflow") instead).
    // EmptyStackException's getMessage() is null, so its name is printed, not its message.
    private static void demoBuiltInStack() {
        System.out.println("=== java.util.Stack ===");
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("after push 10, 20, 30: " + stack + " (printed bottom to top)");
        System.out.println("peek() = " + stack.peek() + ", stack is still " + stack);
        System.out.println("size() = " + stack.size());
        System.out.println("search(10) = " + stack.search(10) + " (position from the top, starting at 1)");
        System.out.println("search(99) = " + stack.search(99) + " (not in the stack)");
        while (!stack.empty()) {
            System.out.println("pop() = " + stack.pop());
        }
        System.out.println("empty() = " + stack.empty());
        try {
            stack.pop();
        } catch (EmptyStackException e) {
            System.out.println("pop() on an empty stack = " + e.getClass().getSimpleName());
        }
        try {
            stack.peek();
        } catch (EmptyStackException e) {
            System.out.println("peek() on an empty stack = " + e.getClass().getSimpleName());
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
