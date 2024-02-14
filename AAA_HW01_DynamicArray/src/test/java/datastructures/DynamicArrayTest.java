package datastructures;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {

    private DynamicArray array;

    @BeforeEach
    void setUp() {
        array = new DynamicArray<String>(10);
    }

    @Test
    public void GetAndSet() {
        array.add("1");
        array.set(0,"ABC");
        assertEquals("ABC",array.get(0));
    }

    @Test
    void indexOf() {
        array.add("A");
        array.add("B");
        array.add("C");
        assertEquals(0,array.indexOf("A"));
    }

    @Test
    void insert() {
        array.add("A");
        array.add("B");
        array.add("C");
        array.insert(1,"ABC");
        assertEquals("ABC",array.get(1));
        assertEquals(4,array.size());
    }

    @Test
    void add() {
        array.add("A");
        array.add("B");
        array.add("C");
        assertEquals("C",array.get(2));
        assertEquals(3,array.size());
    }

    @Test
    void resize() {
        assertEquals(10,array.getInitialCapacity());
        assertEquals(0,array.size());
        array.resize();
        assertEquals(20,array.getInitialCapacity());
        assertEquals(0,array.size());

    }

    @Test
    void deleteAt() {
        array.add("A");
        array.add("B");
        array.add("C");
        array.add("D");
        array.deleteAt(2);
        assertEquals("D",array.get(2));

    }

    @Test
    void delete() {
        array.add("A");
        array.add("B");
        array.add("C");
        array.add("D");
        array.add("A");
        array.add("B");
        array.delete("A");
        assertEquals("B",array.get(0));
        assertEquals("B",array.get(3));
        assertEquals(4,array.size());

    }

    @Test
    void isEmpty() {
        assertEquals(true,array.isEmpty());
        array.add("A");
        assertEquals(false,array.isEmpty());
    }

    @Test
    void contains() {
        array.add("A");
        array.add("B");
        array.add("C");
        array.add("D");
        array.add("A");
        array.add("B");
        assertEquals(true,array.contains("A"));
        assertEquals(true,array.contains("B"));
        assertEquals(false,array.contains("a"));
    }

    @Test
    void size() {
        array.add("A");
        array.add("B");
        assertEquals(2,array.size());
    }

    @Test
    void print() {
        array.add("A");
        array.add("B");
        array.add("C");
        array.print();
    }
}