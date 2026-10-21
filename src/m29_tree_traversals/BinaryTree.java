package m29_tree_traversals;

import m26_queue.GenericCircularQueue;

import java.util.ArrayList;
import java.util.List;

// Binary tree in linked representation, used for all m29 lectures.
// From m28 (copied as-is): the level-order array constructor, isEmpty, size, display,
// and the helpers makeChild, checkNoValuesLeft, childText.
// Queue: m26's GenericCircularQueue<Node>.
// New in m29: countRecursive (lectures 1 and 2),
// preorderRecursive, inorderRecursive, postorderRecursive (lectures 3 to 7).
public class BinaryTree {
    private Node root;
    private int size; // number of nodes

    // Array format:
    //   levelOrder[0] is the root.
    //   After that, every two values are the lchild and rchild of the next node dequeued from the queue.
    //   null means "no child". A null is never enqueued, so no values in the array belong to it.
    // Example: {10, 20, 30, null, 40}
    //   10 gets lchild 20 and rchild 30
    //   20 gets no lchild and rchild 40
    //   30 and 40 get no children, because the array has ended
    // null is used for "no child" instead of -1, so -1 can be a normal value.
    //
    // Cases:
    //   null array throws NullPointerException("levelOrder must not be null")
    //   {} gives an empty tree; return before the queue is made, because capacity 0 is not allowed
    //   levelOrder[0] == null gives an empty tree, but the values after it must all be null (checkNoValuesLeft)
    //   the array ends while nodes are still in the queue: those nodes get no children
    //   the queue becomes empty while the array still has values: checkNoValuesLeft
    // queue capacity: levelOrder.length, the queue never holds more nodes than that
    public BinaryTree(Integer[] levelOrder) {
        if (levelOrder == null) {
            throw new NullPointerException("levelOrder must not be null");
        }
        if (levelOrder.length == 0) {
            return;
        }
        GenericCircularQueue<Node> queue = new GenericCircularQueue<>(levelOrder.length);
        root = makeChild(levelOrder[0], queue);
        int index = 1;
        while (!queue.isEmpty() && index < levelOrder.length) {
            Node parent = queue.dequeue();
            parent.lchild = makeChild(levelOrder[index++], queue);
            if (index < levelOrder.length) {
                parent.rchild = makeChild(levelOrder[index++], queue);
            }
        }
        checkNoValuesLeft(levelOrder, index);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    // prints one line per node, in level order (the same order the nodes were made):
    //   10: lchild 20, rchild 30
    //   20: lchild -, rchild 40
    // "-" means no child (childText)
    // uses its own GenericCircularQueue<Node> with capacity size
    // empty tree prints nothing; return before the queue is made, because capacity 0 is not allowed
    public void display() {
        if (isEmpty()) {
            return;
        }
        GenericCircularQueue<Node> queue = new GenericCircularQueue<>(size);
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node node = queue.dequeue();
            System.out.println(node.data + ": lchild " + childText(node.lchild) + ", rchild " + childText(node.rchild));
            if (node.lchild != null) {
                queue.enqueue(node.lchild);
            }
            if (node.rchild != null) {
                queue.enqueue(node.rchild);
            }
        }
    }

    // Lectures 1 and 2. Counts the nodes by visiting the tree. It does not read the size field,
    // so the demo can check countRecursive() == size().
    // Empty tree returns 0 (that is the real count, not a fake value).
    // No isEmpty() check needed: for an empty tree the helper gets null and its base case returns 0.
    public int countRecursive() {
        return countRecursive(root);
    }

    // number of nodes in the subtree that starts at node
    // node == null: an empty subtree has 0 nodes (base case)
    // otherwise: 1 for this node + nodes in lchild's subtree + nodes in rchild's subtree;
    // the + 1 is added in the returning phase, after both calls come back
    // one stack frame per level, so a very deep skewed tree can hit StackOverflowError
    private int countRecursive(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + countRecursive(node.lchild) + countRecursive(node.rchild);
    }

    // Lectures 3 to 7. Recursive traversals, same shape as countRecursive.
    // Each one returns the data in visit order as a new list. Returned, not printed,
    // so later lectures can compare them with the iterative versions using equals.
    // Empty tree returns an empty list: the helper gets null and does nothing, so no isEmpty() check.
    // Public wrapper: makes a new ArrayList, passes it with root to the helper, returns the list.
    // Helper: node == null means nothing to visit (base case); otherwise "visit" means result.add(node.data),
    // done in the order given above each pair.

    // preorder: visit node, then lchild's subtree, then rchild's subtree
    public List<Integer> preorderRecursive() {
        List<Integer> result = new ArrayList<>();
        preorderRecursive(root, result);
        return result;
    }

    private void preorderRecursive(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.data);
        preorderRecursive(node.lchild, result);
        preorderRecursive(node.rchild, result);
    }

    // inorder: lchild's subtree, then visit node, then rchild's subtree
    public List<Integer> inorderRecursive() {
        List<Integer> result = new ArrayList<>();
        inorderRecursive(root, result);
        return result;
    }

    private void inorderRecursive(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inorderRecursive(node.lchild, result);
        result.add(node.data);
        inorderRecursive(node.rchild, result);
    }

    // postorder: lchild's subtree, then rchild's subtree, then visit node
    public List<Integer> postorderRecursive() {
        List<Integer> result = new ArrayList<>();
        postorderRecursive(root, result);
        return result;
    }

    private void postorderRecursive(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }
        postorderRecursive(node.lchild, result);
        postorderRecursive(node.rchild, result);
        result.add(node.data);
    }

    // value is null: returns null and changes nothing
    // otherwise: makes a new Node, enqueues it, adds 1 to size and returns it
    // the caller links the returned node as lchild or rchild
    private Node makeChild(Integer value, GenericCircularQueue<Node> queue) {
        if (value == null) {
            return null;
        }
        Node newNode = new Node(value);
        queue.enqueue(newNode);
        size++;
        return newNode;
    }

    // called when the queue is empty; checks levelOrder from index start to the end
    // nulls are fine, the first non-null value throws
    // IllegalArgumentException("Value x at index i has no parent")
    private static void checkNoValuesLeft(Integer[] levelOrder, int start) {
        for (int i = start; i < levelOrder.length; i++) {
            if (levelOrder[i] != null) {
                throw new IllegalArgumentException("Value " + levelOrder[i] + " at index " + i + " has no parent");
            }
        }
    }

    // "-" for null, otherwise the node's data as text
    private static String childText(Node child) {
        if (child == null) {
            return "-";
        }
        return String.valueOf(child.data);
    }
}
