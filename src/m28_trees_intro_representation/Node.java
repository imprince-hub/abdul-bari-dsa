package m28_trees_intro_representation;

// Lecture 10. One node of a binary tree in linked representation: lchild | data | rchild.
public class Node {
    public Node lchild;
    public int data;
    public Node rchild;

    public Node(int data) {
        this.data = data;
    }
}
