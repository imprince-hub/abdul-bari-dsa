# 19. Linked List Functions

Package: `m19_linked_list_functions` · 19 lectures · 3 done

Put this line at the top of every file in this folder:

```java
package m19_linked_list_functions;
```

## Removal, Reversal, and Recursion

- [x] LinkedList Remove
- [x] LinkedList Reverse
- [x] LinkedList Reverse Solution
- [ ] LinkedList Reverse using Recursion
- [ ] LinkedList Reverse using Recursion - Solution

## Sorting and Sorted Insert

- [ ] LinkedList isSorted
- [ ] LinkedList isSorted - Solution
- [ ] LinkedList Sorted Insert
- [ ] LinkedList Sorted Insert - Solution
- [ ] LinkedList Insertion Sort
- [ ] LinkedList Insertion Sort - Solution

## Concatenation and Merging

- [ ] LinkedList Concatenation
- [ ] LinkedList Concatenation Simple Solution
- [ ] LinkedList Concatenation Class Solution copy
- [ ] LinkedList Merging
- [ ] LinkedList Merging Simple Solution
- [ ] LinkedList Merging Class Solution

## Advanced Algorithms

- [ ] Floyd's Cycle Algorithm
- [ ] LinkedList isLoop Solution

## Code in this folder

| File | What it has |
|------|-------------|
| `Node.java` | One node: `data`, `next`, and a constructor (copy of the m18 `Node`) |
| `SinglyLinkedList.java` | From m18: `create(int[])`, `display()`, `length()`. New: `remove(key)` removes the first match and returns `false` if the key is missing or the list is empty. `reverse()` reverses the links in place with three sliding pointers (`ahead`, `current`, `prev`). `reverseUsingArray()` copies the data into an array and writes it back in reverse order, so the links stay the same (extra O(n) space, not from a lecture) |
| `NodeUtils.java` | Static helpers for raw `Node` chains (the "Simple Solution" lectures). Empty for now |
| `LinkedListFunctionsDemo.java` | `main()` that runs `remove` on the head, a middle node, the last node, a missing key, a list with duplicates and an empty list, then reverses a list with `reverse()` and another with `reverseUsingArray()` |

[Back to all modules](../../README.md)
