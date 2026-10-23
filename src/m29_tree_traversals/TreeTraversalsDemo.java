package m29_tree_traversals;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TreeTraversalsDemo {
    public static void main(String[] args) {
        demoCountNodes();
        demoTraversals();
        demoCreateFromKeyboard();
        demoTypeYourOwnTree();
    }

    // Lectures 1 and 2. Calls showCount for each array:
    // 1. {1, 2, 3, 4, 5, 6, 7}: full binary tree, count 7
    // 2. {10, 20, 30, null, 40, 50, null, -1, null, null, 60}: some children missing, count 7
    // 3. {1, 2, null, 3, null, 4}: left-skewed (every node has only an lchild), count 4
    // 4. {1, null, 2, null, 3}: right-skewed (every node has only an rchild), count 3
    // 5. {7}: one node, count 1
    // 6. {}: empty tree, count 0
    // Then a large tree: new BinaryTree(rangeArray(1_000_000)) is a complete tree of height 19.
    // Do not print this array; print "1000000 nodes: countRecursive = x, size = y"
    private static void demoCountNodes() {
        System.out.println("=== Counting nodes (countRecursive) ===");
        showCount(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        showCount(new Integer[]{10, 20, 30, null, 40, 50, null, -1, null, null, 60});
        showCount(new Integer[]{1, 2, null, 3, null, 4});
        showCount(new Integer[]{1, null, 2, null, 3});
        showCount(new Integer[]{7});
        showCount(new Integer[]{});

        BinaryTree largeTree = new BinaryTree(rangeArray(1_000_000));
        System.out.println("1000000 nodes: countRecursive = " + largeTree.countRecursive() + ", size = " + largeTree.size());
        System.out.println();
    }

    // prints the array (Arrays.toString) as a label, builds the tree,
    // then prints "countRecursive = x, size = y" on one line
    private static void showCount(Integer[] levelOrder) {
        System.out.println("levelOrder = " + Arrays.toString(levelOrder));
        BinaryTree tree = new BinaryTree(levelOrder);
        System.out.println("countRecursive = " + tree.countRecursive() + ", size = " + tree.size());
        System.out.println();
    }

    // returns {1, 2, ..., n}; n = 0 gives {}
    private static Integer[] rangeArray(int n) {
        Integer[] values = new Integer[n];
        for (int i = 0; i < n; i++) {
            values[i] = i + 1;
        }
        return values;
    }

    // Lectures 3 to 7. Calls showTraversals for each array (expected output below each one):
    // 1. {1, 2, 3, 4, 5, 6, 7}: full tree
    //    preorder [1, 2, 4, 5, 3, 6, 7], inorder [4, 2, 5, 1, 6, 3, 7], postorder [4, 5, 2, 6, 7, 3, 1]
    // 2. {10, 20, 30, null, 40, 50, null, -1, null, null, 60}: some children missing
    //    preorder [10, 20, 40, -1, 30, 50, 60], inorder [20, -1, 40, 10, 50, 60, 30], postorder [-1, 40, 20, 60, 50, 30, 10]
    // 3. {1, 2, null, 3, null, 4}: left-skewed
    //    preorder [1, 2, 3, 4], inorder [4, 3, 2, 1], postorder [4, 3, 2, 1]
    // 4. {1, null, 2, null, 3}: right-skewed
    //    preorder [1, 2, 3], inorder [1, 2, 3], postorder [3, 2, 1]
    // 5. {7}: all three are [7]
    // 6. {}: all three are []
    private static void demoTraversals() {
        System.out.println("=== Recursive traversals ===");
        showTraversals(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        showTraversals(new Integer[]{10, 20, 30, null, 40, 50, null, -1, null, null, 60});
        showTraversals(new Integer[]{1, 2, null, 3, null, 4});
        showTraversals(new Integer[]{1, null, 2, null, 3});
        showTraversals(new Integer[]{7});
        showTraversals(new Integer[]{});
    }

    // prints the array (Arrays.toString) as a label, builds the tree,
    // then one line each: "preorder = [...]", "inorder = [...]", "postorder = [...]", then an empty line
    private static void showTraversals(Integer[] levelOrder) {
        System.out.println("levelOrder = " + Arrays.toString(levelOrder));
        BinaryTree tree = new BinaryTree(levelOrder);
        System.out.println("preorder = " + tree.preorderRecursive());
        System.out.println("inorder = " + tree.inorderRecursive());
        System.out.println("postorder = " + tree.postorderRecursive());
        System.out.println();
    }

    // Lectures 8 and 9. Keyboard constructor with fixed inputs, so this part runs without typing.
    // Calls showKeyboardTree for each input:
    // 1. "1 2 3 4 5 6 7 - - - - - - - -": full tree of height 2, size 7 (eight "-" for the four leaves)
    // 2. "10 20 30 - 40 50 - -1 - - 60 - - - -": the same tree as the array
    //    {10, 20, 30, null, 40, 50, null, -1, null, null, 60}. Build that array tree too and print
    //    "same preorder and inorder as the array tree = true" (compare the lists with equals)
    // 3. "7 - -": one node, size 1
    // 4. "-": empty tree, size 0, display prints nothing
    // 5. "5 - - 99": 99 is never read, because the tree is complete after 5's two answers; size 1
    // 6. errors with labels:
    //    "1 2": input ends before 1's rchild, NoSuchElementException
    //    "1 x -": x is not a number, NumberFormatException
    private static void demoCreateFromKeyboard() {
        System.out.println("=== Create tree from keyboard (fixed inputs) ===");
        showKeyboardTree("1 2 3 4 5 6 7 - - - - - - - -");

        BinaryTree keyboardTree = showKeyboardTree("10 20 30 - 40 50 - -1 - - 60 - - - -");
        BinaryTree arrayTree = new BinaryTree(new Integer[]{10, 20, 30, null, 40, 50, null, -1, null, null, 60});
        boolean sameTree = keyboardTree.preorderRecursive().equals(arrayTree.preorderRecursive())
                && keyboardTree.inorderRecursive().equals(arrayTree.inorderRecursive());
        System.out.println("same preorder and inorder as the array tree = " + sameTree);
        System.out.println();

        showKeyboardTree("7 - -");
        showKeyboardTree("-");
        showKeyboardTree("5 - - 99");

        System.out.println("=== Keyboard errors ===");
        try {
            new BinaryTree(new Scanner("1 2"), silentPrompts());
        } catch (NoSuchElementException e) {
            System.out.println("input \"1 2\" = " + e.getMessage());
        }
        try {
            new BinaryTree(new Scanner("1 x -"), silentPrompts());
        } catch (NumberFormatException e) {
            System.out.println("input \"1 x -\" = " + e.getMessage());
        }
        System.out.println();
    }

    // prints the input as a label (input = "..."), builds the tree from new Scanner(input) with silentPrompts(),
    // then prints "size = x" and display(), then an empty line
    // returns the tree, so case 2 can compare it with the array tree
    private static BinaryTree showKeyboardTree(String input) {
        System.out.println("input = \"" + input + "\"");
        BinaryTree tree = new BinaryTree(new Scanner(input), silentPrompts());
        System.out.println("size = " + tree.size());
        tree.display();
        System.out.println();
        return tree;
    }

    // a PrintStream that prints nothing: new PrintStream(OutputStream.nullOutputStream())
    private static PrintStream silentPrompts() {
        return new PrintStream(OutputStream.nullOutputStream());
    }

    // Lectures 8 and 9. Real keyboard: prints a label line "=== Type your own tree (- for the root to skip) ===",
    // builds a tree from new Scanner(System.in) with System.out as prompts, then prints "size = x" and display().
    // Runs last in main, because it waits for typing.
    // Do not close this Scanner: closing it also closes System.in.
    // A typing mistake (not a number and not "-") or the input ending early prints
    // "Could not build the tree: ..." instead of crashing the demo.
    private static void demoTypeYourOwnTree() {
        System.out.println("=== Type your own tree (- for the root to skip) ===");
        Scanner keyboard = new Scanner(System.in);
        try {
            BinaryTree tree = new BinaryTree(keyboard, System.out);
            System.out.println();
            System.out.println("size = " + tree.size());
            tree.display();
        } catch (NoSuchElementException | NumberFormatException e) {
            System.out.println();
            System.out.println("Could not build the tree: " + e.getMessage());
        }
    }
}
