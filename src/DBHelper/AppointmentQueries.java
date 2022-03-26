package DBHelper;

import model.Appointment;
import model.Customer;

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
    } // END TEST

    public static void populateAppointments() {
        String queryString = "SELECT * FROM appointments";

        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), queryString);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Appointment.addAppointment(new Appointment(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getTimestamp(6).toLocalDateTime(),
                        resultSet.getTimestamp(6).toLocalDateTime().toLocalDate(),
                        resultSet.getTimestamp(6).toLocalDateTime().toLocalTime(),
                        resultSet.getTimestamp(6).toLocalDateTime(),
                        resultSet.getTimestamp(7).toLocalDateTime().toLocalDate(),
                        resultSet.getTimestamp(7).toLocalDateTime().toLocalTime(),
                        resultSet.getInt(12),
                        resultSet.getInt(13),
                        resultSet.getInt(14)));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void removeAppointment(Appointment appointment) {
        String queryString = "DELETE FROM appointments WHERE Appointment_ID = ?";

        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), queryString);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

            preparedStatement.setInt(1, appointment.getAppointmentId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
