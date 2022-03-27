package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Contact;
import model.Customer;
import model.Sanitization;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    ObservableList<String> typeList = FXCollections.observableArrayList();
    ObservableList<LocalTime> timeList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeList.addAll("Introduction", "Briefing", "Update", "Debrief", "Consultation");
        timeList.add(LocalTime.of(00, 00));
        LocalTime firstTime = LocalTime.of(00, 30);

        while (firstTime != LocalTime.of(00, 00)) {
            timeList.add(firstTime);
            firstTime = firstTime.plusMinutes(30);
        }

        contactCombo.setItems(Contact.getAllContacts());
        customerIdCombo.setItems(Customer.getAllCustomers());
        startTimeCombo.setItems(timeList);
        endTimeCombo.setItems(timeList);
        userIdCombo.setItems(User.getAllUsers());
        typeCombo.setItems(typeList);
    }

    @FXML
    private TextField appointmentIdText;

    @FXML
    private ComboBox<Contact> contactCombo;

    @FXML
    private ComboBox<Customer> customerIdCombo;

    @FXML
    private TextField descriptionText;

    @FXML
    private DatePicker endDateChooser;

    @FXML
    private ComboBox<LocalTime> endTimeCombo;

    @FXML
    private TextField locationText;

    @FXML
    private DatePicker startDateChooser;

    @FXML
    private ComboBox<LocalTime> startTimeCombo;

    @FXML
    private TextField titleText;

    @FXML
    private ComboBox<String> typeCombo;

    @FXML
    private ComboBox<User> userIdCombo;

    @FXML
    void addAppointment(ActionEvent event) {
        Sanitization.setIsValidTrue();

        int appointmentId = 0; // Placeholder. Database will provide the appropriate appointmentId.
        String title = titleText.getText();
        String description = descriptionText.getText();
        String location = locationText.getText();
        String type = typeCombo.getSelectionModel().getSelectedItem();
        LocalDateTime startDateTime = LocalDateTime.of(startDateChooser.getValue(), startTimeCombo.getSelectionModel().getSelectedItem());
        LocalDateTime endDateTime = LocalDateTime.of(endDateChooser.getValue(), endTimeCombo.getSelectionModel().getSelectedItem());
        int customerId = customerIdCombo.getSelectionModel().getSelectedItem().getCustomerId();
        int userId = userIdCombo.getSelectionModel().getSelectedItem().getUserId();
        int contactId = contactCombo.getSelectionModel().getSelectedItem().getContactId();

        Sanitization.sanitizeString(13, title);
        Sanitization.sanitizeString(14, description);
        Sanitization.sanitizeString(15, location);
        Sanitization.checkBusinessHours(startDateTime, endDateTime);
    }

    @FXML
    void displayAppointmentView(ActionEvent event) throws IOException {
        Stage stage;
        Parent scene;

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }



}