# 27. Queue Techniques

Package: `m27_queue_techniques` · 3 lectures · 2 done

Put this line at the top of every file in this folder:

```java
package m27_queue_techniques;
```

## Queue Implementations Using Stacks

- [x] Queue Using Two Stack
- [x] Queue Using Two Stack - Solution

## Stack Implementations Using Queue

- [ ] Implement Stack Using Queue

## Code in this folder

Lectures 1 and 2 are done. Lecture 3 (Implement Stack Using Queue) is not started yet.

| File | What it has |
|------|-------------|
| `TwoStackQueue.java` | Lectures 1 and 2. `int` queue made from two m23 `ArrayStack`s, using only stack operations. `inStack` (the lecture's S1) takes every enqueue and `outStack` (S2) gives every dequeue. When `outStack` is empty and the front element is needed, `moveInToOut()` pops everything from `inStack` and pushes it onto `outStack`; this reverses the order, so the oldest element ends up on top of `outStack`. The move happens only when `outStack` is empty: if it ran while `outStack` still had elements, newer elements would land on top of older ones and come out first (enqueue 1, 2, `first()`, enqueue 3, dequeue would give 3 instead of 1). `dequeue()` and `first()` throw `NoSuchElementException("Queue is empty")` when both stacks are empty, move if `outStack` is empty, and then `pop` or `stackTop` from `outStack`. `enqueue` is O(1). `dequeue` and `first` are O(n) when they move, but O(1) amortized, because every element is moved only once. Fixed capacity like m26's array queues: `size()` is the sum of both stack sizes, `isFull()` is `size() == capacity`, a full queue throws `IllegalStateException("Queue is full")` and a capacity of 0 or less throws `IllegalArgumentException("Capacity must be positive: x")`. Both stacks get the full capacity, because all elements can be in one stack at the same time, so capacity n uses 2n `int` slots and `Integer.MAX_VALUE` fails with `OutOfMemoryError` inside `ArrayStack`. `display()` prints front to rear without changing the stacks: `outStack` with `peek(1)` up to `peek(size)` (its top is the front), then `inStack` with `peek(size)` down to `peek(1)` (its bottom is its oldest element) |
| `QueueTechniquesDemo.java` | `main()` that calls one section method per lecture. `demoTwoStackQueue()` enqueues 10, 20, 30 into a capacity-4 queue and shows `display`, `size` and `first` (the `first()` call does the first move), dequeues 10, enqueues 40 and 50 while 20 and 30 are still in `outStack` and shows that `display` is `20 30 40 50`, that `isFull` is true and the error for one more enqueue, dequeues everything in the order 20 30 40 50 (the second move happens after 30), shows the errors for `dequeue` and `first` on the empty queue, enqueues -5 to show the queue works again after it was emptied, and shows the error for `new TwoStackQueue(0)` |

[Back to all modules](../../README.md)
