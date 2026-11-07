package m31_avl_trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// AVL tree in linked representation, used for the m31 lectures.
// An AVL tree is a binary search tree (the same BST rule as m30, no duplicate keys) that keeps itself balanced:
// for every node, balance factor = height of lchild's subtree - height of rchild's subtree, and it must be -1, 0 or 1.
// After an insert, every node on the path back up to root is checked in the returning phase. The first node whose
// balance factor becomes 2 or -2 is fixed with one rotation (LL, LR, RR or RL). That rotation brings the subtree back
// to its old height, so the nodes above it are balanced again too.
// After a delete the same check runs on the way back up, but a rotation can leave the subtree 1 level shorter than
// before, so a node higher up can reach 2 or -2 as well. A delete can therefore rotate at more than one level.
// Each node stores its height, so a balance factor takes O(1) steps. The tree height stays about log2(n),
// so recursion is safe here even for 1,000,000 sorted keys (20 levels).
// From m30 (your code, copied): isEmpty, size, searchIterative, preorderRecursive, inorderRecursive,
// heightRecursive, childText.
// New in m31, Introduction and Basic Operations in AVL Trees (lectures 1 to 4, theory)
// and AVL Tree Insert Program (lectures 5 and 6):
//   storedHeight, nodeHeight, balanceFactor (the lecture's NodeHeight and BalanceFactor)
//   llRotation, lrRotation, rrRotation, rlRotation (the lecture's LLRotation, LRRotation, RRRotation, RLRotation)
//   rebalance
//   insertRecursive (the lecture's RInsert) and the constructor AvlTree(int[] keys)
//   height, display (m30's display plus height and balance factor), isValidAvl with isStrictlyAscending
// New in m31, AVL Tree Operations and Properties (lectures 7 and 8):
//   deleteRecursive (the lecture's Delete with rotations)
//   inorderPredecessor and inorderSuccessor (from m30, now static)
public final class AvlTree {
    private Node root;
    private int size; // number of nodes

    // empty tree; add keys with insertRecursive
    public AvlTree() {
    }

