package DBHelper;

import javafx.collections.ObservableList;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerQueries {

    public static void populateCustomers() {
        String queryString = "SELECT * FROM customers";

        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), queryString);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Customer.addCustomer(new Customer(
                        result.getInt("Customer_ID"),
                        result.getString("Customer_Name"),
                        result.getString("Address"),
                        result.getString("Postal_Code"),
                        result.getString("Phone"),
                        result.getInt("Division_ID")));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getStackTrace());
        }
    }

    public static void removeCustomer(Customer customer) {
        String queryString = "DELETE FROM customers WHERE Customer_ID = ?";

        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), queryString);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

            preparedStatement.setInt(1, customer.getDivisionId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getStackTrace());
        }
    }
}
