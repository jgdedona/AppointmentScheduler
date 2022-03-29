package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** The Customer class models a customer based on the customer table found in the database. */
public class Customer {
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private int divisionId;

    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    /** Constructor for customer class. */
    public Customer(int customerId, String customerName, String address, String postalCode, String phone, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = divisionId;
    }

    /** Gets customerId attribute. */
    public int getCustomerId() {
        return customerId;
    }

    /** Sets customerId attribute. */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /** Gets customerName attribute. */
    public String getCustomerName() {
        return customerName;
    }

    /** Sets customerName attribute. */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /** Gets address attribute. */
    public String getAddress() {
        return address;
    }

    /** Sets address attribute. */
    public void setAddress(String address) {
        this.address = address;
    }

    /** Gets postalCode attribute. */
    public String getPostalCode() {
        return postalCode;
    }

    /** Sets postalCode attribute. */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /** Gets phone attribute. */
    public String getPhone() {
        return phone;
    }

    /** Sets phone attribute. */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** Gets divisionId attribute. */
    public int getDivisionId() {
        return divisionId;
    }

    /** Sets divisionId attribute. */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /** Gets static allCustomers ObservableList attribute. */
    public static ObservableList<Customer> getAllCustomers() {
        return allCustomers;
    }

    /** Adds a Customer object to the allCustomers ObservableList.
     * @param customer Customer object to be added */
    public static void addCustomer(Customer customer) {
        allCustomers.add(customer);
    }

    /** Removes a Customer object from the allCustomers ObservableList.
     * @param customer Customer object to be removed */
    public static void removeCustomer(Customer customer) {
        allCustomers.remove(customer);
    }

    /** Searches for a customer in allCustomers by customerName.
     * @param customerName Customer name to be searched
     * @return filteredCustomerList if not empty, else allCustomers */
    public static ObservableList<Customer> lookupCustomer(String customerName) {
        ObservableList<Customer> filteredCustomerList = FXCollections.observableArrayList();
        for (Customer customer : allCustomers) {
            if (customer.getCustomerName().contains(customerName)) {
                filteredCustomerList.add(customer);
            }
        }
        if (filteredCustomerList.size() > 0) {
            return filteredCustomerList;
        } else {
            return allCustomers;
        }
    }

    /** Searches for a customer in allCustomers by customerId.
     * @param customerId Customer ID to be searched
     * @return customer if not empty, else null */
    public static Customer lookupCustomer(int customerId) {
        for (Customer customer : allCustomers) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    /** Updates a Customer object in the allCustomers ObservableList.
     * @param customer Customer object to be updated.*/
    public static void updateCustomer(Customer customer) {
        for (int i = 0; i < allCustomers.size(); i++) {
            if (allCustomers.get(i).getCustomerId() == customer.getCustomerId()) {
                allCustomers.set(i, customer);
                return;
            }
        }
    }

    /** Returns a string representation of the customerId when a Customer object is printed. */
    @Override
    public String toString() {
        return String.valueOf(customerId);
    }

}
