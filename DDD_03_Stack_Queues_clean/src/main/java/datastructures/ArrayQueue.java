package datastructures;


import java.util.NoSuchElementException;

public class ArrayQueue<E> implements IQueue<E> {
    private E[] array;
    private int front, rear, size, capacity;
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        array = (E[]) new Object[DEFAULT_CAPACITY];
        front = 0;
        rear = 0;
        size = 0;
        capacity = DEFAULT_CAPACITY;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return array[front];
    }

    @Override
    public void add(E item) {
        if (size == capacity) {
            // Handle array resizing
            resize();
        }
        array[rear] = item;
        rear = (rear + 1) % capacity;
        size++;
    }

    private void resize() {
        E[] newArray = (E[]) new Object[capacity * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[(front + i) % capacity];
        }
        array = newArray;
        front = 0;
        rear = size;
        capacity *= 2;
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E item = array[front];
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    // @Override
    public int size() {
        return size;
    }
}

