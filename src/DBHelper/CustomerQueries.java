package DBHelper;

import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class contains static methods responsible for querying the customers table in the database. */
public class CustomerQueries {

    /** Called in Main to populate the allCustomers ObservableList. */
    public static void populateCustomers() {
        String queryString = "SELECT * FROM customers";

        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), queryString);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer.addCustomer(new Customer(
                        resultSet.getInt("Customer_ID"),
                        resultSet.getString("Customer_Name"),
                        resultSet.getString("Address"),
                        resultSet.getString("Postal_Code"),
                        resultSet.getString("Phone"),
                        resultSet.getInt("Division_ID")));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /** Utilized to remove a customer row from the table.
     * @param customer Customer object/row to be removed. */
    public static void removeCustomer(Customer customer) {
        String queryString = "DELETE FROM customers WHERE Customer_ID = ?";

        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), queryString);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

            preparedStatement.setInt(1, customer.getCustomerId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /** Adds a customer row to the table.
     * @param customer Customer object/row to be added. */
    public static void addCustomer(Customer customer) {
        String queryString = "INSERT INTO customers "
        + "(Customer_Name, Address, Postal_Code, Phone, Division_ID) "
        + "VALUES (?, ?, ?, ?, ?)";

        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), queryString);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setString(2, customer.getAddress());
            preparedStatement.setString(3, customer.getPostalCode());
            preparedStatement.setString(4, customer.getPhone());
            preparedStatement.setInt(5, customer.getDivisionId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /** Updates a customer row in the table.
     * @param customer Customer object/row to be updated.*/
    public static void modifyCustomer(Customer customer) {
        String queryString = "UPDATE customers " +
                "SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? " +
                "WHERE Customer_ID = ?";

        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), queryString);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setString(2, customer.getAddress());
            preparedStatement.setString(3, customer.getPostalCode());
            preparedStatement.setString(4, customer.getPhone());
            preparedStatement.setInt(5, customer.getDivisionId());
            preparedStatement.setInt(6, customer.getCustomerId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Retrieves the last inserted entry in the customers table and updates the static allCustomers ObservableList. */
    public static void addInsertedCustomer() {
        String queryString = "SELECT * FROM customers ORDER BY Customer_ID DESC LIMIT 1";

        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), queryString);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Customer.addCustomer(new Customer(
                    resultSet.getInt("Customer_ID"),
                    resultSet.getString("Customer_Name"),
                    resultSet.getString("Address"),
                    resultSet.getString("Postal_Code"),
                    resultSet.getString("Phone"),
                    resultSet.getInt("Division_ID")));
            } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
