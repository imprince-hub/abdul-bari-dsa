# 21. Doubly Linked List

Package: `m21_doubly_linked_list` · 9 lectures · 6 done

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

- [x] Doubly LinkedList Delete
- [x] Doubly LinkedList Delete - Solution
- [ ] Doubly LinkedList Class - Solution

## Circular Doubly Linked List

- [ ] Circular Doubly LinkedList
- [ ] Circular Doubly LinkedList - Solution

## Code in this folder

| File | What it has |
|------|-------------|
| `Node.java` | One node: `data`, `next`, `prev`, and a constructor |
| `DoublyLinkedList.java` | `create(int[])` makes the first node the `head`, then links each new node both ways (`last.next` and `newNode.prev`); it replaces any old list, an empty array gives an empty list, and `null` is rejected; `display()` walks forward with `next`; `displayReverse()` goes to the last node (private helper `lastNode()`) and walks back with `prev`, and prints only a newline for an empty list; `length()` counts the nodes; `insert(index, data)` adds a node at any index from 0 to `length()`: at index 0 it becomes the new `head` (on an empty list it is the only node), otherwise it goes after the node at `index - 1` (private helper `nodeAt(index)`) and the next node's `prev` is set only when there is a next node, so inserting at the end works; a bad index throws `IndexOutOfBoundsException` and leaves the list unchanged; `delete(index)` removes the node at an index from 0 to `length() - 1` and returns its data: it goes straight to that node with `nodeAt(index)` (no need to stop one node early, because the node has `prev`), and the next node's `prev` is fixed only when there is a next node, so deleting the last node works; removing the head clears the new head's `prev`, deleting the only node empties the list, and an empty list throws `NoSuchElementException` |
| `CircularDoublyLinkedList.java` | `create(int[])` closes the loop both ways (`last.next = head` and `head.prev = last`), so a one-node list points to itself; `display()` and `length()` walk with a do-while loop and stop when they come back to `head`; `displayReverse()` steps to `prev` first and prints, so it starts at the last node (`head.prev`) with no extra walk and stops after printing `head`; `insert(index, data)` adds a node at any index from 0 to `length()`: on an empty list the node links to itself, at index 0 it goes between the last node and `head` and becomes the new `head`, otherwise it goes after the node at `index - 1` (private helper `nodeAt(index)`); no `null` checks are needed because a non-empty circular list has no `null` links |
| `DoublyLinkedListDemo.java` | `main()` that creates a five-node list, a one-node list and an empty list and prints each one forward, reversed and its length, then calls `create` again on the five-node list to show the old nodes are replaced, then inserts at the head, in the middle and at the end of a three-node list and into an empty list, printing the list forward and reversed after each insert, and shows the error for a bad index; then deletes the head, a middle node and the last node of a five-node list (printing it forward and reversed after each delete), shows the error for a bad index, empties a one-node list and shows the error for an empty list; then a "Circular doubly version" part shows a five-node and an empty `CircularDoublyLinkedList`, inserts at the head, in the middle, at the end and into an empty list (printing forward and reversed each time), and shows the error for a bad index |

[Back to all modules](../../README.md)
