package datastructures;

public class DriverInterfaces {
    public static void main(String[] args) {
        IQueue<String> queue = new LinkedListQueue<>(); // Queue of Integers
        queue.add("Hello");
        queue.add("World");
        System.out.println("Queue front: " + queue.peek());


        IStack<String> stack = new ArrayStack<>(); // Stack of Strings
        stack.push("Hello");
        stack.push("World");
        System.out.println("Stack top: " + stack.peek());




        //
        System.out.println("A Stack of Integers:");

        IStack<Integer> another_stack;
            // we have commited to use Integers but we are still deciding
                // on either array or linked list

        // You can easily switch between different stack implementations
        // For example, using an array-based stack:
        another_stack = new ArrayStack<>();

        // Or using a linked-list-based stack:
        // another_stack = new LinkedListStack<>();

        // The rest of your code that uses the stack doesn't need to change:
            // same result
        another_stack.push(10);
        another_stack.push(20);
        another_stack.push(30);
        System.out.println(another_stack.pop()); // Outputs 30


        // What bout doing a QUEUE:
        IQueue<Integer> arrayQueue = new ArrayQueue<>();
        IQueue<Integer> linkedListQueue = new LinkedListQueue<>();

        arrayQueue.add(1);
        arrayQueue.add(2);

        linkedListQueue.add(1);
        linkedListQueue.add(2);

        System.out.println("Array Queue Remove: " + arrayQueue.remove()); // Outputs 1
        System.out.println("Linked List Queue Remove: " + linkedListQueue.remove()); // Outputs 1
    }
}

