package datastructures;
public interface IQueue<E> {
    boolean isEmpty();
    E peek();
    void add(E data);
    E remove();
    int size();
}
