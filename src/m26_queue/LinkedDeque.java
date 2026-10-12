package m26_queue;

// Lectures 13 and 15. int deque on a doubly linked list.
// front is the first node and rear is the last node. Both are null when the deque is empty,
// and both point to the same node when it has one element.
// prev is what makes deleteRear O(1): without it, finding the node before rear means walking from front.
// Fixed capacity, like LinkedQueue.
public class LinkedDeque {
    private DoublyNode front;
    private DoublyNode rear;
    private int size; // number of elements in the deque
    private final int capacity; // max number of elements

    // capacity of 0 or less throws IllegalArgumentException("Capacity must be positive: x")
    public LinkedDeque(int capacity) {
        throw new UnsupportedOperationException("TODO");
    }

    // adds a new node before front
    // full deque throws IllegalStateException("Deque is full")
    public void insertFront(int value) {
        throw new UnsupportedOperationException("TODO");
    }

    // adds a new node after rear
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

    // prints from front to rear
    public void display() {
        throw new UnsupportedOperationException("TODO");
    }
}
