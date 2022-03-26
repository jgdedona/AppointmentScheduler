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

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AppointmentsController implements Initializable {

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

    @FXML
    void deleteAppointment(ActionEvent event) {

    }

    @FXML
    void displayAddAppointment(ActionEvent event) throws IOException {
        Stage stage;
        Parent scene;

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void displayAll(ActionEvent event) {
        appointmentsTableView.setItems(Appointment.getAllAppointments());
    }

    @FXML
    void displayByMonth(ActionEvent event) {
        apptByMonth.clear();
        for (Appointment appointment : Appointment.getAllAppointments()) {
            if (appointment.getStartDateTime().isAfter(LocalDateTime.now())
            && appointment.getStartDateTime().isBefore(LocalDateTime.now().plusDays(30))) {
                apptByMonth.add(appointment);
            }
        }
        appointmentsTableView.setItems(apptByMonth);
    }

    @FXML
    void displayByWeek(ActionEvent event) {
        apptByWeek.clear();
        for (Appointment appointment : Appointment.getAllAppointments()) {
            if (appointment.getStartDateTime().isAfter(LocalDateTime.now())
                    && appointment.getStartDateTime().isBefore(LocalDateTime.now().plusDays(7))) {
                apptByWeek.add(appointment);
            }
        }
        appointmentsTableView.setItems(apptByWeek);
    }

    @FXML
    void displayMainMenu(ActionEvent event) throws IOException {
        Stage stage;
        Parent scene;

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void displayModifyAppointment(ActionEvent event) throws IOException {
        Stage stage;
        Parent scene;

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ModifyAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void searchOrFilterAppointments(ActionEvent event) {
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