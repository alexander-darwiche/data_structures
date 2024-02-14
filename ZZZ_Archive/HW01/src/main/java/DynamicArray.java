package data structures;

public class DynamicArray <String> {
    private Object[] data;
    private int initialCapacity;

    public DynamicArray(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        data = new Object[initialCapacity];
    }

    // methods to implement
    public  String get(int index) { }
    public     int indexOf(String value) { }
    public    void set(int index, String value) { }
    public    void insert(int index, String value) { }
    public    void add(String value) { // add element @ end of the array.  }
        public    void deleteAt(int index) {
            public    void delete(String value) { }
            public boolean isEmpty() { }
            public boolean contains(String value) { }
            private   void resize() { }
            public     int size() { }
            public    void print() { }
        }