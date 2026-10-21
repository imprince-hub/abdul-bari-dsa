package m28_trees_intro_representation;

import java.util.Arrays;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        demoBinaryTreeCreate();
    }

    // Lectures 10 to 12. Calls showTree for each array:
    // 1. {1, 2, 3, 4, 5, 6, 7}: full binary tree of height 2, size 7
    // 2. {10, 20, 30, null, 40, 50, null, -1, null, null, 60}: some children missing; -1 is a normal value;
    //    the array ends while -1 and 60 are still in the queue, so they get no children; size 7
    // 3. {1, 2}: the array ends before 1's rchild, so rchild is "-"; size 2
    // 4. {1, null, null, null, null}: extra nulls after the last node are fine; size 1
    // 5. {7}: one node
    // 6. {} and {null}: empty tree, size 0, display prints nothing
    // 7. errors with labels: {1, null, null, 5} (5 has no parent), {null, 5}, and a null array
    private static void demoBinaryTreeCreate() {
        System.out.println("=== BinaryTree from a level-order array ===");
        showTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        showTree(new Integer[]{10, 20, 30, null, 40, 50, null, -1, null, null, 60});
        showTree(new Integer[]{1, 2});
        showTree(new Integer[]{1, null, null, null, null});
        showTree(new Integer[]{7});
        showTree(new Integer[]{});
        showTree(new Integer[]{null});

        System.out.println("=== Errors ===");
        try {
            new BinaryTree(new Integer[]{1, null, null, 5});
        } catch (IllegalArgumentException e) {
            System.out.println("[1, null, null, 5] = " + e.getMessage());
        }
        try {
            new BinaryTree(new Integer[]{null, 5});
        } catch (IllegalArgumentException e) {
            System.out.println("[null, 5] = " + e.getMessage());
        }
        try {
            new BinaryTree(null);
        } catch (NullPointerException e) {
            System.out.println("null array = " + e.getMessage());
        }
    }

    // prints the array (Arrays.toString) as a label, builds the tree,
    // then prints isEmpty and size on one line, then display()
    private static void showTree(Integer[] levelOrder) {
        System.out.println("levelOrder = " + Arrays.toString(levelOrder));
        BinaryTree tree = new BinaryTree(levelOrder);
        System.out.println("isEmpty = " + tree.isEmpty() + ", size = " + tree.size());
        tree.display();
        System.out.println();
    }
}
