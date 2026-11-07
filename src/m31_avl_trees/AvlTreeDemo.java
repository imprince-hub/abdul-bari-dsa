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
        demoDeleteRotations();
        demoDelete();
        demoDeleteMultipleRotations();
        demoDeleteAll();
        demoDeleteLarge();
        demoDeleteRandomCheck();
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
        System.out.println();
    }

    // Lectures 7 and 8. The six rotation cases of a delete, named the lecture's way (the side that is too high,
    // then the child's balance factor). Calls showDelete(keys, key) for each. Every tree is built with the
    // constructor without any rotation, and the delete makes one node's balance factor 2 or -2.
    // 1. {30, 20, 40, 10} delete 40: L1 at 30 (lchild 20 has bf 1), llRotation
    //    after: size 3, height 2, preorder [20, 10, 30]
    // 2. {30, 10, 40, 20} delete 40: L-1 at 30 (lchild 10 has bf -1), lrRotation
    //    after: size 3, height 2, preorder [20, 10, 30]
    // 3. {30, 20, 40, 10, 25} delete 40: L0 at 30 (lchild 20 has bf 0, which only a delete can cause), llRotation
    //    after: size 4, height 3, preorder [20, 10, 30, 25], display():
    //      20 (height 3, bf -1): lchild 10, rchild 30
    //      10 (height 1, bf 0): lchild -, rchild -
    //      30 (height 2, bf 1): lchild 25, rchild -
    //      25 (height 1, bf 0): lchild -, rchild -
    //    The height stays 3 here: after an L0 rotation the subtree is not shorter than before the delete.
    // 4. {20, 10, 30, 40} delete 10: R-1 at 20 (rchild 30 has bf -1), rrRotation
    //    after: size 3, height 2, preorder [30, 20, 40]
    // 5. {20, 10, 40, 30} delete 10: R1 at 20 (rchild 40 has bf 1), rlRotation
    //    after: size 3, height 2, preorder [30, 20, 40]
    // 6. {20, 10, 30, 25, 40} delete 10: R0 at 20 (rchild 30 has bf 0), rrRotation
    //    after: size 4, height 3, preorder [30, 20, 25, 40]
    // Every one returns true and prints "isValidAvl = true".
    private static void demoDeleteRotations() {
        System.out.println("=== Delete rotations (L1, L-1, L0, R-1, R1, R0) ===");
        int[][] cases = {
                {30, 20, 40, 10},
                {30, 10, 40, 20},
                {30, 20, 40, 10, 25},
                {20, 10, 30, 40},
                {20, 10, 40, 30},
                {20, 10, 30, 25, 40}
        };
        int[] deleteKeys = {40, 40, 40, 10, 10, 10};
        String[] rotations = {
                "L1 at 30 (llRotation)", "L-1 at 30 (lrRotation)", "L0 at 30 (llRotation)",
                "R-1 at 20 (rrRotation)", "R1 at 20 (rlRotation)", "R0 at 20 (rrRotation)"
        };
        for (int i = 0; i < cases.length; i++) {
            System.out.println("case " + (i + 1) + ", expected rotation: " + rotations[i]);
            showDelete(cases[i], deleteKeys[i]);
        }
    }

    // Lectures 7 and 8. Deleting nodes that hold a key in different places. Calls showDelete(keys, key) for each.
    // On the lecture's tree {40, 20, 10, 25, 30, 22, 50} (preorder [25, 20, 10, 22, 40, 30, 50], height 3):
    // 1. delete 10, a leaf: true, size 6, height 3, preorder [25, 20, 22, 40, 30, 50], no rotation
    // 2. delete 25, the root: both subtrees have height 2, so its inorder successor 30 takes its place:
    //    preorder [30, 20, 10, 22, 40, 50]
    // 3. delete 20: both children are leaves (same height), so successor 22: preorder [25, 22, 10, 40, 30, 50]
    // 4. delete 40: the same, successor 50: preorder [25, 20, 10, 22, 50, 30]
    // 5. delete 99, not in the tree: false, size 7, preorder does not change
    // Other trees:
    // 6. {20, 10, 30, 25} delete 30, only an lchild: predecessor 25, preorder [20, 10, 25], height 2
    // 7. {20, 10, 30, 40} delete 30, only an rchild: successor 40, preorder [20, 10, 40], height 2
    // 8. {40, 20, 50, 10, 30, 60, 5} (preorder [40, 20, 10, 5, 30, 50, 60], height 4) delete 40: lchild's subtree
    //    is higher (3 > 2), so predecessor 30 takes its place. Removing 30 from under 20 gives 20 a bf of 2 with
    //    lchild 10 at bf 1: L1 at 20, llRotation. After: size 6, height 3, preorder [30, 10, 5, 20, 50, 60]
    // 9. {7} delete 7, the only node: true, size 0, height 0, display() prints nothing
    // 10. {} delete 5: false, size 0
    // Every one prints "isValidAvl = true".
    private static void demoDelete() {
        System.out.println("=== Deleting (deleteRecursive) ===");
        int[] lectureTree = {40, 20, 10, 25, 30, 22, 50};
        for (int key : new int[]{10, 25, 20, 40, 99}) {
            showDelete(lectureTree, key);
        }
        showDelete(new int[]{20, 10, 30, 25}, 30);
        showDelete(new int[]{20, 10, 30, 40}, 30);
        showDelete(new int[]{40, 20, 50, 10, 30, 60, 5}, 40);
        showDelete(new int[]{7}, 7);
        showDelete(new int[]{}, 5);
    }

    // prints "keys = [...], delete 40", then "before: preorder = [...]",
    // then "deleteRecursive(40) = true, size = 3, height = 2, isValidAvl = true",
    // then "after: preorder = [...]" and "after: inorder = [...]", then display() and an empty line
    private static void showDelete(int[] keys, int key) {
        System.out.println("keys = " + Arrays.toString(keys) + ", delete " + key);
        AvlTree tree = new AvlTree(keys);
        System.out.println("before: preorder = " + tree.preorderRecursive());
        boolean deleted = tree.deleteRecursive(key);
        System.out.println("deleteRecursive(" + key + ") = " + deleted + ", size = " + tree.size()
                + ", height = " + tree.height() + ", isValidAvl = " + tree.isValidAvl());
        System.out.println("after: preorder = " + tree.preorderRecursive());
        System.out.println("after: inorder = " + tree.inorderRecursive());
        tree.display();
        System.out.println();
    }

    // Lectures 7 and 8. One delete that needs two rotations. Calls showDelete for
    // {80, 30, 100, 20, 50, 90, 110, 10, 40, 60, 120, 70} delete 90.
    // The keys are the tree's level order, so the constructor makes no rotation: size 12, height 5,
    // preorder [80, 30, 20, 10, 50, 40, 60, 70, 100, 90, 110, 120].
    // Deleting 90 (a leaf) gives 100 a bf of -2 with rchild 110 at bf -1: R-1 at 100, rrRotation. That subtree
    // drops from height 3 to 2, so now 80 has bf 2 (4 against 2) with lchild 30 at bf -1: L-1 at 80, lrRotation,
    // and 50 becomes the root.
    // After: true, size 11, height 4, preorder [50, 30, 20, 10, 40, 80, 60, 70, 110, 100, 120], display():
    //   50 (height 4, bf 0): lchild 30, rchild 80
    //   30 (height 3, bf 1): lchild 20, rchild 40
    //   80 (height 3, bf 0): lchild 60, rchild 110
    //   20 (height 2, bf 1): lchild 10, rchild -
    //   40 (height 1, bf 0): lchild -, rchild -
    //   60 (height 2, bf -1): lchild -, rchild 70
    //   110 (height 2, bf 0): lchild 100, rchild 120
    //   10 (height 1, bf 0): lchild -, rchild -
    //   70 (height 1, bf 0): lchild -, rchild -
    //   100 (height 1, bf 0): lchild -, rchild -
    //   120 (height 1, bf 0): lchild -, rchild -
    private static void demoDeleteMultipleRotations() {
        System.out.println("=== One delete, two rotations (R-1 at 100, then L-1 at 80) ===");
        int[] keys = {80, 30, 100, 20, 50, 90, 110, 10, 40, 60, 120, 70};
        AvlTree before = new AvlTree(keys);
        System.out.println("before: size = " + before.size() + ", height = " + before.height());
        showDelete(keys, 90);
    }

    // Lectures 7 and 8. Builds the lecture's tree {40, 20, 10, 25, 30, 22, 50} and deletes its keys in the same
    // order. After each one print
    // "deleteRecursive(40) = true, size = 6, height = 3, isValidAvl = true, inorder = [...]".
    // Expected height and inorder after each delete:
    //   40: height 3, [10, 20, 22, 25, 30, 50]
    //   20: height 3, [10, 22, 25, 30, 50]
    //   10: height 3, [22, 25, 30, 50]
    //   25: height 2, [22, 30, 50]
    //   30: height 2, [22, 50]
    //   22: height 1, [50]
    //   50: height 0, []
    // Then print "isEmpty = true", then deleteRecursive(40) once more on the empty tree: false.
    private static void demoDeleteAll() {
        System.out.println("=== Deleting every key of the lecture's tree ===");
        int[] keys = {40, 20, 10, 25, 30, 22, 50};
        AvlTree tree = new AvlTree(keys);
        for (int key : keys) {
            boolean deleted = tree.deleteRecursive(key);
            System.out.println("deleteRecursive(" + key + ") = " + deleted + ", size = " + tree.size()
                    + ", height = " + tree.height() + ", isValidAvl = " + tree.isValidAvl()
                    + ", inorder = " + tree.inorderRecursive());
        }
        System.out.println("isEmpty = " + tree.isEmpty());
        System.out.println("deleteRecursive(40) again = " + tree.deleteRecursive(40));
        System.out.println();
    }

    // Lectures 7 and 8. Large deletes. Do not print the arrays.
    // 1. new AvlTree(rangeArray(1_000_000)), then showDeleteEvens("sorted", tree, 1_000_000). Expected:
    //      "sorted: deleted the 500000 even keys: all returned true = true, size = 500000, height = 20"
    //      "inorder equals 1, 3, ..., 999999 = true"
    //      "isValidAvl = true"
    //      "deleteRecursive(2) again = false"
    // 2. new AvlTree(shuffledRange(1_000_000, 30)), then showDeleteEvens("shuffled", tree, 1_000_000): the same lines
    //    with height = 23.
    // Then on the shuffled tree delete every odd key 1, 3, ..., 999999 and print
    //    "deleted the 500000 odd keys: all returned true = true, isEmpty = true, height = 0"
    // Recursion is fine here: the height never goes above 24.
    private static void demoDeleteLarge() {
        System.out.println("=== Large deletes (1,000,000 keys) ===");
        int n = 1_000_000;
        showDeleteEvens("sorted", new AvlTree(rangeArray(n)), n);
        AvlTree shuffled = new AvlTree(shuffledRange(n, 30));
        showDeleteEvens("shuffled", shuffled, n);
        boolean allDeleted = true;
        for (int key = 1; key <= n; key += 2) {
            if (!shuffled.deleteRecursive(key)) {
                allDeleted = false;
            }
        }
        System.out.println("deleted the " + n / 2 + " odd keys: all returned true = " + allDeleted
                + ", isEmpty = " + shuffled.isEmpty() + ", height = " + shuffled.height());
        System.out.println();
    }

    // calls deleteRecursive for every even key 2, 4, ..., n in that order on a tree that holds 1..n, then prints the
    // four lines that demoDeleteLarge describes (inorderRecursive() compared with oddList(n) using equals);
    // order is "sorted" or "shuffled"
    private static void showDeleteEvens(String order, AvlTree tree, int n) {
        boolean allDeleted = true;
        for (int key = 2; key <= n; key += 2) {
            if (!tree.deleteRecursive(key)) {
                allDeleted = false;
            }
        }
        System.out.println(order + ": deleted the " + n / 2 + " even keys: all returned true = " + allDeleted
                + ", size = " + tree.size() + ", height = " + tree.height());
        System.out.println("inorder equals 1, 3, ..., " + (n - 1) + " = " + tree.inorderRecursive().equals(oddList(n)));
        System.out.println("isValidAvl = " + tree.isValidAvl());
        System.out.println("deleteRecursive(2) again = " + tree.deleteRecursive(2));
    }

    // Lectures 7 and 8. Compares deleteRecursive with java.util.TreeSet<Integer> on 2000 random trees.
    // Random random = new Random(32). For each tree: count = random.nextInt(51). First insert count keys, each
    // random.nextInt(100), into new AvlTree() and new TreeSet<>(). Then do count deletes, each key random.nextInt(100),
    // so some keys are not in the tree. Make the random calls in exactly this order, so every run is the same.
    // For every delete:
    //   deleteRecursive(key) must return the same as set.remove(key)
    //   after the delete, isValidAvl() must be true
    //   after the delete, inorderRecursive() must equal new ArrayList<>(set)
    // Every check that fails adds 1 to failures. Also count all deletes, and the deletes that found the key.
    // Expected: "2000 random trees, 49686 deletes (11567 found the key): failures = 0"
    private static void demoDeleteRandomCheck() {
        System.out.println("=== Random delete check against java.util.TreeSet ===");
        Random random = new Random(32);
        int trees = 2000;
        int deletes = 0;
        int found = 0;
        int failures = 0;
        for (int trial = 0; trial < trees; trial++) {
            int count = random.nextInt(51);
            AvlTree tree = new AvlTree();
            TreeSet<Integer> set = new TreeSet<>();
            for (int i = 0; i < count; i++) {
                int key = random.nextInt(100);
                tree.insertRecursive(key);
                set.add(key);
            }
            for (int i = 0; i < count; i++) {
                int key = random.nextInt(100);
                boolean deleted = tree.deleteRecursive(key);
                boolean removed = set.remove(key);
                deletes++;
                if (removed) {
                    found++;
                }
                if (deleted != removed) {
                    failures++;
                }
                if (!tree.isValidAvl()) {
                    failures++;
                }
                if (!tree.inorderRecursive().equals(new ArrayList<>(set))) {
                    failures++;
                }
            }
        }
        System.out.println(trees + " random trees, " + deletes + " deletes (" + found + " found the key): failures = "
                + failures);
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

    // returns the odd numbers from 1 to n: [1, 3, 5, ...]; n = 0 gives [] (the same helper as m30's oddList)
    private static List<Integer> oddList(int n) {
        List<Integer> values = new ArrayList<>((n + 1) / 2);
        for (int i = 1; i <= n; i += 2) {
            values.add(i);
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
