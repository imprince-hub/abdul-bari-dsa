package m21_doubly_linked_list;

import java.util.NoSuchElementException;

public class DoublyLinkedListDemo {
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        list.create(new int[]{1, 2, 3, 4, 5});
        System.out.print("list = ");
        list.display();
        System.out.print("list reversed = ");
        list.displayReverse();
        System.out.println("length = " + list.length());

        System.out.println();
        DoublyLinkedList oneNodeList = new DoublyLinkedList();
        oneNodeList.create(new int[]{7});
        System.out.print("oneNodeList = ");
        oneNodeList.display();
        System.out.print("oneNodeList reversed = ");
        oneNodeList.displayReverse();
        System.out.println("length = " + oneNodeList.length());

        System.out.println();
        DoublyLinkedList emptyList = new DoublyLinkedList();
        emptyList.create(new int[]{});
        System.out.print("emptyList = ");
        emptyList.display();
        System.out.print("emptyList reversed = ");
        emptyList.displayReverse();
        System.out.println("length = " + emptyList.length());

        System.out.println();
        list.create(new int[]{8, 9});
        System.out.print("after create({8, 9}) on list = ");
        list.display();
        System.out.print("list reversed = ");
        list.displayReverse();
        System.out.println("length = " + list.length());

        System.out.println();
        DoublyLinkedList toInsert = new DoublyLinkedList();
        toInsert.create(new int[]{1, 2, 3});
        System.out.print("before insert = ");
        toInsert.display();
        toInsert.insert(0, 0);
        System.out.print("after insert(0, 0) head = ");
        toInsert.display();
        System.out.print("reversed = ");
        toInsert.displayReverse();
        toInsert.insert(2, 9);
        System.out.print("after insert(2, 9) middle = ");
        toInsert.display();
        System.out.print("reversed = ");
        toInsert.displayReverse();
        toInsert.insert(5, 4);
        System.out.print("after insert(5, 4) end = ");
        toInsert.display();
        System.out.print("reversed = ");
        toInsert.displayReverse();

        DoublyLinkedList emptyForInsert = new DoublyLinkedList();
        emptyForInsert.insert(0, 5);
        System.out.print("insert(0, 5) on empty list = ");
        emptyForInsert.display();
        System.out.print("reversed = ");
        emptyForInsert.displayReverse();
        try {
            emptyForInsert.insert(3, 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("insert(3, 1) = " + e.getMessage());
        }

        System.out.println();
        DoublyLinkedList toDelete = new DoublyLinkedList();
        toDelete.create(new int[]{1, 2, 3, 4, 5});
        System.out.print("before delete = ");
        toDelete.display();
        System.out.println("delete(0) head = " + toDelete.delete(0));
        System.out.print("list = ");
        toDelete.display();
        System.out.print("reversed = ");
        toDelete.displayReverse();
        System.out.println("delete(1) middle = " + toDelete.delete(1));
        System.out.print("list = ");
        toDelete.display();
        System.out.print("reversed = ");
        toDelete.displayReverse();
        System.out.println("delete(2) last = " + toDelete.delete(2));
        System.out.print("list = ");
        toDelete.display();
        System.out.print("reversed = ");
        toDelete.displayReverse();
        try {
            toDelete.delete(2);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("delete(2) = " + e.getMessage());
        }

        DoublyLinkedList oneNodeToDelete = new DoublyLinkedList();
        oneNodeToDelete.create(new int[]{7});
        System.out.println("delete(0) on one node list = " + oneNodeToDelete.delete(0));
        System.out.print("after delete, one node list = ");
        oneNodeToDelete.display();
        try {
            oneNodeToDelete.delete(0);
        } catch (NoSuchElementException e) {
            System.out.println("delete(0) on empty list = " + e.getMessage());
        }

        System.out.println();
        System.out.println("Circular doubly version");
        CircularDoublyLinkedList circularList = new CircularDoublyLinkedList();
        circularList.create(new int[]{1, 2, 3, 4, 5});
        System.out.print("circularList = ");
        circularList.display();
        System.out.print("circularList reversed = ");
        circularList.displayReverse();
        System.out.println("length = " + circularList.length());

        CircularDoublyLinkedList circularEmptyList = new CircularDoublyLinkedList();
        circularEmptyList.create(new int[]{});
        System.out.print("circularEmptyList = ");
        circularEmptyList.display();
        System.out.print("circularEmptyList reversed = ");
        circularEmptyList.displayReverse();
        System.out.println("length = " + circularEmptyList.length());

        System.out.println();
        CircularDoublyLinkedList circularToInsert = new CircularDoublyLinkedList();
        circularToInsert.create(new int[]{1, 2, 3});
        System.out.print("before insert = ");
        circularToInsert.display();
        circularToInsert.insert(0, 0);
        System.out.print("after insert(0, 0) head = ");
        circularToInsert.display();
        System.out.print("reversed = ");
        circularToInsert.displayReverse();
        circularToInsert.insert(2, 9);
        System.out.print("after insert(2, 9) middle = ");
        circularToInsert.display();
        System.out.print("reversed = ");
        circularToInsert.displayReverse();
        circularToInsert.insert(5, 4);
        System.out.print("after insert(5, 4) end = ");
        circularToInsert.display();
        System.out.print("reversed = ");
        circularToInsert.displayReverse();

        CircularDoublyLinkedList circularEmptyForInsert = new CircularDoublyLinkedList();
        circularEmptyForInsert.insert(0, 5);
        System.out.print("insert(0, 5) on empty list = ");
        circularEmptyForInsert.display();
        System.out.print("reversed = ");
        circularEmptyForInsert.displayReverse();
        System.out.println("length = " + circularEmptyForInsert.length());
        try {
            circularEmptyForInsert.insert(3, 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("insert(3, 1) = " + e.getMessage());
        }
    }
}
