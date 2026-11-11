# 32. Binary Heap

Package: `m32_binary_heap` · 12 lectures · 4 done

Put this line at the top of every file in this folder:

```java
package m32_binary_heap;
```

## Introduction to Binary Heap and Basic Operations

- [x] Binary Heap
- [x] Inserting into a Heap
- [x] Inserting into a Heap - Solution
- [x] Generating a Heap

## Deletion and Sorting in Heaps

- [ ] Deleting from Heap
- [ ] Deleting from Heap - Solution
- [ ] Heap Sort
- [ ] Heap Sort - Solution

## Heap Operations

- [ ] Heapify
- [ ] Heapify - Solution
- [ ] Priority Queue Using Heap
- [ ] Priority Queue Using Heap - Solution

## Code in this folder

| File | What it has |
|------|-------------|
| `MaxHeap.java` | Introduction to Binary Heap and Basic Operations (lectures 1 to 4), code complete. Max heap of `int` keys in array representation: `heap[1..size]` in level order, index 0 never used, lchild at 2i, rchild at 2i+1, parent at i/2 (the m28 formulas). `size` is the number of keys and also the index of the last key. Duplicates allowed. Your code: `parent(i)` (`i / 2`); `growIfFull()` (when `size == heap.length - 1`, replaces `heap` with `Arrays.copyOf(heap, heap.length * 2)`, so a heap from `{}` grows 1, 2, 4, 8; Claude changed the length formula and the copy on request); `siftUp(index)` (the lecture's Insert loop with a hole, not swaps: the key moves up while it is bigger than its parent, `i > 1` stops at root, strict `>` keeps an equal key below); `insert(key)` (`growIfFull`, key at `++size`, `siftUp(size)`). Written by Claude on request: `peek()` (`NoSuchElementException("heap is empty")` on an empty heap), `height()` (counts levels by walking from the last key up to root with `parent`), `toArray()` (copy of `heap[1..size]`), `isValidHeap()`, `display()` (one line per level), and the constructor `MaxHeap(int[] keys)` (the lecture's create in one array: copy keys into `heap[1..n]`, then `siftUp(i)` for i = 2 to n; `NullPointerException("keys must not be null")` for `null`; gives the same array as inserting one by one). Tested: all expected outputs in the demo, 2000 random heaps against `java.util.PriorityQueue` (0 failures), 1,000,000 ascending, shuffled and descending keys (height 20), `height()` for every size from 0 to 5000 |
| `MaxHeapDemo.java` | `main()` calls `demoInsertSteps()`, `demoGenerate()`, `demoPeek()`, `demoLargeHeaps()` and `demoRandomCheck()`, in that order. Expected output is in the comments above each method, and the real output matches it. Helpers: `showHeap(keys)`, `buildByInsert(keys)`, `showLargeHeap(label, heap)` and `matchesPriorityQueue(keys)` (one round of the random check, `new Random(33)`). `rangeArray(n)` and `shuffledRange(n, seed)` are copied from m31. The demo bodies were written by Claude on request |

[Back to all modules](../../README.md)
