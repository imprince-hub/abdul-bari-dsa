package m29_tree_traversals;

// One entry on postorderIterative's stack (lectures 12 and 13): a node and the lecture's mark.
// secondTime is false when the node is pushed on the way down, and true when it is pushed again
// before going to its rchild (the C version stores the negative address for this).
// Both fields are final: an entry never changes. To change the mark, push a new entry.
public class StackEntry {
    public final Node node;
    public final boolean secondTime;

    public StackEntry(Node node, boolean secondTime) {
        this.node = node;
        this.secondTime = secondTime;
    }
}
