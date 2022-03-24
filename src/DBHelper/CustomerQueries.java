package DBHelper;

import javafx.collections.ObservableList;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerQueries {

    public static void populateCustomers() throws SQLException {
        String queryString = "SELECT * FROM customers";

        DBQuery.setPreparedStatement(JDBC.getConnection(), queryString);
        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

        try {
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
}
