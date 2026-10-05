# 24. Stack & Expression Notations

Package: `m24_stack_expression_notations` · 13 lectures · 3 done

Put this line at the top of every file in this folder:

```java
package m24_stack_expression_notations;
```

## Infix to Postfix Conversion - Basics

- [x] Infix to Postfix Conversion - 1
- [x] Infix to Postfix Conversion - Solution
- [x] Infix to Postfix Program

## Understanding Operators and Parentheses

- [ ] Associativity and Unary Operator
- [ ] Why Parantheses
- [ ] Infix to Postfix Conversion - 2
- [ ] InfixToPostfixAssociative-Solution

## Postfix Evaluation

- [ ] Eval Postfix
- [ ] Eval Postfix - Solution

## Parentheses and Bracket Matching

- [ ] Parantheses Matching using Stack
- [ ] Parantheses Matching using Stack - Solution
- [ ] Brackets Matching Using Stack
- [ ] Brackets Matching Using Stack - Solution

## Code in this folder

`InfixToPostfix` is complete. The other files are skeletons: method names are in place and unfinished methods throw `UnsupportedOperationException("TODO")`. All four classes are static utility classes (private constructor) and use m23's `GenericArrayStack` with capacity = input length, because the stack can never hold more characters than the input has.

| File | What it has |
|------|-------------|
| `InfixToPostfix.java` | Lectures 1 to 3. `convert(infix)` for `+ - * /` with single-letter or single-digit operands, no parentheses, no spaces. It scans left to right: an operand goes straight to the output; an operator is pushed if the stack is empty or its precedence is higher than the top's, otherwise the top is popped to the output and the same operator is checked again (so equal precedence pops, which gives left to right order). After the scan, everything left on the stack is popped to the output. `precedence(operator)` gives `+ -` 1 and `* /` 2 and is called before the empty-stack check, so an invalid character always throws `IllegalArgumentException("Invalid character: 'x'")`. `""` returns `""` before the stack is made (capacity 0 is not allowed), `null` throws `NullPointerException`. The output is built with a `StringBuilder`, because `+=` on a `String` copies the whole string every time |
| `InfixToPostfixAssociative.java` | Lectures 4 to 7. `convert(infix)` that adds `^` (right to left) and parentheses, using two tables: `outStackPrecedence(symbol)` and `inStackPrecedence(symbol)`. Unmatched `)` or `(` throws `IllegalArgumentException`. All stubs |
| `PostfixEvaluator.java` | Lectures 8 and 9. `evaluate(postfix)` returns an `int` for single-digit operands and `+ - * /`; helpers `isOperand(ch)`, `isOperator(ch)`, `apply(operator, left, right)`. Empty input and malformed postfix throw `IllegalArgumentException`; division by zero throws `ArithmeticException`. All stubs |
| `BracketMatcher.java` | Lectures 10 to 13. `hasBalancedParentheses(expression)` checks only `( )`; `hasBalancedBrackets(expression)` checks `( ) { } [ ]`; helpers `isOpening(ch)`, `isClosing(ch)`, `isMatchingPair(opening, closing)`. Other characters are ignored, `""` is balanced. All stubs |
| `ExpressionDemo.java` | `main()` that calls one section method per class. `demoInfixToPostfix()` converts seven valid expressions (including `"a"` and `""`), then shows the errors for `"a%b"`, `"a+(b)"`, `"a + b"` and `null`. The other sections only print their heading so far |

[Back to all modules](../../README.md)
