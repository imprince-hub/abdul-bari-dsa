package m30_binary_search_trees;

import java.util.List;

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
    }

    // Lectures 4 and 5. Calls showTree for each array (expected size, preorder and inorder below each one):
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
    }

    // prints "keys = [...]" (Arrays.toString), builds the tree with the constructor,
    // then prints "size = x", "preorder = [...]" and "inorder = [...]" on separate lines, then display(),
    // then an empty line
    private static void showTree(int[] keys) {
    }

    // Lecture 2. Builds the lecture's tree {9, 15, 5, 20, 16, 8, 12, 3, 6} and calls showSearch for these keys:
    //   found: 9 (root), 15 (inside the tree), 6 and 16 (leaves on the deepest level)
    //   not found: 1 (smaller than every key), 25 (bigger than every key), 10 (between 9 and 12), 7 (between 6 and 8)
    // Then key 5 on an empty tree, new BinarySearchTree(new int[]{}): false and false.
    // Iterative and recursive must always give the same answer.
    private static void demoSearch() {
    }

    // prints "search 9: iterative = true, recursive = true"
    private static void showSearch(BinarySearchTree tree, int key) {
    }

    // Lectures 4 and 5. Calls showInsertRecursive for the same 7 arrays as demoCreate.
    // Expected for every array: "same preorder as constructor = true".
    // For {30, 20, 30, 10, 20} the results are [true, true, false, true, false].
    private static void demoInsertRecursive() {
    }

    // prints "keys = [...]", then starts with new BinarySearchTree() and calls insertRecursive(key) for each key
    // in order, keeping the return values in a List<Boolean>.
    // Then prints "results = [...]", "size = x" and "same preorder as constructor = true/false" on separate lines,
    // where the last one compares preorderRecursive() with new BinarySearchTree(keys).preorderRecursive() using equals.
    // Preorder, not inorder: every BST of the same keys has the same inorder (sorted), so only preorder shows the shape.
    private static void showInsertRecursive(int[] keys) {
    }

    // Lectures 4 and 5. A large tree: new BinarySearchTree(shuffledRange(1_000_000, 30)).
    // Do not print the array. Print on separate lines:
    //   "1000000 shuffled keys: size = 1000000"
    //   "inorder equals 1..1000000 = true" (inorderRecursive() compared with rangeList(1_000_000) using equals)
    // Then showSearch for 1, 500000 and 1000000 (all true) and for 0 and 1000001 (both false).
    // Random order keeps the tree short (a few dozen levels for a million keys), so the recursive methods are fine here.
    // Sorted input of this size would make a 1,000,000-level skewed tree: that is the "Drawbacks of BST" lecture.
    private static void demoLargeTree() {
    }

    // returns {1, 2, ..., n} in a random order, shuffled with new Random(seed) (Fisher-Yates),
    // so every run gives the same order; n = 0 gives {}
    private static int[] shuffledRange(int n, long seed) {
        throw new UnsupportedOperationException("TODO: lectures 4 and 5");
    }

    // returns the list [1, 2, ..., n]; n = 0 gives []
    private static List<Integer> rangeList(int n) {
        throw new UnsupportedOperationException("TODO: lectures 4 and 5");
    }
}
