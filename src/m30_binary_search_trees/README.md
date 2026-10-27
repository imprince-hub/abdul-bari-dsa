# 30. Binary Search Trees

Package: `m30_binary_search_trees` · 13 lectures · 5 done

Put this line at the top of every file in this folder:

```java
package m30_binary_search_trees;
```

## Introduction and Basic Operations in BST

- [x] Introduction to Binary Search Tree
- [x] Searching in BST
- [x] Inserting in BST
- [x] Creating a BST
- [x] Creating a Binary Search Tree - Solution

## Deletion and Advanced BST Operations

- [ ] Deleting from BST
- [ ] Deleting from BST Program
- [ ] Deleting from BST Solution
- [ ] BST From Traversals
- [ ] Number of BST from n Nodes

## Challenges and Implementation

- [ ] Drawbacks of BST
- [ ] Class for BST
- [ ] Class for BST Program

## Code in this folder

| File | What it has |
|------|-------------|
| `Node.java` | One node in linked representation: `lchild`, `data` (the key), `rchild` (copy of the m29 `Node`) |
| `BinarySearchTree.java` | Introduction and Basic Operations in BST. Copied from m29: `isEmpty()`, `size()`, `display()`, `childText`, `preorderRecursive()`, `inorderRecursive()`. Done, lecture 3: `insertIterative(key)` walks down from `root` with `current` and keeps `parent` one step behind (the lecture's tail pointer `r`). Equal key returns `false` and changes nothing; otherwise the new node is linked as `parent`'s lchild or rchild, `size` goes up by 1 and it returns `true`. Empty tree: the new node becomes `root`. No recursion, so 20,000 sorted keys (a 20,000-level skewed tree) and 1,000,000 shuffled keys both work. Done, lecture 2: `searchIterative(key)` and `searchRecursive(key)` return `true` if the key is in the tree. Both follow one path down from `root` and make one move per step: equal key returns `true`, a smaller key goes to lchild, otherwise rchild; reaching `null` returns `false` (also for an empty tree). The recursive helper returns `false` for `null`; its recursive call is the last thing it does, so nothing happens in the returning phase. Tested on 1,000,000 shuffled even keys, searching every number from 0 to 2,000,001: both give the right answer every time. A 50,000-level skewed tree works with the default stack; with a small stack (`-Xss256k`) `searchRecursive` throws `StackOverflowError` while `searchIterative` still works. Done, lectures 4 and 5: the constructor `BinarySearchTree(int[] keys)` throws `NullPointerException("keys must not be null")` for `null`, then gives every key to `insertIterative` in order, so the empty tree, `size` and duplicates are all handled in one place (`{}` gives an empty tree, duplicates are skipped). It does not search before inserting, because `insertIterative` already finds duplicates; searching first walked the tree two times per key. The class is `final`: the constructor calls the public `insertIterative`, and a subclass could override it before the object is ready (javac `-Xlint:all` gives a `this-escape` warning without `final`). Done, lecture 4 (the lecture's `RInsert`): `insertRecursive(key)` gives the same shape, `size` and return value as `insertIterative`. The private helper `insertRecursive(Node node, int key)` returns the root of the subtree after the insert: for `null` it adds 1 to `size` and returns a new node; a smaller key goes to `node.lchild = insertRecursive(node.lchild, key)`, a bigger key to the rchild in the same way, and an equal key changes nothing; then it returns `node`. Each parent links the returned subtree root to itself in the returning phase, so only the parent of the old `null` gets a new link. The wrapper stores the helper's result in `root` (this is how an empty tree gets its root), and returns `true` only if `size` went up by 1. Tested from an empty tree on the 7 demo arrays and `{7, 7, 7}` against `insertIterative`, and on 1,000,000 shuffled keys. A 50,000-level skewed tree gives `StackOverflowError` in the calling phase, before any link changes, so the tree and `size` stay the same |
| `BinarySearchTreeDemo.java` | `main()` calls `demoInsertIterative()`, `demoCreate()`, `demoSearch()`, `demoInsertRecursive()` and `demoLargeTree()`, in that order. Expected output is in the comments above each method, and the real output matches it. `demoInsertIterative()` inserts 30, 20, 40, 20, 30, 35 one by one and prints each result and `size` (the two repeats give `false`), then preorder, inorder and `display()`. `demoKeyArrays()` returns the 7 test arrays used by both `demoCreate()` and `demoInsertRecursive()`: the lecture's example `{9, 15, 5, 20, 16, 8, 12, 3, 6}`, duplicates, sorted (right-skewed), reverse sorted (left-skewed), negatives with `Integer.MIN_VALUE`/`MAX_VALUE`, one key, and `{}`. `showTree(keys)` prints the keys, `size`, preorder, inorder and `display()` of the constructor's tree; `demoCreate()` ends with `null` keys, which prints the `NullPointerException` message. `demoSearch()` uses `showSearch(tree, key)`, which prints `searchIterative` and `searchRecursive` on one line, for 4 keys in the lecture's tree, 4 keys not in it and one key on an empty tree. `showInsertRecursive(keys)` builds the tree with `insertRecursive` from an empty tree and prints the results, `size` and whether its preorder equals the constructor's. `demoLargeTree()` builds a tree from `shuffledRange(1_000_000, 30)` (Fisher-Yates with a fixed seed, so every run is the same), checks that the inorder equals `rangeList(1_000_000)` (`[1, 2, ..., n]`) and searches 5 keys. The demo bodies were written by Claude on request |

[Back to all modules](../../README.md)
