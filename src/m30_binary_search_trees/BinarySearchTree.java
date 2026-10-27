package m30_binary_search_trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
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
// New in m30, Deletion and Advanced BST Operations:
//   deleteRecursive, with inorderPredecessor and inorderSuccessor (lectures 6 to 8)
//   fromPreorder, with isStrictlyAscending (lecture 9)
//   Also copied from m29 for these: heightRecursive (deleteRecursive uses it)
//   and inorderIterative (fromPreorder uses it).
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

    // Lectures 6 to 8. Deletes key the lecture's way (the lecture's Delete, with InPre and InSucc).
    // Returns true if key was found and deleted, false if key is not in the tree (the tree does not change),
    // like java.util.Set.remove. Empty tree returns false.
    // The same wrapper and helper pattern as insertRecursive: the wrapper stores the helper's result in root
    // and finds out what happened from size (the helper takes 1 from size only when it removes a node).
    // Storing the result in root also covers deleting the last node (the lecture's "if p == root, root = NULL").
    // One stack frame per level, so a very deep skewed tree can hit StackOverflowError.
    // Cost: the height checks visit the whole subtree of the node that holds key, so deleting near the root
    // of a large tree visits almost every node. This is the lecture's method; AVL trees (m31) keep the height
    // inside the node instead.
    public boolean deleteRecursive(int key) {
        int sizeBefore = size;
        root = deleteRecursive(root, key);
        return sizeBefore - 1 == size;
    }

    // deletes key from the subtree that starts at node and returns the root of that subtree after the delete
    // node == null means an empty subtree, so key is not here
    // When node holds key:
    //   node is a leaf: node is removed (take 1 from size) and its parent gets null
    //   otherwise node stays, but its data is replaced: by its inorder predecessor's data if its lchild's
    //   subtree is higher than its rchild's subtree (heightRecursive), otherwise by its inorder successor's data.
    //   Then that copied key is deleted from the subtree it was copied from.
    // Variables: Node predecessor, Node successor (the lecture's q).
    private Node deleteRecursive(Node node, int key) {
        if (node == null) {
            return null;
        }
        if (key < node.data) {
            node.lchild = deleteRecursive(node.lchild, key);
        } else if (key > node.data) {
            node.rchild = deleteRecursive(node.rchild, key);
        } else if (node.lchild == null && node.rchild == null) {
            size--;
            return null;
        } else if (heightRecursive(node.lchild) > heightRecursive(node.rchild)) {
            Node predecessor = inorderPredecessor(node);
            node.data = predecessor.data;
            node.lchild = deleteRecursive(node.lchild, predecessor.data);
        } else {
            Node successor = inorderSuccessor(node);
            node.data = successor.data;
            node.rchild = deleteRecursive(node.rchild, successor.data);
        }
        return node;
    }

    // The lecture's InPre. Returns node's inorder predecessor: the node with the biggest key in node's
    // lchild's subtree (the rightmost node there).
    // Only called when node.lchild != null; deleteRecursive's height rule makes sure of that.
    private Node inorderPredecessor(Node node) {
        Node current = node.lchild;
        while (current.rchild != null) {
            current = current.rchild;
        }
        return current;
    }

    // The lecture's InSucc. Returns node's inorder successor: the node with the smallest key in node's
    // rchild's subtree (the leftmost node there).
    // Only called when node.rchild != null; deleteRecursive's height rule makes sure of that.
    private Node inorderSuccessor(Node node) {
        Node current = node.rchild;
        while (current.lchild != null) {
            current = current.lchild;
        }
        return current;
    }

    // Lecture 9. Builds the BST whose preorder is the given array, in one pass with a stack (the lecture's method),
    // instead of inserting the keys one by one. Every key is placed by looking only at current and the stack,
    // so it takes O(n) steps even for sorted input, where the constructor takes O(n^2).
    // A static factory method, not a constructor, because BinarySearchTree(int[] keys) already takes an int[].
    // It makes an empty tree with new BinarySearchTree() and sets that tree's root and size.
    // Variables: BinarySearchTree tree, Deque<Node> stack (ArrayDeque: push, pop, peek, isEmpty),
    // Node current (the lecture's p), Node newNode (the lecture's t), int index (the lecture's i).
    // The stack's top node is the upper limit for a key that goes to current's rchild. When the stack is empty
    // there is no upper limit. Do not use Integer.MAX_VALUE as the limit (like the lecture's 32767):
    // MAX_VALUE can be a real key, and {0, -5, Integer.MAX_VALUE} would then fail.
    //
    // Cases:
    //   preorder null throws NullPointerException("preorder must not be null")
    //   {} gives an empty tree
    //   input that is not the preorder of a BST with distinct keys throws
    //   IllegalArgumentException("not the preorder of a BST with distinct keys"). Two checks find it:
    //     the lecture's pop step finds the stack empty (happens for a repeated key, like {5, 5}), or
    //     after building, inorderIterative() is not strictly ascending (isStrictlyAscending), like {5, 3, 8, 4}
    public static BinarySearchTree fromPreorder(int[] preorder) {
        throw new UnsupportedOperationException("TODO: lecture 9");
    }

    // true if every value is smaller than the next one; [] and [x] give true
    private static boolean isStrictlyAscending(List<Integer> values) {
        throw new UnsupportedOperationException("TODO: lecture 9");
    }

    // From m29. Height as the number of levels: empty tree 0, one node 1, {9, 15, 5, 20, 16, 8, 12, 3, 6} 4.
    // deleteRecursive uses the helper to choose between the inorder predecessor and the inorder successor.
    public int heightRecursive() {
        return heightRecursive(root);
    }

    // number of levels in the subtree that starts at node; node == null means an empty subtree
    private int heightRecursive(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(heightRecursive(node.lchild), heightRecursive(node.rchild));
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

    // From m29. The same list as inorderRecursive, but with its own stack instead of the call stack,
    // so it works for any height. fromPreorder uses it to check the tree it built, because a sorted
    // preorder gives a very deep skewed tree.
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
