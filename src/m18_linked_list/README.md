# 18. Linked List

Package: `m18_linked_list` · 17 lectures · 17 done

Put this line at the top of every file in this folder:

```java
package m18_linked_list;
```

## Introduction and Traversal

- [x] Basics of Linked List
- [x] Introduction to Linked List
- [x] Traversing a Linked List
- [x] Creating a Linked List

## Recursive Traversal

- [x] Recursive Traversal
- [x] Recursive Traversal - Solution
- [x] Recursive Traversal Examples
- [x] Recursive Traversal Examples - Solution

## Insertion and Deletion Operations

- [x] LinkedList Insert
- [x] LinkedList Insert Solution
- [x] Deleting a Node in Linked List
- [x] Deleting a Node in Linked List - Solution

## Searching, Improvements, and Class Implementation

- [x] LinkedList Linear Search
- [x] LinkedList Linear Search - Solution
- [x] LinkedList Improving Linear Search
- [x] LinkedList Improving Linear Search - Solution
- [x] LinkedList Class

## Code in this folder

| File | What it has |
|------|-------------|
| `Node.java` | One node: `data`, `next`, and a constructor |
| `SinglyLinkedList.java` | `create(int[])`, `insert(index, data)`, `delete(index)`, `searchMoveToHead(key)`. Iterative: `display()`, `length()`, `sum()`, `max()`, `min()`, `search(key)`. Recursive: `displayRecursive()`, `displayReverseRecursive()`, `lengthRecursive()`, `sumRecursive()`, `maxRecursive()`, `minRecursive()`, `searchRecursive(key)` |
| `GenericLinkedList.java` | Same operations as `SinglyLinkedList` for any `T extends Comparable<T>` (no `sum`), with a private nested `Node<T>`. Rejects `null` elements |
| `LinkedListDemo.java` | `main()` that prints each iterative and recursive result side by side, then builds a second list with `insert`, removes a node with `delete`, and runs the three searches, then uses `GenericLinkedList` with `Integer` and `String` |

[Back to all modules](../../README.md)
