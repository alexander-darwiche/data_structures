package datastructures;

import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayStack<E> implements IStack<E> {
    private E[] array;
    private int top;  // 'head' in LL implementation
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        array = (E[]) new Object[DEFAULT_CAPACITY];
        top = -1; // index to denote an empty stack
    }

    @Override
    public void push(E item) {
        if (top == array.length - 1) {
            resize(); // Handle array resizing

        }
        array[++top] = item;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array[top];
    }

    private void resize() {
        array = Arrays.copyOf(array, array.length * 2);
        // Handle array resizing in this stack implementation:
        // 1. Here using Java's util.Arrays for a straightforward & efficient approach.
        //    This is recommended for real-world applications where
        //    reliability and performance are key.
        // 2. Alternatively, since you have already implemented your own
        //    dynamic array in a previous homework,
        //    you can utilize that here to reinforce your understanding
        //    of dynamic resizing and memory management.
        }


    @Override
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array[top--];
    }

    @Override
    public int size() {
        return top + 1;
    }
}
