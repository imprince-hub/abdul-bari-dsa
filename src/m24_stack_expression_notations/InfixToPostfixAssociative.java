package m24_stack_expression_notations;

import m23_stack_introduction.GenericArrayStack;

import java.util.Objects;

// Infix to postfix with associativity and parentheses: + - * / ^ ( )
// ^ is right to left associative, the others are left to right.
// Every symbol has two precedences: out-stack (when it comes from the input)
// and in-stack (when it is sitting on the stack).
// Operands are single letters or digits, no spaces.
// Stack: m23's GenericArrayStack<Character> with capacity = infix.length().
public class InfixToPostfixAssociative {
    private InfixToPostfixAssociative() {
    }

    // "a^b^c" -> "abc^^", "(a+b)*c" -> "ab+c*", "" -> ""
    // null throws NullPointerException
    // a ')' with no '(' before it throws IllegalArgumentException("Unmatched ')'")
    // a '(' that is never closed throws IllegalArgumentException("Unmatched '('")
    // any other character throws IllegalArgumentException
    public static String convert(String infix) {
        Objects.requireNonNull(infix, "infix must not be null");
        if (infix.isEmpty()) {
            return "";
        }
        GenericArrayStack<Character> stack = new GenericArrayStack<>(infix.length());
        StringBuilder postfix = new StringBuilder();
        int i = 0;
        while (i < infix.length()) {
            char symbol = infix.charAt(i);
            if (isOperand(symbol)) {
                postfix.append(symbol);
                i++;
            } else {
                // call outStackPrecedence first, so an invalid character throws even when the stack is empty
                int symbolPrecedence = outStackPrecedence(symbol);
                if (stack.isEmpty() && symbol == ')') {
                    throw new IllegalArgumentException("Unmatched ')'");
                }
                if (stack.isEmpty() || symbolPrecedence > inStackPrecedence(stack.stackTop())) {
                    stack.push(symbol);
                    i++;
                } else if (symbolPrecedence < inStackPrecedence(stack.stackTop())) {
                    postfix.append(stack.pop());
                } else {
                    stack.pop(); // equal only when ')' meets '(': drop both, nothing goes to the output
                    i++;
                }
            }
        }
        while (!stack.isEmpty()) {
            char top = stack.pop();
            if (top == '(') {
                throw new IllegalArgumentException("Unmatched '('");
            }
            postfix.append(top);
        }
        return postfix.toString();
    }

    // a letter or a digit
    private static boolean isOperand(char ch) {
        return (ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }

    // + - 1, * / 3, ^ 6, ( 7, ) 0; any other character throws IllegalArgumentException
    private static int outStackPrecedence(char symbol) {
        return switch (symbol) {
            case '+', '-' -> 1;
            case '*', '/' -> 3;
            case '^' -> 6;
            case '(' -> 7;
            case ')' -> 0;
            default -> throw new IllegalArgumentException("Invalid character: '" + symbol + "'");
        };
    }

    // + - 2, * / 4, ^ 5, ( 0; ')' never goes on the stack
    // any other character throws IllegalArgumentException
    private static int inStackPrecedence(char symbol) {
        return switch (symbol) {
            case '+', '-' -> 2;
            case '*', '/' -> 4;
            case '^' -> 5;
            case '(' -> 0;
            default -> throw new IllegalArgumentException("Invalid character: '" + symbol + "'");
        };
    }
}
