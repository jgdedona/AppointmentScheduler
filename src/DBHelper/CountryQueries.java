package DBHelper;

import model.Country;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryQueries {

    public static void populateCountries() throws SQLException {
        String queryString = "SELECT * FROM countries";

        DBQuery.setPreparedStatement(JDBC.getConnection(), queryString);
        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

        try {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Country.addCountry(new Country(
                        result.getInt("Country_ID"),
                        result.getString("Country")));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
