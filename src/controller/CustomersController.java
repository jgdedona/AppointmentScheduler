package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomersController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    @FXML
    private TableColumn<?, ?> addressCol;

    @FXML
    private TableColumn<?, ?> custIdCol;

    @FXML
    private TableColumn<?, ?> custNameCol;

    @FXML
    private TableView<?> customersTableView;

    @FXML
    private TableColumn<?, ?> divisionCol;

    @FXML
    private TableColumn<?, ?> phoneCol;

    @FXML
    private TableColumn<?, ?> postalCodeCol;

    @FXML
    void deleteCustomer(ActionEvent event) {

    }

    @FXML
    void displayAddCustomer(ActionEvent event) {

    }

    @FXML
    void displayMainMenu(ActionEvent event) {

    }

    @FXML
    void displayModifyCustomer(ActionEvent event) {

    }

    @FXML
    void searchOrFilterCustomers(ActionEvent event) {

    }

}