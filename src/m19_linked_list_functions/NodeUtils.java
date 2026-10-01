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

    // Merges two chains that are already sorted in ascending order and returns the head of the result.
    // No new nodes are created; both input chains are used up.
    public static Node merge(Node first, Node second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }
        if (first == second) {
            throw new IllegalArgumentException("Cannot merge a chain with itself");
        }
        Node mergedHead;
        if (first.data <= second.data) {
            mergedHead = first;
            first = first.next;
        } else {
            mergedHead = second;
            second = second.next;
        }
        Node last = mergedHead;
        while (first != null && second != null) {
            Node picked;
            if (first.data <= second.data) {
                picked = first;
                first = first.next;
            } else {
                picked = second;
                second = second.next;
            }
            last.next = picked;
            last = picked;
        }
        if (first == null) {
            last.next = second;
        } else {
            last.next = first;
        }
        return mergedHead;
    }

    // Floyd's cycle algorithm: slow moves 1 step and fast moves 2 steps. They can meet only if the chain has a loop.
    public static boolean hasLoop(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
