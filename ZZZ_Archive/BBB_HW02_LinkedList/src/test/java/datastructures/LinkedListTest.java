package datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    private LinkedList linkedList;

    @BeforeEach
    void setup() throws Exception {
        linkedList = new LinkedList();
    }

    @Test
    void addFront() {
        linkedList.addFront(1);
        linkedList.addFront(2);
        linkedList.addFront(3);

        assertEquals(3,linkedList.getFirst());
        assertEquals(1, linkedList.getLast());
    }

    @Test
    void getFirst() {
    }

    @Test
    void getLast() {
    }

    @Test
    void addBack() {
    }

    @Test
    void size() {
    }

    @Test
    void clear() {
    }

    @Test
    void print() {
    }
}