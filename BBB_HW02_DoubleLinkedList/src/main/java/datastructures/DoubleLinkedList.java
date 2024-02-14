package datastructures;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DoubleLinkedList<T> {
    // inner private class
    private class Node {
        T data;
        Node prev;
        Node next;

        // Consider additional fields if needed for contact information
        public Node(T data) {
            this.data = data;
            Node next = null;
            Node prev = null;        }
    }

    private Node head;
    private Node tail;
    private int size;

    public DoubleLinkedList()  {
        // TODO: Constructor logic (initialize, reset)
        this.reset();
    }
    public void reset(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }


    private void mergeEmailAddresses(Node existingNode, Contact newContact) {
        Contact existingContact = (Contact) existingNode.data;
        for (String email : newContact.getEmailAddresses()) {
            if (!existingContact.getEmailAddresses().contains(email)) {
                existingContact.addEmail(email);
            }
        }
    }


    /***
     *
     * @param data
     */
    public void addContact(T data) {
        if (!(data instanceof Contact)) {
            return; // Handle non-Contact data
        }

        Contact newContact = (Contact) data;    // create contact object to add
        Node newNode = new Node(data);  // create a new node that will be added

        // Case 1: Empty List
        if (head == null) {
            head = newNode;
            tail = newNode;
            size++;
            return;
        }

        // Check for existing contact and merge emails if found
        Node current = head;
        Node previous = null;
        while (current != null) {
            if (current.data.equals(newContact)) {
                Node finalCurrent = current;
                mergeEmailAddresses(current, newContact); // If contact already exists, merge in new emails
                return;
            }
            if (((Comparable<T>) current.data).compareTo(newNode.data) > 0) {
                break; // Found the insertion point (where we will insert the node before
            }
            previous = current;
            current = current.next;
        }

        // Insert the newNode before 'current'
        insertNode(newNode, current, true);
        size++;
    }

    /***
     * This method inserts a node in front of the current node, or at the end of the list
     * @param newNode the node to insert
     * @param current the node you insert before
     * @param insertBefore if true, insert before current node, if false insert at end of list
     */
    private void insertNode(Node newNode, Node current, boolean insertBefore) {
        if (insertBefore){
            // Insert the newNode before 'current'
            if (current == head) {
                // Insert at the beginning
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            } else if (current == null) {
                // Insert at the end
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            } else {
                // Insert in the middle
                newNode.prev = current.prev;
                newNode.next = current;
                current.prev.next = newNode;
                current.prev = newNode;
            }
        } else {
            // Insert at the end
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    /***
     * This method deletes ALL instances of a name from the contact list
     * @param lastName the last name of the contact to be deleted
     * @param firstName the first name of the contact to deleted
     * @return returns false if the name is not found in the contact list
     */
    public boolean deleteContact(String lastName, String firstName) {
        Node current = head; // start at the head of the linked list

        while (current != null) {
            Contact contactCurrent = (Contact) current.data; // current Contact
            Contact contactDelete = new Contact(lastName, firstName); // Contact to be deleted

            if (contactCurrent.equals(contactDelete)){ // If first and last name is found
                if (size == 1){
                    head = null;
                    tail = null;
                } else if (current == head){
                    // delete at start
                    head = current.next;
                    head.prev = null;
                } else if (current.next == null) {
                    // delete from end
                    current.prev.next = null;
                    tail = current.prev;
                } else {
                    // delete from middle
                    current.next.prev = current.prev;
                    current.prev.next = current.next;

                }
                size--;
                return true;
            }
            current = current.next;
        }
        return false; // Contact not found
    }

    /***
     * This method deletes a specfic email from a specific contact
     * @param lastName last name of user with email to delete
     * @param firstName first name of user with email to delete
     * @param email email string to delete
     * @return return true if email deleted, return false if contact or email not found.
     */
    public boolean deleteEmailFromContact(String lastName, String firstName, String email) {
        Node current = head;
        while (current != null) {
            Contact contactCurrent = (Contact) current.data; // current Contact
            Contact contactDelete = new Contact(lastName, firstName); // Contact to be deleted

            if (contactCurrent.equals(contactDelete)) { // If first name, last name, and email addresses are equal
                if (contactCurrent.removeEmail(email)){
                    return true; // try to remove email, if successful pass back true
                }
            }
            current = current.next;
        }
        return false; // Contact not found
    }

    public int getNumberOfContacts() {
        return this.size;
    }

    /***
     * This method returns the emails for a specific first and last name
     * @param lastName last name of person to retrieve emails for
     * @param firstName first name of person to retrieve emails for
     * @return return emails if found, otherwise return null
     */
    public List<String> getEmailAddressesForContact(String lastName, String firstName) {
        if (head == null) {
            return null; // List is empty, return null or an empty list
        }

        Node current = head;
        while (current != null) {
            Contact contact = (Contact) current.data;
            Contact contactGet = new Contact(lastName, firstName);
            if (contact.equals(contactGet) && !Objects.equals(contact.getEmailAddresses().toString(),"[]")){
                return contact.getEmailAddresses();
            }
            current = current.next;
        }
        return null; // Contact not found, return null or an empty list
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /***
     * Merge two lists of emails together
     * @param otherList the other list to merge with the current one
     */
    public void mergeLists(DoubleLinkedList<T> otherList) {
        if (otherList == null || otherList.isEmpty()) {
            return; // Nothing to merge if the other list is empty or null
        }

        Node currentOther = otherList.head;
        while (currentOther != null){
            this.addContact(currentOther.data);
            currentOther = currentOther.next;
        }
    }

    public void printList() {
        // TODO
        Node current = head;
        while (current != null) {
            System.out.println(current.data.toString());
            current = current.next;
        }
    }

    // Additional methods as needed for handling duplicates
}