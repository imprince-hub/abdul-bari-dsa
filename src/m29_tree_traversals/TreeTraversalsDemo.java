package m29_tree_traversals;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TreeTraversalsDemo {
    public static void main(String[] args) {
        demoCountNodes();
        demoTraversals();
        demoCreateFromKeyboard();
        demoIterativeTraversals();
        demoDeepSkewedTree();
        demoIterativePostorder();
        demoHeight();
        demoDeleteTree();
        demoLevelOrder();
        demoTypeYourOwnTree();
    }

    // Lectures 1 and 2. Calls showCount for each array:
    // 1. {1, 2, 3, 4, 5, 6, 7}: full binary tree, count 7
    // 2. {10, 20, 30, null, 40, 50, null, -1, null, null, 60}: some children missing, count 7
    // 3. {1, 2, null, 3, null, 4}: left-skewed (every node has only an lchild), count 4
    // 4. {1, null, 2, null, 3}: right-skewed (every node has only an rchild), count 3
    // 5. {7}: one node, count 1
    // 6. {}: empty tree, count 0
    // Then a large tree: new BinaryTree(rangeArray(1_000_000)) is a complete tree with 20 levels (heightRecursive() = 20).
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
    // 1. "1 2 3 4 5 6 7 - - - - - - - -": full tree with 3 levels, size 7 (eight "-" for the four leaves)
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

    // Lectures 10 and 11. Label line "=== Iterative traversals ===", then showIterativeTraversals
    // for the same 6 arrays as demoTraversals. The expected lists are in the comments above
    // demoTraversals, and every "same as recursive" must be true.
    // Then the 1,000,000-node complete tree from rangeArray(1_000_000). Do not print its lists; print
    // "1000000-node complete tree: preorder same = x, inorder same = y" (iterative equals recursive)
    private static void demoIterativeTraversals() {
        System.out.println("=== Iterative traversals ===");
        showIterativeTraversals(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        showIterativeTraversals(new Integer[]{10, 20, 30, null, 40, 50, null, -1, null, null, 60});
        showIterativeTraversals(new Integer[]{1, 2, null, 3, null, 4});
        showIterativeTraversals(new Integer[]{1, null, 2, null, 3});
        showIterativeTraversals(new Integer[]{7});
        showIterativeTraversals(new Integer[]{});

        BinaryTree largeTree = new BinaryTree(rangeArray(1_000_000));
        boolean preorderSame = largeTree.preorderIterative().equals(largeTree.preorderRecursive());
        boolean inorderSame = largeTree.inorderIterative().equals(largeTree.inorderRecursive());
        System.out.println("1000000-node complete tree: preorder same = " + preorderSame + ", inorder same = " + inorderSame);
        System.out.println();
    }

    // prints the array (Arrays.toString) as a label, builds the tree, then one line each:
    //   "preorderIterative = [...], same as recursive = true"
    //   "inorderIterative = [...], same as recursive = true"
    // then an empty line
    private static void showIterativeTraversals(Integer[] levelOrder) {
        System.out.println("levelOrder = " + Arrays.toString(levelOrder));
        BinaryTree tree = new BinaryTree(levelOrder);
        List<Integer> preorder = tree.preorderIterative();
        System.out.println("preorderIterative = " + preorder + ", same as recursive = " + preorder.equals(tree.preorderRecursive()));
        List<Integer> inorder = tree.inorderIterative();
        System.out.println("inorderIterative = " + inorder + ", same as recursive = " + inorder.equals(tree.inorderRecursive()));
        System.out.println();
    }

    // Lectures 10 and 11. Shows why the iterative versions exist: a left-skewed tree of 1,000,000 levels
    // from leftSkewedArray(1_000_000). Label line "=== Deep left-skewed tree (1000000 levels) ===".
    // Do not print the lists; print the list size and its first and last value:
    //   "preorderIterative: size = 1000000, first = 1, last = 1000000"
    //   "inorderIterative: size = 1000000, first = 1000000, last = 1"
    // Then call preorderRecursive() on the same tree inside try/catch (StackOverflowError) and print
    // "preorderRecursive = StackOverflowError", then an empty line.
    // Catching an Error is fine only in a demo like this one, never in the data structure class.
    private static void demoDeepSkewedTree() {
        System.out.println("=== Deep left-skewed tree (1000000 levels) ===");
        BinaryTree deepTree = new BinaryTree(leftSkewedArray(1_000_000));
        List<Integer> preorder = deepTree.preorderIterative();
        System.out.println("preorderIterative: size = " + preorder.size()
                + ", first = " + preorder.get(0) + ", last = " + preorder.get(preorder.size() - 1));
        List<Integer> inorder = deepTree.inorderIterative();
        System.out.println("inorderIterative: size = " + inorder.size()
                + ", first = " + inorder.get(0) + ", last = " + inorder.get(inorder.size() - 1));
        try {
            deepTree.preorderRecursive();
            System.out.println("preorderRecursive = finished without StackOverflowError");
        } catch (StackOverflowError e) {
            System.out.println("preorderRecursive = StackOverflowError");
        }
        System.out.println();
    }

    // returns a left-skewed tree in level order: 1 is the root, then the pair (k, null) for k = 2 to n,
    // so node k gets lchild k + 1 and no rchild. Length is 2n - 1:
    //   n = 4 gives {1, 2, null, 3, null, 4, null}
    //   n = 1 gives {1}
    //   n = 0 gives {}
    private static Integer[] leftSkewedArray(int n) {
        if (n == 0) {
            return new Integer[0];
        }
        Integer[] values = new Integer[2 * n - 1];
        values[0] = 1;
        for (int k = 2; k <= n; k++) {
            values[2 * k - 3] = k;
        }
        return values;
    }

    // Lectures 12 and 13. Label line "=== Iterative postorder ===", then showIterativePostorder
    // for the same 6 arrays as demoTraversals. The expected lists are the postorder lists in the
    // comments above demoTraversals, and every "same as recursive" must be true.
    // Then two large trees. Do not print their lists:
    //   new BinaryTree(rangeArray(1_000_000)), complete tree:
    //     "1000000-node complete tree: postorder same = x" (postorderIterative equals postorderRecursive)
    //   new BinaryTree(leftSkewedArray(1_000_000)):
    //     "1000000-level left-skewed tree: size = 1000000, first = 1000000, last = 1" (size, first and last of the list)
    // then an empty line
    private static void demoIterativePostorder() {
        System.out.println("=== Iterative postorder ===");
        showIterativePostorder(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        showIterativePostorder(new Integer[]{10, 20, 30, null, 40, 50, null, -1, null, null, 60});
        showIterativePostorder(new Integer[]{1, 2, null, 3, null, 4});
        showIterativePostorder(new Integer[]{1, null, 2, null, 3});
        showIterativePostorder(new Integer[]{7});
        showIterativePostorder(new Integer[]{});

        BinaryTree largeTree = new BinaryTree(rangeArray(1_000_000));
        boolean postorderSame = largeTree.postorderIterative().equals(largeTree.postorderRecursive());
        System.out.println("1000000-node complete tree: postorder same = " + postorderSame);

        BinaryTree deepTree = new BinaryTree(leftSkewedArray(1_000_000));
        List<Integer> postorder = deepTree.postorderIterative();
        System.out.println("1000000-level left-skewed tree: size = " + postorder.size()
                + ", first = " + postorder.get(0) + ", last = " + postorder.get(postorder.size() - 1));
        System.out.println();
    }

    // prints the array (Arrays.toString) as a label, builds the tree, then
    //   "postorderIterative = [...], same as recursive = true"
    // then an empty line
    private static void showIterativePostorder(Integer[] levelOrder) {
        System.out.println("levelOrder = " + Arrays.toString(levelOrder));
        BinaryTree tree = new BinaryTree(levelOrder);
        List<Integer> postorder = tree.postorderIterative();
        System.out.println("postorderIterative = " + postorder + ", same as recursive = " + postorder.equals(tree.postorderRecursive()));
        System.out.println();
    }

    // Lectures 14 and 15. Label line "=== Height (heightRecursive) ===", then showHeight for each array
    // (height = number of levels, as in the lecture's code):
    // 1. {1, 2, 3, 4, 5, 6, 7}: full tree, height 3
    // 2. {10, 20, 30, null, 40, 50, null, -1, null, null, 60}: some children missing, height 4
    //    (levels: 10 | 20, 30 | 40, 50 | -1, 60)
    // 3. {1, 2, null, 3, null, 4}: left-skewed, height 4
    // 4. {1, null, 2, null, 3}: right-skewed, height 3
    // 5. {7}: one node, height 1
    // 6. {}: empty tree, height 0
    // Then two large trees. Do not print their arrays:
    //   new BinaryTree(rangeArray(1_000_000)): "1000000-node complete tree: heightRecursive = 20"
    //     (the smallest height for n nodes: 2^19 <= 1000000 < 2^20, so 20 levels)
    //   new BinaryTree(leftSkewedArray(1000)): "1000-node left-skewed tree: heightRecursive = 1000"
    //     (the largest height for n nodes: one node per level)
    // then an empty line
    private static void demoHeight() {
        System.out.println("=== Height (heightRecursive) ===");
        showHeight(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        showHeight(new Integer[]{10, 20, 30, null, 40, 50, null, -1, null, null, 60});
        showHeight(new Integer[]{1, 2, null, 3, null, 4});
        showHeight(new Integer[]{1, null, 2, null, 3});
        showHeight(new Integer[]{7});
        showHeight(new Integer[]{});

        BinaryTree largeTree = new BinaryTree(rangeArray(1_000_000));
        System.out.println("1000000-node complete tree: heightRecursive = " + largeTree.heightRecursive());
        BinaryTree skewedTree = new BinaryTree(leftSkewedArray(1000));
        System.out.println("1000-node left-skewed tree: heightRecursive = " + skewedTree.heightRecursive());
        System.out.println();
    }

    // prints the array (Arrays.toString) as a label, builds the tree,
    // then prints "size = x, heightRecursive = y" on one line, then an empty line
    private static void showHeight(Integer[] levelOrder) {
        System.out.println("levelOrder = " + Arrays.toString(levelOrder));
        BinaryTree tree = new BinaryTree(levelOrder);
        System.out.println("size = " + tree.size() + ", heightRecursive = " + tree.heightRecursive());
        System.out.println();
    }

    // Lecture 16. Label line "=== Delete tree (deleteTreeRecursive) ===", then showDeleteTree
    // for the same 6 arrays as demoTraversals. After the delete every tree must show
    // "size = 0, isEmpty = true, preorder = [], heightRecursive = 0", the empty tree too.
    // Then:
    //   new BinaryTree(new Integer[]{1, 2, 3}): call deleteTreeRecursive() two times. The second call
    //     deletes an empty tree and must not throw. Print "deleted two times: size = 0, isEmpty = true"
    //   new BinaryTree(rangeArray(1_000_000)): do not print the lists; print
    //     "1000000-node complete tree: size before = 1000000, size after = 0, isEmpty = true"
    // then an empty line
    private static void demoDeleteTree() {
        System.out.println("=== Delete tree (deleteTreeRecursive) ===");
        showDeleteTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        showDeleteTree(new Integer[]{10, 20, 30, null, 40, 50, null, -1, null, null, 60});
        showDeleteTree(new Integer[]{1, 2, null, 3, null, 4});
        showDeleteTree(new Integer[]{1, null, 2, null, 3});
        showDeleteTree(new Integer[]{7});
        showDeleteTree(new Integer[]{});

        BinaryTree smallTree = new BinaryTree(new Integer[]{1, 2, 3});
        smallTree.deleteTreeRecursive();
        smallTree.deleteTreeRecursive();
        System.out.println("deleted two times: size = " + smallTree.size() + ", isEmpty = " + smallTree.isEmpty());

        BinaryTree largeTree = new BinaryTree(rangeArray(1_000_000));
        int sizeBefore = largeTree.size();
        largeTree.deleteTreeRecursive();
        System.out.println("1000000-node complete tree: size before = " + sizeBefore
                + ", size after = " + largeTree.size() + ", isEmpty = " + largeTree.isEmpty());
        System.out.println();
    }

    // prints the array (Arrays.toString) as a label and builds the tree, then two lines:
    //   "before delete: size = x, preorder = [...]"
    //   (calls deleteTreeRecursive())
    //   "after delete: size = 0, isEmpty = true, preorder = [], heightRecursive = 0"
    // then an empty line
    private static void showDeleteTree(Integer[] levelOrder) {
        System.out.println("levelOrder = " + Arrays.toString(levelOrder));
        BinaryTree tree = new BinaryTree(levelOrder);
        System.out.println("before delete: size = " + tree.size() + ", preorder = " + tree.preorderRecursive());
        tree.deleteTreeRecursive();
        System.out.println("after delete: size = " + tree.size() + ", isEmpty = " + tree.isEmpty()
                + ", preorder = " + tree.preorderRecursive() + ", heightRecursive = " + tree.heightRecursive());
        System.out.println();
    }

    // Lectures 17 and 18. Label line "=== Level order (levelOrderIterative) ===", then showLevelOrder
    // for the same 6 arrays as demoTraversals (expected lists):
    // 1. {1, 2, 3, 4, 5, 6, 7}: [1, 2, 3, 4, 5, 6, 7]
    // 2. {10, 20, 30, null, 40, 50, null, -1, null, null, 60}: [10, 20, 30, 40, 50, -1, 60]
    // 3. {1, 2, null, 3, null, 4}: [1, 2, 3, 4]
    // 4. {1, null, 2, null, 3}: [1, 2, 3]
    // 5. {7}: [7]
    // 6. {}: []
    // Then two large trees. Do not print their lists:
    //   new BinaryTree(rangeArray(1_000_000)):
    //     "1000000-node complete tree: same as the array without nulls = x"
    //   new BinaryTree(leftSkewedArray(1_000_000)):
    //     "1000000-level left-skewed tree: size = 1000000, first = 1, last = 1000000" (size, first and last of the list)
    // then an empty line
    private static void demoLevelOrder() {
        System.out.println("=== Level order (levelOrderIterative) ===");
        showLevelOrder(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        showLevelOrder(new Integer[]{10, 20, 30, null, 40, 50, null, -1, null, null, 60});
        showLevelOrder(new Integer[]{1, 2, null, 3, null, 4});
        showLevelOrder(new Integer[]{1, null, 2, null, 3});
        showLevelOrder(new Integer[]{7});
        showLevelOrder(new Integer[]{});

        Integer[] completeArray = rangeArray(1_000_000);
        BinaryTree largeTree = new BinaryTree(completeArray);
        boolean sameAsArray = largeTree.levelOrderIterative().equals(nonNullValues(completeArray));
        System.out.println("1000000-node complete tree: same as the array without nulls = " + sameAsArray);

        BinaryTree deepTree = new BinaryTree(leftSkewedArray(1_000_000));
        List<Integer> visited = deepTree.levelOrderIterative();
        System.out.println("1000000-level left-skewed tree: size = " + visited.size()
                + ", first = " + visited.get(0) + ", last = " + visited.get(visited.size() - 1));
        System.out.println();
    }

    // prints the array (Arrays.toString) as a label, builds the tree, then
    //   "levelOrderIterative = [...], same as the array without nulls = true"
    // (compare with nonNullValues(levelOrder) using equals), then an empty line
    private static void showLevelOrder(Integer[] levelOrder) {
        System.out.println("levelOrder = " + Arrays.toString(levelOrder));
        BinaryTree tree = new BinaryTree(levelOrder);
        List<Integer> visited = tree.levelOrderIterative();
        System.out.println("levelOrderIterative = " + visited
                + ", same as the array without nulls = " + visited.equals(nonNullValues(levelOrder)));
        System.out.println();
    }

    // returns the values of the array in the same order, with the nulls left out, as a new List<Integer>
    // {10, 20, null, 40} gives [10, 20, 40]; {} gives []; {null, null} gives []
    private static List<Integer> nonNullValues(Integer[] values) {
        List<Integer> result = new ArrayList<>();
        for (Integer value : values) {
            if (value != null) {
                result.add(value);
            }
        }
        return result;
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
