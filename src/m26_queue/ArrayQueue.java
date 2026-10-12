package m26_queue;

// Lectures 3 to 6. Queue on an int array with two pointers, front and rear.
// enqueue moves rear forward and dequeue moves front forward, so both are O(1).
// Drawback (lecture 6): a slot is never used again after a dequeue. Once rear reaches
// the last index, isFull() is true even if every element has been dequeued.
// This class keeps that drawback on purpose. CircularQueue fixes it.
public class ArrayQueue {
    private final int[] elements;
    private int front = -1; // index just BEFORE the first element
    private int rear = -1; // index of the last element

    // capacity of 0 or less throws IllegalArgumentException("Capacity must be positive: x")
    public ArrayQueue(int capacity) {
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

    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    // prints from front to rear
    public void display() {
        throw new UnsupportedOperationException("TODO");
    }
}
