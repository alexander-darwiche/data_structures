package datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleLinkedListTest {

    private DoubleLinkedList<Contact> contactList;

    @BeforeEach
    void setUp() {
        contactList = new DoubleLinkedList<>();
        contactList.addContact(new Contact("Obama","Barack"));
    }


    @Test
    void addContact() {

        Contact contactAdded = new Contact("Clinton", "Hillary");
        contactAdded.addEmail("hillary@gmail.com");
        contactList.addContact(contactAdded);

        assertEquals(2,contactList.getNumberOfContacts());

        assertEquals("[hillary@gmail.com]",contactList.getEmailAddressesForContact("Clinton","Hillary").toString());
    }

    @Test
    void deleteContact() {
        assertEquals(false,contactList.isEmpty());
        contactList.deleteContact("Obama","Barack");
        assertEquals(true,contactList.isEmpty());

    }

    @Test
    void deleteEmailFromContact() {
        Contact contactAdded = new Contact("Clinton", "Hillary");
        contactAdded.addEmail("hillary@gmail.com");
        contactList.addContact(contactAdded);
        assertEquals("[hillary@gmail.com]",contactList.getEmailAddressesForContact("Clinton","Hillary").toString());

        contactList.deleteEmailFromContact("Clinton","Hillary","hillary@gmail.com");
        assertEquals(null,contactList.getEmailAddressesForContact("Clinton","Hillary"));


    }

    @Test
    void getNumberOfContacts() {
        assertEquals(1,contactList.getNumberOfContacts());
    }

    @Test
    void getEmailAddressesForContact() {
        Contact contactAdded = new Contact("Clinton", "Hillary");
        contactAdded.addEmail("hillary@gmail.com");
        contactList.addContact(contactAdded);
        assertEquals("[hillary@gmail.com]",contactList.getEmailAddressesForContact("Clinton","Hillary").toString());
    }


    @Test
    void size() {
        assertEquals(1,contactList.size());
        Contact contactAdded = new Contact("Clinton", "Hillary");
        contactList.addContact(contactAdded);
        assertEquals(2,contactList.size());
    }

    @Test
    void isEmpty() {
        assertEquals(false,contactList.isEmpty());
        contactList.deleteContact("Obama","Barack");
        assertEquals(true,contactList.isEmpty());
    }

    @Test
    void mergeLists() {
        DoubleLinkedList<Contact> contactList2 = new DoubleLinkedList<>();
        Contact contactDonald = new Contact("Trump","Donald");
        contactDonald.addEmail("donald@gmail.com");
        contactList2.addContact(contactDonald);

        contactList.mergeLists(contactList2);
        assertEquals("[donald@gmail.com]",contactList.getEmailAddressesForContact("Trump","Donald").toString());
    }

    @Test
    void reset() {
        contactList.reset();
        assertEquals(0,contactList.size());
    }

}