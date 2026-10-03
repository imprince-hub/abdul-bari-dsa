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
}
