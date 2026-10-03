# 21. Doubly Linked List

Package: `m21_doubly_linked_list` · 9 lectures · 4 done

Put this line at the top of every file in this folder:

```java
package m21_doubly_linked_list;
```

## Basic Operations on Doubly Linked List

- [x] Doubly LinkedList Traverse
- [x] Doubly LinkedList Create - Solution
- [x] Doubly LinkedList Insert
- [x] Doubly LinkedList Insert - Solution

## Deletion and Class Implementation

- [ ] Doubly LinkedList Delete
- [ ] Doubly LinkedList Delete - Solution
- [ ] Doubly LinkedList Class - Solution

## Circular Doubly Linked List

- [ ] Circular Doubly LinkedList
- [ ] Circular Doubly LinkedList - Solution

## Code in this folder

| File | What it has |
|------|-------------|
| `Node.java` | One node: `data`, `next`, `prev`, and a constructor |
| `DoublyLinkedList.java` | `create(int[])` makes the first node the `head`, then links each new node both ways (`last.next` and `newNode.prev`); it replaces any old list, an empty array gives an empty list, and `null` is rejected; `display()` walks forward with `next`; `displayReverse()` goes to the last node (private helper `lastNode()`) and walks back with `prev`, and prints only a newline for an empty list; `length()` counts the nodes; `insert(index, data)` adds a node at any index from 0 to `length()`: at index 0 it becomes the new `head` (on an empty list it is the only node), otherwise it goes after the node at `index - 1` (private helper `nodeAt(index)`) and the next node's `prev` is set only when there is a next node, so inserting at the end works; a bad index throws `IndexOutOfBoundsException` and leaves the list unchanged |
| `CircularDoublyLinkedList.java` | Empty for now (lectures 8-9) |
| `DoublyLinkedListDemo.java` | `main()` that creates a five-node list, a one-node list and an empty list and prints each one forward, reversed and its length, then calls `create` again on the five-node list to show the old nodes are replaced, then inserts at the head, in the middle and at the end of a three-node list and into an empty list, printing the list forward and reversed after each insert, and shows the error for a bad index |

[Back to all modules](../../README.md)
