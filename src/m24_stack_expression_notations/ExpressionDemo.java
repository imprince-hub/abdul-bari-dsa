package m24_stack_expression_notations;

public class ExpressionDemo {
    public static void main(String[] args) {
        demoInfixToPostfix();
        demoInfixToPostfixAssociative();
        demoPostfixEvaluator();
        demoBracketMatcher();
    }

    private static void demoInfixToPostfix() {
        System.out.println("=== InfixToPostfix ===");
        String[] expressions = {"a+b*c", "a+b*c-d/e", "a*b+c", "a-b+c", "3*4-2", "a", ""};
        for (String infix : expressions) {
            System.out.println("convert(\"" + infix + "\") = \"" + InfixToPostfix.convert(infix) + "\"");
        }
        String[] invalidExpressions = {"a%b", "a+(b)", "a + b"};
        for (String infix : invalidExpressions) {
            try {
                System.out.println("convert(\"" + infix + "\") = \"" + InfixToPostfix.convert(infix) + "\"");
            } catch (IllegalArgumentException e) {
                System.out.println("convert(\"" + infix + "\") = " + e.getMessage());
            }
        }
        try {
            InfixToPostfix.convert(null);
        } catch (NullPointerException e) {
            System.out.println("convert(null) = " + e.getMessage());
        }
        System.out.println();
    }

    private static void demoInfixToPostfixAssociative() {
        System.out.println("=== InfixToPostfixAssociative ===");
        String[] expressions = {"a^b^c", "a-b-c", "a*b^c", "(a+b)*c", "a+(b*c)", "((a+b)*c-d)^e^f", "a", ""};
        for (String infix : expressions) {
            System.out.println("convert(\"" + infix + "\") = \"" + InfixToPostfixAssociative.convert(infix) + "\"");
        }
        String[] invalidExpressions = {"(a+b", "a+b)", ")", "a%b"};
        for (String infix : invalidExpressions) {
            try {
                System.out.println("convert(\"" + infix + "\") = \"" + InfixToPostfixAssociative.convert(infix) + "\"");
            } catch (IllegalArgumentException e) {
                System.out.println("convert(\"" + infix + "\") = " + e.getMessage());
            }
        }
        try {
            InfixToPostfixAssociative.convert(null);
        } catch (NullPointerException e) {
            System.out.println("convert(null) = " + e.getMessage());
        }
        System.out.println();
    }

    private static void demoPostfixEvaluator() {
        System.out.println("=== PostfixEvaluator ===");
    }

    private static void demoBracketMatcher() {
        System.out.println("=== BracketMatcher ===");
    }
}
