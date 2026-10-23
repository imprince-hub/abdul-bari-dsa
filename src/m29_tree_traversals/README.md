# 29. Tree Traversals

Package: `m29_tree_traversals` · 20 lectures · 9 done

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

- [x] Create Tree from Keyboard
- [x] Create Tree from Keyboard - Solution
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
| `BinaryTree.java` | From m28: the level-order `Integer[]` constructor (`null` means no child), `isEmpty()`, `size()`, `display()` and the helpers `makeChild`, `checkNoValuesLeft`, `childText`. Since lecture 8 every queue here is a `java.util.ArrayDeque<Node>` (used as `Queue<Node>`) instead of m26's `GenericCircularQueue`, because that queue has a fixed capacity and the keyboard constructor cannot know the number of nodes before they are typed. New, lectures 1 and 2: `countRecursive()` counts the nodes by visiting the tree, without reading the `size` field. The public wrapper only calls the private helper with `root`. The helper returns 0 for `null` (this base case also covers the empty tree, so the wrapper needs no `isEmpty()` check) and otherwise `1 + count(lchild) + count(rchild)`, added in the returning phase. One stack frame per level, so a very deep skewed tree hits `StackOverflowError` (around 50,000 levels in a test with the default stack size). New, lectures 3 to 7: `preorderRecursive()`, `inorderRecursive()` and `postorderRecursive()` return the data in visit order as a new `List<Integer>`. They return the list instead of printing, so the iterative versions in later lectures can be checked with `equals`. Each public wrapper makes an `ArrayList`, calls the private helper with `root` and returns the list. The helper does nothing for `null`; otherwise it adds `node.data` before (pre), between (in) or after (post) the two recursive calls. An empty tree gives `[]` without an `isEmpty()` check, the same as `countRecursive`. New, lectures 8 and 9: `BinaryTree(Scanner input, PrintStream prompts)` builds the tree level by level from typed values, like the lecture's create. `input` is where the values come from (`System.in`, or a String in the demo) and `prompts` is where the questions are printed (`System.out`, or a stream that prints nothing), so the same constructor works for the keyboard and for fixed test inputs. `-` means no child, so `-1` stays a normal value. Every node that is made gets two questions (lchild and rchild), and the tree is complete when the queue is empty; values after that are not read. Root `-` gives an empty tree. Every value goes through `makeChild`, the same as the array constructor. `readValue(input, prompts, prompt)` prints the question, reads one token and returns `null` for `-`, otherwise `Integer.parseInt(token)`; it throws `NoSuchElementException("Input ended before the tree was complete")` if no token is left, and a bad token gives `NumberFormatException` |
| `TreeTraversalsDemo.java` | `main()` that calls `demoCountNodes()`, `demoTraversals()`, `demoCreateFromKeyboard()` and `demoTypeYourOwnTree()`, in that order. `showCount(levelOrder)` prints the array, then `countRecursive` and `size` on one line, which must match. Arrays: `{1..7}` (full tree), `{10, 20, 30, null, 40, 50, null, -1, null, null, 60}` (missing children, `-1` as a value), `{1, 2, null, 3, null, 4}` (left-skewed), `{1, null, 2, null, 3}` (right-skewed), `{7}` and `{}`. Then a complete tree of 1,000,000 nodes from `rangeArray(n)`, which returns `{1, 2, ..., n}`. `demoTraversals()` uses the same 6 arrays with `showTraversals(levelOrder)`, which prints the array, then `preorder`, `inorder` and `postorder` on separate lines (expected output is in the comments above `demoTraversals`). `demoCreateFromKeyboard()` builds trees from fixed inputs with `showKeyboardTree(input)`, which prints the input, `size` and `display()` and uses `silentPrompts()` so no questions are printed: a full tree, the m28 example tree (checked against the array tree by comparing preorder and inorder), one node, `-` (empty tree) and `5 - - 99` (99 is never read). Then the errors for `1 2` (input ends early) and `1 x -` (not a number). `demoTypeYourOwnTree()` runs last and reads a tree from the real keyboard, so the demo waits for typing; type `-` for the root to skip. A typing mistake or the input ending prints a message instead of crashing |

[Back to all modules](../../README.md)
