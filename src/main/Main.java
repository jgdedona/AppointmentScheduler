package main;

import DBHelper.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Main extends Application {

    /** Loads initial scene.
     * @param primaryStage Initial scene. */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
        primaryStage.setTitle("Appointment Scheduler");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException {
        JDBC.makeConnection();
        UsersQueries.populateUsers();
        CustomerQueries.populateCustomers();
        CountryQueries.populateCountries();
        DivisionQueries.populateDivisions();
        AppointmentQueries.populateAppointments();
        ContactQueries.populateContacts();
//        Locale.setDefault(new Locale("fr"));
        launch(args);
        JDBC.closeConnection();
    }
}
