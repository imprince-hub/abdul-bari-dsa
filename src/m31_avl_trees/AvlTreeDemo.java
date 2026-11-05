package m31_avl_trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class AvlTreeDemo {
    public static void main(String[] args) {
        demoRotations();
        demoInsertSteps();
        demoCreate();
        demoLargeTrees();
        demoRandomCheck();
    }

    // Lectures 3, 5 and 6. Calls showTree for each array. Each one does exactly one rotation (named below).
    // The first 4 are the lecture's 3-node cases. All 4 end as the same tree: size 3, height 2,
    // preorder [20, 10, 30], display():
    //   20 (height 2, bf 0): lchild 10, rchild 30
    //   10 (height 1, bf 0): lchild -, rchild -
    //   30 (height 1, bf 0): lchild -, rchild -
    // 1. {30, 20, 10}: LL at 30
    // 2. {30, 10, 20}: LR at 30
    // 3. {10, 20, 30}: RR at 10
    // 4. {10, 30, 20}: RL at 10
    // The next 4 have a subtree that moves to a new parent during the rotation (size 6, height 3):
    // 5. {50, 30, 70, 20, 40, 10}: LL at 50, 40 (the lecture's plr) moves to 50's lchild
    //    preorder [30, 20, 10, 50, 40, 70]
    // 6. {50, 20, 70, 10, 40, 30}: LR at 50, 40 becomes the root and its old lchild 30 moves to 20's rchild
    //    preorder [40, 20, 10, 30, 50, 70]
    // 7. {10, 5, 30, 20, 40, 50}: RR at 10, 20 (the lecture's prl) moves to 10's rchild
    //    preorder [30, 10, 5, 20, 40, 50]
    // 8. {10, 5, 40, 30, 50, 20}: RL at 10, 30 becomes the root and its old lchild 20 moves to 10's rchild
    //    preorder [30, 10, 5, 20, 40, 50]
    // Every one prints "isValidAvl = true" and a sorted inorder.
    private static void demoRotations() {
        System.out.println("=== Rotations (one rotation per tree) ===");
        int[][] cases = {
                {30, 20, 10},
                {30, 10, 20},
                {10, 20, 30},
                {10, 30, 20},
                {50, 30, 70, 20, 40, 10},
                {50, 20, 70, 10, 40, 30},
                {10, 5, 30, 20, 40, 50},
                {10, 5, 40, 30, 50, 20}
        };
        String[] rotations = {
                "LL at 30", "LR at 30", "RR at 10", "RL at 10",
                "LL at 50", "LR at 50", "RR at 10", "RL at 10"
        };
        for (int i = 0; i < cases.length; i++) {
            System.out.println("case " + (i + 1) + ", expected rotation: " + rotations[i]);
            showTree(cases[i]);
        }
    }

    // Lecture 4 (Creating AVL Tree). Starts with new AvlTree() and calls insertRecursive for
    // 40, 20, 10, 25, 30, 22, 50, in that order. This order uses all 4 rotations.
    // After each call print "insertRecursive(40) = true, size = 1, height = 1, preorder = [40]".
    // Expected height and preorder after each insert:
    //   40: height 1, [40]
    //   20: height 2, [40, 20]
    //   10: LL at 40, height 2, [20, 10, 40]
    //   25: height 3, [20, 10, 40, 25]
    //   30: LR at 40 (40's lchild 25 has bf -1), height 3, [20, 10, 30, 25, 40]
    //   22: RL at 20, the root, so root changes to 25, height 3, [25, 20, 10, 22, 30, 40]
    //   50: RR at 30, height 3, [25, 20, 10, 22, 40, 30, 50]
    // Then insertRecursive(25) once more: false, size stays 7. Then display():
    //   25 (height 3, bf 0): lchild 20, rchild 40
    //   20 (height 2, bf 0): lchild 10, rchild 22
    //   40 (height 2, bf 0): lchild 30, rchild 50
    //   10 (height 1, bf 0): lchild -, rchild -
    //   22 (height 1, bf 0): lchild -, rchild -
    //   30 (height 1, bf 0): lchild -, rchild -
    //   50 (height 1, bf 0): lchild -, rchild -
    // The same 7 keys in m30's BinarySearchTree give height 4.
    private static void demoInsertSteps() {
        System.out.println("=== Creating an AVL tree one key at a time (insertRecursive) ===");
        AvlTree tree = new AvlTree();
        for (int key : new int[]{40, 20, 10, 25, 30, 22, 50}) {
            boolean added = tree.insertRecursive(key);
            System.out.println("insertRecursive(" + key + ") = " + added + ", size = " + tree.size()
                    + ", height = " + tree.height() + ", preorder = " + tree.preorderRecursive());
        }
        boolean addedAgain = tree.insertRecursive(25);
        System.out.println("insertRecursive(25) = " + addedAgain + ", size = " + tree.size());
        tree.display();
        System.out.println();
    }

    // Lecture 4. Calls showTree for each array from demoKeyArrays:
    // 1. {40, 20, 10, 25, 30, 22, 50}: the same tree as demoInsertSteps, size 7, height 3
    // 2. {30, 20, 30, 10, 20}: duplicates are skipped, size 3, LL at 30 when 10 comes, preorder [20, 10, 30]
    // 3. {10, 20, 30, 40, 50, 60, 70}: sorted. m30 gave a 7-level right-skewed tree; here RR rotations give
    //    a perfect tree: size 7, height 3, preorder [40, 20, 10, 30, 60, 50, 70]
    // 4. {70, 60, 50, 40, 30, 20, 10}: reverse sorted, LL rotations, the same tree as 3
    // 5. {0, -5, 5, -10, Integer.MIN_VALUE, Integer.MAX_VALUE}: LL at -5 when MIN_VALUE comes, size 6, height 3,
    //    preorder [0, -10, -2147483648, -5, 5, 2147483647]
    // 6. {7}: size 1, height 1
    // 7. {}: size 0, height 0, isValidAvl true, display() prints nothing
    // Then new AvlTree(null): catch the NullPointerException here in the demo and print
    // "keys = null: NullPointerException: keys must not be null"
    private static void demoCreate() {
        System.out.println("=== Creating an AVL tree from an array (constructor) ===");
        for (int[] keys : demoKeyArrays()) {
            showTree(keys);
        }
        try {
            new AvlTree(null);
            System.out.println("keys = null: no exception");
        } catch (NullPointerException e) {
            System.out.println("keys = null: NullPointerException: " + e.getMessage());
        }
        System.out.println();
    }

    // prints "keys = [...]" (Arrays.toString), builds the tree with the constructor,
    // then prints "size = x, height = y, isValidAvl = true/false" on one line,
    // then "preorder = [...]" and "inorder = [...]" on separate lines, then display(), then an empty line
    private static void showTree(int[] keys) {
        System.out.println("keys = " + Arrays.toString(keys));
        AvlTree tree = new AvlTree(keys);
        System.out.println("size = " + tree.size() + ", height = " + tree.height()
                + ", isValidAvl = " + tree.isValidAvl());
        System.out.println("preorder = " + tree.preorderRecursive());
        System.out.println("inorder = " + tree.inorderRecursive());
        tree.display();
        System.out.println();
    }

    // Lectures 5 and 6. Two large trees. Do not print the arrays.
    // 1. new AvlTree(rangeArray(1_000_000)), sorted input. Print on separate lines:
    //      "1000000 sorted keys: size = 1000000, height = 20, heightRecursive = 20"
    //      "inorder equals 1..1000000 = true" (inorderRecursive() compared with rangeList(1_000_000) using equals)
    //      "isValidAvl = true"
    //    20 is the smallest height possible for 1,000,000 nodes (20 full levels hold 2^20 - 1 = 1,048,575).
    //    m30's BinarySearchTree would be 1,000,000 levels high for this input.
    // 2. new AvlTree(shuffledRange(1_000_000, 30)): the same three lines, with "shuffled" in place of "sorted"
    //    and height = 24, heightRecursive = 24. The AVL limit for 1,000,000 nodes is about 1.44 * log2(n), so 28
    //    (lecture 9, Height of AVL Tree).
    // Then on the sorted tree print "search 1 = true" with searchIterative for 1, 500000 and 1000000 (true)
    // and 0 and 1000001 (false).
    private static void demoLargeTrees() {
        System.out.println("=== Large trees (1,000,000 keys) ===");
        int n = 1_000_000;
        AvlTree sorted = new AvlTree(rangeArray(n));
        showLargeTree("sorted", sorted, n);
        showLargeTree("shuffled", new AvlTree(shuffledRange(n, 30)), n);
        for (int key : new int[]{1, 500_000, 1_000_000, 0, 1_000_001}) {
            System.out.println("search " + key + " = " + sorted.searchIterative(key));
        }
        System.out.println();
    }

    // prints the three lines that demoLargeTrees describes, for a tree that holds the keys 1..n;
    // order is "sorted" or "shuffled"
    private static void showLargeTree(String order, AvlTree tree, int n) {
        System.out.println(n + " " + order + " keys: size = " + tree.size() + ", height = " + tree.height()
                + ", heightRecursive = " + tree.heightRecursive());
        System.out.println("inorder equals 1.." + n + " = " + tree.inorderRecursive().equals(rangeList(n)));
        System.out.println("isValidAvl = " + tree.isValidAvl());
    }

    // Lectures 5 and 6. Compares AvlTree with java.util.TreeSet<Integer> on 2000 random trees.
    // Random random = new Random(31). For each tree: count = random.nextInt(51) (0 to 50 keys), and each key is
    // random.nextInt(100), so duplicates happen. Make the random calls in exactly this order, so every run is the same.
    // Start each tree with new AvlTree() and new TreeSet<>(). For every key:
    //   insertRecursive(key) must return the same as set.add(key)
    //   after the insert, isValidAvl() must be true
    //   after the insert, inorderRecursive() must equal new ArrayList<>(set)
    // Every check that fails adds 1 to failures. Also keep the biggest height() seen at the end of a tree.
    // Expected: "2000 random trees: failures = 0, max height = 7"
    private static void demoRandomCheck() {
        System.out.println("=== Random check against java.util.TreeSet ===");
        Random random = new Random(31);
        int trees = 2000;
        int failures = 0;
        int maxHeight = 0;
        for (int trial = 0; trial < trees; trial++) {
            int count = random.nextInt(51);
            AvlTree tree = new AvlTree();
            TreeSet<Integer> set = new TreeSet<>();
            for (int i = 0; i < count; i++) {
                int key = random.nextInt(100);
                if (tree.insertRecursive(key) != set.add(key)) {
                    failures++;
                }
                if (!tree.isValidAvl()) {
                    failures++;
                }
                if (!tree.inorderRecursive().equals(new ArrayList<>(set))) {
                    failures++;
                }
            }
            maxHeight = Math.max(maxHeight, tree.height());
        }
        System.out.println(trees + " random trees: failures = " + failures + ", max height = " + maxHeight);
    }

    // the 7 arrays used by demoCreate, in the order its comment lists them
    private static int[][] demoKeyArrays() {
        return new int[][]{
                {40, 20, 10, 25, 30, 22, 50},
                {30, 20, 30, 10, 20},
                {10, 20, 30, 40, 50, 60, 70},
                {70, 60, 50, 40, 30, 20, 10},
                {0, -5, 5, -10, Integer.MIN_VALUE, Integer.MAX_VALUE},
                {7},
                {}
        };
    }

    // returns {1, 2, ..., n}; n = 0 gives {} (the same helper as m30's rangeArray)
    private static int[] rangeArray(int n) {
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = i + 1;
        }
        return values;
    }

    // From m30. Returns {1, 2, ..., n} in a random order, shuffled with new Random(seed) (Fisher-Yates),
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

    // From m30. Returns the list [1, 2, ..., n]; n = 0 gives []
    private static List<Integer> rangeList(int n) {
        List<Integer> values = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            values.add(i);
        }
        return values;
    }
}
