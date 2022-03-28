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
import model.*;

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
        String type = "";
        LocalDateTime startDateTime = null;
        LocalDateTime endDateTime = null;
        int customerId = 0;
        int userId = 0;
        int contactId = 0;

        Sanitization.sanitizeString(13, title);
        Sanitization.sanitizeString(14, description);
        Sanitization.sanitizeString(15, location);

        try {
            type = typeCombo.getSelectionModel().getSelectedItem();
            startDateTime = LocalDateTime.of(startDateChooser.getValue(), startTimeCombo.getSelectionModel().getSelectedItem());
            endDateTime = LocalDateTime.of(endDateChooser.getValue(), endTimeCombo.getSelectionModel().getSelectedItem());
            customerId = customerIdCombo.getSelectionModel().getSelectedItem().getCustomerId();
            userId = userIdCombo.getSelectionModel().getSelectedItem().getUserId();
            contactId = contactCombo.getSelectionModel().getSelectedItem().getContactId();

            Sanitization.checkBusinessHours(startDateTime, endDateTime);
        } catch (NullPointerException e) {
            Sanitization.setIsValidFalse();
            Sanitization.displayAlert(18);
        }

        if (!(Sanitization.getIsValid())) {
            return;
        }

        Appointment appointment = new Appointment(
                appointmentId,
                title,
                description,
                location,
                type,
                startDateTime,
                endDateTime,
                customerId,
                userId,
                contactId);
        // AppointmentQueries.add
        // Appointment.add
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