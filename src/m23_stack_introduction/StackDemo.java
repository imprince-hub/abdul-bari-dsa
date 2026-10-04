package m23_stack_introduction;

import java.util.NoSuchElementException;

public class StackDemo {
    public static void main(String[] args) {
        demoArrayStack();
        demoGenericArrayStack();
        demoLinkedStack();
        demoGenericLinkedStack();
    }

    private static void demoArrayStack() {
        System.out.println("=== ArrayStack ===");
        ArrayStack stack = new ArrayStack(5);
        System.out.println("new stack(5): isEmpty = " + stack.isEmpty() + ", size = " + stack.size());
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.print("after push 10, 20, 30 (top to bottom) = ");
        stack.display();
        System.out.println("size = " + stack.size());
        System.out.println("stackTop = " + stack.stackTop());
        System.out.println("peek(1) = " + stack.peek(1));
        System.out.println("peek(2) = " + stack.peek(2));
        System.out.println("peek(3) = " + stack.peek(3));
        try {
            stack.peek(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("peek(0) = " + e.getMessage());
        }
        try {
            stack.peek(4);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("peek(4) = " + e.getMessage());
        }

        System.out.println();
        System.out.println("pop = " + stack.pop());
        System.out.print("after pop = ");
        stack.display();
        System.out.println("stackTop = " + stack.stackTop());

        System.out.println();
        stack.push(40);
        stack.push(50);
        stack.push(60);
        System.out.print("after push 40, 50, 60 = ");
        stack.display();
        System.out.println("isFull = " + stack.isFull() + ", size = " + stack.size());
        try {
            stack.push(70);
        } catch (IllegalStateException e) {
            System.out.println("push(70) on full stack = " + e.getMessage());
        }

        System.out.println();
        System.out.print("pop all = ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
        System.out.println("isEmpty = " + stack.isEmpty() + ", size = " + stack.size());
        System.out.print("display on empty stack = ");
        stack.display();
        try {
            stack.pop();
        } catch (NoSuchElementException e) {
            System.out.println("pop on empty stack = " + e.getMessage());
        }
        try {
            stack.stackTop();
        } catch (NoSuchElementException e) {
            System.out.println("stackTop on empty stack = " + e.getMessage());
        }
        try {
            stack.peek(1);
        } catch (NoSuchElementException e) {
            System.out.println("peek(1) on empty stack = " + e.getMessage());
        }

        System.out.println();
        ArrayStack oneSlot = new ArrayStack(1);
        oneSlot.push(-7);
        System.out.println("stack(1) after push(-7): isFull = " + oneSlot.isFull() + ", stackTop = " + oneSlot.stackTop());
        try {
            new ArrayStack(0);
        } catch (IllegalArgumentException e) {
            System.out.println("new ArrayStack(0) = " + e.getMessage());
        }
        System.out.println();
    }

    private static void demoGenericArrayStack() {
        System.out.println("=== GenericArrayStack ===");
    }

    private static void demoLinkedStack() {
        System.out.println("=== LinkedStack ===");
    }

    private static void demoGenericLinkedStack() {
        System.out.println("=== GenericLinkedStack ===");
    }
}
