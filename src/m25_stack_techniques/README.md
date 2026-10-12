# 25. Stack Techniques

Package: `m25_stack_techniques` · 4 lectures · 3 done

Put this line at the top of every file in this folder:

```java
package m25_stack_techniques;
```

## Monotonic Stack

- [x] Monotonic Stack

## Iterative Approaches using Stack

- [x] Recursion to Loop using Stack
- [x] Iterative Tower Of Hanoi Using Stack-Solution

## Stack in Java

- [ ] Stack Built in Class

## Code in this folder

Lectures 1 to 3 are complete. Lecture 4 comes later.

| File | What it has |
|------|-------------|
| `NextGreaterElement.java` | Lecture 1. Static utility class (private constructor). `nextGreaterIndices(values)` returns, for each element, the index of the first element to its right that is strictly greater, or `NONE` (-1). It returns indices, not values, because a value of -1 for "none" is ambiguous when the array has -1 in it. It scans right to left with m23's `GenericArrayStack<Integer>` of indices (capacity = `values.length`): for each `i`, it first pops every index whose value is not strictly greater than `values[i]` (it can never be the answer for `i` or anything to its left), then the top is the answer (empty stack means `NONE`), then `i` is pushed. So the values on the stack always decrease from bottom to top, which is why it is called a monotonic stack. Every index is pushed once and popped at most once, so it is O(n). `nextGreaterIndicesBruteForce(values)` is the O(n^2) version: for each `i`, `result[i]` starts as `NONE` and the scan to the right stops (`break`) at the first greater value. `{}` returns `{}` before the stack is made (capacity 0 is not allowed), `null` throws `NullPointerException("values must not be null")` |
| `TowerOfHanoi.java` | Lectures 2 and 3. Static utility class (private constructor). Moves disks from rod A to rod C using rod B. `movesRecursive(disks)` is the lecture's `toh(n, from, via, to)` (first call, move disk n, second call) through the private helper `movesRecursive(n, from, via, to, moves)`. `movesIterative(disks)` gives the same moves with a loop and a programmer stack (m23's `GenericArrayStack<Call>`, capacity = `disks`) in place of the system stack. `Call` is a private nested class (like `Node` in m23) with the parameters of one call: `n`, `from`, `via`, `to`. Both versions return a `List<String>` of moves like `disk 1: A -> C`, built by `moveText(disk, from, to)`, instead of printing, so they can be compared with `List.equals`. The recursive helper returns at `n == 0`, so `n == 1` needs no special case: the two calls do nothing and only the move of disk 1 is added. Every call adds one move, from its own `from` to its own `to`; the first call passes `(from, to, via)`, so inside it `to` is the caller's `via`. `0` disks returns `[]`; fewer than 0 or more than `MAX_DISKS` (20) throws `IllegalArgumentException("disks must be between 0 and 20: x")` through `checkDisks(disks)`. `movesIterative` keeps a `current` call: while `current.n > 0` it pushes `current` (the call waits on the stack with its own rods) and moves on to the first call `(n - 1, from, to, via)`; when `current.n` is 0 it pops, adds the popped call's move (disk `popped.n`, `from -> to`) and moves on to the second call `(n - 1, via, from, to)`. It stops when `current.n` is 0 and the stack is empty. Every pending call is a new `Call` object, so the stack never holds more than `disks` calls. `0` disks returns `[]` before the stack is made |
| `StackTechniquesDemo.java` | `main()` that calls one section method per lecture. `demoNextGreaterElement()` runs eight arrays (`{4, 5, 2, 25}`, `{13, 7, 6, 12}`, `{-3, -1, -5}`, increasing, decreasing, `{2, 2}`, `{7}`, `{}`). For each one, `printNextGreater(values, nextIndices)` prints the array, the returned indices and one line per element like `4 -> 5` or `25 -> none`, then it prints whether the brute force result is the same (`Arrays.equals`). Last, it shows the error for `null`. `demoTowerOfHanoi()` prints the moves for 0 to 3 disks from both versions and whether they agree (`List.equals`), then only the move count for 10 disks (1023) and whether they agree, then the errors from both versions for -1 and 21 disks |

[Back to all modules](../../README.md)
