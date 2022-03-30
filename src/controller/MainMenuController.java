package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

/** Provides functionality to the MainMenu view. */
public class MainMenuController implements Initializable {

    /**
     * The initialize method sets the starting state for the scene.
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    /** Called to display a new scene.
     * @param event Used to capture button click and display new scene.
     * @param location The location of the new scene. */
    private void displayScene(ActionEvent event, String location) throws IOException {
        Stage stage;
        Parent scene;

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(location));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Displays Appointments scene.
     * @param event Used to capture button click and change scene.
     * @throws IOException If scene not found */
    @FXML
    void displayAppointments(ActionEvent event) throws IOException {
        displayScene(event, "/view/Appointments.fxml");
    }

    /** Displays Customers scene.
     * @param event Used to capture button click and change scene.
     * @throws IOException If scene not found */
    @FXML
    void displayCustomers(ActionEvent event) throws IOException {
        displayScene(event, "/view/Customers.fxml");
    }

    /** Displays Reports scene.
     * @param event Used to capture button click and change scene.
     * @throws IOException If scene not found */
    @FXML
    void displayReports(ActionEvent event) throws IOException {
        displayScene(event, "/view/Reports.fxml");
    }

    /** Displays Login scene.
     * @param event Used to capture button click and change scene.
     * @throws IOException If scene not found */
    @FXML
    void returnToLogin(ActionEvent event) throws IOException {
        displayScene(event, "/view/Login.fxml");
    }

}