# 24. Stack & Expression Notations

Package: `m24_stack_expression_notations` · 13 lectures · 9 done

Put this line at the top of every file in this folder:

```java
package m24_stack_expression_notations;
```

## Infix to Postfix Conversion - Basics

- [x] Infix to Postfix Conversion - 1
- [x] Infix to Postfix Conversion - Solution
- [x] Infix to Postfix Program

## Understanding Operators and Parentheses

- [x] Associativity and Unary Operator
- [x] Why Parantheses
- [x] Infix to Postfix Conversion - 2
- [x] InfixToPostfixAssociative-Solution

## Postfix Evaluation

- [x] Eval Postfix
- [x] Eval Postfix - Solution

## Parentheses and Bracket Matching

- [ ] Parantheses Matching using Stack
- [ ] Parantheses Matching using Stack - Solution
- [ ] Brackets Matching Using Stack
- [ ] Brackets Matching Using Stack - Solution

## Code in this folder

All four classes are complete. They are static utility classes (private constructor) and use m23's `GenericArrayStack` with capacity = input length, because the stack can never hold more characters than the input has. An empty input returns early, before the stack is made, because a capacity of 0 is not allowed.

| File | What it has |
|------|-------------|
| `InfixToPostfix.java` | Lectures 1 to 3. `convert(infix)` for `+ - * /` with single-letter or single-digit operands, no parentheses, no spaces. It scans left to right: an operand goes straight to the output; an operator is pushed if the stack is empty or its precedence is higher than the top's, otherwise the top is popped to the output and the same operator is checked again (so equal precedence pops, which gives left to right order). After the scan, everything left on the stack is popped to the output. `precedence(operator)` gives `+ -` 1 and `* /` 2 and is called before the empty-stack check, so an invalid character always throws `IllegalArgumentException("Invalid character: 'x'")`. `""` returns `""` before the stack is made (capacity 0 is not allowed), `null` throws `NullPointerException`. The output is built with a `StringBuilder`, because `+=` on a `String` copies the whole string every time |
| `InfixToPostfixAssociative.java` | Lectures 4 to 7. `convert(infix)` that adds `^` (right to left) and parentheses. Every symbol has two precedences: `outStackPrecedence(symbol)` when it comes from the input (`+ -` 1, `* /` 3, `^` 6, `(` 7, `)` 0) and `inStackPrecedence(symbol)` when it is on the stack (`+ -` 2, `* /` 4, `^` 5, `(` 0). Three cases against the top: out higher, push; out lower, pop the top to the output and check the same symbol again; equal, which only happens when `)` meets `(`, pop the `(` and drop both. `^` is right to left because its out value (6) is higher than its in value (5), and `+ - * /` are left to right because their out value is lower. A `)` that finds the stack empty throws `IllegalArgumentException("Unmatched ')'")`, and a `(` still on the stack at the end throws `IllegalArgumentException("Unmatched '('")`. Invalid characters, `""`, `null` and `StringBuilder` work the same as in `InfixToPostfix` |
| `PostfixEvaluator.java` | Lectures 8 and 9. `evaluate(postfix)` returns an `int` for single-digit operands and `+ - * /`. It scans left to right: a digit is pushed as its value (`symbol - '0'`, so `'5'` becomes 5, not its character code 53); an operator pops two values, and the first pop is the right operand (`"83-"` is 8 - 3), then `apply(operator, left, right)` pushes the result back. The stack only ever holds real numbers, so a result can be used again as an operand. At the end exactly one value must be left. `""` throws `IllegalArgumentException("Empty expression")`; an operator with fewer than two values on the stack, or more than one value left at the end, throws `IllegalArgumentException("Invalid postfix expression")`; any other character throws `IllegalArgumentException("Invalid character: 'x'")`; `null` throws `NullPointerException`. Integer division, and division by zero throws `ArithmeticException` (Java does this itself). `int` overflow is not checked |
| `BracketMatcher.java` | Lectures 10 to 13. Characters that are not brackets are ignored, `""` is balanced and `null` throws `NullPointerException`. `hasBalancedParentheses(expression)` checks only `( )`: a `(` is pushed; a `)` with an empty stack means false (nothing to close), otherwise it pops; at the end the stack must be empty (anything left is a `(` that was never closed). `hasBalancedBrackets(expression)` checks `( ) { } [ ]` the same way, but a closing bracket also has to be the same type as the opening bracket it pops, so `"([)]"` is false even though the counts match. One wrong pair is enough to return false; a right pair only means keep checking. `isMatchingPair(opening, closing)` switches on the opening bracket and checks for its own closing bracket, so it never depends on character codes |
| `ExpressionDemo.java` | `main()` that calls one section method per class. `demoInfixToPostfix()` converts seven valid expressions (including `"a"` and `""`), then shows the errors for `"a%b"`, `"a+(b)"`, `"a + b"` and `null`. `demoInfixToPostfixAssociative()` converts eight expressions (right to left `^`, left to right `-`, parentheses, nested parentheses, `"a"`, `""`), then shows the errors for `"(a+b"`, `"a+b)"`, `")"`, `"a%b"` and `null`. `demoPostfixEvaluator()` evaluates five expressions (including operand order and a negative result used again), converts `"(2+3)*4"` and evaluates the result, then shows the errors for `""`, `"+"`, `"2+"`, `"23"`, `"2a+"`, `"50/"` and `null`. `demoBracketMatcher()` checks six strings with `hasBalancedParentheses` and seven with `hasBalancedBrackets` (including `"([)]"`, `"{(})"`, `"()("` and `""`), then shows the error for `null` |

[Back to all modules](../../README.md)
