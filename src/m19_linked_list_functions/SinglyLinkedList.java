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
}
