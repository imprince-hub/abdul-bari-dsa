package m19_linked_list_functions;

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

    public int length() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Removes the first node whose data equals key. Returns false if key is not in the list.
    public boolean remove(int key) {
        if (head == null) {
            return false;
        }
        if (head.data == key) {
            head = head.next;
            return true;
        }
        Node prev = head;
        Node current = head.next;
        while (current != null) {
            if (current.data == key) {
                prev.next = current.next;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    public void reverse() {
        Node ahead = head;
        Node current = null;
        while (ahead != null) {
            Node prev = current;
            current = ahead;
            ahead = ahead.next;
            current.next = prev;
        }
        head = current;
    }

    public void reverseUsingArray() {
        Node current = head;
        int[] array = new int[length()];
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        current = head;
        while (index > 0) {
            current.data = array[--index];
            current = current.next;
        }
    }

    public void reverseRecursive() {
        reverseRecursive(head, null);
    }

    private void reverseRecursive(Node current, Node prev) {
        if (current == null) {
            head = prev;
            return;
        }
        reverseRecursive(current.next, current);
        current.next = prev;
    }

    public boolean isSorted() {
        Node current = head;
        while (current != null && current.next != null) {
            if (current.data > current.next.data) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    // Assumes the list is already sorted in ascending order. Puts data before the first bigger value.
    public void sortedInsert(int data) {
        insertNodeSorted(new Node(data));
    }

    public void insertionSort() {
        Node unsortedHead = head;
        head = null;
        while (unsortedHead != null) {
            Node current = unsortedHead;
            unsortedHead = unsortedHead.next;
            insertNodeSorted(current);
        }
    }

    // Same result as insertionSort(), but finds the place for each node inside this method.
    public void insertionSortInline() {
        if (head == null) {
            return;
        }
        Node unsortedHead = head.next;
        head.next = null;
        while (unsortedHead != null) {
            Node current = unsortedHead;
            unsortedHead = unsortedHead.next;
            Node currentSorted = head;
            Node prevSorted = null;
            while (currentSorted != null && currentSorted.data <= current.data) {
                prevSorted = currentSorted;
                currentSorted = currentSorted.next;
            }
            current.next = currentSorted;
            if (prevSorted != null) {
                prevSorted.next = current;
            } else {
                head = current;
            }
        }
    }

    // Links an existing node into the sorted list that starts at head.
    private void insertNodeSorted(Node node) {
        Node prev = null;
        Node current = head;
        while (current != null && current.data <= node.data) {
            prev = current;
            current = current.next;
        }
        node.next = current;
        if (prev == null) {
            head = node;
        } else {
            prev.next = node;
        }
    }
}