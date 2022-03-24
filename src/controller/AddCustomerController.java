package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Country;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private TextField addressText;

    @FXML
    private ComboBox<Country> countryCombo;

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
        String customerName = nameText.getText();
        String address = addressText.getText();
        String postalCode = postalCodeText.getText();
        String phone = phoneText.getText();
    }

    @FXML
    void displayCustomerView(ActionEvent event) {

    }

}