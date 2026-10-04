package m23_stack_introduction;

public class GenericLinkedStack<T> {

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> top; // head of the list is the top of the stack
    private int size;

    public void push(T value) {
        throw new UnsupportedOperationException("TODO");
    }

    public T pop() {
        throw new UnsupportedOperationException("TODO");
    }

    // position 1 = top
    public T peek(int position) {
        throw new UnsupportedOperationException("TODO");
    }

    public T stackTop() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO");
    }

    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    // prints from top to bottom
    public void display() {
        throw new UnsupportedOperationException("TODO");
    }
}
