package m30_binary_search_trees;

// One node of a binary search tree in linked representation: lchild | data | rchild (copy of the m29 Node).
// data holds the key.
public class Node {
    public Node lchild;
    public int data;
    public Node rchild;

    public Node(int data) {
        this.data = data;
    }
}
