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
        demoArrayDeque();
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
        ArrayQueue queue = new ArrayQueue(3);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.print("queue(3) after enqueue 10, 20, 30 = ");
        queue.display();
        System.out.print("dequeue all = ");
        while (!queue.isEmpty()) {
            System.out.print(queue.dequeue() + " ");
        }
        System.out.println();
        System.out.println("isEmpty = " + queue.isEmpty() + ", isFull = " + queue.isFull() + ", size = " + queue.size());
        try {
            queue.enqueue(40);
        } catch (IllegalStateException e) {
            System.out.println("enqueue(40) on an empty queue = " + e.getMessage());
        }

        System.out.println();
        ArrayQueue half = new ArrayQueue(4);
        half.enqueue(1);
        half.enqueue(2);
        half.enqueue(3);
        half.enqueue(4);
        half.dequeue();
        half.dequeue();
        System.out.print("queue(4) after enqueue 1, 2, 3, 4 and two dequeues = ");
        half.display();
        System.out.println("size = " + half.size() + ", isFull = " + half.isFull());
        try {
            half.enqueue(5);
        } catch (IllegalStateException e) {
            System.out.println("enqueue(5) with 2 free slots = " + e.getMessage());
        }
        System.out.println("front and rear only move forward, so a dequeued slot is never used again");
        System.out.println();
    }

    private static void demoCircularQueue() {
        System.out.println("=== CircularQueue ===");
        CircularQueue queue = new CircularQueue(3);
        System.out.println("new queue(3): isEmpty = " + queue.isEmpty() + ", size = " + queue.size());
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.print("after enqueue 10, 20, 30 = ");
        queue.display();
        System.out.println("isFull = " + queue.isFull() + ", size = " + queue.size()
                + ", first = " + queue.first() + ", last = " + queue.last());
        try {
            queue.enqueue(40);
        } catch (IllegalStateException e) {
            System.out.println("enqueue(40) on full queue = " + e.getMessage());
        }

        System.out.println();
        System.out.println("dequeue = " + queue.dequeue());
        System.out.println("dequeue = " + queue.dequeue());
        queue.enqueue(40);
        queue.enqueue(50);
        System.out.print("after enqueue 40, 50 (rear wrapped to the start of the array) = ");
        queue.display();
        System.out.println("isFull = " + queue.isFull() + ", size = " + queue.size()
                + ", first = " + queue.first() + ", last = " + queue.last());

        System.out.println();
        System.out.print("dequeue all = ");
        while (!queue.isEmpty()) {
            System.out.print(queue.dequeue() + " ");
        }
        System.out.println();
        System.out.println("isEmpty = " + queue.isEmpty() + ", isFull = " + queue.isFull() + ", size = " + queue.size());
        queue.enqueue(60);
        System.out.print("enqueue 60 after emptying, the ArrayQueue drawback is gone = ");
        queue.display();
        System.out.println("dequeue = " + queue.dequeue());
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
        CircularQueue single = new CircularQueue(1);
        single.enqueue(-5);
        System.out.println("queue(1) after enqueue -5: isFull = " + single.isFull()
                + ", first = " + single.first() + ", last = " + single.last());
        System.out.println("dequeue = " + single.dequeue() + ", isEmpty = " + single.isEmpty());
        try {
            new CircularQueue(0);
        } catch (IllegalArgumentException e) {
            System.out.println("new CircularQueue(0) = " + e.getMessage());
        }
        try {
            new CircularQueue(Integer.MAX_VALUE);
        } catch (IllegalArgumentException e) {
            System.out.println("new CircularQueue(Integer.MAX_VALUE) = " + e.getMessage());
        }
        System.out.println();
    }

    private static void demoGenericCircularQueue() {
        System.out.println("=== GenericCircularQueue ===");
        GenericCircularQueue<String> queue = new GenericCircularQueue<>(3);
        System.out.println("new queue<String>(3): isEmpty = " + queue.isEmpty() + ", size = " + queue.size());
        queue.enqueue("red");
        queue.enqueue("green");
        queue.enqueue("blue");
        System.out.print("after enqueue red, green, blue = ");
        queue.display();
        System.out.println("isFull = " + queue.isFull() + ", size = " + queue.size()
                + ", first = " + queue.first() + ", last = " + queue.last());
        try {
            queue.enqueue("black");
        } catch (IllegalStateException e) {
            System.out.println("enqueue(\"black\") on full queue = " + e.getMessage());
        }
        try {
            queue.enqueue(null);
        } catch (NullPointerException e) {
            System.out.println("enqueue(null) = " + e.getMessage());
        }

        System.out.println();
        System.out.println("dequeue = " + queue.dequeue());
        System.out.println("dequeue = " + queue.dequeue());
        queue.enqueue("yellow");
        queue.enqueue("white");
        System.out.print("after enqueue yellow, white (rear wrapped to the start of the array) = ");
        queue.display();
        System.out.println("isFull = " + queue.isFull() + ", size = " + queue.size()
                + ", first = " + queue.first() + ", last = " + queue.last());

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
        GenericCircularQueue<Integer> numbers = new GenericCircularQueue<>(2);
        numbers.enqueue(-5);
        numbers.enqueue(7);
        System.out.print("queue<Integer>(2) after enqueue -5, 7 = ");
        numbers.display();
        System.out.println("isFull = " + numbers.isFull() + ", dequeue = " + numbers.dequeue() + ", size = " + numbers.size());
        try {
            new GenericCircularQueue<Integer>(0);
        } catch (IllegalArgumentException e) {
            System.out.println("new GenericCircularQueue<>(0) = " + e.getMessage());
        }
        try {
            new GenericCircularQueue<Integer>(Integer.MAX_VALUE);
        } catch (IllegalArgumentException e) {
            System.out.println("new GenericCircularQueue<>(Integer.MAX_VALUE) = " + e.getMessage());
        }
        System.out.println();
    }

    private static void demoLinkedQueue() {
        System.out.println("=== LinkedQueue ===");
        LinkedQueue queue = new LinkedQueue();
        System.out.println("new queue: isEmpty = " + queue.isEmpty() + ", size = " + queue.size());
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.print("after enqueue 10, 20, 30 = ");
        queue.display();
        System.out.println("size = " + queue.size() + ", first = " + queue.first() + ", last = " + queue.last());

        System.out.println();
        System.out.println("dequeue = " + queue.dequeue());
        System.out.print("after dequeue = ");
        queue.display();
        System.out.println("size = " + queue.size() + ", first = " + queue.first() + ", last = " + queue.last());

        System.out.println();
        for (int value = 40; value <= 100; value += 10) {
            queue.enqueue(value);
        }
        System.out.print("after enqueue 40 to 100, no capacity limit = ");
        queue.display();
        System.out.println("size = " + queue.size() + ", last = " + queue.last());

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
        queue.enqueue(-5);
        System.out.println("enqueue -5 after emptying: first = " + queue.first() + ", last = " + queue.last()
                + ", size = " + queue.size());
        System.out.println("dequeue = " + queue.dequeue() + ", isEmpty = " + queue.isEmpty());
        System.out.println();
    }

    private static void demoArrayDeque() {
        System.out.println("=== ArrayDeque ===");
        ArrayDeque deque = new ArrayDeque(5);
        System.out.println("new deque(5): isEmpty = " + deque.isEmpty() + ", size = " + deque.size());
        try {
            deque.insertFront(5);
        } catch (IllegalStateException e) {
            System.out.println("insertFront(5) on a new deque = " + e.getMessage());
        }
        deque.insertRear(10);
        deque.insertRear(20);
        deque.insertRear(30);
        System.out.print("after insertRear 10, 20, 30 = ");
        deque.display();
        System.out.println("size = " + deque.size() + ", first = " + deque.first() + ", last = " + deque.last());

        System.out.println();
        System.out.println("deleteFront = " + deque.deleteFront());
        System.out.println("deleteFront = " + deque.deleteFront());
        System.out.print("after 2 deleteFront = ");
        deque.display();
        deque.insertFront(20);
        deque.insertFront(10);
        System.out.print("after insertFront 20, 10 (the free slots at the front are used again) = ");
        deque.display();
        System.out.println("size = " + deque.size() + ", first = " + deque.first() + ", last = " + deque.last());
        try {
            deque.insertFront(5);
        } catch (IllegalStateException e) {
            System.out.println("insertFront(5) with no slot before index 0 = " + e.getMessage());
        }

        System.out.println();
        deque.insertRear(40);
        deque.insertRear(50);
        System.out.print("after insertRear 40, 50 = ");
        deque.display();
        System.out.println("isFull = " + deque.isFull() + ", size = " + deque.size());
        try {
            deque.insertRear(60);
        } catch (IllegalStateException e) {
            System.out.println("insertRear(60) on full deque = " + e.getMessage());
        }
        System.out.println("deleteFront = " + deque.deleteFront());
        System.out.println("isFull = " + deque.isFull() + ", size = " + deque.size());
        try {
            deque.insertRear(60);
        } catch (IllegalStateException e) {
            System.out.println("insertRear(60) with a free slot only at the front = " + e.getMessage());
        }

        System.out.println();
        System.out.println("deleteRear = " + deque.deleteRear());
        System.out.print("after deleteRear = ");
        deque.display();
        System.out.println("last = " + deque.last());
        System.out.print("deleteRear until empty = ");
        while (!deque.isEmpty()) {
            System.out.print(deque.deleteRear() + " ");
        }
        System.out.println();
        System.out.println("isEmpty = " + deque.isEmpty() + ", size = " + deque.size());
        System.out.print("display on empty deque = ");
        deque.display();
        try {
            deque.deleteFront();
        } catch (NoSuchElementException e) {
            System.out.println("deleteFront on empty deque = " + e.getMessage());
        }
        try {
            deque.deleteRear();
        } catch (NoSuchElementException e) {
            System.out.println("deleteRear on empty deque = " + e.getMessage());
        }
        try {
            deque.first();
        } catch (NoSuchElementException e) {
            System.out.println("first on empty deque = " + e.getMessage());
        }
        try {
            deque.last();
        } catch (NoSuchElementException e) {
            System.out.println("last on empty deque = " + e.getMessage());
        }

        System.out.println();
        ArrayDeque single = new ArrayDeque(1);
        single.insertRear(-5);
        System.out.println("deque(1) after insertRear -5: isFull = " + single.isFull()
                + ", first = " + single.first() + ", last = " + single.last());
        System.out.println("deleteFront = " + single.deleteFront() + ", isEmpty = " + single.isEmpty());
        try {
            new ArrayDeque(0);
        } catch (IllegalArgumentException e) {
            System.out.println("new ArrayDeque(0) = " + e.getMessage());
        }
        System.out.println();
    }

    private static void demoLinkedDeque() {
        System.out.println("=== LinkedDeque ===");
        LinkedDeque deque = new LinkedDeque();
        System.out.println("new deque: isEmpty = " + deque.isEmpty() + ", size = " + deque.size());
        deque.insertFront(20);
        deque.insertFront(10);
        deque.insertRear(30);
        deque.insertRear(40);
        System.out.print("after insertFront 20, 10 and insertRear 30, 40 (insertFront works on a new deque, unlike ArrayDeque) = ");
        deque.display();
        System.out.println("size = " + deque.size() + ", first = " + deque.first() + ", last = " + deque.last());

        System.out.println();
        System.out.println("deleteFront = " + deque.deleteFront());
        System.out.println("deleteRear = " + deque.deleteRear());
        System.out.print("after deleteFront and deleteRear = ");
        deque.display();
        System.out.println("size = " + deque.size() + ", first = " + deque.first() + ", last = " + deque.last());

        System.out.println();
        for (int value = 1; value <= 5; value++) {
            deque.insertFront(-value);
            deque.insertRear(100 + value);
        }
        System.out.print("after 5 more at each end, no capacity limit = ");
        deque.display();
        System.out.println("size = " + deque.size() + ", first = " + deque.first() + ", last = " + deque.last());

        System.out.println();
        System.out.print("deleteRear until empty = ");
        while (!deque.isEmpty()) {
            System.out.print(deque.deleteRear() + " ");
        }
        System.out.println();
        System.out.println("isEmpty = " + deque.isEmpty() + ", size = " + deque.size());
        System.out.print("display on empty deque = ");
        deque.display();
        try {
            deque.deleteFront();
        } catch (NoSuchElementException e) {
            System.out.println("deleteFront on empty deque = " + e.getMessage());
        }
        try {
            deque.deleteRear();
        } catch (NoSuchElementException e) {
            System.out.println("deleteRear on empty deque = " + e.getMessage());
        }
        try {
            deque.first();
        } catch (NoSuchElementException e) {
            System.out.println("first on empty deque = " + e.getMessage());
        }
        try {
            deque.last();
        } catch (NoSuchElementException e) {
            System.out.println("last on empty deque = " + e.getMessage());
        }

        System.out.println();
        deque.insertRear(-5);
        System.out.println("insertRear -5 after emptying: first = " + deque.first() + ", last = " + deque.last()
                + ", size = " + deque.size());
        System.out.println("deleteFront = " + deque.deleteFront() + ", isEmpty = " + deque.isEmpty());
        System.out.println();
    }
}
