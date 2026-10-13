package m26_queue;

import java.util.NoSuchElementException;
import java.util.Objects;

// Lecture 9. Same circular queue as CircularQueue for any T.
// Java does not allow new T[n], so the array is made as Object[] and cast to T[],
// like m23's GenericArrayStack.
public class GenericCircularQueue<T> {
    private final T[] elements;
    private int front; // index just BEFORE the first element
    private int rear; // index of the last element

    // capacity of 0 or less throws IllegalArgumentException("Capacity must be positive: x")
    // Integer.MAX_VALUE throws IllegalArgumentException("Capacity is too large: x"), because capacity + 1 would overflow
    @SuppressWarnings("unchecked")
    public GenericCircularQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive: " + capacity);
        }
        if (capacity == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Capacity is too large: " + capacity);
        }
        elements = (T[]) new Object[capacity + 1];
    }

    // null throws NullPointerException("null elements are not allowed"), checked before the full check
    // full queue throws IllegalStateException("Queue is full")
    public void enqueue(T value) {
        Objects.requireNonNull(value, "null elements are not allowed");
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        rear = next(rear);
        elements[rear] = value;
    }

    // clears the slot it reads from, so the queue does not keep the dequeued object alive
    // empty queue throws NoSuchElementException("Queue is empty")
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        front = next(front);
        T value = elements[front];
        elements[front] = null;
        return value;
    }

    // first element, removes nothing
    // empty queue throws NoSuchElementException("Queue is empty")
    public T first() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return elements[next(front)];
    }

    // last element, removes nothing
    // empty queue throws NoSuchElementException("Queue is empty")
    public T last() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return elements[rear];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return next(rear) == front;
    }

    // number of elements, also correct after rear has wrapped around behind front
    public int size() {
        if (rear >= front) {
            return rear - front;
        }
        return rear - front + elements.length;
    }

    // prints from front to rear, following the wrap-around
    public void display() {
        int index = front;
        while (index != rear) {
            index = next(index);
            System.out.print(elements[index] + " ");
        }
        System.out.println();
    }

    // the index after index, wrapping to 0 after the last index
    private int next(int index) {
        return (index + 1) % elements.length;
    }
}
