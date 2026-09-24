package m18_linked_list;

import java.util.NoSuchElementException;

public class SinglyLinkedList {
    private Node head;

    public void create(int[] arr) {
        if (arr.length == 0) {
            head = null;
            return;
        }
        head = new Node(arr[0]);
        Node last, newNode;
        last = head;
        for (int i = 1; i < arr.length; i++) {
            newNode = new Node(arr[i]);
            last.next = newNode;
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

    public void displayRecursive() {
        displayRecursive(head);
        System.out.println();
    }

    private void displayRecursive(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            displayRecursive(node.next);
        }
    }

    public int countRecursive() {
        return countRecursive(head);
    }

    private int countRecursive(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + countRecursive(node.next);
    }

    public int sumRecursive() {
        return sumRecursive(head);
    }

    private int sumRecursive(Node node) {
        if (node == null) {
            return 0;
        }
        return node.data + sumRecursive(node.next);
    }

    public int maxRecursive() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        return maxRecursive(head);
    }

    private int maxRecursive(Node node) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }
        int max = maxRecursive(node.next);
        if (node.data > max) {
            max = node.data;
        }
        return max;
    }

    public int minRecursive() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        return minRecursive(head);
    }

    private int minRecursive(Node node) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }
        int min = minRecursive(node.next);
        if (node.data < min) {
            min = node.data;
        }
        return min;
    }
}
