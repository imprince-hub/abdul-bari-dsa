package m26_queue;

// Lecture 9. Same circular queue as CircularQueue for any T.
// Java does not allow new T[n], so the array is made as Object[] and cast to T[],
// like m23's GenericArrayStack.
public class GenericCircularQueue<T> {
    private final T[] elements;
    private int front; // index just BEFORE the first element
    private int rear; // index of the last element

    // capacity of 0 or less throws IllegalArgumentException("Capacity must be positive: x")
    // Integer.MAX_VALUE also throws IllegalArgumentException, because capacity + 1 would overflow
    public GenericCircularQueue(int capacity) {
        throw new UnsupportedOperationException("TODO");
    }

    // null throws NullPointerException("null elements are not allowed"), checked before the full check
    // full queue throws IllegalStateException("Queue is full")
    public void enqueue(T value) {
        throw new UnsupportedOperationException("TODO");
    }

    // clears the slot it reads from, so the queue does not keep the dequeued object alive
    // empty queue throws NoSuchElementException("Queue is empty")
    public T dequeue() {
        throw new UnsupportedOperationException("TODO");
    }

    // first element, removes nothing
    // empty queue throws NoSuchElementException("Queue is empty")
    public T first() {
        throw new UnsupportedOperationException("TODO");
    }

    // last element, removes nothing
    // empty queue throws NoSuchElementException("Queue is empty")
    public T last() {
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
}
