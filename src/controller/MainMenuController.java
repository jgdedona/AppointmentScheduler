package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    private void displayScene(ActionEvent event, String location) throws IOException {
        Stage stage;
        Parent scene;

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(location));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void displayAppointments(ActionEvent event) throws IOException {
        displayScene(event, "/view/Appointments.fxml");
    }

    @FXML
    void displayCustomers(ActionEvent event) throws IOException {
        displayScene(event, "/view/Customers.fxml");
    }

    @FXML
    void displayReports(ActionEvent event) throws IOException {
        displayScene(event, "/view/Reports.fxml");
    }

    @FXML
    void returnToLogin(ActionEvent event) throws IOException {
        displayScene(event, "/view/Login.fxml");
    }

}