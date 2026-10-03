package m21_doubly_linked_list;

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
    }
}
