package datastructures;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class Queue {

    private class Node {
        private int data;
        private Node next;
        private Node (int data) {
            this.data = data;
        }
    }

    private Node head; // remove starting from here
    private Node tail; // OK you will need to 'enter' the queue from the back (tail).

    public boolean isEmpty() {
        return head == null;
    }

    public int peek() {
        // should add - exception handling here ...
        return head.data;
    }

    public void add(int data) {
        // 1. Create a new node
        // 2. Set the node's tail that is head of me to "ME"
        // 3. Set me (the new node) to be the new tail

        Node newNode = new Node(data);
        if (tail != null) {
            tail.next = newNode;
        }
        tail = newNode;

        // handle case of first element where head is null
        if (head == null) {
            head = tail;
        }
    }

    public int remove() {
        // 1. Save the data
        // 2. Point the head to the next element (effectively removing it)
        // 3. Return the data

        // what do we return if the list is empty? check first!
        // NullPointerException if not checked.
        // OR -- throw an exception: NoSuchElementException? maybe?

        if (head == null)
            throw new NoSuchElementException();

        int return_data = head.data;
        head = head.next;

        // Handle queue now being empty
        if (head == null) {
            tail = null;
        }

        return return_data;
    }
}
