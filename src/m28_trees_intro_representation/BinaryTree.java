package m28_trees_intro_representation;

import m26_queue.GenericCircularQueue;

// Lectures 10 to 12. Binary tree in linked representation, built from a level-order array.
// The constructor builds the tree level by level with a queue (lectures 11 and 12),
// taking values from the array instead of the keyboard.
// Queue: m26's GenericCircularQueue<Node>.
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
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not written yet");
    }

    public int size() {
        throw new UnsupportedOperationException("Not written yet");
    }

    // prints one line per node, in level order (the same order the nodes were made):
    //   10: lchild 20, rchild 30
    //   20: lchild -, rchild 40
    // "-" means no child (childText)
    // uses its own GenericCircularQueue<Node> with capacity size
    // empty tree prints nothing; return before the queue is made, because capacity 0 is not allowed
    public void display() {
    }

    // value is null: returns null and changes nothing
    // otherwise: makes a new Node, enqueues it, adds 1 to size and returns it
    // the caller links the returned node as lchild or rchild
    private Node makeChild(Integer value, GenericCircularQueue<Node> queue) {
        throw new UnsupportedOperationException("Not written yet");
    }

    // called when the queue is empty; checks levelOrder from index start to the end
    // nulls are fine, the first non-null value throws
    // IllegalArgumentException("Value x at index i has no parent")
    private static void checkNoValuesLeft(Integer[] levelOrder, int start) {
    }

    // "-" for null, otherwise the node's data as text
    private static String childText(Node child) {
        throw new UnsupportedOperationException("Not written yet");
    }
}
