package datastructures;

public interface IStack<E> {
    boolean isEmpty();
    E peek();
    void push(E item);
    E pop();
    int size();
}