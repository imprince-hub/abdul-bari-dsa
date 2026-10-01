package m20_circular_linked_list;

import java.util.Objects;

public class CircularLinkedList {
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
            last = newNode;
        }
        last.next = head;
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

    public int length() {
        if (head == null) {
            return 0;
        }
        Node current = head;
        int count = 0;
        do {
            current = current.next;
            count++;
        } while (current != head);
        return count;
    }
}