    // Lecture 4 (Creating AVL Tree). Creates the AVL tree by inserting the keys one by one, in the given order,
    // with insertRecursive. Unlike m30, sorted input does not give a skewed tree, because every insert rebalances.
    // The class is final for the same reason as m30's BinarySearchTree: this constructor calls a public method.
    //
    // Cases:
    //   keys null throws NullPointerException("keys must not be null")
    //   {} gives an empty tree
    //   a duplicate key is skipped, the same as insertRecursive, so size can be less than keys.length
    public AvlTree(int[] keys) {
        if (keys == null) {
            throw new NullPointerException("keys must not be null");
        }
        for (int key : keys) {
            insertRecursive(key);
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    // Lectures 5 and 6. Height of the whole tree in levels, read from root's stored height, so O(1).
    // Empty tree returns 0 (the real answer, not a fake value).
    // Must always equal heightRecursive(), which walks every node; the demo checks that.
    public int height() {
        return storedHeight(root);
    }

    // From m30. Returns true if key is in the tree. Search in an AVL tree is the same as in a BST:
    // rotations change the shape but never break the BST rule.
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

    // Lectures 5 and 6 (the lecture's RInsert). Adds key at its place like m30's insertRecursive, then rebalances
    // every node on the way back up.
    // Returns true if key was added, false if key was already in the tree (the tree does not change).
    // Same wrapper pattern as m30: the wrapper stores the helper's result in root (so a rotation at root gives the
    // tree its new root) and returns true only if size went up by 1.
    // One stack frame per level, but the height is about log2(n) here (20 levels for 1,000,000 keys),
    // so even sorted input cannot cause StackOverflowError.
    public boolean insertRecursive(int key) {
        int sizeBefore = size;
        root = insertRecursive(root, key);
        return sizeBefore + 1 == size;
    }

    // inserts key into the subtree that starts at node and returns the root of that subtree after the insert
    // (a different node if a rotation happened)
    // node == null: add 1 to size and return a new Node (its height is 1)
    // key smaller or bigger: insert into lchild's or rchild's subtree and link the result back, the same as m30,
    // then return rebalance(node)
    // key equal: nothing changed below, so return node as it is (no rebalance needed)
    private Node insertRecursive(Node node, int key) {
        if (node == null) {
            size++;
            return new Node(key);
        }
        if (key == node.data) {
            return node;
        }
        if (key < node.data) {
            node.lchild = insertRecursive(node.lchild, key);
        } else {
            node.rchild = insertRecursive(node.rchild, key);
        }
        return rebalance(node);
    }

    // Lectures 7 and 8 (the lecture's Delete with rotations). Deletes key the same way as m30's deleteRecursive,
    // then rebalances every node on the way back up, like insertRecursive.
    // Returns true if key was found and deleted, false if key is not in the tree (the tree does not change),
    // like java.util.Set.remove. Empty tree returns false.
    // Same wrapper pattern as insertRecursive: the wrapper stores the helper's result in root (a rotation at root,
    // or deleting the last node, changes root) and returns true only if size went down by 1.
    // Unlike an insert, one delete can need rotations at more than one level (see the comment at the top of the
    // class). rebalance runs on every node of the path, so each of them is fixed. The path has about log2(n) nodes,
    // so a delete is still O(log n).
    public boolean deleteRecursive(int key) {
        int sizeBefore = size;
        root = deleteRecursive(root, key);
        return sizeBefore - 1 == size;
    }

    // deletes key from the subtree that starts at node and returns the root of that subtree after the delete
    // (a different node if a rotation happened, null if the subtree is now empty)
    // node == null: key is not in this subtree, return null
    // key smaller or bigger: delete from lchild's or rchild's subtree and link the result back, the same as m30,
    // then return rebalance(node)
    // key equal:
    //   node is a leaf: take 1 from size and return null (its parent gets null). No rebalance: nothing is left here.
    //   otherwise node stays, but its data is replaced, with m30's rule but stored heights instead of
    //   heightRecursive (so O(1) instead of walking the subtree): by its inorder predecessor's data if
    //   storedHeight(node.lchild) > storedHeight(node.rchild), otherwise by its inorder successor's data.
    //   Then delete that copied key from the subtree it was copied from, link the result back,
    //   and return rebalance(node).
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
        } else if (storedHeight(node.lchild) > storedHeight(node.rchild)) {
            Node predecessor = inorderPredecessor(node);
            node.data = predecessor.data;
            node.lchild = deleteRecursive(node.lchild, predecessor.data);
        } else {
            Node successor = inorderSuccessor(node);
            node.data = successor.data;
            node.rchild = deleteRecursive(node.rchild, successor.data);
        }
        return rebalance(node);
    }

    // From m30 (your code, copied; now static because it uses no fields). The lecture's InPre.
    // Returns node's inorder predecessor: the node with the biggest key in node's lchild's subtree
    // (the rightmost node there).
    // Only called when node.lchild != null: deleteRecursive only asks for it when lchild's stored height is bigger
    // than rchild's, so lchild's height is at least 1.
    private static Node inorderPredecessor(Node node) {
        Node current = node.lchild;
        while (current.rchild != null) {
            current = current.rchild;
        }
        return current;
    }

    // From m30 (your code, copied; now static because it uses no fields). The lecture's InSucc.
    // Returns node's inorder successor: the node with the smallest key in node's rchild's subtree
    // (the leftmost node there).
    // Only called when node.rchild != null: node is not a leaf, and lchild's stored height is not bigger than
    // rchild's, so if rchild were null, lchild would be null too.
    private static Node inorderSuccessor(Node node) {
        Node current = node.rchild;
        while (current.lchild != null) {
            current = current.lchild;
        }
        return current;
    }

