package m32_binary_heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Random;

public class MaxHeapDemo {
    public static void main(String[] args) {
        demoInsertSteps();
        demoGenerate();
        demoPeek();
        demoLargeHeaps();
        demoRandomCheck();
    }

    // Lectures 2 and 3. Starts with new MaxHeap() and inserts 30, 20, 15, 5, 10, 12, 6, 40, 35, 15, 1 in that order.
    // After each insert print the heap with toArray, like "insert(30): [30]". Expected:
    //   insert(30): [30]
    //   insert(20): [30, 20]
    //   insert(15): [30, 20, 15]
    //   insert(5): [30, 20, 15, 5]
    //   insert(10): [30, 20, 15, 5, 10]
    //   insert(12): [30, 20, 15, 5, 10, 12]
    //   insert(6): [30, 20, 15, 5, 10, 12, 6]
    //   insert(40): [40, 30, 15, 20, 10, 12, 6, 5]
    //   insert(35): [40, 35, 15, 30, 10, 12, 6, 5, 20]
    //   insert(15): [40, 35, 15, 30, 15, 12, 6, 5, 20, 10]
    //   insert(1): [40, 35, 15, 30, 15, 12, 6, 5, 20, 10, 1]
    // The first 7 keys never move: that is the lecture's heap. 40 is the lecture's example: it moves up 3 levels,
    // from a leaf to root. 35 moves up 2 levels and stops below 40. The second 15 moves up 1 level (past 10).
    // 1 is the 11th key, so the array (10 places) grows before it goes in; 1 does not move.
    // Then print "size = 11, height = 4, isValidHeap = true", then display():
    //   level 1: 40
    //   level 2: 35 15
    //   level 3: 30 15 12 6
    //   level 4: 5 20 10 1
    // then an empty line
    private static void demoInsertSteps() {
        MaxHeap heap = new MaxHeap();
        int[] keys = {30, 20, 15, 5, 10, 12, 6, 40, 35, 15, 1};
        for (int key : keys) {
            heap.insert(key);
            System.out.println("insert(" + key + "): " + Arrays.toString(heap.toArray()));
        }
        System.out.println("size = " + heap.size() + ", height = " + heap.height()
                + ", isValidHeap = " + heap.isValidHeap());
        heap.display();
        System.out.println();
    }

