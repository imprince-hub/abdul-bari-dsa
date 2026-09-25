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
        Node last = head;
        for (int i = 1; i < arr.length; i++) {
            Node newNode = new Node(arr[i]);
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

    public void displayReverseRecursive() {
        displayReverseRecursive(head);
        System.out.println();
    }

    private void displayReverseRecursive(Node node) {
        if (node != null) {
            displayReverseRecursive(node.next);
            System.out.print(node.data + " ");
        }
    }

    public int length() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public int lengthRecursive() {
        return lengthRecursive(head);
    }

    private int lengthRecursive(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + lengthRecursive(node.next);
    }

    public int sum() {
        int result = 0;
        Node current = head;
        while (current != null) {
            result += current.data;
            current = current.next;
        }
        return result;
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

    public int max() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        int result = head.data;
        Node current = head.next;
        while (current != null) {
            if (current.data > result) {
                result = current.data;
            }
            current = current.next;
        }
        return result;
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

    public int min() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        int result = head.data;
        Node current = head.next;
        while (current != null) {
            if (current.data < result) {
                result = current.data;
            }
            current = current.next;
        }
        return result;
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

    public void insert(int index, int data) {
        if (index < 0 || index > length()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        Node newNode = new Node(data);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            return;
        }
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
    }

    public int delete(int index) {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        if (index < 0 || index >= length()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        if (index == 0) {
            int deletedValue = head.data;
            head = head.next;
            return deletedValue;
        }
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        Node deletedNode = current.next;
        current.next = deletedNode.next;
        return deletedNode.data;
    }
}
