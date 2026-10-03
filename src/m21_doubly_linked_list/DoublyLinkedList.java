package m21_doubly_linked_list;

import java.util.Objects;

public class DoublyLinkedList {
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
    }

    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void displayReverse() {
        if (head == null) {
            System.out.println();
            return;
        }
        Node current = lastNode();
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.prev;
        }
        System.out.println();
    }

    // Returns the node whose next is null. Call only when the list is not empty.
    private Node lastNode() {
        Node last = head;
        while (last.next != null) {
            last = last.next;
        }
        return last;
    }

    public int length() {
        Node current = head;
        int count = 0;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public void insert(int index, int data) {
        if (index < 0 || index > length()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        Node newNode = new Node(data);
        if (index == 0) {
            if (head == null) {
                head = newNode;
                return;
            }
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            return;
        }
        Node before = nodeAt(index - 1);
        newNode.next = before.next;
        if (before.next != null) {
            before.next.prev = newNode;
        }
        before.next = newNode;
        newNode.prev = before;
    }

    // Returns the node at position index. Call only after the index is checked.
    private Node nodeAt(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
}
