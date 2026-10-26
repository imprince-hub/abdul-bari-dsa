# 30. Binary Search Trees

Package: `m30_binary_search_trees` · 13 lectures · 3 done

Put this line at the top of every file in this folder:

```java
package m30_binary_search_trees;
```

## Introduction and Basic Operations in BST

- [x] Introduction to Binary Search Tree
- [x] Searching in BST
- [x] Inserting in BST
- [ ] Creating a BST
- [ ] Creating a Binary Search Tree - Solution

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
| `BinarySearchTree.java` | Introduction and Basic Operations in BST. Copied from m29: `isEmpty()`, `size()`, `display()`, `childText`, `preorderRecursive()`, `inorderRecursive()`. Done, lecture 3: `insertIterative(key)` walks down from `root` with `current` and keeps `parent` one step behind (the lecture's tail pointer `r`). Equal key returns `false` and changes nothing; otherwise the new node is linked as `parent`'s lchild or rchild, `size` goes up by 1 and it returns `true`. Empty tree: the new node becomes `root`. No recursion, so 20,000 sorted keys (a 20,000-level skewed tree) and 1,000,000 shuffled keys both work. Done, lecture 2: `searchIterative(key)` and `searchRecursive(key)` return `true` if the key is in the tree. Both follow one path down from `root` and make one move per step: equal key returns `true`, a smaller key goes to lchild, otherwise rchild; reaching `null` returns `false` (also for an empty tree). The recursive helper returns `false` for `null`; its recursive call is the last thing it does, so nothing happens in the returning phase. Tested on 1,000,000 shuffled even keys, searching every number from 0 to 2,000,001: both give the right answer every time. A 50,000-level skewed tree works with the default stack; with a small stack (`-Xss256k`) `searchRecursive` throws `StackOverflowError` while `searchIterative` still works. Done, lectures 4 and 5: the constructor `BinarySearchTree(int[] keys)` throws `NullPointerException("keys must not be null")` for `null`, then gives every key to `insertIterative` in order, so the empty tree, `size` and duplicates are all handled in one place (`{}` gives an empty tree, duplicates are skipped). It does not search before inserting, because `insertIterative` already finds duplicates; searching first walked the tree two times per key. The class is `final`: the constructor calls the public `insertIterative`, and a subclass could override it before the object is ready (javac `-Xlint:all` gives a `this-escape` warning without `final`). To write: `insertRecursive(key)` (lectures 4 and 5) |
| `BinarySearchTreeDemo.java` | Skeleton. `main()` calls `demoInsertIterative()`, `demoCreate()`, `demoSearch()`, `demoInsertRecursive()` and `demoLargeTree()`, in that order. Expected output is in the comments above each method |

[Back to all modules](../../README.md)
