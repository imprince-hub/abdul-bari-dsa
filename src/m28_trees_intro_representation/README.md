# 28. Trees Introduction And Representation

Package: `m28_trees_intro_representation` · 12 lectures · 12 done

Put this line at the top of every file in this folder:

```java
package m28_trees_intro_representation;
```

## Basic Tree Concepts and Binary Trees

- [x] Tree Terminologies
- [x] Binary Tree
- [x] Properties of Binary Trees ( Height vs Nodes)
- [x] Number of Binary Trees

## Advanced Tree Concepts

- [x] Catalan Number
- [x] n-ary Trees

## Tree Representations

- [x] Array Representation
- [x] Array Representation Solution
- [x] Types of Binary Tree
- [x] Linked Representation
- [x] Constructing a Binary Tree
- [x] Binary Tree Create - Program

## Code in this folder

All 12 lectures are complete. Lectures 1 to 9 have no code: 1 to 6 and 9 are theory, and 7 and 8 (array representation: lchild at 2i, rchild at 2i+1, parent at i/2, index 0 empty) were practice questions on paper.

| File | What it has |
|------|-------------|
| `Node.java` | Lecture 10. One node in linked representation: `lchild`, `data`, `rchild` (public fields, like the node classes in earlier modules) |
| `BinaryTree.java` | Lectures 10 to 12. Binary tree built from a level-order `Integer[]` with a queue (m26's `GenericCircularQueue<Node>`), like the lecture's create, but values come from the array instead of the keyboard. `levelOrder[0]` is the root; after that, every two values are the `lchild` and `rchild` of the next node dequeued. `null` means no child and is never enqueued, so `-1` can be a normal value. The constructor makes the root with `makeChild`, then `index` (next unread value) starts at 1 and the loop runs while the queue has a parent and the array has values: one round = one parent = two values, and the `rchild` is read only if the array has not ended after the `lchild`. `makeChild(value, queue)` returns `null` for `null`; otherwise it makes the node, enqueues it, adds 1 to `size` and returns it, so `levelOrder[0] == null` needs no special case. After the loop, `checkNoValuesLeft(levelOrder, index)` throws `IllegalArgumentException("Value x at index i has no parent")` for the first non-null value left, because the queue became empty before it (`{1, null, null, 5}`, `{null, 5}`); nulls left over are fine. `{}` returns before the queue is made (capacity 0 is not allowed), and a `null` array throws `NullPointerException("levelOrder must not be null")`. `isEmpty()` is `root == null`, `size()` returns the node count. `display()` prints one line per node in level order, like `20: lchild -, rchild 40`, using its own queue with capacity `size`; `childText` gives `-` for no child. Empty tree prints nothing |
| `BinaryTreeDemo.java` | `main()` that calls `demoBinaryTreeCreate()`. `showTree(levelOrder)` prints the array, `isEmpty` and `size`, then `display()`. Arrays: `{1..7}` (full tree of height 2), `{10, 20, 30, null, 40, 50, null, -1, null, null, 60}` (missing children, `-1` as a value, and the array ends while `-1` and 60 are still in the queue), `{1, 2}` (array ends before 1's `rchild`), `{1, null, null, null, null}` (extra nulls at the end), `{7}`, `{}` and `{null}`. Then the errors for `{1, null, null, 5}`, `{null, 5}` and a `null` array |

[Back to all modules](../../README.md)
