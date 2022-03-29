package DBHelper;

import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Handles all queries against the country table in the database. */
public class CountryQueries {

    /** Called in Main to populate the allCountries ObservableList. */
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
