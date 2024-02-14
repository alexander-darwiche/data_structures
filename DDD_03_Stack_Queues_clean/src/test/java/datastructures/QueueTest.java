package datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    private Queue queue; // private 'storage'

    @BeforeEach
    void setUp() {
        queue = new Queue();
    }

    @Test
    void isEmpty() {
        queue.add(6);
        queue.remove();
        assertTrue(queue.isEmpty());
    }

    @Test
    void peek() {
        queue.add(5);
        assertEquals(5, queue.peek());
    }

    @Test
    void add() {
        queue.add(5);
        queue.add(5);
        queue.add(5);
        assertEquals(5, queue.peek());
    }

    @Test
    void remove() {
        queue.add(8);
        assertEquals(8, queue.peek());
        assertEquals(8, queue.remove());
        // queue.remove(); // Oops something is WRONG // go back and check stack implementation
                // have it return someting 'descriptive' typical NoSuchElementException a good choice here.

        try {
            // Attempt to remove an element from the queue
            int removedElement = queue.remove();
            System.out.println("Removed element: " + removedElement);
        } catch (NoSuchElementException e) { // import class here...
            // Handle the case where the queue is empty
            System.out.println("Cannot remove an element from an empty queue.");
        }


    }
}