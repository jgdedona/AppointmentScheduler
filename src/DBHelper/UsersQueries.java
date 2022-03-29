package DBHelper;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Handles all queries against users table in the database. */
public class UsersQueries {

    /** Checks supplied credentials against those stored in the database.
     * @param userName Username to check
     * @param password Password to check
     * @return true if the userexists and the credentials are correct, else returns false
     * @throws SQLException from SELECT statement */
    public static boolean authenticateUser(String userName, String password) {
        String queryString = "SELECT * FROM users WHERE User_Name = ? AND Password = ?";

        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), queryString);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);

            ResultSet result = preparedStatement.executeQuery();
            return result.next();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    /** Called in Main to populate allUsers ObservableList.
     * Does NOT populate the passwords. */
    public static void populateUsers() {
        String queryString = "SELECT * FROM users";

        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), queryString);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User.addUser(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        ""
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
