package DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/** The DBQuery class is responsible for creating and setting the PreparedStatement*/
public class DBQuery {
    private static PreparedStatement preparedStatement;

    /** This method is responsible for setting the prepared statement used for database interaction
     * @param con The database connection
     * @param sql The statement to be executed */
    public static void setPreparedStatement(Connection con, String sql) throws SQLException {
        preparedStatement = con.prepareStatement(sql);
    }

    /** Returns the PreparedStatement object
     * @return preparedStatement object */
    public static PreparedStatement getPreparedStatement() {return preparedStatement;}
}
