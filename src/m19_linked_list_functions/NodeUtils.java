package m19_linked_list_functions;

public class NodeUtils {
    private NodeUtils() {
    }

    public static Node create(int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        Node head = new Node(arr[0]);
        Node last = head;
        for (int i = 1; i < arr.length; i++) {
            Node newNode = new Node(arr[i]);
            last.next = newNode;
            last = newNode;
        }
        return head;
    }

    public static void display(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Joins second to the end of first and returns the head of the joined chain.
    // The first chain is changed; no new nodes are created.
    public static Node concat(Node first, Node second) {
        if (first == null) {
            return second;
        }
        if (first == second) {
            throw new IllegalArgumentException("Cannot concat a chain with itself");
        }
        Node last = first;
        while (last.next != null) {
            last = last.next;
        }
        last.next = second;
        return first;
    }
}
