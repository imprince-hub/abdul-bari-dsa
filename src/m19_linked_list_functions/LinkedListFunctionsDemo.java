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

        System.out.println();
        SinglyLinkedList toReverseRecursive = new SinglyLinkedList();
        toReverseRecursive.create(new int[]{1, 2, 3, 4, 5});
        System.out.print("before reverseRecursive = ");
        toReverseRecursive.display();
        toReverseRecursive.reverseRecursive();
        System.out.print("after reverseRecursive = ");
        toReverseRecursive.display();

        System.out.println();
        SinglyLinkedList sortedList = new SinglyLinkedList();
        sortedList.create(new int[]{1, 2, 3, 4, 5});
        System.out.print("sortedList = ");
        sortedList.display();
        System.out.println("isSorted() = " + sortedList.isSorted());

        SinglyLinkedList unsortedList = new SinglyLinkedList();
        unsortedList.create(new int[]{1, 3, 2, 4});
        System.out.print("unsortedList = ");
        unsortedList.display();
        System.out.println("isSorted() = " + unsortedList.isSorted());

        SinglyLinkedList equalNeighbours = new SinglyLinkedList();
        equalNeighbours.create(new int[]{1, 2, 2, 3});
        System.out.print("equalNeighbours = ");
        equalNeighbours.display();
        System.out.println("isSorted() = " + equalNeighbours.isSorted());

        System.out.println();
        SinglyLinkedList toSortedInsert = new SinglyLinkedList();
        toSortedInsert.create(new int[]{1, 3, 9, 13, 17});
        System.out.print("before sortedInsert = ");
        toSortedInsert.display();
        toSortedInsert.sortedInsert(14);
        System.out.print("after sortedInsert(14) middle = ");
        toSortedInsert.display();
        toSortedInsert.sortedInsert(0);
        System.out.print("after sortedInsert(0) head = ");
        toSortedInsert.display();
        toSortedInsert.sortedInsert(20);
        System.out.print("after sortedInsert(20) end = ");
        toSortedInsert.display();

        SinglyLinkedList emptyForInsert = new SinglyLinkedList();
        emptyForInsert.sortedInsert(5);
        System.out.print("sortedInsert(5) on empty list = ");
        emptyForInsert.display();
    }
}