    // Lectures 5 to 8. Called on every node on the insert or delete path, in the returning phase (lowest node first).
    // First store node's new height (nodeHeight), then check its balance factor:
    //   2 and balanceFactor(node.lchild) >= 0: llRotation
    //   2 and anything else (lchild's balance factor is -1): lrRotation
    //   -2 and balanceFactor(node.rchild) <= 0: rrRotation
    //   -2 and anything else (rchild's balance factor is 1): rlRotation
    //   -1, 0 or 1: no rotation
    // Returns the root of this subtree after that (node itself when there is no rotation).
    // The lecture checks lchild's balance factor == 1 (and rchild's == -1). After an insert the child's balance
    // factor is always 1 or -1 at this point, so both give the same result. >= 0 and <= 0 also cover 0, which can
    // only happen after a delete, so deleteRecursive uses this same method. The delete lectures name the six cases
    // by the side that is too high and the child's balance factor: L1 and L0 use llRotation, L-1 uses lrRotation,
    // R-1 and R0 use rrRotation, R1 uses rlRotation.
    // A balance factor of 3 or -3 cannot happen: every node is fixed as soon as it reaches 2 or -2.
    private static Node rebalance(Node node) {
        node.height = nodeHeight(node);
        if (balanceFactor(node) == 2 && balanceFactor(node.lchild) >= 0) {
            return llRotation(node);
        }
        if (balanceFactor(node) == 2 && balanceFactor(node.lchild) < 0) {
            return lrRotation(node);
        }
        if (balanceFactor(node) == -2 && balanceFactor(node.rchild) <= 0) {
            return rrRotation(node);
        }
        if (balanceFactor(node) == -2 && balanceFactor(node.rchild) > 0) {
            return rlRotation(node);
        }
        return node;
    }

    // Lectures 3, 5 and 6 (the lecture's LLRotation). Called when node's balance factor is 2 and its lchild's is
    // 1 or 0: the left side of the left child is too high, so one rotation to the right fixes it.
    // Variables: Node left (the lecture's pl, node.lchild), Node leftRight (the lecture's plr, left.rchild, can be null).
    // After it: left is the root of this subtree, node becomes left's rchild, and leftRight becomes node's lchild.
    // Then store the new heights with nodeHeight: node first (it is now below left), then left.
    // Returns left. The caller links it to the parent, so there is no "if (root == p) root = pl" here like in the
    // lecture: the insertRecursive wrapper stores the final result in root.
    private static Node llRotation(Node node) {
        Node left = node.lchild;
        node.lchild = left.rchild;
        left.rchild = node;
        node.height = nodeHeight(node);
        left.height = nodeHeight(left);
        return left;
    }

    // Lectures 3, 5 and 6 (the lecture's LRRotation). Called when node's balance factor is 2 and its lchild's is -1:
    // the right side of the left child is too high.
    // Variables: Node left (the lecture's pl, node.lchild), Node leftRight (the lecture's plr, left.rchild,
    // never null here).
    // After it: leftRight is the root of this subtree, with left as its lchild and node as its rchild.
    // leftRight's old lchild becomes left's rchild, and leftRight's old rchild becomes node's lchild.
    // Then store the new heights: left and node first (both are now below leftRight), then leftRight.
    // Returns leftRight.
    private static Node lrRotation(Node node) {
        Node left = node.lchild;
        Node leftRight = left.rchild;
        left.rchild = leftRight.lchild;
        node.lchild = leftRight.rchild;
        leftRight.lchild = left;
        leftRight.rchild = node;
        left.height = nodeHeight(left);
        node.height = nodeHeight(node);
        leftRight.height = nodeHeight(leftRight);
        return leftRight;
    }

    // Lectures 3, 5 and 6 (the lecture's RRRotation). The mirror of llRotation. Called when node's balance factor
    // is -2 and its rchild's is -1 or 0: one rotation to the left.
    // Variables: Node right (the lecture's pr, node.rchild), Node rightLeft (the lecture's prl, right.lchild,
    // can be null).
    // After it: right is the root of this subtree, node becomes right's lchild, and rightLeft becomes node's rchild.
    // Then store the new heights: node first, then right.
    // Returns right.
    private static Node rrRotation(Node node) {
        Node right = node.rchild;
        node.rchild = right.lchild;
        right.lchild = node;
        node.height = nodeHeight(node);
        right.height = nodeHeight(right);
        return right;
    }

