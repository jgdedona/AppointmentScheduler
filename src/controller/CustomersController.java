package controller;

import DBHelper.CustomerQueries;
import javafx.beans.property.SimpleStringProperty;
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
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** Provides functionality to the Customers view. */
public class CustomersController implements Initializable {

    /**
     * The initialize method sets the starting state for the scene.
     * The lambda found in this method is utilized to convert the divisionId int to a divisionName string.
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customersTableView.setItems(Customer.getAllCustomers());
        custIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        custNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneCol.setCellValueFactory((new PropertyValueFactory<>("phone")));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        divisionCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(Division.findDivisionId(cellData.getValue().getDivisionId()).getDivisionName()));
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

    @FXML
    private TableColumn<Customer, String> addressCol;

    @FXML
    private TableColumn<Customer, Integer> custIdCol;

    @FXML
    private TableColumn<Customer, String> custNameCol;

    @FXML
    private TableView<Customer> customersTableView;

    @FXML
    private TableColumn<Customer, String> divisionCol;

    @FXML
    private TableColumn<Customer, String> phoneCol;

    @FXML
    private TableColumn<Customer, String> postalCodeCol;

    @FXML
    private TextField custSearchText;

    /** Deletes a customer from the database and allCustomers ObservableList IF the customer has no assigned appointments.*/
    @FXML
    void deleteCustomer() {
        if (Appointment.customerHasAppointments(customersTableView.getSelectionModel().getSelectedItem().getCustomerId())) {
            Sanitization.displayAlert(20);
            return;
        }

        if (Sanitization.deletionConfirmation()) {
            CustomerQueries.removeCustomer(customersTableView.getSelectionModel().getSelectedItem());
            Sanitization.customerDeletionSuccessful(customersTableView.getSelectionModel().getSelectedItem().getCustomerName());
            Customer.removeCustomer(customersTableView.getSelectionModel().getSelectedItem());
        }
    }

    /** Displays AddCustomer scene.
     * @param event Used to capture button click and change scene.
     * @throws IOException If scene not found */
    @FXML
    void displayAddCustomer(ActionEvent event) throws IOException {
        displayScene(event, "/view/AddCustomer.fxml");
    }

    /** Displays MainMenu scene.
     * @param event Used to capture button click and change scene.
     * @throws IOException If scene not found */
    @FXML
    void displayMainMenu(ActionEvent event) throws IOException {
        displayScene(event, "/view/MainMenu.fxml");
    }

    /** Displays ModifyCustomer scene and sends data from selected Customer object to the scene.
     * @param event Used to capture button click and change scene.
     * @throws IOException If scene not found */
    @FXML
    void displayModifyCustomer(ActionEvent event) throws IOException {
        Stage stage;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ModifyCustomer.fxml"));
        loader.load();

        ModifyCustomerController MCController = loader.getController();
        try {
            MCController.sendCustomer(customersTableView.getSelectionModel().getSelectedItem());
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Implements search functionality for customersTableView. */
    @FXML
    void searchOrFilterCustomers() {
        ObservableList<Customer> filteredList = FXCollections.observableArrayList();
        if (custSearchText != null) {
            try {
                customersTableView.getSelectionModel().select(Customer.lookupCustomer(Integer.parseInt(custSearchText.getText())));
            } catch (NumberFormatException e) {
                customersTableView.setItems(Customer.lookupCustomer(custSearchText.getText()));
            }
        }
    }

}