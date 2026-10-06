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
        String[] expressions = {"234*+82/-", "83-", "72/", "35-2*", "5"};
        for (String postfix : expressions) {
            System.out.println("evaluate(\"" + postfix + "\") = " + PostfixEvaluator.evaluate(postfix));
        }
        String infix = "(2+3)*4";
        String postfix = InfixToPostfixAssociative.convert(infix);
        System.out.println("evaluate(convert(\"" + infix + "\")) = evaluate(\"" + postfix + "\") = " + PostfixEvaluator.evaluate(postfix));
        String[] invalidExpressions = {"", "+", "2+", "23", "2a+"};
        for (String invalid : invalidExpressions) {
            try {
                System.out.println("evaluate(\"" + invalid + "\") = " + PostfixEvaluator.evaluate(invalid));
            } catch (IllegalArgumentException e) {
                System.out.println("evaluate(\"" + invalid + "\") = " + e.getMessage());
            }
        }
        try {
            PostfixEvaluator.evaluate("50/");
        } catch (ArithmeticException e) {
            System.out.println("evaluate(\"50/\") = " + e.getMessage());
        }
        try {
            PostfixEvaluator.evaluate(null);
        } catch (NullPointerException e) {
            System.out.println("evaluate(null) = " + e.getMessage());
        }
        System.out.println();
    }

    private static void demoBracketMatcher() {
        System.out.println("=== BracketMatcher ===");
    }
}
