package m27_queue_techniques;

import java.util.NoSuchElementException;

public class QueueTechniquesDemo {
    public static void main(String[] args) {
        demoTwoStackQueue();
    }

    // capacity-4 queue:
    // 1. enqueue 10, 20, 30; show display, size and first (first() does the first move from inStack to outStack)
    // 2. dequeue once (prints 10, popped from outStack); show display
    // 3. enqueue 40 and 50 (they go to inStack while 20 and 30 are still in outStack); show display,
    //    isFull, and the error for one more enqueue
    // 4. dequeue until empty; the order must be 20 30 40 50
    // 5. errors for dequeue and first on the empty queue
    // 6. enqueue -5 and show first, to show the queue works again after it was emptied
    // 7. error for new TwoStackQueue(0)
    private static void demoTwoStackQueue() {
        System.out.println("=== TwoStackQueue ===");
        TwoStackQueue queue = new TwoStackQueue(4);
        System.out.println("new queue: isEmpty = " + queue.isEmpty() + ", size = " + queue.size());
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.print("after enqueue 10, 20, 30 = ");
        queue.display();
        System.out.println("size = " + queue.size() + ", first = " + queue.first());

        System.out.println();
        System.out.println("dequeue = " + queue.dequeue());
        System.out.print("after dequeue = ");
        queue.display();
        System.out.println("size = " + queue.size() + ", first = " + queue.first());

        System.out.println();
        queue.enqueue(40);
        queue.enqueue(50);
        System.out.print("after enqueue 40, 50 (20, 30 still in outStack) = ");
        queue.display();
        System.out.println("size = " + queue.size() + ", isFull = " + queue.isFull());
        try {
            queue.enqueue(60);
        } catch (IllegalStateException e) {
            System.out.println("enqueue 60 on full queue = " + e.getMessage());
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
        try {
            queue.first();
        } catch (NoSuchElementException e) {
            System.out.println("first on empty queue = " + e.getMessage());
        }

        System.out.println();
        queue.enqueue(-5);
        System.out.println("enqueue -5 after emptying: first = " + queue.first() + ", size = " + queue.size());
        System.out.println("dequeue = " + queue.dequeue() + ", isEmpty = " + queue.isEmpty());

        System.out.println();
        try {
            new TwoStackQueue(0);
        } catch (IllegalArgumentException e) {
            System.out.println("new TwoStackQueue(0) = " + e.getMessage());
        }
        System.out.println();
    }
}
