package m18_linked_list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class GenericLinkedList<T extends Comparable<T>> {

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head;

    public void create(T[] arr) {
        for (T item : arr) {
            Objects.requireNonNull(item, "null elements are not allowed");
        }
        if (arr.length == 0) {
            head = null;
            return;
        }
        head = new Node<>(arr[0]);
        Node<T> last = head;
        for (int i = 1; i < arr.length; i++) {
            Node<T> newNode = new Node<>(arr[i]);
            last.next = newNode;
            last = newNode;
        }
    }

    public void display() {
        Node<T> current = head;
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

    private void displayRecursive(Node<T> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            displayRecursive(node.next);
        }
    }

    public void displayReverseRecursive() {
        displayReverseRecursive(head);
        System.out.println();
    }

    private void displayReverseRecursive(Node<T> node) {
        if (node != null) {
            displayReverseRecursive(node.next);
            System.out.print(node.data + " ");
        }
    }

    public int length() {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public int lengthRecursive() {
        return lengthRecursive(head);
    }

    private int lengthRecursive(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + lengthRecursive(node.next);
    }

    public T max() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        T result = head.data;
        Node<T> current = head.next;
        while (current != null) {
            if (current.data.compareTo(result) > 0) {
                result = current.data;
            }
            current = current.next;
        }
        return result;
    }

    public T maxRecursive() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        return maxRecursive(head);
    }

    private T maxRecursive(Node<T> node) {
        if (node.next == null) {
            return node.data;
        }
        T maxOfRest = maxRecursive(node.next);
        if (node.data.compareTo(maxOfRest) > 0) {
            return node.data;
        }
        return maxOfRest;
    }

    public T min() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        T result = head.data;
        Node<T> current = head.next;
        while (current != null) {
            if (current.data.compareTo(result) < 0) {
                result = current.data;
            }
            current = current.next;
        }
        return result;
    }

    public T minRecursive() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        return minRecursive(head);
    }

    private T minRecursive(Node<T> node) {
        if (node.next == null) {
            return node.data;
        }
        T minOfRest = minRecursive(node.next);
        if (node.data.compareTo(minOfRest) < 0) {
            return node.data;
        }
        return minOfRest;
    }

    public void insert(int index, T data) {
        Objects.requireNonNull(data, "null elements are not allowed");
        if (index < 0 || index > length()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        Node<T> newNode = new Node<>(data);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            return;
        }
        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
    }

    public T delete(int index) {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        if (index < 0 || index >= length()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        if (index == 0) {
            T deletedValue = head.data;
            head = head.next;
            return deletedValue;
        }
        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        Node<T> deletedNode = current.next;
        current.next = deletedNode.next;
        return deletedNode.data;
    }

    public int search(T key) {
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(key)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public int searchRecursive(T key) {
        return searchRecursive(key, head, 0);
    }

    private int searchRecursive(T key, Node<T> node, int index) {
        if (node == null) {
            return -1;
        }
        if (node.data.equals(key)) {
            return index;
        }
        return searchRecursive(key, node.next, index + 1);
    }

    // Returns the index where key was found (before it is moved to head), or -1 if not found.
    public int searchMoveToHead(T key) {
        Node<T> current = head;
        Node<T> prev = null;
        int index = 0;
        while (current != null) {
            if (current.data.equals(key)) {
                if (current != head) {
                    prev.next = current.next;
                    current.next = head;
                    head = current;
                }
                return index;
            }
            prev = current;
            current = current.next;
            index++;
        }
        return -1;
    }
}
