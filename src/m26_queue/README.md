# 26. Queue

Package: `m26_queue` · 15 lectures · 6 done

Put this line at the top of every file in this folder:

```java
package m26_queue;
```

## Introduction and Basic Queue Implementations

- [x] Introduction to Queue
- [x] Queue Using Single Pointer
- [x] Queue using Array - Two Pointer
- [x] Queue using Array - Two Pointer Solution
- [x] Implementation of Queue

## Circular Queue

- [x] Drawbacks of Queue using two pointers
- [ ] Circular Queue
- [ ] Circular Queue Solution
- [ ] Circular Queue Generic Solution

## Queue using Linked List

- [ ] Queue using LinkedList
- [ ] Queue using LinkedList Solution

## Double Ended Queue (Deque)

- [ ] Double Ended Queue - Deque
- [ ] Deque using LinkedList
- [ ] Deque using Array - Solution
- [ ] Deque using LinkedList - Solution

## Code in this folder

Introduction to Queue is theory, and Implementation of Queue repeats the two-pointer queue on the board, so neither has its own code. `SinglePointerQueue` and `ArrayQueue` are complete. The other files are skeletons: method names are in place and unfinished methods throw `UnsupportedOperationException("TODO")`. All classes use the same messages: a capacity of 0 or less throws `IllegalArgumentException("Capacity must be positive: x")`, a full queue throws `IllegalStateException("Queue is full")` and an empty queue throws `NoSuchElementException("Queue is empty")`. The deques say `Deque` instead of `Queue`.

| File | What it has |
|------|-------------|
| `SinglePointerQueue.java` | Lecture 2. `int` queue on an array with only `rear` (starts at -1); the first element is always at index 0. `enqueue(value)` puts the value at `++rear` and throws `IllegalStateException("Queue is full")` when `isFull()`. `dequeue()` saves `elements[0]`, moves every element from index 1 to `rear` one step left (`elements[i - 1] = elements[i]`) and then moves `rear` down, so it is O(n) while `enqueue` is O(1); it throws `NoSuchElementException("Queue is empty")` on an empty queue. The shift loop and `display()` stop at `rear`, not at `elements.length`, because the slots after `rear` still hold old values. `isEmpty()` is `rear == -1`, `isFull()` is `rear == elements.length - 1`, `size()` is `rear + 1`. Once the queue is emptied, `rear` is back at -1, so index 0 is used again and no space is lost (unlike `ArrayQueue`, see lecture 6) |
| `ArrayQueue.java` | Lectures 3 to 6. `int` queue on an array with two pointers: `front` is the index just before the first element and `rear` is the index of the last element, both start at -1. `enqueue(value)` puts the value at `++rear` and `dequeue()` returns `elements[++front]`. Both use pre-increment, because the pointer has to move before the array is used; `elements[front++]` would read index -1 first. Both are O(1), because nothing is shifted: a dequeued value stays in the array and only `front` moves past it. `first()` reads `elements[front + 1]` without changing `front` (`++front` there would turn it into a dequeue), `last()` reads `elements[rear]`, and `dequeue`, `first` and `last` throw `NoSuchElementException("Queue is empty")` on an empty queue. `isEmpty()` is `front == rear`, `isFull()` is `rear == elements.length - 1`, `size()` is `rear - front` (no `+ 1`, because `front` is one before the first element). `display()` prints from `front + 1` to `rear`. The pointers are not reset when the queue becomes empty, on purpose, for the lecture 6 drawback |
| `CircularQueue.java` | Lectures 7 and 8. Same methods as `ArrayQueue`, but `front` and `rear` start at 0 and wrap around, plus a private `next(index)` helper. One slot always stays empty, so the array has `capacity + 1` slots and `new CircularQueue(5)` holds 5 elements. `Integer.MAX_VALUE` is rejected because `capacity + 1` would overflow. All stubs |
| `GenericCircularQueue.java` | Lecture 9. `CircularQueue` for any `T` (`Object[]` cast to `T[]`, like m23's `GenericArrayStack`). `enqueue(null)` throws `NullPointerException`, and `dequeue` clears the slot it reads. All stubs |
| `Node.java` | One node: `data`, `next`, and a constructor |
| `LinkedQueue.java` | Lectures 10 and 11. `int` queue on a singly linked list: `front` is the first node and `rear` the last, both `null` when empty. Fixed `capacity`, like m23's `LinkedStack`. Same methods as `ArrayQueue`; all stubs |
| `CircularDeque.java` | Lectures 12 and 14. `int` deque on a circular array, same pointer rules as `CircularQueue`. `insertFront`, `insertRear`, `deleteFront`, `deleteRear`, `first`, `last`, `isEmpty`, `isFull`, `size`, `display`, plus private `next(index)` and `previous(index)`. All stubs |
| `DoublyNode.java` | One node for the deque: `data`, `next`, `prev`, and a constructor |
| `LinkedDeque.java` | Lectures 13 and 15. `int` deque on a doubly linked list: `front` and `rear` are both `null` when empty, fixed `capacity`. Same public methods as `CircularDeque`; all stubs |
| `QueueDemo.java` | `main()` that calls one section method per class, plus `demoArrayQueueDrawback()` for lecture 6. `demoSinglePointerQueue()` enqueues 10, 20, 30 into a capacity-4 queue, dequeues once to show the shift, fills the queue and shows the error for an enqueue on a full queue, dequeues everything and shows the error for a dequeue on an empty queue, then enqueues 70 to show the queue works again after emptying. Last, it shows a capacity-1 queue and the error for `new SinglePointerQueue(0)`. `demoArrayQueue()` enqueues 10, 20, 30 into a capacity-4 queue and shows `size`, `first` and `last`, fills it and shows the error for an enqueue on a full queue, dequeues once and shows that `first` is now 20 and that a second `first()` removes nothing, dequeues everything and shows the errors for `dequeue`, `first` and `last` on an empty queue, then shows a capacity-1 queue and the error for `new ArrayQueue(0)`. `demoArrayQueueDrawback()` (lecture 6) fills a capacity-3 queue and dequeues everything, then shows that `isEmpty` and `isFull` are both true and that one more enqueue throws `Queue is full`. Then it fills a capacity-4 queue and dequeues twice: `size` is 2 but `isFull` is true, so the 2 dequeued slots are lost. The other sections only print their heading so far |

[Back to all modules](../../README.md)
