package m26_queue;

import java.util.NoSuchElementException;

public class QueueDemo {
    public static void main(String[] args) {
        demoSinglePointerQueue();
        demoArrayQueue();
        demoArrayQueueDrawback();
        demoCircularQueue();
        demoGenericCircularQueue();
        demoLinkedQueue();
        demoCircularDeque();
        demoLinkedDeque();
    }

    private static void demoSinglePointerQueue() {
        System.out.println("=== SinglePointerQueue ===");
        SinglePointerQueue queue = new SinglePointerQueue(4);
        System.out.println("new queue(4): isEmpty = " + queue.isEmpty() + ", size = " + queue.size());
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.print("after enqueue 10, 20, 30 (front to rear) = ");
        queue.display();
        System.out.println("size = " + queue.size());

        System.out.println();
        System.out.println("dequeue = " + queue.dequeue());
        System.out.print("after dequeue, the rest shifted left = ");
        queue.display();

        System.out.println();
        queue.enqueue(40);
        queue.enqueue(50);
        System.out.print("after enqueue 40, 50 = ");
        queue.display();
        System.out.println("isFull = " + queue.isFull() + ", size = " + queue.size());
        try {
            queue.enqueue(60);
        } catch (IllegalStateException e) {
            System.out.println("enqueue(60) on full queue = " + e.getMessage());
        }

        System.out.println();
        System.out.print("dequeue all = ");
        while (!queue.isEmpty()) {
            System.out.print(queue.dequeue() + " ");
        }
        System.out.println();
        System.out.println("isEmpty = " + queue.isEmpty() + ", size = " + queue.size());
        System.out.print("display on empty queue = ");
        queue.display();
        try {
            queue.dequeue();
        } catch (NoSuchElementException e) {
            System.out.println("dequeue on empty queue = " + e.getMessage());
        }
        queue.enqueue(70);
        System.out.print("enqueue 70 after emptying = ");
        queue.display();

        System.out.println();
        SinglePointerQueue single = new SinglePointerQueue(1);
        single.enqueue(-5);
        System.out.println("queue(1) after enqueue -5: isFull = " + single.isFull());
        System.out.println("dequeue = " + single.dequeue() + ", isEmpty = " + single.isEmpty());
        try {
            new SinglePointerQueue(0);
        } catch (IllegalArgumentException e) {
            System.out.println("new SinglePointerQueue(0) = " + e.getMessage());
        }
        System.out.println();
    }

    private static void demoArrayQueue() {
        System.out.println("=== ArrayQueue ===");
        ArrayQueue queue = new ArrayQueue(4);
        System.out.println("new queue(4): isEmpty = " + queue.isEmpty() + ", size = " + queue.size());
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.print("after enqueue 10, 20, 30 (front to rear) = ");
        queue.display();
        System.out.println("size = " + queue.size() + ", first = " + queue.first() + ", last = " + queue.last());

        System.out.println();
        queue.enqueue(40);
        System.out.print("after enqueue 40 = ");
        queue.display();
        System.out.println("isFull = " + queue.isFull() + ", size = " + queue.size());
        try {
            queue.enqueue(50);
        } catch (IllegalStateException e) {
            System.out.println("enqueue(50) on full queue = " + e.getMessage());
        }

        System.out.println();
        System.out.println("dequeue = " + queue.dequeue());
        System.out.print("after dequeue = ");
        queue.display();
        System.out.println("size = " + queue.size() + ", first = " + queue.first() + ", last = " + queue.last());
        System.out.println("first again = " + queue.first() + ", size still = " + queue.size());

        System.out.println();
        System.out.print("dequeue all = ");
        while (!queue.isEmpty()) {
            System.out.print(queue.dequeue() + " ");
        }
        System.out.println();
        System.out.println("isEmpty = " + queue.isEmpty() + ", size = " + queue.size());
        System.out.print("display on empty queue = ");
        queue.display();
        try {
            queue.dequeue();
        } catch (NoSuchElementException e) {
            System.out.println("dequeue on empty queue = " + e.getMessage());
        }
        try {
            queue.first();
        } catch (NoSuchElementException e) {
            System.out.println("first on empty queue = " + e.getMessage());
        }
        try {
            queue.last();
        } catch (NoSuchElementException e) {
            System.out.println("last on empty queue = " + e.getMessage());
        }

        System.out.println();
        ArrayQueue single = new ArrayQueue(1);
        single.enqueue(-5);
        System.out.println("queue(1) after enqueue -5: isFull = " + single.isFull()
                + ", first = " + single.first() + ", last = " + single.last());
        System.out.println("dequeue = " + single.dequeue() + ", isEmpty = " + single.isEmpty());
        try {
            new ArrayQueue(0);
        } catch (IllegalArgumentException e) {
            System.out.println("new ArrayQueue(0) = " + e.getMessage());
        }
        System.out.println();
    }

    private static void demoArrayQueueDrawback() {
        System.out.println("=== ArrayQueue drawback ===");
    }

    private static void demoCircularQueue() {
        System.out.println("=== CircularQueue ===");
    }

    private static void demoGenericCircularQueue() {
        System.out.println("=== GenericCircularQueue ===");
    }

    private static void demoLinkedQueue() {
        System.out.println("=== LinkedQueue ===");
    }

    private static void demoCircularDeque() {
        System.out.println("=== CircularDeque ===");
    }

    private static void demoLinkedDeque() {
        System.out.println("=== LinkedDeque ===");
    }
}
