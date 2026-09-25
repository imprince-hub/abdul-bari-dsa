package m18_linked_list;

public class LinkedListDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        SinglyLinkedList list = new SinglyLinkedList();
        list.create(arr);
        System.out.print("display = ");
        list.display();
        System.out.print("displayRecursive = ");
        list.displayRecursive();
        System.out.print("displayReverseRecursive = ");
        list.displayReverseRecursive();
        System.out.println("length = " + list.length());
        System.out.println("lengthRecursive = " + list.lengthRecursive());
        System.out.println("sum = " + list.sum());
        System.out.println("sumRecursive = " + list.sumRecursive());
        System.out.println("max = " + list.max());
        System.out.println("maxRecursive = " + list.maxRecursive());
        System.out.println("min = " + list.min());
        System.out.println("minRecursive = " + list.minRecursive());

        System.out.println();
        SinglyLinkedList listFromInserts = new SinglyLinkedList();
        listFromInserts.insert(0, 5);
        listFromInserts.insert(1, 2);
        listFromInserts.insert(2, 3);
        listFromInserts.insert(3, 4);
        System.out.print("after inserts = ");
        listFromInserts.display();
        int deleted = listFromInserts.delete(2);
        System.out.println("deleted = " + deleted);
        System.out.print("after delete(2) = ");
        listFromInserts.display();
    }
}
