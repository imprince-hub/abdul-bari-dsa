package m20_circular_linked_list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SentinelCircularLinkedList {
    private final Node sentinel = new Node(0);

    public SentinelCircularLinkedList() {
        sentinel.next = sentinel;
    }

    public void create(int[] arr) {
        Objects.requireNonNull(arr, "arr is null");
        Node last = sentinel;
        for (int i = 0; i < arr.length; i++) {
            Node newNode = new Node(arr[i]);
            last.next = newNode;
            last = newNode;
        }
        last.next = sentinel;
    }

    public void display() {
        Node current = sentinel.next;
        while (current != sentinel) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public int length() {
        Node current = sentinel.next;
        int count = 0;
        while (current != sentinel) {
            current = current.next;
            count++;
        }
        return count;
    }

    public void insert(int index, int data) {
        if (index < 0 || index > length()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        Node prev = nodeBefore(index);
        Node newNode = new Node(data);
        newNode.next = prev.next;
        prev.next = newNode;
    }

    // Returns the node just before position index (sentinel for index 0). Call only after the index is checked.
    private Node nodeBefore(int index) {
        Node prev = sentinel;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        return prev;
    }

    public int delete(int index) {
        if (sentinel.next == sentinel) {
            throw new NoSuchElementException("List is empty");
        }
        if (index < 0 || index >= length()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        Node prev = nodeBefore(index);
        Node deletedNode = prev.next;
        prev.next = deletedNode.next;
        return deletedNode.data;
    }
}
