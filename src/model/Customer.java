package model;

import DBHelper.CustomerQueries;
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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

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
     * @return filteredCustomerList if not empty, else allCustomers */
    public static Customer lookupCustomer(int customerId) {
        for (Customer customer : allCustomers) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    public static void updateCustomer(Customer customer) {
        for (int i = 0; i < allCustomers.size(); i++) {
            if (allCustomers.get(i).getCustomerId() == customer.getCustomerId()) {
                allCustomers.set(i, customer);
                return;
            }
        }
    }

}
