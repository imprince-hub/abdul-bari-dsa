# 20. Circular Linked List

Package: `m20_circular_linked_list` · 8 lectures · 6 done

Put this line at the top of every file in this folder:

```java
package m20_circular_linked_list;
```

## Introduction and Basic Operations

- [x] Circular LinkedList Traverse
- [x] Circular LinkedList Create Solution

## Insertion and Deletion Operations

- [x] Circular LinkedList Insert a Node
- [x] Circular LinkedList Insert a Node - Solution
- [x] Circular LinkedList Delete a Node
- [x] Circular LinkedList Delete a Node - Solution

## Advanced Concepts

- [ ] Circular LinkedList Sentiniel Node
- [ ] Circular LinkedList Class - Solution

## Code in this folder

| File | What it has |
|------|-------------|
| `Node.java` | One node: `data`, `next`, and a constructor (copy of the m19 `Node`) |
| `CircularLinkedList.java` | `create(int[])` links the last node back to `head` (rejects `null`); `display()` and `length()` walk with a do-while loop and stop when they come back to `head`; `insert(index, data)` adds a node at any index from 0 to `length()`, and at index 0 it also moves the last node's `next` to the new head (private helper `lastNode()`); `delete(index)` removes the node at an index from 0 to `length() - 1` and returns its data, fixing the last node's `next` when the head is removed; deleting the only node empties the list, and an empty list throws `NoSuchElementException` |
| `SentinelCircularLinkedList.java` | Circular list with a dummy `sentinel` node. Empty for now |
| `CircularLinkedListDemo.java` | `main()` that displays a five-node list, a one-node list and an empty list with their lengths, then inserts at the head, in the middle, at the end and into an empty list, then deletes the head, a middle node and the last node, empties a one-node list and shows the errors for a bad index and an empty list |

[Back to all modules](../../README.md)
