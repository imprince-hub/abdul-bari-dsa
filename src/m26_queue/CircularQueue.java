package m26_queue;

// Lectures 7 and 8. Circular queue on an int array. front and rear move forward with
// wrap-around (after the last index comes index 0), so dequeued slots are used again.
// Same pointer meaning as ArrayQueue, but both start at 0.
// One slot always stays empty, so that a full queue and an empty queue look different.
// That is why the array has capacity + 1 slots: new CircularQueue(5) holds 5 elements.
public class CircularQueue {
    private final int[] elements;
    private int front; // index just BEFORE the first element
    private int rear; // index of the last element

    // capacity of 0 or less throws IllegalArgumentException("Capacity must be positive: x")
    // Integer.MAX_VALUE also throws IllegalArgumentException, because capacity + 1 would overflow
    public CircularQueue(int capacity) {
        throw new UnsupportedOperationException("TODO");
    }

    // full queue throws IllegalStateException("Queue is full")
    public void enqueue(int value) {
        throw new UnsupportedOperationException("TODO");
    }

    // empty queue throws NoSuchElementException("Queue is empty")
    public int dequeue() {
        throw new UnsupportedOperationException("TODO");
    }

    // first element, removes nothing
    // empty queue throws NoSuchElementException("Queue is empty")
    public int first() {
        throw new UnsupportedOperationException("TODO");
    }

    // last element, removes nothing
    // empty queue throws NoSuchElementException("Queue is empty")
    public int last() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isFull() {
        throw new UnsupportedOperationException("TODO");
    }

    // number of elements, also correct after rear has wrapped around behind front
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
}
