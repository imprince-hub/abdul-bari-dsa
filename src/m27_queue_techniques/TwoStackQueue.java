package m27_queue_techniques;

import m23_stack_introduction.ArrayStack;

import java.util.NoSuchElementException;

// Lectures 1 and 2. int queue built from two stacks (m23's ArrayStack), using only stack operations.
// inStack (lecture's S1): every enqueue is pushed here.
// outStack (lecture's S2): every dequeue pops from here.
// When outStack is empty and the front element is needed, everything in inStack is moved into outStack.
// Moving reverses the order, so the oldest element ends up on top of outStack.
// Fixed capacity like m26's array queues; size() counts both stacks together.
public class TwoStackQueue {
    private final ArrayStack inStack;
    private final ArrayStack outStack;
    private final int capacity; // max number of elements in both stacks together

    // capacity 0 or less throws IllegalArgumentException("Capacity must be positive: x"),
    // checked here before the stacks are made
    // both stacks get the full capacity, because all elements can be in one stack at the same time
    public TwoStackQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive: " + capacity);
        }
        this.capacity = capacity;
        inStack = new ArrayStack(capacity);
        outStack = new ArrayStack(capacity);
    }

    // pushes onto inStack, O(1)
    // full queue throws IllegalStateException("Queue is full")
    public void enqueue(int value) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        inStack.push(value);
    }

    // pops from outStack; if outStack is empty, calls moveInToOut() first
    // O(1) amortized: every element is pushed and popped at most twice in its whole life
    // empty queue throws NoSuchElementException("Queue is empty")
    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (outStack.isEmpty()) {
            moveInToOut();
        }
        return outStack.pop();
    }

    // front element, removes nothing; same rule as dequeue() for when to move
    // empty queue throws NoSuchElementException("Queue is empty")
    public int first() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (outStack.isEmpty()) {
            moveInToOut();
        }
        return outStack.stackTop();
    }

    public boolean isEmpty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    public boolean isFull() {
        return size() == capacity;
    }

    public int size() {
        return inStack.size() + outStack.size();
    }

    // prints from front to rear without changing either stack:
    // outStack from top to bottom, then inStack from bottom to top, both with peek(position)
    public void display() {
        for (int i = 1; i <= outStack.size(); i++) {
            System.out.print(outStack.peek(i) + " ");
        }
        for (int i = inStack.size(); i > 0; i--) {
            System.out.print(inStack.peek(i) + " ");
        }
        System.out.println();
    }

    // pops every element of inStack and pushes it onto outStack
    // call it only when outStack is empty
    private void moveInToOut() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
    }
}
