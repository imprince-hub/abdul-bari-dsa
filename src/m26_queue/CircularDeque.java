package m26_queue;

// Lectures 12 and 14. Double ended queue on an int array: insert and delete at both ends.
// Circular like CircularQueue, so all four operations are O(1) and no slot is wasted for good.
// Same pointer meaning as CircularQueue, both start at 0, and one slot always stays empty,
// so the array has capacity + 1 slots.
// insertRear and deleteFront work like enqueue and dequeue.
// insertFront and deleteRear move the pointers backward, wrapping from 0 to the last index.
public class CircularDeque {
    private final int[] elements;
    private int front; // index just BEFORE the first element
    private int rear; // index of the last element

    // capacity of 0 or less throws IllegalArgumentException("Capacity must be positive: x")
    // Integer.MAX_VALUE also throws IllegalArgumentException, because capacity + 1 would overflow
    public CircularDeque(int capacity) {
        throw new UnsupportedOperationException("TODO");
    }

    // full deque throws IllegalStateException("Deque is full")
    public void insertFront(int value) {
        throw new UnsupportedOperationException("TODO");
    }

    // full deque throws IllegalStateException("Deque is full")
    public void insertRear(int value) {
        throw new UnsupportedOperationException("TODO");
    }

    // empty deque throws NoSuchElementException("Deque is empty")
    public int deleteFront() {
        throw new UnsupportedOperationException("TODO");
    }

    // empty deque throws NoSuchElementException("Deque is empty")
    public int deleteRear() {
        throw new UnsupportedOperationException("TODO");
    }

    // first element, removes nothing
    // empty deque throws NoSuchElementException("Deque is empty")
    public int first() {
        throw new UnsupportedOperationException("TODO");
    }

    // last element, removes nothing
    // empty deque throws NoSuchElementException("Deque is empty")
    public int last() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isFull() {
        throw new UnsupportedOperationException("TODO");
    }

    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    // prints from front to rear, following the wrap-around
    public void display() {
        throw new UnsupportedOperationException("TODO");
    }

    // the index after index, wrapping to 0 after the last index
    private int next(int index) {
        throw new UnsupportedOperationException("TODO");
    }

    // the index before index, wrapping to the last index before 0
    private int previous(int index) {
        throw new UnsupportedOperationException("TODO");
    }
}
