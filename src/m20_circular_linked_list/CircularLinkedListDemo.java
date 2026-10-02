package m20_circular_linked_list;

import java.util.NoSuchElementException;

public class CircularLinkedListDemo {
    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();
        list.create(new int[]{1, 2, 3, 4, 5});
        System.out.print("list = ");
        list.display();
        System.out.println("length = " + list.length());

        System.out.println();
        CircularLinkedList oneNodeList = new CircularLinkedList();
        oneNodeList.create(new int[]{7});
        System.out.print("oneNodeList = ");
        oneNodeList.display();
        System.out.println("length = " + oneNodeList.length());

        System.out.println();
        CircularLinkedList emptyList = new CircularLinkedList();
        emptyList.create(new int[]{});
        System.out.print("emptyList = ");
        emptyList.display();
        System.out.println("length = " + emptyList.length());

        System.out.println();
        CircularLinkedList toInsert = new CircularLinkedList();
        toInsert.create(new int[]{1, 2, 3});
        System.out.print("before insert = ");
        toInsert.display();
        toInsert.insert(0, 0);
        System.out.print("after insert(0, 0) head = ");
        toInsert.display();
        toInsert.insert(2, 9);
        System.out.print("after insert(2, 9) middle = ");
        toInsert.display();
        toInsert.insert(5, 4);
        System.out.print("after insert(5, 4) end = ");
        toInsert.display();

        CircularLinkedList emptyForInsert = new CircularLinkedList();
        emptyForInsert.insert(0, 5);
        System.out.print("insert(0, 5) on empty list = ");
        emptyForInsert.display();

        System.out.println();
        CircularLinkedList toDelete = new CircularLinkedList();
        toDelete.create(new int[]{1, 2, 3, 4, 5});
        System.out.print("before delete = ");
        toDelete.display();
        System.out.println("delete(0) head = " + toDelete.delete(0));
        System.out.println("delete(1) middle = " + toDelete.delete(1));
        System.out.println("delete(2) last = " + toDelete.delete(2));
        System.out.print("after deletes = ");
        toDelete.display();
        try {
            toDelete.delete(5);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("delete(5) = " + e.getMessage());
        }

        CircularLinkedList oneNodeToDelete = new CircularLinkedList();
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
        System.out.println("Sentinel version");
        SentinelCircularLinkedList sentinelList = new SentinelCircularLinkedList();
        sentinelList.create(new int[]{1, 2, 3, 4, 5});
        System.out.print("sentinelList = ");
        sentinelList.display();
        System.out.println("length = " + sentinelList.length());

        SentinelCircularLinkedList sentinelEmptyList = new SentinelCircularLinkedList();
        System.out.print("sentinelEmptyList = ");
        sentinelEmptyList.display();
        System.out.println("length = " + sentinelEmptyList.length());

        System.out.println();
        SentinelCircularLinkedList sentinelToInsert = new SentinelCircularLinkedList();
        sentinelToInsert.create(new int[]{1, 2, 3});
        System.out.print("before insert = ");
        sentinelToInsert.display();
        sentinelToInsert.insert(0, 0);
        System.out.print("after insert(0, 0) head = ");
        sentinelToInsert.display();
        sentinelToInsert.insert(2, 9);
        System.out.print("after insert(2, 9) middle = ");
        sentinelToInsert.display();
        sentinelToInsert.insert(5, 4);
        System.out.print("after insert(5, 4) end = ");
        sentinelToInsert.display();

        SentinelCircularLinkedList sentinelEmptyForInsert = new SentinelCircularLinkedList();
        sentinelEmptyForInsert.insert(0, 5);
        System.out.print("insert(0, 5) on empty list = ");
        sentinelEmptyForInsert.display();

        System.out.println();
        SentinelCircularLinkedList sentinelToDelete = new SentinelCircularLinkedList();
        sentinelToDelete.create(new int[]{1, 2, 3, 4, 5});
        System.out.print("before delete = ");
        sentinelToDelete.display();
        System.out.println("delete(0) head = " + sentinelToDelete.delete(0));
        System.out.println("delete(1) middle = " + sentinelToDelete.delete(1));
        System.out.println("delete(2) last = " + sentinelToDelete.delete(2));
        System.out.print("after deletes = ");
        sentinelToDelete.display();
        try {
            sentinelToDelete.delete(2);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("delete(2) = " + e.getMessage());
        }

        SentinelCircularLinkedList sentinelOneNodeToDelete = new SentinelCircularLinkedList();
        sentinelOneNodeToDelete.create(new int[]{7});
        System.out.println("delete(0) on one node list = " + sentinelOneNodeToDelete.delete(0));
        System.out.print("after delete, one node list = ");
        sentinelOneNodeToDelete.display();
        try {
            sentinelOneNodeToDelete.delete(0);
        } catch (NoSuchElementException e) {
            System.out.println("delete(0) on empty list = " + e.getMessage());
        }
    }
}
