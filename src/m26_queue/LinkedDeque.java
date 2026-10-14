package m26_queue;

import java.util.NoSuchElementException;

// Lectures 13 and 15. int deque on a doubly linked list.
// front is the first node and rear is the last node. Both are null when the deque is empty,
// and both point to the same node when it has one element.
// prev is what makes deleteRear O(1): without it, finding the node before rear means walking from front.
// No capacity limit, like LinkedQueue, so there is no isFull().
public class LinkedDeque {
    private DoublyNode front;
    private DoublyNode rear;
    private int size; // number of elements in the deque

    // adds a new node before front
    public void insertFront(int value) {
        DoublyNode newNode = new DoublyNode(value);
        if (front == null) {
            front = newNode;
            rear = newNode;
        } else {
            newNode.next = front;
            front.prev = newNode;
            front = newNode;
        }
        size++;
    }

    // adds a new node after rear
    public void insertRear(int value) {
        DoublyNode newNode = new DoublyNode(value);
        if (rear == null) {
            rear = newNode;
            front = newNode;
        } else {
            newNode.prev = rear;
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    // empty deque throws NoSuchElementException("Deque is empty")
    public int deleteFront() {
        if (front == null) {
            throw new NoSuchElementException("Deque is empty");
        }
        int value = front.data;
        if (front.next == null) {
            front = null;
            rear = null;
        } else {
            front = front.next;
            front.prev = null;
        }
        size--;
        return value;
    }

    // empty deque throws NoSuchElementException("Deque is empty")
    public int deleteRear() {
        if (rear == null) {
            throw new NoSuchElementException("Deque is empty");
        }
        int value = rear.data;
        if (rear.prev == null) {
            rear = null;
            front = null;
        } else {
            rear = rear.prev;
            rear.next = null;
        }
        size--;
        return value;
    }

    // first element, removes nothing
    // empty deque throws NoSuchElementException("Deque is empty")
    public int first() {
        if (front == null) {
            throw new NoSuchElementException("Deque is empty");
        }
        return front.data;
    }

    // last element, removes nothing
    // empty deque throws NoSuchElementException("Deque is empty")
    public int last() {
        if (rear == null) {
            throw new NoSuchElementException("Deque is empty");
        }
        return rear.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    // prints from front to rear
    public void display() {
        DoublyNode current = front;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}
