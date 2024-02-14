package datastructures;

import java.util.EmptyStackException;


// Push:    Adds        Adds a new item to the 'top' (head) of 'Stack'
// Pop:     Removes     Removes the top item from Stack
// Peak;    Check       What is at the top of Stack without removing
public class Stack {
    private class Node {
        private int data;
        private Node next;
        private Node (int data) {
            this.data = data;
        }
    }

    private Node head; // add and remove 'things' starting
                       // from here
    private int size;

    public boolean isEmpty() {
        return head == null;  // conditional statement returns "true'
    }

    public int peek() {
        return head.data;
    }

    public void push(int data) {
        // 1. Create new node
        // 2. Set it's next to be head
        // 3. Set head to be the new node

        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public int pop() {
        // Store the value you want to return
        // Set the current head.next to be the new head
        // return the value

        if (head == null)
            throw new EmptyStackException();

        int data = head.data;
        head = head.next;
        size--;

        return data;
    }

    public int size() {
        return size;
    }
}

