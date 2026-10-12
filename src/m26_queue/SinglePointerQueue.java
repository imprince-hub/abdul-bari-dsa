package m26_queue;

import java.util.NoSuchElementException;

// Lecture 2. Queue on an int array with only one pointer: rear.
// The first element is always at index 0, so enqueue is O(1) but dequeue has to
// shift every remaining element one step left, which is O(n).
public class SinglePointerQueue {
    private final int[] elements;
    private int rear = -1; // index of the last element, -1 means empty

    // capacity of 0 or less throws IllegalArgumentException("Capacity must be positive: x")
    public SinglePointerQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive: " + capacity);
        }
        elements = new int[capacity];
    }

    // full queue throws IllegalStateException("Queue is full")
    public void enqueue(int value) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        elements[++rear] = value;
    }

    // returns the element at index 0 and shifts the rest one step left
    // empty queue throws NoSuchElementException("Queue is empty")
    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        int value = elements[0];
        for (int i = 1; i <= rear; i++) {
            elements[i - 1] = elements[i];
        }
        rear--;
        return value;
    }

    public boolean isEmpty() {
        return rear == -1;
    }

    public boolean isFull() {
        return rear == elements.length - 1;
    }

    public int size() {
        return rear + 1;
    }

    // prints from front to rear
    public void display() {
        for (int i = 0; i <= rear; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }
}
