package m23_stack_introduction;

import java.util.NoSuchElementException;
import java.util.Objects;

public class GenericArrayStack<T> {
    private final T[] elements;
    private int top = -1; // index of the top element, -1 means empty

    @SuppressWarnings("unchecked")
    public GenericArrayStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive: " + capacity);
        }
        elements = (T[]) new Object[capacity];
    }

    public void push(T value) {
        Objects.requireNonNull(value, "null elements are not allowed");
        if (isFull()) {
            throw new IllegalStateException("Stack overflow");
        }
        elements[++top] = value;
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        T poppedValue = elements[top];
        elements[top] = null; // clear the slot so the popped object can be garbage collected
        top--;
        return poppedValue;
    }

    // position 1 = top
    public T peek(int position) {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        if (position < 1 || position > size()) {
            throw new IndexOutOfBoundsException("Invalid position: " + position);
        }
        int index = top - position + 1;
        return elements[index];
    }

    public T stackTop() {
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
