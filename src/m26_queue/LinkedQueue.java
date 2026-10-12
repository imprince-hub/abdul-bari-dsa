package m26_queue;

// Lectures 10 and 11. int queue on a singly linked list.
// front is the first node (dequeue side) and rear is the last node (enqueue side).
// Both are null when the queue is empty, and both point to the same node when it has one element.
// Fixed capacity, like m23's LinkedStack.
public class LinkedQueue {
    private Node front;
    private Node rear;
    private int size; // number of elements in the queue
    private final int capacity; // max number of elements

    // capacity of 0 or less throws IllegalArgumentException("Capacity must be positive: x")
    public LinkedQueue(int capacity) {
        throw new UnsupportedOperationException("TODO");
    }

    // adds a new node after rear
    // full queue throws IllegalStateException("Queue is full")
    public void enqueue(int value) {
        throw new UnsupportedOperationException("TODO");
    }

    // removes the front node
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
