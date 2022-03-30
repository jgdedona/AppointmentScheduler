package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.Sanitization;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/** Provides functionality to the Reports view. */
public class ReportsController implements Initializable {

    ObservableList<String> months = FXCollections.observableArrayList();
    ObservableList<String> type = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type.addAll("Introduction", "Briefing", "Update", "Debrief", "Consultation");
        months.addAll("January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December");

        typeCombo.setItems(type);
        monthCombo.setItems(months);
        customerIdCombo.setItems(Customer.getAllCustomers());
        contactNameCombo.setItems(Contact.getAllContacts());
    }

    @FXML
    private ToggleGroup appointmentsBy;

    @FXML
    private TableColumn<Appointment, Integer> apptIdCol;

    @FXML
    private ComboBox<Contact> contactNameCombo;

    @FXML
    private ComboBox<String> typeCombo;

    @FXML
    private ComboBox<String> monthCombo;

    @FXML
    private TableView<Appointment> contactScheduleTableView;

    @FXML
    private TableColumn<Appointment, Integer> custIdCol;

    @FXML
    private ComboBox<Customer> customerIdCombo;

    @FXML
    private TableColumn<Appointment, String> descCol;

    @FXML
    private TableColumn<Appointment, LocalDate> endDateCol;

    @FXML
    private TableColumn<Appointment, LocalDate> startDateCol;

    @FXML
    private TableColumn<Appointment, String> titleCol;

    @FXML
    private TableColumn<Appointment, String> typeCol;

    /** Displays the MainMenu scene on button click.
     * @param event Used to capture button click and change scene.
     * @throws IOException If scene not found. */
    @FXML
    void displayMain(ActionEvent event) throws IOException {
        Stage stage;
        Parent scene;

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Displays an alert that contains a count of appointments of assigned to a specific customer ID.
     * Customer ID is supplied through the customerIdCombo combo box. */
    @FXML
    void generateCustomerIdReport() {
        try {
            Appointment.appointmentByCustomerIdReport(customerIdCombo.getSelectionModel().getSelectedItem().getCustomerId());
        } catch (NullPointerException e) {
            Sanitization.displayAlert(22);
        }
    }

    /** Displays an alert that contains a count of appointments of a certain type within a certain month.
     * Type and month input is supplied through the type and month combo boxes. */
    @FXML
    void generateTypeOrMonthReport() {
        if (typeCombo.getSelectionModel().getSelectedItem() == null || monthCombo.getSelectionModel().getSelectedItem() == null) {
            Sanitization.displayAlert(21);
        } else {
            Appointment.appointmentByTypeAndMonthReport(typeCombo.getSelectionModel().getSelectedItem(), monthCombo.getSelectionModel().getSelectedItem());
        }
    }

    /** Populates the contactScheduleTableView based on the contactNameCombo combo box selection. */
    @FXML
    void populateTableView() {
        contactScheduleTableView.setItems(Appointment.appointmentsByContact(contactNameCombo.getSelectionModel().getSelectedItem().getContactId()));
        apptIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        custIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
    }

}