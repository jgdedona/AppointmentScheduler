package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Customer;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private TextField appointmentIdText;

    @FXML
    private ComboBox<?> contactCombo;

    @FXML
    private ComboBox<Customer> customerIdCombo;

    @FXML
    private TextField descriptionText;

    @FXML
    private DatePicker endDateChooser;

    @FXML
    private ComboBox<?> endTimeCombo;

    @FXML
    private TextField locationText;

    @FXML
    private DatePicker startDateChooser;

    @FXML
    private ComboBox<?> startTimeCombo;

    @FXML
    private TextField titleText;

    @FXML
    private ComboBox<?> typeCombo;

    @FXML
    private ComboBox<?> userIdCombo;

    @FXML
    void addAppointment(ActionEvent event) {

    }

    @FXML
    void displayAppointmentView(ActionEvent event) {

    }



}