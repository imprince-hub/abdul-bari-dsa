package m25_stack_techniques;

import m23_stack_introduction.GenericArrayStack;

import java.util.ArrayList;
import java.util.List;

// Tower of Hanoi: move n disks from rod A to rod C using rod B, one disk at a time,
// never a bigger disk on a smaller one. The recursion from the lecture:
//
//     toh(n, from, via, to)
//         if n > 0
//             toh(n - 1, from, to, via)    first call: the n - 1 disks go from -> via (the call does this itself)
//             move disk n: from -> to      the only move this call adds
//             toh(n - 1, via, from, to)    second call: the n - 1 disks go via -> to (the call does this itself)
//
// Every call adds exactly one move, from its own "from" to its own "to". The first call passes
// (from, to, via), so inside it "to" is our via: that is how the n - 1 disks end up on via.
//
// Both versions return the moves as text ("disk 1: A -> C") instead of printing them,
// so the two results can be compared with List.equals.
// n disks need 2^n - 1 moves, so the number of disks is limited to 0..MAX_DISKS.
// Iterative version: a programmer stack (m23's GenericArrayStack<Call>) in place of the system stack.
// Each Call holds the parameters of one call. Capacity = disks, because the recursion is
// never more than n calls deep (counting only calls with n > 0).
public class TowerOfHanoi {
    public static final int MAX_DISKS = 20; // 20 disks need 1,048,575 moves

    // the parameters of one call toh(n, from, via, to): one element of the programmer stack
    private static class Call {
        final int n;
        final char from;
        final char via;
        final char to;

        Call(int n, char from, char via, char to) {
            this.n = n;
            this.from = from;
            this.via = via;
            this.to = to;
        }
    }

    private TowerOfHanoi() {
    }

    // 1 -> [disk 1: A -> C]
    // 2 -> [disk 1: A -> B, disk 2: A -> C, disk 1: B -> C]
    // 0 -> [] (nothing to move)
    // disks < 0 or disks > MAX_DISKS throws IllegalArgumentException
    public static List<String> movesRecursive(int disks) {
        checkDisks(disks);
        List<String> moves = new ArrayList<>();
        movesRecursive(disks, 'A', 'B', 'C', moves);
        return moves;
    }

    // toh(n, from, via, to) from the lecture, adding each move to moves instead of printing it
    private static void movesRecursive(int n, char from, char via, char to, List<String> moves) {
        if (n == 0) {
            return;
        }
        movesRecursive(n - 1, from, to, via, moves);
        moves.add(moveText(n, from, to));
        movesRecursive(n - 1, via, from, to, moves);
    }

    // same results as movesRecursive, with a loop and the programmer stack instead of recursion
    // 0 -> [], returned before the stack is made (capacity 0 is not allowed)
    // disks < 0 or disks > MAX_DISKS throws IllegalArgumentException
    public static List<String> movesIterative(int disks) {
        checkDisks(disks);
        if (disks == 0) {
            return new ArrayList<>();
        }
        List<String> moves = new ArrayList<>();
        GenericArrayStack<Call> stack = new GenericArrayStack<>(disks);
        Call current = new Call(disks, 'A', 'B', 'C');
        while (current.n > 0 || !stack.isEmpty()) {
            if (current.n > 0) {
                stack.push(current); // this call waits on the stack with its own rods
                current = new Call(current.n - 1, current.from, current.to, current.via); // first call
            } else {
                Call popped = stack.pop();
                moves.add(moveText(popped.n, popped.from, popped.to));
                current = new Call(popped.n - 1, popped.via, popped.from, popped.to); // second call
            }
        }
        return moves;
    }

    // moveText(1, 'A', 'C') -> "disk 1: A -> C"; both versions use it, so their text is the same
    private static String moveText(int disk, char from, char to) {
        return "disk " + disk + ": " + from + " -> " + to;
    }

    // disks < 0 or disks > MAX_DISKS throws IllegalArgumentException("disks must be between 0 and " + MAX_DISKS + ": " + disks)
    private static void checkDisks(int disks) {
        if (disks < 0 || disks > MAX_DISKS) {
            throw new IllegalArgumentException("disks must be between 0 and " + MAX_DISKS + ": " + disks);
        }
    }
}