    // Lectures 3, 5 and 6 (the lecture's RLRotation). The mirror of lrRotation. Called when node's balance factor
    // is -2 and its rchild's is 1.
    // Variables: Node right (the lecture's pr, node.rchild), Node rightLeft (the lecture's prl, right.lchild,
    // never null here).
    // After it: rightLeft is the root of this subtree, with node as its lchild and right as its rchild.
    // rightLeft's old lchild becomes node's rchild, and rightLeft's old rchild becomes right's lchild.
    // Then store the new heights: node and right first, then rightLeft.
    // Returns rightLeft.
    private static Node rlRotation(Node node) {
        Node right = node.rchild;
        Node rightLeft = right.lchild;
        right.lchild = rightLeft.rchild;
        node.rchild = rightLeft.lchild;
        rightLeft.rchild = right;
        rightLeft.lchild = node;
        right.height = nodeHeight(right);
        node.height = nodeHeight(node);
        rightLeft.height = nodeHeight(rightLeft);
        return rightLeft;
    }

    // Lectures 5 and 6. node's stored height, or 0 for null (an empty subtree has 0 levels).
    // nodeHeight and balanceFactor read the children's heights through this, so they need no null checks of their own.
    private static int storedHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // Lectures 5 and 6 (the lecture's NodeHeight). Works out node's height from its children's stored heights:
    // 1 + the bigger of the two. It does not change node.height; the caller stores the result.
    // Only called with node != null. O(1): it never walks down the subtree like heightRecursive does.
    private static int nodeHeight(Node node) {
        return 1 + Math.max(storedHeight(node.lchild), storedHeight(node.rchild));
    }

    // Lectures 5 and 6 (the lecture's BalanceFactor). storedHeight(node.lchild) - storedHeight(node.rchild).
    // Positive: the left side is higher. Negative: the right side is higher. Only called with node != null.
    private static int balanceFactor(Node node) {
        return storedHeight(node.lchild) - storedHeight(node.rchild);
    }

    // Lectures 5 and 6, for the demo's checks. Returns true if the tree follows all three rules:
    //   BST rule: inorderRecursive() is strictly ascending (isStrictlyAscending)
    //   every node's stored height is right (it equals nodeHeight(node))
    //   every node's balance factor is -1, 0 or 1
    // Empty tree returns true. It does not change the tree.
    public boolean isValidAvl() {
        return isStrictlyAscending(inorderRecursive()) && isValidAvl(root);
    }

    // true if every node in the subtree that starts at node has the right stored height and a balance factor of
    // -1, 0 or 1; node == null gives true.
    // Check both children's subtrees first (returning phase), then node itself: nodeHeight(node) reads the
    // children's stored heights, so its answer can only be trusted after the children have passed.
    private static boolean isValidAvl(Node node) {
        if (node == null) {
            return true;
        }
        if (!isValidAvl(node.lchild) || !isValidAvl(node.rchild)) {
            return false;
        }
        return node.height == nodeHeight(node) && Math.abs(balanceFactor(node)) <= 1;
    }

    // true if every value is smaller than the next one; [] and [x] give true (the same method as in m30)
    private static boolean isStrictlyAscending(List<Integer> values) {
        for (int i = 1; i < values.size(); i++) {
            if (values.get(i - 1) >= values.get(i)) {
                return false;
            }
        }
        return true;
    }

    // From m30. Height as the number of levels, found by walking every node, so O(n).
    // The demo compares it with height(), which only reads root's stored height.
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

    // From m30. preorder: visit node, then lchild's subtree, then rchild's subtree.
    // All AVL trees with the same keys have the same inorder (sorted), so the demo compares preorder to check the shape.
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

    // From m30. inorder: lchild's subtree, then visit node, then rchild's subtree.
    // Sorted ascending for a BST, so isValidAvl uses it to check the BST rule.
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

    // Lectures 5 and 6. m30's display, with each node's height and balance factor added. One line per node,
    // in level order:
    //   25 (height 3, bf 0): lchild 20, rchild 40
    //   20 (height 2, bf 0): lchild 10, rchild 22
    // "-" means no child (childText). Empty tree prints nothing.
    public void display() {
        if (isEmpty()) {
            return;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.println(node.data + " (height " + node.height + ", bf " + balanceFactor(node) + "): lchild "
                    + childText(node.lchild) + ", rchild " + childText(node.rchild));
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
