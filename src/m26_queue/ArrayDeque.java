package m26_queue;

import java.util.NoSuchElementException;

// Lectures 12 and 14. Double ended queue on an int array with two pointers, as in the lecture:
// insert and delete at both ends. It is not circular, so front and rear never wrap around.
// Same pointer meaning as ArrayQueue: front is the index just BEFORE the first element,
// rear is the index of the last element, and both start at -1.
// insertRear and deleteFront work like enqueue and dequeue.
// insertFront and deleteRear move the pointers backward.
// insertFront needs a free slot before the first element, so it only works after some deleteFront.
// Not the same as java.util.ArrayDeque, which is circular and grows when it is full.
public class ArrayDeque {
    private final int[] elements;
    private int front = -1; // index just BEFORE the first element
    private int rear = -1; // index of the last element

    // capacity of 0 or less throws IllegalArgumentException("Capacity must be positive: x")
    public ArrayDeque(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive: " + capacity);
        }
        elements = new int[capacity];
    }

    // no free slot before the first element throws IllegalStateException("No space at front")
    public void insertFront(int value) {
        if (front == -1) {
            throw new IllegalStateException("No space at front");
        }
        elements[front] = value;
        front--;
    }

    // no free slot after the last element throws IllegalStateException("No space at rear")
    public void insertRear(int value) {
        if (rear == elements.length - 1) {
            throw new IllegalStateException("No space at rear");
        }
        elements[++rear] = value;
    }

    // empty deque throws NoSuchElementException("Deque is empty")
    public int deleteFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        return elements[++front];
    }

    // empty deque throws NoSuchElementException("Deque is empty")
    public int deleteRear() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        return elements[rear--];
    }

    // first element, removes nothing
    // empty deque throws NoSuchElementException("Deque is empty")
    public int first() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        return elements[front + 1];
    }

    // last element, removes nothing
    // empty deque throws NoSuchElementException("Deque is empty")
    public int last() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        return elements[rear];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    // true only when every slot holds an element, so neither end has space
    public boolean isFull() {
        return size() == elements.length;
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
