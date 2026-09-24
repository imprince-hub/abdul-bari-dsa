package m18_linked_list;

public class LinkedListDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        SinglyLinkedList list = new SinglyLinkedList();
        list.create(arr);
        list.display();
        list.displayRecursive();
        System.out.println("count = " + list.countRecursive());
        System.out.println("sum = " + list.sumRecursive());
        System.out.println("max = " + list.maxRecursive());
        System.out.println("min = " + list.minRecursive());
    }
}
