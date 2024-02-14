package datastructures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class DriverMerge {


    private static void populateListFromFile(DoubleLinkedList<Contact> list, String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            boolean isFirstLine = true;
            String line;
            while ((line = reader.readLine()) != null) {
                // Remove BOM if present on the first line
                if (isFirstLine && line.startsWith("\uFEFF")) {
                    line = line.substring(1);
                    isFirstLine = false;
                }

                String[] parts = line.split(",\\s*");
                for (int i=0;i<parts.length;i++){
                    parts[i] = parts[i].replaceAll("\u00A0", "");
                }
                if (parts.length >= 2) {
                    Contact contact = new Contact(parts[0], parts[1]);
                    for (int i = 2; i < parts.length; i++) {
                        if (!parts[i].isEmpty()) {
                            contact.addEmail(parts[i]);
                        }
                    }
                    list.addContact(contact);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String filename1 = "contacts10.csv";
        // Assuming a second file for the second list
        String filename2 = "contacts11.csv";

        if (args.length < 2) {
            System.out.println("Using default files: contacts10.csv & contacts10.csv");
            System.out.println("DriverMerge <contacts10.csv> <contacts11.csv>");
        }
        else {
            // if commandline is provided
            filename1 = args[0];
            filename2 = args[1];
        }


        DoubleLinkedList<Contact> contactList1 = new DoubleLinkedList<>();
        DoubleLinkedList<Contact> contactList2 = new DoubleLinkedList<>();

        // Populate the first list
        System.out.println("\nPopulate List 1:");
        populateListFromFile(contactList1, filename1);

        // Populate the second list
        System.out.println("\nPopulate List 2:");
        populateListFromFile(contactList2, filename2);

        // For demonstration: Print both lists
        System.out.println("\nList 1:");
        // TODO printList
        contactList1.printList();
        System.out.println("\nList 2:");
        // TODO printList
        contactList2.printList();

        // Merge lists command
        System.out.println("\nMerging List 1 into List 2:");
        // TODO: mergeLists() method
        contactList2.mergeLists(contactList1);
        // TODO print final merged list printLists
        System.out.println("\nPrint Merged List:");
        contactList2.printList();
    }
}