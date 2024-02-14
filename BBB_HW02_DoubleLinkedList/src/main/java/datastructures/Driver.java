package datastructures;

import org.w3c.dom.Node;

import java.io.*;

public class Driver {
    private static void processCommand(String line, DoubleLinkedList<Contact> contactList) {
        String[] parts = line.split("\\s+", 2);
        String command = parts[0].toLowerCase();
        String[] args = parts.length > 1 ? parts[1].split(",\\s*") : new String[0];

        switch (command) {
            case "addcontact":
                System.out.println("Adding Contact:     " + args[0] + ", " + args[1] );

                Contact contact = new Contact(args[0], args[1]);
                for (int i = 2; i < args.length; i++) {
                    if (!args[i].isEmpty()) {
                        contact.addEmail(args[i]);
                    }
                }
                contactList.addContact(contact);
                break;
            case "deletecontact":
                System.out.println("Deleting Contact:   " + args[0] + ", " + args[1]);
                contactList.deleteContact(args[0], args[1]);
                break;
            case "lengthcontacts":
                System.out.println("Number of contacts: "+contactList.getNumberOfContacts());
                break;
            case "getemailaddress":
                if (args.length == 2) {
                    System.out.println("Emails list:        " + args[0] + ", " + args[1] + ":\t" +
                            contactList.getEmailAddressesForContact(args[0],args[1])
                    );
                }
                break;
            case "isemptycontactlist":
                System.out.println("List is empty:  " + contactList.isEmpty());
                break;
            case "deleteemail":
                System.out.println("Email Deleted:  " +
                        contactList.deleteEmailFromContact(args[0],args[1],args[2])
                );
                break;
            case "printcontacts":
                System.out.println("\nCurrent Contact List:");
                contactList.printList();
                break;
            default:
                System.out.println("Unknown command: >" + command + "<");
        }
    }

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
        String filename     = "contacts10.csv"; // default
        String commandFile  = "commands.txt";

        if (args.length < 2) {
            System.out.println("Using default files: " + filename + " & " + commandFile );
            System.out.println("Driver <" + filename + "> <" + commandFile + ">" );
            // return;
        }
        else {
            filename = args[0];
            commandFile = args[1];
        }

        DoubleLinkedList<Contact> contactList = new DoubleLinkedList<>();

        // Populate the List  with filename
        populateListFromFile(contactList, filename);

        // For demonstration: Print the contacts
        System.out.println("\nInitial Contact List:");

        contactList.printList();

        System.out.println("\nProcessing Commands: ");

        // Process command file
        try (BufferedReader commandReader = new BufferedReader(new FileReader(commandFile))) {
            String line;
            while ((line = commandReader.readLine()) != null) {
                processCommand(line, contactList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nFinal List:" );
        // For demonstration: Print final contacts list
        contactList.printList();
    }
}