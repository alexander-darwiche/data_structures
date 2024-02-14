package datastructures;

public class LinkedList {

    private class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            Node next;
        }
    } // Node

    public Node head;

    // methods
    public void addFront(int data) {
        // 1. Create Node
        Node newNode = new Node(data);
        // 2. if at front
        if (head == null){
            head = newNode;
            return;
        }
        // if not head
        newNode.next = head;
        head = newNode
    }
    public int getFirst() {
        // should to error checking ... here
        // if head == null for empty list ...
        return head.data;
    }
    public int getLast() {
        if (head == null){
            throw new IllegalStateException("Empty List");
        }
        Node current = head;
        while( current.next != null ){
            current = current.next;
        }

        return current.data;
    }
    public void addBack(int data) {
        Node newNode = new Node( data );
        if (head == null){
            head = newNode;
            return;
        }

        Node current = head;
        while ( current.next = new Node){

        }

        return;
    }
    public int  size() {
        return -1;
    }
    public void clear() {
        head = null;
    }
    public void print() {
    }

    // public deleteValue(int data)
}