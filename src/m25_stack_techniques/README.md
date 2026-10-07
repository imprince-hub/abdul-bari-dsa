# 25. Stack Techniques

Package: `m25_stack_techniques` · 4 lectures · 1 done

Put this line at the top of every file in this folder:

```java
package m25_stack_techniques;
```

## Monotonic Stack

- [x] Monotonic Stack

## Iterative Approaches using Stack

- [ ] Recursion to Loop using Stack
- [ ] Iterative Tower Of Hanoi Using Stack-Solution

## Stack in Java

- [ ] Stack Built in Class

## Code in this folder

Lecture 1 is complete. Files for lectures 2 to 4 come after those lectures.

| File | What it has |
|------|-------------|
| `NextGreaterElement.java` | Lecture 1. Static utility class (private constructor). `nextGreaterIndices(values)` returns, for each element, the index of the first element to its right that is strictly greater, or `NONE` (-1). It returns indices, not values, because a value of -1 for "none" is ambiguous when the array has -1 in it. It scans right to left with m23's `GenericArrayStack<Integer>` of indices (capacity = `values.length`): for each `i`, it first pops every index whose value is not strictly greater than `values[i]` (it can never be the answer for `i` or anything to its left), then the top is the answer (empty stack means `NONE`), then `i` is pushed. So the values on the stack always decrease from bottom to top, which is why it is called a monotonic stack. Every index is pushed once and popped at most once, so it is O(n). `nextGreaterIndicesBruteForce(values)` is the O(n^2) version: for each `i`, `result[i]` starts as `NONE` and the scan to the right stops (`break`) at the first greater value. `{}` returns `{}` before the stack is made (capacity 0 is not allowed), `null` throws `NullPointerException("values must not be null")` |
| `StackTechniquesDemo.java` | `main()` that calls one section method per lecture. `demoNextGreaterElement()` runs eight arrays (`{4, 5, 2, 25}`, `{13, 7, 6, 12}`, `{-3, -1, -5}`, increasing, decreasing, `{2, 2}`, `{7}`, `{}`). For each one, `printNextGreater(values, nextIndices)` prints the array, the returned indices and one line per element like `4 -> 5` or `25 -> none`, then it prints whether the brute force result is the same (`Arrays.equals`). Last, it shows the error for `null` |

[Back to all modules](../../README.md)
