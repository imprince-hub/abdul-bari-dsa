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
        throw new UnsupportedOperationException("TODO");
    }

    // no free slot before the first element throws IllegalStateException("No space at front")
    public void insertFront(int value) {
        throw new UnsupportedOperationException("TODO");
    }

    // no free slot after the last element throws IllegalStateException("No space at rear")
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

    // true only when every slot holds an element, so neither end has space
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
