package DBHelper;

import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/** Handles all database queries on the appointments table. */
public class AppointmentQueries {

    /** Called in Main to populate allAppointments ObservableList from the appointments table. */
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
                        resultSet.getTimestamp(7).toLocalDateTime(),
                        resultSet.getInt(12),
                        resultSet.getInt(13),
                        resultSet.getInt(14)));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /** Removes appointment row from the appointments table.
     * @param appointment Appointment object to be removed. */
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

    /** Adds appointment row to the appointments table.
     * @param appointment Appointment object to be added. */
    public static void addAppointment(Appointment appointment) {
        String queryString = "INSERT INTO appointments "
                + "(Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), queryString);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

            preparedStatement.setString(1, appointment.getTitle());
            preparedStatement.setString(2, appointment.getDescription());
            preparedStatement.setString(3, appointment.getLocation());
            preparedStatement.setString(4, appointment.getType());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(appointment.getStartDateTime()));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(appointment.getEndDateTime()));
            preparedStatement.setInt(7, appointment.getCustomerId());
            preparedStatement.setInt(8, appointment.getUserId());
            preparedStatement.setInt(9, appointment.getContactId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /** Updates an appointment in the appointment table.
     * @param appointment Appointment object to be updated. */
    public static void updateAppointment(Appointment appointment) {
        String queryString = "UPDATE appointments " +
        "SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? " +
        "WHERE Appointment_ID = ?";

        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), queryString);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

            preparedStatement.setString(1, appointment.getTitle());
            preparedStatement.setString(2, appointment.getDescription());
            preparedStatement.setString(3, appointment.getLocation());
            preparedStatement.setString(4, appointment.getType());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(appointment.getStartDateTime()));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(appointment.getEndDateTime()));
            preparedStatement.setInt(7, appointment.getCustomerId());
            preparedStatement.setInt(8, appointment.getUserId());
            preparedStatement.setInt(9, appointment.getContactId());
            preparedStatement.setInt(10, appointment.getAppointmentId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /** Adds a newly inserted appointment to the allAppointments ObservableList. */
    public static void addInsertedAppointment() {
        String queryString = "SELECT * FROM appointments ORDER BY Appointment_ID DESC LIMIT 1";

        try {
            DBQuery.setPreparedStatement(JDBC.getConnection(), queryString);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Appointment.addAppointment(new Appointment(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getTimestamp(6).toLocalDateTime(),
                    resultSet.getTimestamp(6).toLocalDateTime(),
                    resultSet.getInt(12),
                    resultSet.getInt(13),
                    resultSet.getInt(14)));
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
