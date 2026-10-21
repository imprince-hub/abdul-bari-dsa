package m29_tree_traversals;

import java.util.Arrays;

public class TreeTraversalsDemo {
    public static void main(String[] args) {
        demoCountNodes();
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
}
