package m23_stack_introduction;

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
        throw new UnsupportedOperationException("TODO");
    }

    // position 1 = top
    public int peek(int position) {
        throw new UnsupportedOperationException("TODO");
    }

    public int stackTop() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isFull() {
        return top == elements.length - 1;
    }

    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    // prints from top to bottom
    public void display() {
        throw new UnsupportedOperationException("TODO");
    }
}
