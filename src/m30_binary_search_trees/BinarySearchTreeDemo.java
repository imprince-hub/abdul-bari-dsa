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
        demoDelete();
        demoDeleteAll();
        demoDeleteLarge();
        demoFromPreorder();
        demoFromPreorderLarge();
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

    // Lectures 6 to 8. Calls showDelete(keys, key) for these cases (expected result after each one):
    // On the lecture's tree {9, 15, 5, 20, 16, 8, 12, 3, 6} (preorder [9, 5, 3, 8, 6, 15, 12, 20, 16]):
    // 1. delete 3, a leaf: true, size 8, preorder [9, 5, 8, 6, 15, 12, 20, 16]
    // 2. delete 8, only an lchild (6), so its predecessor 6 takes its place: preorder [9, 5, 3, 6, 15, 12, 20, 16]
    // 3. delete 15, two children, rchild's subtree higher (2 > 1), so successor 16: preorder [9, 5, 3, 8, 6, 16, 12, 20]
    // 4. delete 9, the root, both subtrees 3 levels high, so successor 12: preorder [12, 5, 3, 8, 6, 15, 20, 16]
    // 5. delete 5, rchild's subtree higher (2 > 1), so successor 6: preorder [9, 6, 3, 8, 15, 12, 20, 16]
    // 6. delete 10, not in the tree: false, size 9, preorder does not change
    // Other trees:
    // 7. {9, 5, 15, 3, 8, 6, 12} delete 9: lchild's subtree higher (3 > 2), so predecessor 8: preorder [8, 5, 3, 6, 15, 12]
    // 8. {10, 20, 30, 40, 50} delete 20, only an rchild: successor 30, and the copying goes on down the chain
    //    (30, 40 and 50 each move up one place, the last node removed is the old 50): preorder [10, 30, 40, 50]
    // 9. {50, 40, 30, 20, 10} delete 40, only an lchild: predecessor 30, same chain: preorder [50, 30, 20, 10]
    // 10. {7} delete 7, the only node: true, size 0, empty tree
    // 11. {} delete 5: false, size 0
    // Cases 1 to 5 all give size 8. Every inorder after a delete is still sorted ascending.
    private static void demoDelete() {
    }

    // prints "keys = [...], delete 9", then "before: preorder = [...]",
    // then "deleteRecursive(9) = true, size = 8", then "after: preorder = [...]" and "after: inorder = [...]",
    // then display() and an empty line
    private static void showDelete(int[] keys, int key) {
    }

    // Lectures 6 to 8. Builds the lecture's tree {9, 15, 5, 20, 16, 8, 12, 3, 6} and deletes its keys in the
    // same order. After each one print "deleteRecursive(9) = true, size = 8, inorder = [...]".
    // Expected inorder after each delete:
    //   9: [3, 5, 6, 8, 12, 15, 16, 20]
    //   15: [3, 5, 6, 8, 12, 16, 20]
    //   5: [3, 6, 8, 12, 16, 20]
    //   20: [3, 6, 8, 12, 16]
    //   16: [3, 6, 8, 12]
    //   8: [3, 6, 12]
    //   12: [3, 6]
    //   3: [6]
    //   6: []
    // Then print "isEmpty = true", then deleteRecursive(9) once more on the empty tree: false.
    private static void demoDeleteAll() {
    }

    // Lectures 6 to 8. new BinarySearchTree(shuffledRange(1_000_000, 30)), then deleteRecursive for every even key
    // 2, 4, ..., 1000000, in that order. Print on separate lines:
    //   "deleted the 500000 even keys: all returned true = true, size = 500000"
    //   "inorder equals 1, 3, ..., 999999 = true" (inorderRecursive() compared with oddList(1_000_000) using equals)
    //   "deleteRecursive(2) again = false"
    // Then showSearch for 2 (false and false) and 3 (true and true).
    private static void demoDeleteLarge() {
    }

    // returns the odd numbers from 1 to n: [1, 3, 5, ...]; n = 0 gives []
    private static List<Integer> oddList(int n) {
        throw new UnsupportedOperationException("TODO: lectures 6 to 8");
    }

    // Lecture 9. Calls showFromPreorder for each array:
    // 1. {9, 5, 3, 8, 6, 15, 12, 20, 16}: the preorder of the lecture's tree; display() must match demoCreate's first tree
    // 2. {30, 20, 10, 15, 25, 40, 50, 45}: inorder [10, 15, 20, 25, 30, 40, 45, 50], display():
    //      30: lchild 20, rchild 40
    //      20: lchild 10, rchild 25
    //      40: lchild -, rchild 50
    //      10: lchild -, rchild 15
    //      25: lchild -, rchild -
    //      50: lchild 45, rchild -
    //      15: lchild -, rchild -
    //      45: lchild -, rchild -
    // 3. {1, 2, 3, 4, 5}: right-skewed
    // 4. {5, 4, 3, 2, 1}: left-skewed
    // 5. {0, -5, Integer.MAX_VALUE}: MAX_VALUE is a real key here (the reason it cannot be the "no limit" value)
    // 6. {Integer.MIN_VALUE, Integer.MAX_VALUE}
    // 7. {7}
    // 8. {}: empty tree
    // For every one: size equals the array length and "preorder equals input = true".
    // Then showInvalidPreorder for {5, 3, 8, 4} (4 comes after 8 but is smaller than 5), {5, 5} (repeated key)
    // and {5, 3, 4, 3} (repeated key that does not empty the stack): each prints
    // "IllegalArgumentException: not the preorder of a BST with distinct keys".
    // Then fromPreorder(null): catch the NullPointerException here and print
    // "preorder = null: NullPointerException: preorder must not be null"
    private static void demoFromPreorder() {
    }

    // prints "preorder = [...]", builds the tree with BinarySearchTree.fromPreorder, then prints "size = x",
    // "preorder equals input = true/false" (preorderRecursive() compared with toList(preorder) using equals)
    // and "inorder = [...]" on separate lines, then display() and an empty line
    private static void showFromPreorder(int[] preorder) {
    }

    // calls BinarySearchTree.fromPreorder(preorder) and prints
    // "preorder = [5, 3, 8, 4]: IllegalArgumentException: <message>"
    // or "preorder = [...]: no exception" if nothing was thrown
    private static void showInvalidPreorder(int[] preorder) {
    }

    // Lecture 9. Two large inputs:
    // 1. The preorder of the 1,000,000-key shuffled tree: build new BinarySearchTree(shuffledRange(1_000_000, 30)),
    //    turn its preorderRecursive() into an int[] (toArray) and give that to fromPreorder.
    //    Print "shuffled tree rebuilt from its preorder: size = 1000000, same preorder = true".
    // 2. The sorted preorder rangeArray(1_000_000), which gives a 1,000,000-level right-skewed tree.
    //    fromPreorder has no recursion, so it works. Print
    //    "sorted preorder: size = 1000000, inorder equals 1..1000000 = true" using inorderIterative()
    //    (inorderRecursive() would throw StackOverflowError on this tree).
    //    The constructor would need about 500 billion steps for the same sorted input, because every insert
    //    walks the whole chain.
    private static void demoFromPreorderLarge() {
    }

    // returns the values as a new List<Integer>, in the same order
    private static List<Integer> toList(int[] values) {
        throw new UnsupportedOperationException("TODO: lecture 9");
    }

    // returns the values as a new int[], in the same order
    private static int[] toArray(List<Integer> values) {
        throw new UnsupportedOperationException("TODO: lecture 9");
    }

    // returns {1, 2, ..., n}; n = 0 gives {}
    private static int[] rangeArray(int n) {
        throw new UnsupportedOperationException("TODO: lecture 9");
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
