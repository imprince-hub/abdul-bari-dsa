package m30_binary_search_trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BinarySearchTreeDemo {
    public static void main(String[] args) {
        demoInsertIterative();
        demoCreate();
        demoSearch();
        demoInsertRecursive();
        demoLargeTree();
    }

    // Lecture 3. Starts with new BinarySearchTree() and calls insertIterative for 30, 20, 40, 20, 30, 35, in that order.
    // After each call print "insertIterative(30) = true, size = 1".
    // Expected: true, true, true, false (20 again), false (30 again, the root), true. Final size 4.
    // Then print "preorder = [30, 20, 40, 35]" and "inorder = [20, 30, 35, 40]", then display():
    //   30: lchild 20, rchild 40
    //   20: lchild -, rchild -
    //   40: lchild 35, rchild -
    //   35: lchild -, rchild -
    private static void demoInsertIterative() {
        System.out.println("=== Inserting one key at a time (insertIterative) ===");
        BinarySearchTree tree = new BinarySearchTree();
        for (int key : new int[]{30, 20, 40, 20, 30, 35}) {
            boolean added = tree.insertIterative(key);
            System.out.println("insertIterative(" + key + ") = " + added + ", size = " + tree.size());
        }
        System.out.println("preorder = " + tree.preorderRecursive());
        System.out.println("inorder = " + tree.inorderRecursive());
        tree.display();
        System.out.println();
    }

    // Lectures 4 and 5. Calls showTree for each array from demoKeyArrays (expected size, preorder and inorder below each one):
    // 1. {9, 15, 5, 20, 16, 8, 12, 3, 6}: the lecture's example, size 9
    //    preorder [9, 5, 3, 8, 6, 15, 12, 20, 16], inorder [3, 5, 6, 8, 9, 12, 15, 16, 20]
    // 2. {30, 20, 30, 10, 20}: duplicates are skipped, size 3
    //    preorder [30, 20, 10], inorder [10, 20, 30]
    // 3. {10, 20, 30, 40, 50}: sorted input gives a right-skewed tree, size 5
    //    preorder [10, 20, 30, 40, 50], inorder [10, 20, 30, 40, 50]
    // 4. {50, 40, 30, 20, 10}: reverse sorted input gives a left-skewed tree, size 5
    //    preorder [50, 40, 30, 20, 10], inorder [10, 20, 30, 40, 50]
    // 5. {0, -5, 5, -10, Integer.MIN_VALUE, Integer.MAX_VALUE}: negatives and the int limits, size 6
    //    preorder [0, -5, -10, -2147483648, 5, 2147483647], inorder [-2147483648, -10, -5, 0, 5, 2147483647]
    // 6. {7}: one node, size 1, preorder and inorder [7]
    // 7. {}: empty tree, size 0, preorder and inorder [], display() prints nothing
    // Every inorder is sorted ascending: that is the check that the tree follows the BST rule.
    // Then new BinarySearchTree(null): catch the NullPointerException here in the demo and print
    // "keys = null: NullPointerException: keys must not be null"
    private static void demoCreate() {
        System.out.println("=== Creating a BST from an array (constructor) ===");
        for (int[] keys : demoKeyArrays()) {
            showTree(keys);
        }
        try {
            new BinarySearchTree(null);
            System.out.println("keys = null: no exception");
        } catch (NullPointerException e) {
            System.out.println("keys = null: NullPointerException: " + e.getMessage());
        }
        System.out.println();
    }

    // prints "keys = [...]" (Arrays.toString), builds the tree with the constructor,
    // then prints "size = x", "preorder = [...]" and "inorder = [...]" on separate lines, then display(),
    // then an empty line
    private static void showTree(int[] keys) {
        System.out.println("keys = " + Arrays.toString(keys));
        BinarySearchTree tree = new BinarySearchTree(keys);
        System.out.println("size = " + tree.size());
        System.out.println("preorder = " + tree.preorderRecursive());
        System.out.println("inorder = " + tree.inorderRecursive());
        tree.display();
        System.out.println();
    }

    // Lecture 2. Builds the lecture's tree {9, 15, 5, 20, 16, 8, 12, 3, 6} and calls showSearch for these keys:
    //   found: 9 (root), 15 (inside the tree), 6 and 16 (leaves on the deepest level)
    //   not found: 1 (smaller than every key), 25 (bigger than every key), 10 (between 9 and 12), 7 (between 6 and 8)
    // Then key 5 on an empty tree, new BinarySearchTree(new int[]{}): false and false.
    // Iterative and recursive must always give the same answer.
    private static void demoSearch() {
        System.out.println("=== Searching (searchIterative and searchRecursive) ===");
        int[] keys = {9, 15, 5, 20, 16, 8, 12, 3, 6};
        System.out.println("keys = " + Arrays.toString(keys));
        BinarySearchTree tree = new BinarySearchTree(keys);
        System.out.println("keys in the tree:");
        for (int key : new int[]{9, 15, 6, 16}) {
            showSearch(tree, key);
        }
        System.out.println("keys not in the tree:");
        for (int key : new int[]{1, 25, 10, 7}) {
            showSearch(tree, key);
        }
        System.out.println("empty tree:");
        showSearch(new BinarySearchTree(new int[]{}), 5);
        System.out.println();
    }

    // prints "search 9: iterative = true, recursive = true"
    private static void showSearch(BinarySearchTree tree, int key) {
        System.out.println("search " + key + ": iterative = " + tree.searchIterative(key)
                + ", recursive = " + tree.searchRecursive(key));
    }

    // Lectures 4 and 5. Calls showInsertRecursive for the same 7 arrays as demoCreate (demoKeyArrays).
    // Expected for every array: "same preorder as constructor = true".
    // For {30, 20, 30, 10, 20} the results are [true, true, false, true, false].
    private static void demoInsertRecursive() {
        System.out.println("=== Inserting with recursion (insertRecursive) ===");
        for (int[] keys : demoKeyArrays()) {
            showInsertRecursive(keys);
        }
    }

    // prints "keys = [...]", then starts with new BinarySearchTree() and calls insertRecursive(key) for each key
    // in order, keeping the return values in a List<Boolean>.
    // Then prints "results = [...]", "size = x" and "same preorder as constructor = true/false" on separate lines,
    // where the last one compares preorderRecursive() with new BinarySearchTree(keys).preorderRecursive() using equals.
    // Preorder, not inorder: every BST of the same keys has the same inorder (sorted), so only preorder shows the shape.
    private static void showInsertRecursive(int[] keys) {
        System.out.println("keys = " + Arrays.toString(keys));
        BinarySearchTree tree = new BinarySearchTree();
        List<Boolean> results = new ArrayList<>();
        for (int key : keys) {
            results.add(tree.insertRecursive(key));
        }
        System.out.println("results = " + results);
        System.out.println("size = " + tree.size());
        boolean samePreorder = tree.preorderRecursive().equals(new BinarySearchTree(keys).preorderRecursive());
        System.out.println("same preorder as constructor = " + samePreorder);
        System.out.println();
    }

    // Lectures 4 and 5. A large tree: new BinarySearchTree(shuffledRange(1_000_000, 30)).
    // Do not print the array. Print on separate lines:
    //   "1000000 shuffled keys: size = 1000000"
    //   "inorder equals 1..1000000 = true" (inorderRecursive() compared with rangeList(1_000_000) using equals)
    // Then showSearch for 1, 500000 and 1000000 (all true) and for 0 and 1000001 (both false).
    // Random order keeps the tree short (a few dozen levels for a million keys), so the recursive methods are fine here.
    // Sorted input of this size would make a 1,000,000-level skewed tree: that is the "Drawbacks of BST" lecture.
    private static void demoLargeTree() {
        System.out.println("=== Large tree (1,000,000 shuffled keys) ===");
        int n = 1_000_000;
        BinarySearchTree tree = new BinarySearchTree(shuffledRange(n, 30));
        System.out.println(n + " shuffled keys: size = " + tree.size());
        System.out.println("inorder equals 1.." + n + " = " + tree.inorderRecursive().equals(rangeList(n)));
        for (int key : new int[]{1, 500_000, 1_000_000, 0, 1_000_001}) {
            showSearch(tree, key);
        }
    }

    // the 7 arrays used by demoCreate and demoInsertRecursive, in the order demoCreate's comment lists them
    private static int[][] demoKeyArrays() {
        return new int[][]{
                {9, 15, 5, 20, 16, 8, 12, 3, 6},
                {30, 20, 30, 10, 20},
                {10, 20, 30, 40, 50},
                {50, 40, 30, 20, 10},
                {0, -5, 5, -10, Integer.MIN_VALUE, Integer.MAX_VALUE},
                {7},
                {}
        };
    }

    // returns {1, 2, ..., n} in a random order, shuffled with new Random(seed) (Fisher-Yates),
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

    // returns the list [1, 2, ..., n]; n = 0 gives []
    private static List<Integer> rangeList(int n) {
        List<Integer> values = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            values.add(i);
        }
        return values;
    }
}
