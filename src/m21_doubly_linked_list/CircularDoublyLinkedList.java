package m21_doubly_linked_list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class CircularDoublyLinkedList {
    private Node head;

    public void create(int[] arr) {
        Objects.requireNonNull(arr, "arr is null");
        if (arr.length == 0) {
            head = null;
            return;
        }
        head = new Node(arr[0]);
        Node last = head;
        for (int i = 1; i < arr.length; i++) {
            Node newNode = new Node(arr[i]);
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        last.next = head;
        head.prev = last;
    }

    public void display() {
        if (head != null) {
            Node current = head;
            do {
                System.out.print(current.data + " ");
                current = current.next;
            } while (current != head);
        }
        System.out.println();
    }

    public void displayReverse() {
        if (head != null) {
            Node current = head;
            do {
                current = current.prev;
                System.out.print(current.data + " ");
            } while (current != head);
        }
        System.out.println();
    }

    public int length() {
        if (head == null) {
            return 0;
        }
        Node current = head;
        int count = 0;
        do {
            count++;
            current = current.next;
        } while (current != head);
        return count;
    }

    public void insert(int index, int data) {
        if (index < 0 || index > length()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        Node newNode = new Node(data);
        if (index == 0) {
            if (head == null) {
                newNode.next = newNode;
                newNode.prev = newNode;
                head = newNode;
                return;
            }
            Node last = head.prev;
            newNode.next = head;
            newNode.prev = last;
            last.next = newNode;
            head.prev = newNode;
            head = newNode;
            return;
        }
        Node before = nodeAt(index - 1);
        newNode.next = before.next;
        newNode.prev = before;
        before.next.prev = newNode;
        before.next = newNode;
    }

    // Returns the node at position index. Call only after the index is checked.
    private Node nodeAt(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    public int delete(int index) {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        if (index < 0 || index >= length()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        if (head.next == head) {
            int deletedValue = head.data;
            head = null;
            return deletedValue;
        }
        Node deletedNode = nodeAt(index);
        deletedNode.prev.next = deletedNode.next;
        deletedNode.next.prev = deletedNode.prev;
        if (index == 0) {
            head = deletedNode.next;
        }
        return deletedNode.data;
    }
}
