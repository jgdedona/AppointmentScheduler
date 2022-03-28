package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class is responsible for modeling User objects that represent user accounts stored in the database. */
public class Contact {
    private int contactId;
    private String contactName;
    private String contactEmail;

    private static ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    public Contact(int contactId, String contactName, String contactEmail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    public static ObservableList<Contact> getAllContacts() {
        return allContacts;
    }

    public static void setAllContacts(ObservableList<Contact> allContacts) {
        Contact.allContacts = allContacts;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * Adds a Contact object to the allContacts ObservableList. 
     * @param contact Contact object to be added */
    public static void addContact(Contact contact) {
        allContacts.add(contact);
    }

    @Override
    public String toString() {
        return contactName;
    }

    public static Contact findContactById(int contactId) {
        for (Contact contact : allContacts) {
            if (contact.getContactId() == contactId) {
                return contact;
            }
        }
        return null;
    }
}
