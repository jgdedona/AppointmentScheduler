package controller;

import DBHelper.CustomerQueries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomersController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customersTableView.setItems(Customer.getAllCustomers());
        custIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        custNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneCol.setCellValueFactory((new PropertyValueFactory<>("phone")));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
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
    private TableColumn<Customer, String> addressCol;

    @FXML
    private TableColumn<Customer, Integer> custIdCol;

    @FXML
    private TableColumn<Customer, String> custNameCol;

    @FXML
    private TableView<Customer> customersTableView;

    @FXML
    private TableColumn<Customer, Integer> divisionCol;

    @FXML
    private TableColumn<Customer, String> phoneCol;

    @FXML
    private TableColumn<Customer, String> postalCodeCol;

    @FXML
    private TextField custSearchText;

    @FXML
    void deleteCustomer(ActionEvent event) {
        Customer.removeCustomer(customersTableView.getSelectionModel().getSelectedItem());
        CustomerQueries.removeCustomer(customersTableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    void displayAddCustomer(ActionEvent event) throws IOException {
        displayScene(event, "/view/AddCustomer.fxml");
    }

    @FXML
    void displayMainMenu(ActionEvent event) throws IOException {
        displayScene(event, "/view/MainMenu.fxml");
    }

    @FXML
    void displayModifyCustomer(ActionEvent event) throws IOException {
        displayScene(event, "/view/AddCustomer.fxml");
    }

    /** Implements search functionality for customersTableView.
     * @param event */
    @FXML
    void searchOrFilterCustomers(ActionEvent event) {
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