package m23_stack_introduction;

import java.util.NoSuchElementException;

public class ArrayStack {
    private final int[] elements;
    private int top = -1; // index of the top element, -1 means empty

    public ArrayStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive: " + capacity);
        }
        elements = new int[capacity];
    }

    public void push(int value) {
        if (isFull()) {
            throw new IllegalStateException("Stack overflow");
        }
        elements[++top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        return elements[top--];
    }

    // position 1 = top
    public int peek(int position) {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        if (position < 1 || position > size()) {
            throw new IndexOutOfBoundsException("Invalid position: " + position);
        }
        int index = top - position + 1;
        return elements[index];
    }

    public int stackTop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        return elements[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == elements.length - 1;
    }

    public int size() {
        return top + 1;
    }

    // prints from top to bottom
    public void display() {
        for (int i = top; i >= 0; i--) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }
}
