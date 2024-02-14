package datastructures;

import com.sun.jdi.Value;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DynamicArray <String> {

    public static void main(java.lang.String[] args){

        DynamicArray<java.lang.String> myArray = new DynamicArray<>(5);

        // implement command line arguments and interactive input for testing
        try{
            File inputTxt = new File(args[0]);
            Scanner input = new Scanner(inputTxt);
            while(input.hasNextLine()) {
                myArray.add(input.nextLine());
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        boolean continueUserSession = true;



        while (continueUserSession){
            java.lang.String selection;
            java.lang.String value;
            int index;
            printMenu(); //print menu of choices

            Scanner userInput = new Scanner(System.in);
            selection = userInput.next();

            System.out.println(selection);
            switch (selection) {
                case "z":
                    continueUserSession = false;
                    break;
                case "a":
                    System.out.print("Enter an index to get: ");
                    try {
                        index = userInput.nextInt();
                        myArray.get(index);
                    } catch (InputMismatchException e) {
                        System.out.println("Wrong Input Type, try again!");
                    }
                    myArray.print();
                    break;
                case "b":
                    System.out.print("Enter an value to set: ");
                    value = userInput.next();
                    System.out.print("Enter an index of \"" + value + "\" to set: ");
                    try {
                        index = userInput.nextInt();
                        myArray.set(index,value);
                    } catch (InputMismatchException e) {
                        System.out.println("Wrong Input Type, try again!");
                    }
                    myArray.print();
                    break;
                case "c":
                    System.out.print("Enter a value to insert: ");
                    value = userInput.next();
                    System.out.print("Enter the index of \"" + value + "\" to insert: ");
                    try {
                        index = userInput.nextInt();
                        myArray.insert(index,value);
                    } catch (InputMismatchException e) {
                        System.out.println("Wrong Input Type, try again!");
                    }
                    myArray.print();
                    break;
                case "d":
                    System.out.print("Enter a value to add: ");
                    value = userInput.next();
                    myArray.add(value);
                    myArray.print();
                    break;
                case "e":
                    System.out.print("Enter the index to delete: ");
                    try {
                        index = userInput.nextInt();
                        myArray.deleteAt(index);
                    } catch (InputMismatchException e) {
                        System.out.println("Wrong Input Type, try again!");
                    }
                    myArray.print();
                    break;
                case "f":
                    System.out.print("Enter the value to delete (all): ");
                    value = userInput.next();
                    myArray.delete(value);
                    myArray.print();
                    break;
                case "g":
                    if (myArray.isEmpty()){
                        System.out.println("The array is empty!");
                    } else {
                        System.out.println("The array is NOT empty!");
                    }
                    myArray.print();
                    break;
                case "h":
                    System.out.print("Value to check if the array contains: ");
                    value = userInput.next();
                    if (myArray.contains(value)) {
                        System.out.println("The array does contain \""+value+"\"");

                    } else {
                        System.out.println("The array does NOT contain \""+value+"\"");
                    }
                    myArray.print();
                    break;
                case "i":
                    System.out.println("Original Capacity: "+myArray.getInitialCapacity());
                    System.out.println("The array's capacity has doubled.");
                    myArray.resize();
                    System.out.println("New Capacity: "+myArray.getInitialCapacity());
                    myArray.print();
                    break;
                case "j":
                    System.out.println("The array is size (contains this many elements): "+myArray.size());
                    myArray.print();
                    break;
                case "k":
                    System.out.println("The array is size (contains this many elements): "+myArray.getInitialCapacity());
                    myArray.print();
                    break;
                case "l":
                    System.out.println("Printing array...");
                    myArray.print();
                    break;
                default:
                    System.out.println("That is not a recognized command.");
                    myArray.print();
                    break;
                }
        }
    }

    private Object[] data;
    private int initialCapacity;
    private int size = 0;

    /***
     *
     * @param initialCapacity the starting size of the array
     */
    public DynamicArray(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        data = new Object[initialCapacity];
    }

    /***
     * Gets the value at a specific index
     * @param index the position of the array you're looking up
     * @return the data element at the index passed
     */
    public String get(int index) {
        if (index < size && index >= 0){ // check if index is contained in elements of the array
            return(String) data[index];
        } else {
            throw new IndexOutOfBoundsException("Index is out of bounds!"); // error for passing an index that is too long
        }
    }

    /***
     * Return the position of the first instance
     * @param Value String to find the index of
     * @return The index of the inputted value
     */
    public int indexOf(String Value) {
        int indexOfValue = -1; /* currently returns -1 if not found */
        for (int i = 0; i < size; i++) {
            if (data[i] == (Value)) {
                indexOfValue = i;
                break;
            }
        }
        return indexOfValue;
    }

    /***
     * Sets a specific index to a specific value
     * @param index the position of the array you're changing
     * @param value the value you're changing to at the index passed
     */
    public void set(int index, String value){
        if (size == 0){
            throw new IndexOutOfBoundsException("This array currently has size 0!");
        } else if (index < size && index >= 0){ // check if index is contained in list
            data[index] = value;
        } else {
            throw new IndexOutOfBoundsException("Index is out of bounds!"); // error for passing an index that is too long
        }

    }

    /***
     * Insert a value into the array, and shift all necessary elements right to make room
     * @param index position of the array to insert value
     * @param value String to be inserted into array
     */
    public void insert(int index, String value) {
        if (index < size && index >= 0){ // check if index is contained in array (you cannot insert before resize, this would be nonsensical)
            if( size == initialCapacity) {
                resize();
            }
            for (int j = size; j > index; j--) { // loop through all values after the inserted value, and push them back 1
                data[j] = data[j-1];
            }
            data[index] = value;   // set the index as the new value
            size++; // size increases by 1
        } else {
            throw new IndexOutOfBoundsException("Index is out of bounds!"); // error for passing an index outside the original elements
        }
    }

    /***
     * Add the value to the end of the array, resize if array is not large enough
     * @param value String to be added to array
     */
    public void add(String value) {
        if( size == initialCapacity) {
            resize();
        }
        data[size] = value;
        size++;
    }

    /***
     * Doubles the capacity of the array
     */
    public void resize() {
        Object[] newData = new Object[initialCapacity * 2];
        for (int i = 0; i < initialCapacity; i++){
            newData[i] = data[i];
        }
        data = newData;
        initialCapacity = initialCapacity * 2;
    }

    /***
     * Deletes array value at specified index and shifts all necessary elements forward
     * @param index index of element to delete
     */
    public void deleteAt(int index){
        Object[] newData = new Object[initialCapacity];
        if (size == 0){
            throw new IndexOutOfBoundsException("This array currently has size 0, there is nothing to delete!");
        } else if (index < size && index >= 0){ // check if index is contained in elements of the array
            for (int i = 0;i < size;i++){
                if (i < index){ // for values before the index do nothing
                    newData[i] = data[i];
                } else if (i == index) { // skip the loop when index == i
                    continue;
                } else { // for values after index, shift them up
                    newData[i-1] = data[i];
                }
            }
            data = newData;
            size--;
        } else {
            throw new IndexOutOfBoundsException("Index is out of bounds!"); // error for passing an index that is too long
        }
    }

    /***
     * This function deletes all instances of a specified value
     * @param value the value to be deleted
     */
    public void delete(String value){
        for (int i = 0;i<size;i++){
            if (data[i].equals(value)){
                deleteAt(i);
            }
        }
    }

    /***
     *
     * @return if the size of data is currently 0
     */
    public boolean isEmpty(){
        return(size == 0);
    }

    /***
     * Determine if the value is in the array
     * @param value String to look for in the array
     * @return True if value is contained in array, false if value is not contained in array
     */
    public boolean contains(String value){
        boolean flag = false;
        for (int i=0; i<size;i++){
            if (data[i].equals(value)){
                flag = true;
                break;
            }
        }
        return(flag);
    }

    /***
     * Return the number of elements currently in the array
     * @return size of array (the current elements of the array)
     */
    public int size() {
        return size;
    }

    /***
     * Return the capacity of the array
     * @return capacity of array
     */
    public int getInitialCapacity() {
        return initialCapacity;
    }

    /***
     * Print the array element by element
     */
    public void print() {
        System.out.print("Array content is:    ");
        for (int i = 0;i<size;i++){
            System.out.print(data[i] + " ");
        }
        System.out.println();
        System.out.println();
    }

    public static void printMenu() {
        System.out.println("Commands:");
        System.out.println("(a): - Get Value ...");
        System.out.println("(b): - Set Value ...");
        System.out.println("(c): - Insert Value ...");
        System.out.println("(d): - Add Value ...");
        System.out.println("(e): - Delete Value at ...");
        System.out.println("(f): - Delete Value ...");
        System.out.println("(g): - Is the array empty? ...");
        System.out.println("(h): - Does the array contain ...");
        System.out.println("(i): - Resize the array (2x) ...");
        System.out.println("(j): - Get size of array ...");
        System.out.println("(k): - Get capacity of array ...");
        System.out.println("(l): - print values ...");
        System.out.println("(z): - exit the program ...");
        System.out.println();
        System.out.print("Enter Command:  ");
    }
}
