package m24_stack_expression_notations;

import m23_stack_introduction.GenericArrayStack;

// Checks if the brackets in an expression are balanced.
// Characters that are not brackets are ignored.
// Stack: m23's GenericArrayStack<Character> with capacity = expression.length().
public class BracketMatcher {
    private BracketMatcher() {
    }

    // only ( and ): "((a+b)*(c-d))" -> true, "((a+b)" -> false, "a+b)" -> false, "" -> true
    // null throws NullPointerException
    public static boolean hasBalancedParentheses(String expression) {
        throw new UnsupportedOperationException("TODO");
    }

    // ( ) { } [ ]: "{([a+b]*[c-d])/e}" -> true, "([)]" -> false, "" -> true
    // null throws NullPointerException
    public static boolean hasBalancedBrackets(String expression) {
        throw new UnsupportedOperationException("TODO");
    }

    // ( { [
    private static boolean isOpening(char ch) {
        throw new UnsupportedOperationException("TODO");
    }

    // ) } ]
    private static boolean isClosing(char ch) {
        throw new UnsupportedOperationException("TODO");
    }

    // ( with ), { with }, [ with ]
    private static boolean isMatchingPair(char opening, char closing) {
        throw new UnsupportedOperationException("TODO");
    }
}
