package m24_stack_expression_notations;

import m23_stack_introduction.GenericArrayStack;

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
        throw new UnsupportedOperationException("TODO");
    }

    // a letter or a digit
    private static boolean isOperand(char ch) {
        throw new UnsupportedOperationException("TODO");
    }

    // + - 1, * / 3, ^ 6, ( 7, ) 0; any other character throws IllegalArgumentException
    private static int outStackPrecedence(char symbol) {
        throw new UnsupportedOperationException("TODO");
    }

    // + - 2, * / 4, ^ 5, ( 0; ')' never goes on the stack
    // any other character throws IllegalArgumentException
    private static int inStackPrecedence(char symbol) {
        throw new UnsupportedOperationException("TODO");
    }
}
