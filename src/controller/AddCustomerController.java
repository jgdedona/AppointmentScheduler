package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private TextField addressText;

    @FXML
    private ComboBox<?> countryCombo;

    @FXML
    private TextField customerIdText;

    @FXML
    private ComboBox<?> divisionCombo;

    @FXML
    private TextField nameText;

    @FXML
    private TextField phoneText;

    @FXML
    private TextField postalCodeText;

    @FXML
    void addCustomer(ActionEvent event) {

    }

    @FXML
    void displayCustomerView(ActionEvent event) {

    }

}