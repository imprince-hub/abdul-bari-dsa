package m31_avl_trees;

// One node of an AVL tree in linked representation: lchild | data | height | rchild (the lecture's struct Node).
// It is the m30 Node plus height. data holds the key.
// height is the number of levels in the subtree that starts at this node: a leaf has height 1, and an empty
// subtree (null) counts as 0. This is the same counting as heightRecursive in m29 and m30.
// The node stores its own height, so AvlTree can get a balance factor without walking down the subtree.
public class Node {
    public Node lchild;
    public int data;
    public int height;
    public Node rchild;

    // a new node is always a leaf, so its height starts at 1 (the lecture's t->height = 1)
    public Node(int data) {
        this.data = data;
        this.height = 1;
    }
}
