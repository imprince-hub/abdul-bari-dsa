# 23. Stack Introduction

Package: `m23_stack_introduction` · 8 lectures · 8 done

Put this line at the top of every file in this folder:

```java
package m23_stack_introduction;
```

## Introduction and ADT

- [x] Stack Intro
- [x] Stack ADT

## Stack Implementation Using Array

- [x] Stack using Array
- [x] Stack using Array - Solution
- [x] Generic Stack Class using Arrays - Solution

## Stack Implementation Using LinkedList and Generics

- [x] Stack using LinkedList
- [x] Stack using LinkedList - Solution
- [x] Stack using Generic Array - Solution

## Code in this folder

Stack Intro and Stack ADT are theory, so they have no code. `ArrayStack`, `GenericArrayStack` and `LinkedStack` are complete. The other file is a skeleton for the next lectures: method names are in place and unfinished methods throw `UnsupportedOperationException("TODO")`.

| File | What it has |
|------|-------------|
| `ArrayStack.java` | Fixed-size `int` stack on an array; `top` starts at -1 and `elements.length` is the capacity. The constructor rejects a capacity of 0 or less with `IllegalArgumentException`; `push(value)` throws `IllegalStateException("Stack overflow")` when `isFull()`; `pop()` returns the top value and moves `top` down; `stackTop()` returns the top value without removing it; `peek(position)` counts from the top (position 1 = top) and reads index `top - position + 1`; `pop`, `stackTop` and `peek` throw `NoSuchElementException("Stack underflow")` on an empty stack, and `peek` throws `IndexOutOfBoundsException` for a position outside 1 to `size()`; `isEmpty()` is `top == -1`, `isFull()` is `top == elements.length - 1`, `size()` is `top + 1`; `display()` prints from top to bottom |
| `GenericArrayStack.java` | Same stack as `ArrayStack` for any `T`. Java does not allow `new T[capacity]` (type erasure), so the constructor makes an `Object[]` and casts it to `T[]` with `@SuppressWarnings("unchecked")`; this is safe because the array never leaves the class. `push(null)` is rejected with `NullPointerException`. `pop()` sets the old top slot to `null` before moving `top` down, so the stack does not keep a reference to the popped object and it can be garbage collected. Same exceptions as `ArrayStack` |
| `Node.java` | One node: `data`, `next`, and a constructor |
| `LinkedStack.java` | `int` stack on a linked list with a fixed capacity: `top` is the head of the list, `size` counts the elements and `capacity` is the max. The constructor rejects a capacity of 0 or less with `IllegalArgumentException`; `push(value)` puts a new node at the head (this works on an empty stack too, because `top` is just `null` there) and throws `IllegalStateException("Stack overflow")` when `isFull()`; `pop()` returns the top value and moves `top` to the next node (the old node is then unreachable, so the garbage collector frees it); `stackTop()` returns the top value without removing it; `peek(position)` walks `position - 1` nodes from `top`, so it is O(position) instead of O(1) like the array version; `pop`, `stackTop` and `peek` throw `NoSuchElementException("Stack underflow")` on an empty stack, and `peek` throws `IndexOutOfBoundsException` for a position outside 1 to `size()`; `isEmpty()` is `size == 0`, `isFull()` is `size == capacity`; `display()` prints from top to bottom |
| `GenericLinkedStack.java` | Linked stack for any `T`, with a private nested `Node<T>` and the same fixed `capacity` design as `LinkedStack` (constructor, `isFull()`); all stubs |
| `StackDemo.java` | `main()` that calls one section method per class. `demoArrayStack()` pushes 10, 20, 30 into a capacity-5 stack and shows `display`, `size`, `stackTop` and `peek(1)` to `peek(3)`, plus the error for `peek(0)` and `peek(4)`; pops once; fills the stack and shows the error for a push on a full stack; pops everything and shows the errors for `pop`, `stackTop` and `peek(1)` on an empty stack; then shows a capacity-1 stack and the error for `new ArrayStack(0)`. `demoGenericArrayStack()` does the same with a `String` stack of colors (plus the error for `push(null)`), then shows an `Integer` stack and the error for capacity 0. `demoLinkedStack()` runs the same steps as `demoArrayStack()` on a capacity-4 `LinkedStack`. The `GenericLinkedStack` section only prints its heading so far |

[Back to all modules](../../README.md)
