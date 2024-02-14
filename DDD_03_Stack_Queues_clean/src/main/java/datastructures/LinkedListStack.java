package datastructures;

import java.util.EmptyStackException;


// This implementation of LinkedListStack<E> is a
// // progression from the Stack class we developed earlier this week.
// Key differences to note:
// 1. We are now implementing the IStack<E> interface, ensuring our class
//          adheres to a defined contract for stack operations.
// 2. Instead of using 'int data', this class is generic ('E'),
//        allowing it to be more versatile and handle different data types.
// 3. Exception handling has been refined and improved in this implementation.
//      Pay attention to how we handle scenarios like popping from an empty stack.


public class LinkedListStack<E> implements IStack<E> {
    private Node head;
    private int size;

    private class Node {
        E data;
        Node next;

        Node(E data) {
            this.data = data;
        }
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return head.data;
    }

    @Override
    public void push(E item) {
        Node newNode = new Node(item);
        newNode.next = head;
        head = newNode;
        size++;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E data = head.data;
        head = head.next;
        size--;
        return data;
    }

    @Override
    public int size() {
        return size;
    }
}
