package datastructures;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args){

        //TODO: Implement testing logic here
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

        boolean continueUserSession = true; // boolean to indicate whether the program is still running

        while (continueUserSession){
            java.lang.String selection;
            java.lang.String value;
            int index;
            DynamicArray.printMenu(); //print menu of choices

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
}
