# 31. AVL Trees

Package: `m31_avl_trees` · 9 lectures · 6 done

Put this line at the top of every file in this folder:

```java
package m31_avl_trees;
```

## Introduction and Basic Operations in AVL Trees

- [x] Idea of AVL Trees
- [x] Introduction to AVL Trees
- [x] AVL Tree Rotations
- [x] Creating AVL Tree

## AVL Tree Operations and Properties

- [x] AVL Tree Insert Program
- [x] Insert AVL Tree - Solution
- [ ] Deleting from AVL Tree
- [ ] Delete AVL Tree - Solution
- [ ] Height of AVL Tree

## Code in this folder

| File | What it has |
|------|-------------|
| `Node.java` | One node in linked representation: `lchild`, `data` (the key), `height`, `rchild` (the m30 `Node` plus `height`). `height` counts levels: a new node is a leaf, so the constructor sets it to 1; an empty subtree counts as 0 |
| `AvlTree.java` | Copied from m30: `isEmpty()`, `size()`, `searchIterative(key)`, `heightRecursive()`, `preorderRecursive()`, `inorderRecursive()`, `childText`. Done, lectures 1 to 6 (1 to 4 are theory): `storedHeight(node)` gives a node's stored height or 0 for `null`, so no other method needs its own null check. `nodeHeight(node)` (the lecture's NodeHeight) is `1 + Math.max` of the children's stored heights and does not change the node. `balanceFactor(node)` (the lecture's BalanceFactor) is left stored height minus right stored height. The four rotations each return the new root of the subtree and store the new heights lower node first: `llRotation` and `rrRotation` change 2 links; `lrRotation` and `rlRotation` change 4 links directly (the lecture's way), moving `leftRight`'s (or `rightLeft`'s) old children before overwriting them. No rotation sets `root`: the wrapper does. `rebalance(node)` stores the node's height, then picks LL (bf 2, lchild bf >= 0), LR (bf 2, lchild bf -1), RR (bf -2, rchild bf <= 0) or RL (bf -2, rchild bf 1); `&&` makes sure a child's bf is only read when that child exists. The `>= 0` and `<= 0` cover bf 0, which only happens after a delete, so delete can reuse it. `insertRecursive(key)` (the lecture's RInsert) uses the m30 wrapper and helper pattern: equal key returns early with no change, otherwise insert left or right and return `rebalance(node)` in the returning phase; the wrapper stores the result in `root` and returns `true` only if `size` went up. The constructor `AvlTree(int[] keys)` throws `NullPointerException("keys must not be null")` for `null` and gives every key to `insertRecursive`, so `{}` is an empty tree and duplicates are skipped. `height()` is `storedHeight(root)`, O(1). `isValidAvl()` checks the BST rule (inorder strictly ascending, `isStrictlyAscending`), then every node's stored height and bf in postorder; the first problem returns `false` straight up. `display()` is m30's level order display with height and bf on each line. Tested: each rotation on hand-built trees (8000 to 12000 random cases each, links, inorder, heights and bf all checked), `rebalance` on all 10 cases, 1,000,000 sorted, reverse sorted and shuffled keys (heights 20, 20 and 24, default stack), 2000 random trees against `java.util.TreeSet` after every insert, and `isValidAvl` on 7 broken trees (all `false`). `lrRotation`, `isValidAvl`, `isStrictlyAscending` and `display` were written by Claude on request; the cleanups in `nodeHeight`, `insertRecursive`, the constructor and `height` were also made by Claude on request |
| `AvlTreeDemo.java` | `main()` calls `demoRotations()`, `demoInsertSteps()`, `demoCreate()`, `demoLargeTrees()` and `demoRandomCheck()`, in that order. Expected output is in the comments above each method, and the real output matches it. `demoRotations()` prints the expected rotation, then `showTree(keys)` for the 4 three-node cases and 4 cases where a subtree moves. `demoInsertSteps()` inserts 40, 20, 10, 25, 30, 22, 50 one by one (all 4 rotations; the root changes to 25 at 22), then 25 again (`false`), then `display()`. `demoCreate()` calls `showTree` for the 7 arrays in `demoKeyArrays()`, then `null`. `demoLargeTrees()` builds 1,000,000 sorted keys (`rangeArray`) and 1,000,000 shuffled keys (`shuffledRange(n, 30)`) and prints size, `height()`, `heightRecursive()`, inorder check and `isValidAvl()` for each (`showLargeTree`), then 5 searches. `demoRandomCheck()` compares 2000 random trees with `java.util.TreeSet` (`new Random(31)`): 0 failures, max height 7. Copied from m30: `shuffledRange(n, seed)` and `rangeList(n)`. The demo bodies were written by Claude on request |

[Back to all modules](../../README.md)
