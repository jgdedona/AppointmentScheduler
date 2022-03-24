package main;

import DBHelper.CountryQueries;
import DBHelper.CustomerQueries;
import DBHelper.JDBC;
import DBHelper.UsersQueries;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Locale;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
        primaryStage.setTitle("Appointment Scheduler");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {
        JDBC.makeConnection();
        CustomerQueries.populateCustomers();
        CountryQueries.populateCountries();
//        Locale.setDefault(new Locale("fr"));
        launch(args);
    }
}
