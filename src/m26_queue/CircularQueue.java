package m26_queue;

import java.util.NoSuchElementException;

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
    // Integer.MAX_VALUE throws IllegalArgumentException("Capacity is too large: x"), because capacity + 1 would overflow
    public CircularQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive: " + capacity);
        }
        if (capacity == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Capacity is too large: " + capacity);
        }
        elements = new int[capacity + 1];
    }

    // full queue throws IllegalStateException("Queue is full")
    public void enqueue(int value) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        rear = next(rear);
        elements[rear] = value;
    }

    // empty queue throws NoSuchElementException("Queue is empty")
    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        front = next(front);
        return elements[front];
    }

    // first element, removes nothing
    // empty queue throws NoSuchElementException("Queue is empty")
    public int first() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return elements[next(front)];
    }

    // last element, removes nothing
    // empty queue throws NoSuchElementException("Queue is empty")
    public int last() {
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
