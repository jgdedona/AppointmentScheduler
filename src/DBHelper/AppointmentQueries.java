package DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class AppointmentQueries {

    // TEST
    public static LocalDateTime getStartTime() throws SQLException {
        String queryString = "SELECT start FROM appointments";

        DBQuery.setPreparedStatement(JDBC.getConnection(), queryString);
        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        LocalDateTime ldt = resultSet.getTimestamp("start").toLocalDateTime();
        return ldt;
    }

}
