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
    }
}
