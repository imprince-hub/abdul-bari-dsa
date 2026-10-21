# 29. Tree Traversals

Package: `m29_tree_traversals` · 20 lectures · 7 done

Put this line at the top of every file in this folder:

```java
package m29_tree_traversals;
```

## Tree Traversals (Basics and Program)

- [x] Counting Nodes in a Binary Tree
- [x] Counting Nodes in a Binary Tree - Solution
- [x] Tree Traversals - 1
- [x] Tree Traversals - 2
- [x] Tree Traversals - 3
- [x] Tree Traversals - Program
- [x] Tree Traversals - Solutions

## Tree Creation and Iterative Traversals

- [ ] Create Tree from Keyboard
- [ ] Create Tree from Keyboard - Solution
- [ ] Iterative Tree Traversal
- [ ] Iterative Tree Traversal - Solution
- [ ] Iterative Tree Traversal - Post Order
- [ ] Iterative Tree Traversal - Post Order Solution

## Tree Properties and Advanced Operations

- [ ] Height of a Binary Tree
- [ ] Height of a Binary Tree - Solution
- [ ] Deleting a Binary Tree
- [ ] Level Order Traversal
- [ ] Level Order Traversal Solution
- [ ] Generating Tree from Traversals
- [ ] Tree Traversal Solutions

## Code in this folder

| File | What it has |
|------|-------------|
| `Node.java` | One node in linked representation: `lchild`, `data`, `rchild` (copy of the m28 `Node`) |
| `BinaryTree.java` | From m28 (copied as-is): the level-order `Integer[]` constructor (built with m26's `GenericCircularQueue<Node>`, `null` means no child), `isEmpty()`, `size()`, `display()` and the helpers `makeChild`, `checkNoValuesLeft`, `childText`. New, lectures 1 and 2: `countRecursive()` counts the nodes by visiting the tree, without reading the `size` field. The public wrapper only calls the private helper with `root`. The helper returns 0 for `null` (this base case also covers the empty tree, so the wrapper needs no `isEmpty()` check) and otherwise `1 + count(lchild) + count(rchild)`, added in the returning phase. One stack frame per level, so a very deep skewed tree hits `StackOverflowError` (around 50,000 levels in a test with the default stack size). New, lectures 3 to 7: `preorderRecursive()`, `inorderRecursive()` and `postorderRecursive()` return the data in visit order as a new `List<Integer>`. They return the list instead of printing, so the iterative versions in later lectures can be checked with `equals`. Each public wrapper makes an `ArrayList`, calls the private helper with `root` and returns the list. The helper does nothing for `null`; otherwise it adds `node.data` before (pre), between (in) or after (post) the two recursive calls. An empty tree gives `[]` without an `isEmpty()` check, the same as `countRecursive` |
| `TreeTraversalsDemo.java` | `main()` that calls `demoCountNodes()`, then `demoTraversals()`. `showCount(levelOrder)` prints the array, then `countRecursive` and `size` on one line, which must match. Arrays: `{1..7}` (full tree), `{10, 20, 30, null, 40, 50, null, -1, null, null, 60}` (missing children, `-1` as a value), `{1, 2, null, 3, null, 4}` (left-skewed), `{1, null, 2, null, 3}` (right-skewed), `{7}` and `{}`. Then a complete tree of 1,000,000 nodes from `rangeArray(n)`, which returns `{1, 2, ..., n}`. `demoTraversals()` uses the same 6 arrays with `showTraversals(levelOrder)`, which prints the array, then `preorder`, `inorder` and `postorder` on separate lines (expected output is in the comments above `demoTraversals`) |

[Back to all modules](../../README.md)
