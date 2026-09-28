package m19_linked_list_functions;

public class LinkedListFunctionsDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        SinglyLinkedList list = new SinglyLinkedList();
        list.create(arr);
        System.out.print("list = ");
        list.display();
        System.out.println("remove(1) head = " + list.remove(1));
        System.out.println("remove(3) middle = " + list.remove(3));
        System.out.println("remove(5) last = " + list.remove(5));
        System.out.println("remove(9) missing = " + list.remove(9));
        System.out.print("after removes = ");
        list.display();

        System.out.println();
        SinglyLinkedList duplicates = new SinglyLinkedList();
        duplicates.create(new int[]{4, 2, 4});
        System.out.print("duplicates = ");
        duplicates.display();
        System.out.println("remove(4) = " + duplicates.remove(4));
        System.out.print("after remove(4) = ");
        duplicates.display();

        System.out.println();
        SinglyLinkedList emptyList = new SinglyLinkedList();
        System.out.println("remove(1) on empty list = " + emptyList.remove(1));

        System.out.println();
        SinglyLinkedList toReverse = new SinglyLinkedList();
        toReverse.create(new int[]{1, 2, 3, 4, 5});
        System.out.print("before reverse = ");
        toReverse.display();
        toReverse.reverse();
        System.out.print("after reverse = ");
        toReverse.display();

        System.out.println();
        SinglyLinkedList toReverseUsingArray = new SinglyLinkedList();
        toReverseUsingArray.create(new int[]{1, 2, 3, 4, 5});
        System.out.print("before reverseUsingArray = ");
        toReverseUsingArray.display();
        toReverseUsingArray.reverseUsingArray();
        System.out.print("after reverseUsingArray = ");
        toReverseUsingArray.display();
    }
}
