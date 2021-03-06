package controller;

import DBHelper.AppointmentQueries;
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
import model.Sanitization;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/** Responsible for implementing the functionality of the Appointments view. */
public class AppointmentsController implements Initializable {

    /**
     * The initialize method sets the starting state for the scene.
     * All TableViews are populated with the initialize method.
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentsTableView.setItems(Appointment.getAllAppointments());
        appIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
    }

    ObservableList<Appointment> apptByMonth = FXCollections.observableArrayList();
    ObservableList<Appointment> apptByWeek = FXCollections.observableArrayList();


    @FXML
    private TableColumn<Appointment, Integer> appIdCol;

    @FXML
    private TableView<Appointment> appointmentsTableView;

    @FXML
    private TableColumn<Appointment, Integer> contactCol;

    @FXML
    private TableColumn<Appointment, Integer> customerIdCol;

    @FXML
    private TableColumn<Appointment, String> descriptionCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> endCol;

    @FXML
    private TableColumn<Appointment, String> locationCol;

    @FXML
    private TableColumn<Appointment, LocalDateTime> startCol;

    @FXML
    private TableColumn<Appointment, String> titleCol;

    @FXML
    private TableColumn<Appointment, String> typeCol;

    @FXML
    private TableColumn<Appointment, Integer> userIdCol;

    @FXML
    private ToggleGroup viewBy;

    @FXML
    private TextField searchText;

    @FXML
    private RadioButton showAllRBtn;

    @FXML
    private RadioButton showMonthRBtn;

    @FXML
    private RadioButton showWeekRBtn;

    /** Deletes an appointment from the database and allAppointments ObservableList. */
    @FXML
    void deleteAppointment() {
        if (Sanitization.deletionConfirmation()) {
            AppointmentQueries.removeAppointment(appointmentsTableView.getSelectionModel().getSelectedItem());
            Sanitization.appointmentDeletionSuccessful(appointmentsTableView.getSelectionModel().getSelectedItem().getAppointmentId(),
                    appointmentsTableView.getSelectionModel().getSelectedItem().getType());
            Appointment.removeAppointment(appointmentsTableView.getSelectionModel().getSelectedItem());
        }
    }

    /** Displays AddAppointment view on button click.
     * @param event Used to capture button click and change view.
     * @throws IOException If scene not found. */
    @FXML
    void displayAddAppointment(ActionEvent event) throws IOException {
        Stage stage;
        Parent scene;

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Populates TableView with all appointments. */
    @FXML
    void displayAll() {
        appointmentsTableView.setItems(Appointment.getAllAppointments());
    }

    /** Populates TableView with appointments in the same month as the current month. */
    @FXML
    void displayByMonth() {
        apptByMonth.clear();
        for (Appointment appointment : Appointment.getAllAppointments()) {
            if (appointment.getStartDateTime().getMonth() == LocalDateTime.now().getMonth()) {
                apptByMonth.add(appointment);
            }
        }
        appointmentsTableView.setItems(apptByMonth);
    }

    /**
     * Displays any appointments scheduled in the current week.
     * Sunday is considered the first day of the week in this context, so the week is defined as Sunday-Saturday.  */
    @FXML
    void displayByWeek() {
        apptByWeek.clear();
        LocalDateTime ldt = LocalDateTime.now();
        while (ldt.getDayOfWeek() != DayOfWeek.SUNDAY) {
            ldt = ldt.minusDays(1);
        }
        for (Appointment appointment : Appointment.getAllAppointments()) {
            if (appointment.getStartDateTime().isAfter(ldt.withHour(00).withMinute(00).withSecond(00))
                    && appointment.getStartDateTime().isBefore(ldt.plusDays(6).withHour(23).withMinute(59).withSecond(59))) {
                apptByWeek.add(appointment);
            }
        }
        appointmentsTableView.setItems(apptByWeek);
    }

    /** Displays MainMenu view on button click.
     * @param event Used to capture button click and change view. */
    @FXML
    void displayMainMenu(ActionEvent event) throws IOException {
        Stage stage;
        Parent scene;

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Displays ModifyAppointment view on button click and sends the selected Appointment object attributes to the view.
     * @param event Used to capture button click and changes view.*/
    @FXML
    void displayModifyAppointment(ActionEvent event) throws IOException {
        Stage stage;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ModifyAppointment.fxml"));
        loader.load();

        ModifyAppointmentController MAController = loader.getController();
        try {
            MAController.sendAppointment(appointmentsTableView.getSelectionModel().getSelectedItem());
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Implements search bar functionality. */
    @FXML
    void searchOrFilterAppointments() {
        try {
            appointmentsTableView.getSelectionModel().select(Appointment.lookupAppointment(Integer.parseInt(searchText.getText()), appointmentsTableView.getItems()));
        } catch (NumberFormatException e) {
            if (showAllRBtn.isSelected()) {
                appointmentsTableView.setItems(Appointment.lookupAppointment(searchText.getText(), Appointment.getAllAppointments()));
            } else if (showWeekRBtn.isSelected()) {
                appointmentsTableView.setItems(Appointment.lookupAppointment(searchText.getText(), apptByWeek));
            } else if (showMonthRBtn.isSelected()) {
                appointmentsTableView.setItems(Appointment.lookupAppointment(searchText.getText(), apptByMonth));
            }
        }
    }

}