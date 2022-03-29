package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class is responsible for modeling User objects that represent user accounts stored in the database. */
public class Contact {
    private int contactId;
    private String contactName;
    private String contactEmail;

    private static ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    /** Constructor for Contact class. */
    public Contact(int contactId, String contactName, String contactEmail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    public static ObservableList<Contact> getAllContacts() {
        return allContacts;
    }

    /** Sets allContacts ObservableList static attribute. */
    public static void setAllContacts(ObservableList<Contact> allContacts) {
        Contact.allContacts = allContacts;
    }

    /** Gets contactId attribute. */
    public int getContactId() {
        return contactId;
    }

    /** Sets contactId attribute. */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /** Gets contactName attribute. */
    public String getContactName() {
        return contactName;
    }

    /** Sets contactName attribute. */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /** Gets contactEmail attribute. */
    public String getContactEmail() {
        return contactEmail;
    }

    /** Gets contactEmail attribute. */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * Adds a Contact object to the allContacts ObservableList. 
     * @param contact Contact object to be added */
    public static void addContact(Contact contact) {
        allContacts.add(contact);
    }

    /** Returns contactName attribute when a Contact object is printed. */
    @Override
    public String toString() {
        return contactName;
    }

    /** Searches for a contact in allContacts using a provided contactId.
     * @param contactId contactId to be searched.
     * @return Matching Contact object if found, else null. */
    public static Contact findContactById(int contactId) {
        for (Contact contact : allContacts) {
            if (contact.getContactId() == contactId) {
                return contact;
            }
        }
        return null;
    }
}
