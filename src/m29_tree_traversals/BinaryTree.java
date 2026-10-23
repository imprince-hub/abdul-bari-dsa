package m29_tree_traversals;

import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;

// Binary tree in linked representation, used for all m29 lectures.
// From m28: the level-order array constructor, isEmpty, size, display,
// and the helpers makeChild, checkNoValuesLeft, childText.
// Queue: java.util.ArrayDeque<Node>, used as Queue<Node> (add, remove, isEmpty).
// m26's GenericCircularQueue has a fixed capacity, and the keyboard constructor cannot know
// the number of nodes before they are typed, so m29 uses ArrayDeque everywhere.
// New in m29: countRecursive (lectures 1 and 2),
// preorderRecursive, inorderRecursive, postorderRecursive (lectures 3 to 7),
// the keyboard constructor BinaryTree(Scanner, PrintStream) (lectures 8 and 9),
// preorderIterative, inorderIterative (lectures 10 and 11).
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
    //   {} gives an empty tree; return before levelOrder[0] is read
    //   levelOrder[0] == null gives an empty tree, but the values after it must all be null (checkNoValuesLeft)
    //   the array ends while nodes are still in the queue: those nodes get no children
    //   the queue becomes empty while the array still has values: checkNoValuesLeft
    public BinaryTree(Integer[] levelOrder) {
        if (levelOrder == null) {
            throw new NullPointerException("levelOrder must not be null");
        }
        if (levelOrder.length == 0) {
            return;
        }
        Queue<Node> queue = new ArrayDeque<>();
        root = makeChild(levelOrder[0], queue);
        int index = 1;
        while (!queue.isEmpty() && index < levelOrder.length) {
            Node parent = queue.remove();
            parent.lchild = makeChild(levelOrder[index++], queue);
            if (index < levelOrder.length) {
                parent.rchild = makeChild(levelOrder[index++], queue);
            }
        }
        checkNoValuesLeft(levelOrder, index);
    }

    // Lectures 8 and 9. Builds the tree from typed values, level by level with a queue, in the same
    // order as the array constructor: the root first, then the lchild and rchild of each node dequeued.
    // input: where the values come from. new Scanner(System.in) for the keyboard,
    //        or new Scanner("10 20 - ...") in the demo, so it runs without typing.
    // prompts: where the questions are printed. System.out for the keyboard,
    //          or a PrintStream that prints nothing for the demo's fixed inputs.
    // "-" means "no child" (the same symbol display() prints), so -1 can be a normal value, as in m28.
    // Every node that is made gets two questions (lchild and rchild), so the input must answer "-"
    // for every missing child. The tree is complete when the queue is empty; values after that are not read.
    //
    // Prompts (readValue prints them without a newline, so the value is typed on the same line):
    //   "root (- for empty tree): "
    //   "lchild of 10 (- for none): "
    //   "rchild of 10 (- for none): "
    //
    // Cases:
    //   input null throws NullPointerException("input must not be null")
    //   prompts null throws NullPointerException("prompts must not be null")
    //   "-" for the root gives an empty tree and asks nothing more
    //   input ends before the queue is empty: readValue throws NoSuchElementException
    //   a value that is not a number and not "-": NumberFormatException (from Integer.parseInt in readValue)
    // Every value goes through makeChild, like the array constructor.
    public BinaryTree(Scanner input, PrintStream prompts) {
        if (input == null) {
            throw new NullPointerException("input must not be null");
        }
        if (prompts == null) {
            throw new NullPointerException("prompts must not be null");
        }
        Queue<Node> queue = new ArrayDeque<>();
        root = makeChild(readValue(input, prompts, "root (- for empty tree): "), queue);
        while (!queue.isEmpty()) {
            Node parent = queue.remove();
            parent.lchild = makeChild(readValue(input, prompts, "lchild of " + parent.data + " (- for none): "), queue);
            parent.rchild = makeChild(readValue(input, prompts, "rchild of " + parent.data + " (- for none): "), queue);
        }
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
    // uses its own ArrayDeque<Node>
    // empty tree prints nothing; return before the queue is made, because ArrayDeque does not accept null
    public void display() {
        if (isEmpty()) {
            return;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.println(node.data + ": lchild " + childText(node.lchild) + ", rchild " + childText(node.rchild));
            if (node.lchild != null) {
                queue.add(node.lchild);
            }
            if (node.rchild != null) {
                queue.add(node.rchild);
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

    // Lectures 10 and 11. Iterative traversals: the same visit order as the recursive versions,
    // but the method keeps its own stack of nodes instead of using the call stack.
    // No recursion, so no public wrapper and private helper pair: one public method each.
    // Each one returns a new list, which must be equal to the recursive version for every tree
    // (the demo checks this with equals).
    // Variables: Node current (the node being looked at, starts at root) and Deque<Node> stack.
    // Stack: java.util.ArrayDeque<Node> used as Deque<Node> (push, pop, isEmpty), the same class as the queues here.
    // Not java.util.Stack: it is an old class built on Vector, and its own Javadoc says to use Deque instead.
    // ArrayDeque does not accept null, so a null child must never be pushed.
    // Empty tree returns an empty list.
    // The stack lives on the heap, not on the call stack, so a very deep skewed tree works here,
    // while the recursive versions hit StackOverflowError.

    // preorder: the same order as preorderRecursive (visit node, then lchild's subtree, then rchild's subtree)
    public List<Integer> preorderIterative() {
        List<Integer> result = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();
        Node current = root;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                result.add(current.data);
                stack.push(current);
                current = current.lchild;
            } else {
                current = stack.pop();
                current = current.rchild;
            }
        }
        return result;
    }

    // inorder: the same order as inorderRecursive (lchild's subtree, then visit node, then rchild's subtree)
    public List<Integer> inorderIterative() {
        List<Integer> result = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();
        Node current = root;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.lchild;
            } else {
                current = stack.pop();
                result.add(current.data);
                current = current.rchild;
            }
        }
        return result;
    }

    // value is null: returns null and changes nothing
    // otherwise: makes a new Node, enqueues it, adds 1 to size and returns it
    // the caller links the returned node as lchild or rchild
    private Node makeChild(Integer value, Queue<Node> queue) {
        if (value == null) {
            return null;
        }
        Node newNode = new Node(value);
        queue.add(newNode);
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

    // Lectures 8 and 9. prints prompt to prompts (print, not println), then reads the next token from input
    // "-" returns null (no child); any other token is returned as Integer.parseInt(token)
    // no token left: NoSuchElementException("Input ended before the tree was complete")
    private static Integer readValue(Scanner input, PrintStream prompts, String prompt) {
        prompts.print(prompt);
        if (!input.hasNext()) {
            throw new NoSuchElementException("Input ended before the tree was complete");
        }
        String token = input.next();
        if (token.equals("-")) {
            return null;
        }
        return Integer.parseInt(token);
    }

    // "-" for null, otherwise the node's data as text
    private static String childText(Node child) {
        if (child == null) {
            return "-";
        }
        return String.valueOf(child.data);
    }
}
