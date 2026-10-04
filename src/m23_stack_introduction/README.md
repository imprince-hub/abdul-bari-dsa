# 23. Stack Introduction

Package: `m23_stack_introduction` · 8 lectures · 4 done

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
- [ ] Generic Stack Class using Arrays - Solution

## Stack Implementation Using LinkedList and Generics

- [ ] Stack using LinkedList
- [ ] Stack using LinkedList - Solution
- [ ] Stack using Generic Array - Solution

## Code in this folder

Stack Intro and Stack ADT are theory, so they have no code. `ArrayStack` is complete. The other files are skeletons for the next lectures: method names are in place and unfinished methods throw `UnsupportedOperationException("TODO")`.

| File | What it has |
|------|-------------|
| `ArrayStack.java` | Fixed-size `int` stack on an array; `top` starts at -1 and `elements.length` is the capacity. The constructor rejects a capacity of 0 or less with `IllegalArgumentException`; `push(value)` throws `IllegalStateException("Stack overflow")` when `isFull()`; `pop()` returns the top value and moves `top` down; `stackTop()` returns the top value without removing it; `peek(position)` counts from the top (position 1 = top) and reads index `top - position + 1`; `pop`, `stackTop` and `peek` throw `NoSuchElementException("Stack underflow")` on an empty stack, and `peek` throws `IndexOutOfBoundsException` for a position outside 1 to `size()`; `isEmpty()` is `top == -1`, `isFull()` is `top == elements.length - 1`, `size()` is `top + 1`; `display()` prints from top to bottom |
| `GenericArrayStack.java` | Array stack for any `T`, same methods as `ArrayStack`; all stubs |
| `Node.java` | One node: `data`, `next`, and a constructor |
| `LinkedStack.java` | `int` stack on a linked list: `top` is the head of the list, plus a `size` counter. `push`, `pop`, `peek(position)`, `stackTop`, `isEmpty`, `size`, `display`; all stubs. No `isFull()`, because `new` in Java never returns `null` |
| `GenericLinkedStack.java` | Linked stack for any `T`, with a private nested `Node<T>`; same methods as `LinkedStack`; all stubs |
| `StackDemo.java` | `main()` that calls one section method per class. `demoArrayStack()` pushes 10, 20, 30 into a capacity-5 stack and shows `display`, `size`, `stackTop` and `peek(1)` to `peek(3)`, plus the error for `peek(0)` and `peek(4)`; pops once; fills the stack and shows the error for a push on a full stack; pops everything and shows the errors for `pop`, `stackTop` and `peek(1)` on an empty stack; then shows a capacity-1 stack and the error for `new ArrayStack(0)`. The other sections only print their heading so far |

[Back to all modules](../../README.md)
