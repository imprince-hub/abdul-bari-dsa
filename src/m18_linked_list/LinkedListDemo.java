package m18_linked_list;

public class LinkedListDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        SinglyLinkedList list = new SinglyLinkedList();
        list.create(arr);
        list.display();
        list.displayRecursive();
        System.out.println("length = " + list.length());
        System.out.println("lengthRecursive = " + list.lengthRecursive());
        System.out.println("sum = " + list.sum());
        System.out.println("sumRecursive = " + list.sumRecursive());
        System.out.println("max = " + list.max());
        System.out.println("maxRecursive = " + list.maxRecursive());
        System.out.println("min = " + list.min());
        System.out.println("minRecursive = " + list.minRecursive());
    }
}
