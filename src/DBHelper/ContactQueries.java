package DBHelper;

import model.Contact;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactQueries {

    public static void populateContacts() {
        String queryString = "SELECT * FROM contacts";

        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), queryString);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Contact.addContact(new Contact(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
