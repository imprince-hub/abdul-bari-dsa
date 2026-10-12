package m26_queue;

import java.util.NoSuchElementException;

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

    // empty queue throws NoSuchElementException("Queue is empty")
    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return elements[++front];
    }

    // first element, removes nothing
    // empty queue throws NoSuchElementException("Queue is empty")
    public int first() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return elements[front + 1];
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
        return rear == elements.length - 1;
    }

    public int size() {
        return rear - front;
    }

    // prints from front to rear
    public void display() {
        for (int i = front + 1; i <= rear; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }
}
