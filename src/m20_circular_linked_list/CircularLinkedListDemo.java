package m20_circular_linked_list;

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
    }
}
