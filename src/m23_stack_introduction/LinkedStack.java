package m23_stack_introduction;

import java.util.NoSuchElementException;

public class LinkedStack {
    private Node top; // head of the list is the top of the stack
    private int size; // number of elements in the stack
    private final int capacity; // max number of elements

    public LinkedStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive: " + capacity);
        }
        this.capacity = capacity;
    }

    public void push(int value) {
        if (isFull()) {
            throw new IllegalStateException("Stack overflow");
        }
        Node newNode = new Node(value);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public int pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        int poppedValue = top.data;
        top = top.next;
        size--;
        return poppedValue;
    }

    // position 1 = top
    public int peek(int position) {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        if (position < 1 || position > size) {
            throw new IndexOutOfBoundsException("Invalid position: " + position);
        }
        Node current = top;
        for (int i = 1; i < position; i++) {
            current = current.next;
        }
        return current.data;
    }

    public int stackTop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        return top.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    // prints from top to bottom
    public void display() {
        Node current = top;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}
