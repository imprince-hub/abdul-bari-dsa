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
    }
}
