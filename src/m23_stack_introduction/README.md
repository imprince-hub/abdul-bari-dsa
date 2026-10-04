# 23. Stack Introduction

Package: `m23_stack_introduction` · 8 lectures · 2 done

Put this line at the top of every file in this folder:

```java
package m23_stack_introduction;
```

## Introduction and ADT

- [x] Stack Intro
- [x] Stack ADT

## Stack Implementation Using Array

- [ ] Stack using Array
- [ ] Stack using Array - Solution
- [ ] Generic Stack Class using Arrays - Solution

## Stack Implementation Using LinkedList and Generics

- [ ] Stack using LinkedList
- [ ] Stack using LinkedList - Solution
- [ ] Stack using Generic Array - Solution

## Code in this folder

Stack Intro and Stack ADT are theory, so they have no code. The files below are skeletons for the next lectures: method names are in place and unfinished methods throw `UnsupportedOperationException("TODO")`.

| File | What it has |
|------|-------------|
| `ArrayStack.java` | Fixed-size `int` stack on an array; `top` starts at -1. Done: the constructor rejects a capacity of 0 or less with `IllegalArgumentException`; `push(value)` throws `IllegalStateException("Stack overflow")` when the stack is full; `isFull()` checks `top == elements.length - 1`. Still stubs: `pop()`, `peek(position)`, `stackTop()`, `isEmpty()`, `size()`, `display()` |
| `GenericArrayStack.java` | Array stack for any `T`, same methods as `ArrayStack`; all stubs |
| `Node.java` | One node: `data`, `next`, and a constructor |
| `LinkedStack.java` | `int` stack on a linked list: `top` is the head of the list, plus a `size` counter. `push`, `pop`, `peek(position)`, `stackTop`, `isEmpty`, `size`, `display`; all stubs. No `isFull()`, because `new` in Java never returns `null` |
| `GenericLinkedStack.java` | Linked stack for any `T`, with a private nested `Node<T>`; same methods as `LinkedStack`; all stubs |
| `StackDemo.java` | `main()` that calls one section method per class; each section only prints its heading so far |

[Back to all modules](../../README.md)
