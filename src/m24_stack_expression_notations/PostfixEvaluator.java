package m24_stack_expression_notations;

import m23_stack_introduction.GenericArrayStack;

// Evaluates a postfix expression with + - * / on single-digit operands, no spaces.
// Integer division, so "72/" is 3. Division by zero throws ArithmeticException (Java does this itself).
// Stack: m23's GenericArrayStack<Integer> with capacity = postfix.length().
public class PostfixEvaluator {
    private PostfixEvaluator() {
    }

    // "234*+82/-" -> 10
    // null throws NullPointerException
    // "" throws IllegalArgumentException("Empty expression")
    // an operator without two operands on the stack, or more than one value left at the end,
    // throws IllegalArgumentException("Invalid postfix expression")
    // any other character throws IllegalArgumentException
    public static int evaluate(String postfix) {
        throw new UnsupportedOperationException("TODO");
    }

    // '0' to '9'
    private static boolean isOperand(char ch) {
        throw new UnsupportedOperationException("TODO");
    }

    // + - * /
    private static boolean isOperator(char ch) {
        throw new UnsupportedOperationException("TODO");
    }

    // left operator right, so apply('-', 8, 3) is 5
    private static int apply(char operator, int left, int right) {
        throw new UnsupportedOperationException("TODO");
    }
}
