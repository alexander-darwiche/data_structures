package algorithms;


import java.util.Stack;

public class FibonacciStackExampleTail {
    public static void main(String[] args) {
        int n = 40;
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
        long a = 0, b = 1;


        while (!stack.isEmpty()) {
            counter.increment(); // Increment for each iteration
            int current = stack.pop();

            if (current <= 1) {
                a = current;
            } else {
                if (!stack.isEmpty()) {
                    b += a;
                    a = b - a;
                } else {
                    stack.push(current - 1);
                    stack.push(current - 2);
                }
            }
        }

        return b;
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
