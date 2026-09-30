# 19. Linked List Functions

Package: `m19_linked_list_functions` · 19 lectures · 17 done

Put this line at the top of every file in this folder:

```java
package m19_linked_list_functions;
```

## Removal, Reversal, and Recursion

- [x] LinkedList Remove
- [x] LinkedList Reverse
- [x] LinkedList Reverse Solution
- [x] LinkedList Reverse using Recursion
- [x] LinkedList Reverse using Recursion - Solution

## Sorting and Sorted Insert

- [x] LinkedList isSorted
- [x] LinkedList isSorted - Solution
- [x] LinkedList Sorted Insert
- [x] LinkedList Sorted Insert - Solution
- [x] LinkedList Insertion Sort
- [x] LinkedList Insertion Sort - Solution

## Concatenation and Merging

- [x] LinkedList Concatenation
- [x] LinkedList Concatenation Simple Solution
- [x] LinkedList Concatenation Class Solution copy
- [x] LinkedList Merging
- [x] LinkedList Merging Simple Solution
- [x] LinkedList Merging Class Solution

## Advanced Algorithms

- [ ] Floyd's Cycle Algorithm
- [ ] LinkedList isLoop Solution

## Code in this folder

| File | What it has |
|------|-------------|
| `Node.java` | One node: `data`, `next`, and a constructor (copy of the m18 `Node`) |
| `SinglyLinkedList.java` | From m18: `create(int[])`, `display()`, `length()`. New: `remove(key)` removes the first match and returns `false` if the key is missing or the list is empty. `reverse()` reverses the links in place with three sliding pointers (`ahead`, `current`, `prev`). `reverseUsingArray()` copies the data into an array and writes it back in reverse order, so the links stay the same (extra O(n) space, not from a lecture). `reverseRecursive()` reverses the links in the returning phase (one stack frame per node, so very long lists hit `StackOverflowError`). `isSorted()` checks ascending order by comparing each node with the next one; equal neighbours count as sorted, and an empty or one-node list is sorted. `sortedInsert(data)` puts a new node before the first bigger value, so equal values go after the ones already there; it assumes the list is already sorted in ascending order. `insertionSort()` starts with an empty sorted part at `head` and moves every node into its place by changing links, not data (O(n^2); an already sorted list is the slowest case because every node is added at the end). `insertionSortInline()` gives the same result but finds each node's place inside the method instead of calling the helper. Private helper `insertNodeSorted(node)` links an existing node into the sorted list; `sortedInsert` and `insertionSort` both use it. `concat(other)` moves all nodes of `other` to the end of this list and leaves `other` empty, so the two lists never share nodes; it rejects `null` and the same list. `merge(other)` merges two sorted lists into this one by relinking nodes (`mergedHead`, `last`); on equal values the node from this list comes first, `other` ends up empty, and `null` or the same list is rejected |
| `NodeUtils.java` | Static helpers for raw `Node` chains (the "Simple Solution" lectures): `create(int[])`, `display(head)`, `concat(first, second)` which joins `second` to the end of `first` and returns the head, `merge(first, second)` which merges two sorted chains and returns the head |
| `LinkedListFunctionsDemo.java` | `main()` that runs `remove` on the head, a middle node, the last node, a missing key, a list with duplicates and an empty list, then reverses a list with `reverse()` another with `reverseUsingArray()` and another with `reverseRecursive()`, then runs `isSorted()` on a sorted list, an unsorted list and a list with equal neighbours, then uses `sortedInsert` in the middle, at the head, at the end and on an empty list, then runs `insertionSort()` and `insertionSortInline()` on the same unsorted list and on an empty list, then joins two raw chains with `NodeUtils.concat` and two lists with `concat` (and shows the error for concatenating a list with itself), then merges two sorted raw chains with `NodeUtils.merge` and two sorted lists with `merge` |

[Back to all modules](../../README.md)
