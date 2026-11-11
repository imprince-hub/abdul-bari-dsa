package m32_binary_heap;

import java.util.Arrays;
import java.util.NoSuchElementException;

// Max heap of int keys in array representation, used for the m32 lectures.
// A binary heap is a complete binary tree: in the array there is no gap between index 1 and index size.
// Index formulas from m28: lchild of i is at 2i, rchild at 2i+1, parent at i/2, and index 0 is never used.
// Max heap rule: every node's key is greater than or equal to the keys of its children, so the biggest key
// is always at index 1 (the root). Unlike a BST, duplicate keys are allowed and siblings have no order.
// A complete binary tree with n keys has floor(log2 n) + 1 levels, so one insert moves a key at most
// that many times: O(log n).
// The array doubles when it is full, so insert never fails.
// New in m32, Introduction to Binary Heap and Basic Operations:
//   peek, height, toArray, isValidHeap, display (lecture 1, Binary Heap)
//   insert, siftUp, parent, growIfFull (lectures 2 and 3, Inserting into a Heap)
//   the constructor MaxHeap(int[] keys) (lecture 4, Generating a Heap)
public final class MaxHeap {
    private static final int DEFAULT_CAPACITY = 10;

    private int[] heap; // heap[1..size] holds the keys in level order; heap[0] is never used
    private int size;   // number of keys

    // empty heap with room for DEFAULT_CAPACITY keys (array length DEFAULT_CAPACITY + 1, because index 0 is unused)
    public MaxHeap() {
        heap = new int[DEFAULT_CAPACITY + 1];
    }

    // Lecture 4 (Generating a Heap). Builds a max heap from keys the lecture's way, in one array:
    // copy all keys into heap[1..n] in the given order and set size = n. heap[1..1] is already a heap of one key.
    // Then for i = 2 to n: heap[1..i-1] is a heap, and siftUp(i) inserts the key at i into it, in place.
    // After the loop heap[1..n] is one max heap.
    // The result is the same array you get from new MaxHeap() and insert(key) for each key in the same order;
    // the demo checks that.
    // Each siftUp moves its key up at most (levels - 1) times, so the whole build is O(n log n).
    // Heapify (lectures 9 and 10) builds a heap from the same array in O(n).
    // The caller's array is copied, never changed.
    //
    // Cases:
    //   keys null throws NullPointerException("keys must not be null")
    //   {} gives an empty heap with array length 1 (growIfFull still works, it doubles heap.length)
    //   duplicate keys are all kept, so size is always keys.length
    // Variables: int n = keys.length, int i (the lecture's i).
    public MaxHeap(int[] keys) {
        if (keys == null) {
            throw new NullPointerException("keys must not be null");
        }
        int n = keys.length;
        heap = new int[n + 1];
        System.arraycopy(keys, 0, heap, 1, n);
        size = n;
        for (int i = 2; i <= n; i++) {
            siftUp(i);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    // Lecture 1. Returns the biggest key without removing it. It always sits at index 1 (the root), so O(1).
    // Empty heap throws NoSuchElementException("heap is empty"). There is no biggest key, so no fake value
    // like Integer.MIN_VALUE.
    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("heap is empty");
        }
        return heap[1];
    }

    // Lecture 1. Number of levels, floor(log2 size) + 1. Empty heap returns 0 (the real answer).
    // Same counting as AvlTree.height in m31. A complete tree has no gaps, so the height depends only on size,
    // never on the keys.
    // Use int arithmetic only, no Math.log: double rounding can give the wrong level count near powers of 2.
    // Expected: size 1 gives 1, sizes 2 and 3 give 2, sizes 4 to 7 give 3, size 8 gives 4, 1,000,000 gives 20.
    public int height() {
        // the last key sits on the lowest level, so the path from it up to root passes every level once
        int levels = 0;
        for (int i = size; i >= 1; i = parent(i)) {
            levels++;
        }
        return levels;
    }

    // Lecture 1. Returns a copy of heap[1..size] as a normal 0-based array, in level order (the order the keys sit
    // in the heap array). A copy, so a caller cannot break the heap through it. Empty heap returns {}.
    public int[] toArray() {
        return Arrays.copyOfRange(heap, 1, size + 1);
    }

    // Lecture 1. Checks the max heap rule for every key except root: heap[parent(i)] >= heap[i], for i = 2 to size.
    // Returns false at the first key that breaks it. Empty heap and one key return true.
    // Completeness needs no check: heap[1..size] has no gaps by design.
    // Only the demo uses it, as a test.
    public boolean isValidHeap() {
        for (int i = 2; i <= size; i++) {
            if (heap[parent(i)] < heap[i]) {
                return false;
            }
        }
        return true;
    }

    // Lecture 1. Prints the heap as a tree, one line per level, keys separated by one space, for example:
    //   level 1: 40
    //   level 2: 30 15
    //   level 3: 20 10 12 6
    //   level 4: 5
    // Level k holds indices 2^(k-1) to 2^k - 1. The last level stops at size: it can be partly filled,
    // always from the left.
    // Empty heap prints nothing.
    public void display() {
        int level = 1;
        for (int first = 1; first <= size; first = first * 2) {
            int last = Math.min(2 * first - 1, size);
            StringBuilder line = new StringBuilder("level " + level + ":");
            for (int i = first; i <= last; i++) {
                line.append(' ').append(heap[i]);
            }
            System.out.println(line);
            level++;
        }
    }

    // Lectures 2 and 3 (Inserting into a Heap). Adds key and keeps the max heap rule.
    // growIfFull first, then the key goes to the first free place, index size + 1 (the next leaf from the left,
    // so the tree stays complete), then siftUp moves it up to its place.
    // Always succeeds: duplicates are allowed and the array grows when needed. So no return value and no exception.
    // Time: at most one move per level, O(log n). O(1) when the key is not bigger than its parent.
    public void insert(int key) {
        growIfFull();
        heap[++size] = key;
        siftUp(size);
    }

    // Lectures 2 and 3. The lecture's Insert loop: the key at index moves up while it is bigger than its parent.
    // Do it the lecture's way, not with swaps: keep the key in a variable, move each smaller parent down one level
    // into the hole, and write the key once at the end. One assignment per level instead of three.
    // Stops at root (index 1) or at a parent that is >= the key. Strict >, so an equal key stays below its parent.
    // Callers always pass an index between 1 and size, so no check here.
    // Variables: int key (the lecture's temp), int i (the lecture's i), starts at index.
    private void siftUp(int index) {
        int key = heap[index];
        int i = index;
        while (i > 1 && key > heap[parent(i)]) {
            heap[i] = heap[parent(i)];
            i = parent(i);
        }
        heap[i] = key;
    }

    // index of i's parent (the m28 formula). Integer division, so parent(2) and parent(3) are both 1.
    private static int parent(int i) {
        return i / 2;
    }

    // Lectures 2 and 3. If there is no free place (size == heap.length - 1, because index 0 is unused),
    // replaces heap with a copy twice as long (Arrays.copyOf). It doubles heap.length, not the number of keys,
    // so a heap built from {} (length 1) still grows: 1, 2, 4, 8 ...
    // Doubling means the copying costs O(1) per insert on average (the m10 Dynamic Arrays idea).
    private void growIfFull() {
        if (size == heap.length - 1) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
    }
}