    // Lecture 4. Calls showHeap for each array (expected heap and height below each one):
    // 1. {10, 20, 30, 25, 5, 40, 35}: the lecture's example, heap [40, 25, 35, 10, 5, 20, 30], height 3
    // 2. {10, 20, 30, 40, 50}: ascending is the worst case, every new key is the biggest so far and moves up to root.
    //    heap [50, 40, 20, 10, 30], height 3
    // 3. {50, 40, 30, 20, 10}: descending is already a max heap, the best case, no key moves.
    //    heap [50, 40, 30, 20, 10], height 3
    // 4. {5, 5, 5}: duplicates are all kept, heap [5, 5, 5], size 3, height 2
    // 5. {0, -5, 5, -10, Integer.MIN_VALUE, Integer.MAX_VALUE}: negatives and the int limits,
    //    heap [2147483647, -5, 5, -10, -2147483648, 0], height 3
    // 6. {7}: heap [7], size 1, height 1
    // 7. {}: heap [], size 0, height 0, display() prints nothing
    // For every array, "isValidHeap", "keys unchanged" and "same as insert" are all true.
    // Then new MaxHeap(null): catch the NullPointerException here in the demo and print
    // "keys = null: NullPointerException: keys must not be null"
    // then an empty line
    private static void demoGenerate() {
        showHeap(new int[]{10, 20, 30, 25, 5, 40, 35});
        showHeap(new int[]{10, 20, 30, 40, 50});
        showHeap(new int[]{50, 40, 30, 20, 10});
        showHeap(new int[]{5, 5, 5});
        showHeap(new int[]{0, -5, 5, -10, Integer.MIN_VALUE, Integer.MAX_VALUE});
        showHeap(new int[]{7});
        showHeap(new int[]{});
        try {
            new MaxHeap(null);
        } catch (NullPointerException e) {
            System.out.println("keys = null: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        System.out.println();
    }

    // prints "keys = [...]" (Arrays.toString), keeps keys.clone(), builds new MaxHeap(keys), then prints
    // on separate lines:
    //   "heap = [...]" (toArray)
    //   "size = x, height = h, isValidHeap = true"
    //   "keys unchanged = true" (Arrays.equals with the clone)
    //   "same as insert = true" (Arrays.equals of toArray() and buildByInsert(keys).toArray())
    // then display(), then an empty line
    private static void showHeap(int[] keys) {
        System.out.println("keys = " + Arrays.toString(keys));
        int[] original = keys.clone();
        MaxHeap heap = new MaxHeap(keys);
        System.out.println("heap = " + Arrays.toString(heap.toArray()));
        System.out.println("size = " + heap.size() + ", height = " + heap.height()
                + ", isValidHeap = " + heap.isValidHeap());
        System.out.println("keys unchanged = " + Arrays.equals(keys, original));
        System.out.println("same as insert = " + Arrays.equals(heap.toArray(), buildByInsert(keys).toArray()));
        heap.display();
        System.out.println();
    }

    // returns new MaxHeap() after insert(key) for every key, in order
    private static MaxHeap buildByInsert(int[] keys) {
        MaxHeap heap = new MaxHeap();
        for (int key : keys) {
            heap.insert(key);
        }
        return heap;
    }

    // Lecture 1. Builds the lecture's heap new MaxHeap(new int[]{10, 20, 30, 25, 5, 40, 35}) and prints:
    //   "peek = 40, size = 7" (peek does not remove the key)
    // Then insert(50) and insert(45), printing after each one:
    //   "after insert(50): peek = 50, size = 8"
    //   "after insert(45): peek = 50, size = 9" (45 is not the biggest, so peek does not change)
    // Then an empty heap, new MaxHeap(). Print "empty heap: isEmpty = true, size = 0, height = 0, toArray = []",
    // then call peek and catch the exception here in the demo:
    //   "peek on empty heap: NoSuchElementException: heap is empty"
    // then an empty line
    private static void demoPeek() {
        MaxHeap heap = new MaxHeap(new int[]{10, 20, 30, 25, 5, 40, 35});
        System.out.println("peek = " + heap.peek() + ", size = " + heap.size());
        heap.insert(50);
        System.out.println("after insert(50): peek = " + heap.peek() + ", size = " + heap.size());
        heap.insert(45);
        System.out.println("after insert(45): peek = " + heap.peek() + ", size = " + heap.size());
        MaxHeap empty = new MaxHeap();
        System.out.println("empty heap: isEmpty = " + empty.isEmpty() + ", size = " + empty.size()
                + ", height = " + empty.height() + ", toArray = " + Arrays.toString(empty.toArray()));
        try {
            empty.peek();
        } catch (NoSuchElementException e) {
            System.out.println("peek on empty heap: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        System.out.println();
    }

    // Lectures 2 to 4. Two large heaps. Do not print the arrays.
    // 1. new MaxHeap(rangeArray(1_000_000)): ascending input, the worst case for the build (every key moves up
    //    to root). Print:
    //    "1000000 ascending keys, constructor: size = 1000000, height = 20, peek = 1000000, isValidHeap = true"
    // 2. new MaxHeap(), then insert every key of shuffledRange(1_000_000, 32) in order (the array grows from
    //    10 places to 1,000,000 places on the way). Print:
    //    "1000000 shuffled keys, insert: size = 1000000, height = 20, peek = 1000000, isValidHeap = true"
    // A heap is complete, so it is always as short as possible (20 levels hold up to 2^20 - 1 = 1,048,575 keys)
    // without any rotation, unlike AVL.
    // then an empty line
    private static void demoLargeHeaps() {
        showLargeHeap("1000000 ascending keys, constructor", new MaxHeap(rangeArray(1_000_000)));
        MaxHeap shuffled = new MaxHeap();
        for (int key : shuffledRange(1_000_000, 32)) {
            shuffled.insert(key);
        }
        showLargeHeap("1000000 shuffled keys, insert", shuffled);
        System.out.println();
    }

    // prints "<label>: size = x, height = h, peek = p, isValidHeap = true" on one line
    private static void showLargeHeap(String label, MaxHeap heap) {
        System.out.println(label + ": size = " + heap.size() + ", height = " + heap.height()
                + ", peek = " + heap.peek() + ", isValidHeap = " + heap.isValidHeap());
    }

    // Lectures 1 to 4. Compares with java.util.PriorityQueue<Integer> made with Collections.reverseOrder(),
    // which is Java's own max heap, on 2000 random arrays from new Random(33).
    // Each round: n = random.nextInt(50) (0 to 49 keys), each key random.nextInt(201) - 100 (-100 to 100, so many
    // duplicates). Build one heap with the constructor and one with buildByInsert, and add every key to the
    // PriorityQueue. The round fails if any of these is false:
    //   both heaps pass isValidHeap
    //   the two toArray results are equal (Arrays.equals)
    //   size equals the PriorityQueue's size
    //   peek equals the PriorityQueue's peek (only when n > 0)
    //   toArray sorted equals keys sorted (the heap holds exactly the same keys, duplicates included)
    // Do not compare toArray with the PriorityQueue's own order: different valid heaps can hold the same keys.
    // Print "2000 random heaps: 0 failures".
    private static void demoRandomCheck() {
        Random random = new Random(33);
        int failures = 0;
        for (int round = 0; round < 2000; round++) {
            int n = random.nextInt(50);
            int[] keys = new int[n];
            for (int i = 0; i < n; i++) {
                keys[i] = random.nextInt(201) - 100;
            }
            if (!matchesPriorityQueue(keys)) {
                failures++;
            }
        }
        System.out.println("2000 random heaps: " + failures + " failures");
    }

    // one round of demoRandomCheck: true only if every check listed above passes for these keys
    private static boolean matchesPriorityQueue(int[] keys) {
        MaxHeap built = new MaxHeap(keys);
        MaxHeap inserted = buildByInsert(keys);
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (int key : keys) {
            queue.add(key);
        }
        if (!built.isValidHeap() || !inserted.isValidHeap()) {
            return false;
        }
        if (!Arrays.equals(built.toArray(), inserted.toArray())) {
            return false;
        }
        if (built.size() != queue.size()) {
            return false;
        }
        if (keys.length > 0 && built.peek() != queue.peek()) {
            return false;
        }
        int[] heapKeys = built.toArray();
        int[] sortedKeys = keys.clone();
        Arrays.sort(heapKeys);
        Arrays.sort(sortedKeys);
        return Arrays.equals(heapKeys, sortedKeys);
    }

    // From m31 (copied). Returns {1, 2, ..., n}; n = 0 gives {}
    private static int[] rangeArray(int n) {
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = i + 1;
        }
        return values;
    }

    // From m31 (copied). Returns {1, 2, ..., n} in a random order, shuffled with new Random(seed) (Fisher-Yates),
    // so every run gives the same order; n = 0 gives {}
    private static int[] shuffledRange(int n, long seed) {
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = i + 1;
        }
        Random random = new Random(seed);
        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = values[i];
            values[i] = values[j];
            values[j] = temp;
        }
        return values;
    }
}
