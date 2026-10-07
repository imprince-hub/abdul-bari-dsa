package m24_stack_expression_notations;

import m23_stack_introduction.GenericArrayStack;

import java.util.Objects;

// Checks if the brackets in an expression are balanced.
// Characters that are not brackets are ignored.
// Stack: m23's GenericArrayStack<Character> with capacity = expression.length().
public class BracketMatcher {
    private BracketMatcher() {
    }

    // only ( and ): "((a+b)*(c-d))" -> true, "((a+b)" -> false, "a+b)" -> false, "" -> true
    // null throws NullPointerException
    public static boolean hasBalancedParentheses(String expression) {
        Objects.requireNonNull(expression, "expression must not be null");
        if (expression.isEmpty()) {
            return true;
        }
        GenericArrayStack<Character> stack = new GenericArrayStack<>(expression.length());
        for (char symbol : expression.toCharArray()) {
            if (symbol == '(') {
                stack.push(symbol);
            } else if (symbol == ')') {
                if (stack.isEmpty()) {
                    return false; // a ')' with no '(' before it
                }
                stack.pop();
            }
        }
        return stack.isEmpty(); // anything left is a '(' that was never closed
    }

    // ( ) { } [ ]: "{([a+b]*[c-d])/e}" -> true, "([)]" -> false, "" -> true
    // null throws NullPointerException
    public static boolean hasBalancedBrackets(String expression) {
        Objects.requireNonNull(expression, "expression must not be null");
        if (expression.isEmpty()) {
            return true;
        }
        GenericArrayStack<Character> stack = new GenericArrayStack<>(expression.length());
        for (char symbol : expression.toCharArray()) {
            if (isOpening(symbol)) {
                stack.push(symbol);
            } else if (isClosing(symbol)) {
                if (stack.isEmpty()) {
                    return false;
                }
                char opening = stack.pop();
                // one wrong pair is enough to say false; a right pair only means "keep checking"
                if (!isMatchingPair(opening, symbol)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    // ( { [
    private static boolean isOpening(char ch) {
        return ch == '{' || ch == '[' || ch == '(';
    }

    // ) } ]
    private static boolean isClosing(char ch) {
        return ch == '}' || ch == ']' || ch == ')';
    }

    // ( with ), { with }, [ with ]
    private static boolean isMatchingPair(char opening, char closing) {
        return switch (opening) {
            case '(' -> closing == ')';
            case '{' -> closing == '}';
            case '[' -> closing == ']';
            default -> false;
        };
    }
}
