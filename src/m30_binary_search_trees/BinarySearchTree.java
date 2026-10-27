package m30_binary_search_trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// Binary search tree in linked representation, used for the m30 lectures.
// BST rule: for every node, every key in its lchild's subtree is smaller than node.data,
// and every key in its rchild's subtree is bigger. No duplicate keys (the lecture's rule).
// From m29 (your code, copied): isEmpty, size, display, childText, preorderRecursive, inorderRecursive.
// preorderRecursive is kept because all BSTs with the same keys have the same inorder (sorted),
// so the demo compares preorder to check that two trees have the same shape.
// New in m30, Introduction and Basic Operations in BST:
//   searchIterative, searchRecursive (lecture 2)
//   insertIterative (lecture 3)
//   insertRecursive and the constructor BinarySearchTree(int[] keys) (lectures 4 and 5)
public final class BinarySearchTree {
    private Node root;
    private int size; // number of nodes

    // empty tree; add keys with insertIterative or insertRecursive
    public BinarySearchTree() {
    }

    // Lectures 4 and 5. Creates the BST by inserting the keys one by one, in the given order,
    // with insertIterative (no recursion, so a large input cannot hit StackOverflowError).
    // The order matters: the same keys in a different order can give a different shape.
    //
    // Cases:
    //   keys null throws NullPointerException("keys must not be null")
    //   {} gives an empty tree
    //   a duplicate key is skipped, the same as insertIterative, so size can be less than keys.length
    public BinarySearchTree(int[] keys) {
        if (keys == null) {
            throw new NullPointerException("keys must not be null");
        }
        for (int key : keys) {
            insertIterative(key);
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    // Lecture 2. Returns true if key is in the tree.
    // Returns a boolean, not the Node: a caller holding a Node could change its data or links
    // and break the BST rule.
    // Empty tree returns false (the real answer, not a fake value), so no exception.
    // Variables: Node current (the lecture's t), starts at root.
    // No recursion, so it works for any height.
    public boolean searchIterative(int key) {
        Node current = root;
        while (current != null) {
            if (key == current.data) {
                return true;
            }
            if (key < current.data) {
                current = current.lchild;
            } else {
                current = current.rchild;
            }
        }
        return false;
    }

    // Lecture 2. The same answer as searchIterative, with recursion (the lecture's RSearch).
    // The public wrapper only calls the private helper with root.
    // One stack frame per level, so a very deep skewed tree can hit StackOverflowError.
    public boolean searchRecursive(int key) {
        return searchRecursive(root, key);
    }

    // is key in the subtree that starts at node? node == null means an empty subtree
    private boolean searchRecursive(Node node, int key) {
        if (node == null) {
            return false;
        }
        if (key == node.data) {
            return true;
        }
        if (key < node.data) {
            return searchRecursive(node.lchild, key);
        }
        return searchRecursive(node.rchild, key);
    }

    // Lecture 3. Adds key at its place, so the BST rule still holds. A new key always becomes a leaf.
    // Returns true if key was added, false if key was already in the tree (the tree does not change),
    // like java.util.Set.add. The lecture only returns on a duplicate; the boolean tells the caller which one happened.
    // Adds 1 to size only when a node is added.
    // Empty tree: the new node becomes root.
    // Variables: Node current (the lecture's t), Node parent (the lecture's tail pointer r, one step behind current),
    // Node newNode.
    // No recursion, so it works for any height.
    public boolean insertIterative(int key) {
        if (root == null) {
            root = new Node(key);
            size++;
            return true;
        }
        Node current = root;
        Node parent = null;
        while (current != null) {
            if (key == current.data) {
                return false;
            }
            parent = current;
            if (key < current.data) {
                current = current.lchild;
            } else {
                current = current.rchild;
            }
        }
        Node newNode = new Node(key);
        if (key < parent.data) {
            parent.lchild = newNode;
        } else {
            parent.rchild = newNode;
        }
        size++;
        return true;
    }

    // Lectures 4 and 5. The same result as insertIterative (same shape, same return value), with recursion
    // (the lecture's RInsert).
    // The helper returns a Node, not a boolean, so the wrapper finds out what happened from size:
    // the helper adds 1 to size only when it makes a new node. The wrapper stores the helper's result in root.
    // One stack frame per level, so a very deep skewed tree can hit StackOverflowError.
    public boolean insertRecursive(int key) {
        int sizeBefore = size;
        root = insertRecursive(root, key);
        return sizeBefore + 1 == size;
    }

    // inserts key into the subtree that starts at node and returns the root of that subtree after the insert
    // node == null means an empty subtree
    private Node insertRecursive(Node node, int key) {
        if (node == null) {
            size++;
            return new Node(key);
        }
        if (key < node.data) {
            node.lchild = insertRecursive(node.lchild, key);
        } else if (key > node.data) {
            node.rchild = insertRecursive(node.rchild, key);
        }
        return node;
    }

    // From m29. preorder: visit node, then lchild's subtree, then rchild's subtree.
    // Returns the data in visit order as a new list; empty tree returns an empty list.
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

    // From m29. inorder: lchild's subtree, then visit node, then rchild's subtree.
    // For a BST the result is sorted ascending, so the demo uses it to check the BST rule.
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

    // From m29. Prints one line per node, in level order:
    //   9: lchild 5, rchild 15
    //   5: lchild 3, rchild 8
    // "-" means no child (childText). Empty tree prints nothing.
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

    // "-" for null, otherwise the node's data as text
    private static String childText(Node child) {
        if (child == null) {
            return "-";
        }
        return String.valueOf(child.data);
    }
}
