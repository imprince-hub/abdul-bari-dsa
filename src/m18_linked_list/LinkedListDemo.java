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
        System.out.println("search = " + listFromInserts.search(4));
        System.out.println("searchRecursive = " + listFromInserts.searchRecursive(9));
        System.out.print("before searchMoveToHead(2) = ");
        listFromInserts.display();
        System.out.println("searchMoveToHead(2) = " + listFromInserts.searchMoveToHead(2));
        System.out.print("after searchMoveToHead(2) = ");
        listFromInserts.display();
        System.out.println("searchMoveToHead(2) again = " + listFromInserts.searchMoveToHead(2));

        System.out.println();
        GenericLinkedList<Integer> numbers = new GenericLinkedList<>();
        numbers.create(new Integer[]{8, 3, 11, 5});
        System.out.print("generic Integer list = ");
        numbers.display();
        System.out.println("max = " + numbers.max() + ", maxRecursive = " + numbers.maxRecursive());
        System.out.println("min = " + numbers.min() + ", minRecursive = " + numbers.minRecursive());
        System.out.println("search(11) = " + numbers.search(11) + ", searchRecursive(11) = " + numbers.searchRecursive(11));

        GenericLinkedList<String> words = new GenericLinkedList<>();
        words.create(new String[]{"mango", "apple", "kiwi"});
        words.insert(1, "banana");
        System.out.print("generic String list = ");
        words.display();
        System.out.println("max = " + words.max() + ", min = " + words.min());
        System.out.println("deleted = " + words.delete(0));
        System.out.print("after delete(0) = ");
        words.display();
    }
}
