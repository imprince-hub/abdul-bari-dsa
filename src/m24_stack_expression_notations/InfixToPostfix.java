package m24_stack_expression_notations;

import m23_stack_introduction.GenericArrayStack;

import java.util.Objects;

// Infix to postfix for + - * / only. Operands are single letters or digits.
// No parentheses and no spaces. The input is assumed to be a valid infix expression.
// Stack: m23's GenericArrayStack<Character> with capacity = infix.length().
public class InfixToPostfix {
    private InfixToPostfix() {
    }

    // "a+b*c" -> "abc*+", "" -> ""
    // null throws NullPointerException, any other character throws IllegalArgumentException
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
                // call precedence first, so an invalid character throws even when the stack is empty
                int symbolPrecedence = precedence(symbol);
                if (stack.isEmpty() || symbolPrecedence > precedence(stack.stackTop())) {
                    stack.push(symbol);
                    i++;
                } else {
                    postfix.append(stack.pop());
                }
            }
        }
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        return postfix.toString();
    }

    // a letter or a digit
    private static boolean isOperand(char ch) {
        return (ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }

    // + and - are 1, * and / are 2; any other character throws IllegalArgumentException
    private static int precedence(char operator) {
        return switch (operator) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> throw new IllegalArgumentException("Invalid character: '" + operator + "'");
        };
    }
}
