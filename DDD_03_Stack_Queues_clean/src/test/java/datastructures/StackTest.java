package datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    //** (almost) always add storage to your test
    private Stack stack; // private 'storage'

    @BeforeEach
    void setUp() {
            // create storage
            // initialize if needed
            stack = new Stack();
    }

    @Test
    void isEmpty() {
    }

    @Test
    void peek() {
    }

    @Test
    void push() {
        stack.push(2);
        stack.push(7);
        stack.push(2);
        stack.push(0);

        assertEquals(4, stack.size());
        assertEquals(0, stack.peek());
        assertEquals(0, stack.pop());
        assertFalse(stack.isEmpty());
        assertFalse(stack.isEmpty());
    }

    @Test
    void pop() {
    }
}