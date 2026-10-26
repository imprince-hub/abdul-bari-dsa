# 30. Binary Search Trees

Package: `m30_binary_search_trees` · 13 lectures · 2 done

Put this line at the top of every file in this folder:

```java
package m30_binary_search_trees;
```

## Introduction and Basic Operations in BST

- [x] Introduction to Binary Search Tree
- [ ] Searching in BST
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
| `BinarySearchTree.java` | Introduction and Basic Operations in BST. Copied from m29: `isEmpty()`, `size()`, `display()`, `childText`, `preorderRecursive()`, `inorderRecursive()`. Done, lecture 3: `insertIterative(key)` walks down from `root` with `current` and keeps `parent` one step behind (the lecture's tail pointer `r`). Equal key returns `false` and changes nothing; otherwise the new node is linked as `parent`'s lchild or rchild, `size` goes up by 1 and it returns `true`. Empty tree: the new node becomes `root`. No recursion, so 20,000 sorted keys (a 20,000-level skewed tree) and 1,000,000 shuffled keys both work. To write: `searchIterative(key)`, `searchRecursive(key)` (lecture 2), `insertRecursive(key)` and the constructor `BinarySearchTree(int[] keys)` (lectures 4 and 5). Search returns `boolean`. Only the constructor throws (`NullPointerException` for `null` keys) |
| `BinarySearchTreeDemo.java` | Skeleton. `main()` calls `demoInsertIterative()`, `demoCreate()`, `demoSearch()`, `demoInsertRecursive()` and `demoLargeTree()`, in that order. Expected output is in the comments above each method |

[Back to all modules](../../README.md)
