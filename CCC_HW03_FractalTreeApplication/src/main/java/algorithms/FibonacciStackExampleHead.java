package algorithms;

import java.util.Stack;

public class FibonacciStackExampleHead {
    public static void main(String[] args) {
        int n = 30;
        OperationCounter counter = new OperationCounter();
        System.out.println("Fibonacci(" + n + ") = " + fibonacciStack(n, counter));
        System.out.println("Number of operations: " + counter.getCount());
    }

    public static long fibonacciStack(int n, OperationCounter counter) {
        if (n <= 1) {
            counter.increment(); // Increment for the base case check
            return n;
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(n);

        long result = 0;

        while (!stack.isEmpty()) {
            counter.increment(); // Increment for each iteration
            int current = stack.pop();

            if (current <= 1) {
                result += current;
            } else {
                stack.push(current - 1);
                stack.push(current - 2);
            }
        }

        return result;
    }

    static class OperationCounter {
        private int count = 0;

        public void increment() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }
}
