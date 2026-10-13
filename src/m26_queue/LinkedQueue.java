package m26_queue;

import java.util.NoSuchElementException;

// Lectures 10 and 11. int queue on a singly linked list.
// front is the first node (dequeue side) and rear is the last node (enqueue side).
// Both are null when the queue is empty, and both point to the same node when it has one element.
// No capacity limit: every enqueue just makes a new node, so there is no isFull().
// In Java, new never returns null; if memory runs out it throws OutOfMemoryError.
public class LinkedQueue {
    private Node front;
    private Node rear;
    private int size; // number of elements in the queue

    // adds a new node after rear
    public void enqueue(int value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    // removes the front node
    // empty queue throws NoSuchElementException("Queue is empty")
    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        int value = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return value;
    }

    // first element, removes nothing
    // empty queue throws NoSuchElementException("Queue is empty")
    public int first() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return front.data;
    }

    // last element, removes nothing
    // empty queue throws NoSuchElementException("Queue is empty")
    public int last() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return rear.data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    // prints from front to rear
    public void display() {
        Node current = front;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}
