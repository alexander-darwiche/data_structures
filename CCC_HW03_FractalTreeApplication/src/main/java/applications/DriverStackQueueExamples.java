package applications;

import datastructures.IQueue;
import datastructures.IStack;
import datastructures.LinkedListQueue;
import datastructures.LinkedListStack;

public class DriverStackQueueExamples {
    public static void main(String[] args) {
        //
        System.out.print("A Stack of Integers:  ");

        IStack<Integer> another_stack;
            // we have committed to use Integers, and we are still deciding
                // on either an array ... or a linked list implementation

        // You can easily switch between different stack implementations
        // For example, using an array-based stack:
        another_stack = new LinkedListStack<>();

        // The rest of your code that uses the stack doesn't need to change:
            // same result
        another_stack.push(5);
        another_stack.push(10);
        another_stack.push(20);
        another_stack.push(30);
        System.out.println(another_stack.pop()); // Outputs 30

        // Cast and call the debug LL method printStack
        System.out.print("Stack (Linked Content (top (head) --> bottom): ");

        ((LinkedListStack<Integer>)another_stack).printStack();

        IQueue<Integer> queue = new LinkedListQueue<>(); // Queue of Integers
        queue.add(1);
        queue.add(2);
        queue.add(3);

        System.out.println("Queue front: " + queue.peek());

        System.out.print("Queue (Linked Content (front (head)--> back): ");

        ((LinkedListQueue<Integer>)queue).printQueue();

    }
}

