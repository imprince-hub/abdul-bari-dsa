package m24_stack_expression_notations;

import m23_stack_introduction.GenericArrayStack;

import java.util.Objects;

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
        Objects.requireNonNull(postfix, "postfix must not be null");
        if (postfix.isEmpty()) {
            throw new IllegalArgumentException("Empty expression");
        }
        GenericArrayStack<Integer> stack = new GenericArrayStack<>(postfix.length());
        for (char symbol : postfix.toCharArray()) {
            if (isOperand(symbol)) {
                stack.push(symbol - '0'); // the digit's value, not its character code
            } else if (isOperator(symbol)) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid postfix expression");
                }
                int right = stack.pop(); // the first pop is the right operand
                int left = stack.pop();
                stack.push(apply(symbol, left, right));
            } else {
                throw new IllegalArgumentException("Invalid character: '" + symbol + "'");
            }
        }
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid postfix expression");
        }
        return stack.pop();
    }

    // '0' to '9'
    private static boolean isOperand(char ch) {
        return ch >= '0' && ch <= '9';
    }

    // + - * /
    private static boolean isOperator(char ch) {
        return switch (ch) {
            case '+', '-', '*', '/' -> true;
            default -> false;
        };
    }

    // left operator right, so apply('-', 8, 3) is 5
    private static int apply(char operator, int left, int right) {
        return switch (operator) {
            case '+' -> left + right;
            case '-' -> left - right;
            case '*' -> left * right;
            case '/' -> left / right;
            default -> throw new IllegalArgumentException("Invalid operator: '" + operator + "'");
        };
    }
}
