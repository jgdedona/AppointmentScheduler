package DBHelper;

import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class contains static methods responsible for querying the divisions table in the database. */
public class DivisionQueries {

    /** This method pulls from the database to populate the allDivisions static ObservableList within the Divisions class. */
    public static void populateDivisions() {
        String queryString = "SELECT * FROM first_level_divisions";

        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), queryString);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            ResultSet resultSet = DBQuery.getPreparedStatement().executeQuery();
            while (resultSet.next()) {
                Division.addDivision(new Division(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(7)
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
