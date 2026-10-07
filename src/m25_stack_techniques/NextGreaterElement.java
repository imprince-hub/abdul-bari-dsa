package m25_stack_techniques;

import m23_stack_introduction.GenericArrayStack;

import java.util.Objects;

// Next Greater Element: for every element, the first element to its RIGHT that is strictly greater.
// Returns indices, not values: result[i] is the index of the next greater element of values[i], or NONE.
// Why indices: with values, -1 for "none" is ambiguous when the array itself has -1 in it, like {-3, -1, -5}.
// Stack: m23's GenericArrayStack<Integer> holding indices, capacity = values.length.
public class NextGreaterElement {
    public static final int NONE = -1; // no greater element on the right; never a real index

    private NextGreaterElement() {
    }

    // monotonic stack, O(n)
    // {4, 5, 2, 25} -> {1, 3, 3, NONE}   (4 -> 5, 5 -> 25, 2 -> 25, 25 -> none)
    // {13, 7, 6, 12} -> {NONE, 3, 3, NONE}
    // strictly greater, so {2, 2} -> {NONE, NONE}
    // {} -> {}, returned before the stack is made (capacity 0 is not allowed)
    // null throws NullPointerException
    public static int[] nextGreaterIndices(int[] values) {
        Objects.requireNonNull(values, "values must not be null");
        if (values.length == 0) {
            return new int[0];
        }
        int[] result = new int[values.length];
        GenericArrayStack<Integer> stack = new GenericArrayStack<>(values.length);
        for (int i = values.length - 1; i >= 0; i--) {
            // anything not strictly greater than values[i] can never be the answer for i or anything left of i
            while (!stack.isEmpty() && values[stack.stackTop()] <= values[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? NONE : stack.stackTop();
            stack.push(i);
        }
        return result;
    }

    // brute force, O(n^2): for each i, scan to the right until a greater value is found
    // same contract and same results as nextGreaterIndices; the demo uses it to check the stack version
    public static int[] nextGreaterIndicesBruteForce(int[] values) {
        Objects.requireNonNull(values, "values must not be null");
        int[] result = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = NONE;
            for (int j = i + 1; j < values.length; j++) {
                if (values[i] < values[j]) {
                    result[i] = j;
                    break;
                }
            }
        }
        return result;
    }
}
