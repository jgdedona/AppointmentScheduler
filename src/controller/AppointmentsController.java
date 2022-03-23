package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentsController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    Stage stage;
    Parent scene;

    @FXML
    private TableColumn<?, ?> appIdCol;

    @FXML
    private TableView<?> appointmentsTableView;

    @FXML
    private TableColumn<?, ?> contactCol;

    @FXML
    private TableColumn<?, ?> customerIdCol;

    @FXML
    private TableColumn<?, ?> descriptionCol;

    @FXML
    private TableColumn<?, ?> endCol;

    @FXML
    private TableColumn<?, ?> locationCol;

    @FXML
    private TableColumn<?, ?> startCol;

    @FXML
    private TableColumn<?, ?> titleCol;

    @FXML
    private TableColumn<?, ?> typeCol;

    @FXML
    private TableColumn<?, ?> userIdCol;

    @FXML
    private ToggleGroup viewBy;

    @FXML
    void deleteAppointment(ActionEvent event) {

    }

    @FXML
    void displayAddAppointment(ActionEvent event) {

    }

    @FXML
    void displayAll(ActionEvent event) {

    }

    @FXML
    void displayByMonth(ActionEvent event) {

    }

    @FXML
    void displayByWeek(ActionEvent event) {

    }

    @FXML
    void displayMainMenu(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void displayModifyAppointment(ActionEvent event) {

    }

    @FXML
    void searchOrFilterAppointments(ActionEvent event) {

    }

}