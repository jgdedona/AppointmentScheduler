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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.Division;
import model.Sanitization;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryCombo.setItems(Country.getAllCountries());
    }

    @FXML
    private TextField addressText;

    @FXML
    private ComboBox<Country> countryCombo;

    @FXML
    private TextField customerIdText;

    @FXML
    private ComboBox<Division> divisionCombo;

    @FXML
    private TextField nameText;

    @FXML
    private TextField phoneText;

    @FXML
    private TextField postalCodeText;

    @FXML
    void addCustomer(ActionEvent event) throws IOException {
        Sanitization.setIsValidTrue();

        String customerName = nameText.getText();
        String address = addressText.getText();
        String postalCode = postalCodeText.getText();
        String phone = phoneText.getText();
        int divisionId = 0;

        if (countryCombo.getSelectionModel().getSelectedItem() == null) {
            Sanitization.displayAlert(9);
            Sanitization.setIsValidFalse();
        }

        try {
            divisionId = divisionCombo.getSelectionModel().getSelectedItem().getDivisionID();
        } catch (NullPointerException e) {
            Sanitization.displayAlert(10);
            Sanitization.setIsValidFalse();
        }

        Sanitization.sanitizeString(5, customerName);
        Sanitization.sanitizeString(6, address);
        Sanitization.sanitizeString(7, postalCode);
        Sanitization.sanitizeString(8, phone);

        if (!(Sanitization.getIsValid())) {
            return;
        }

        Customer addedCustomer = new Customer(
                1, // Placeholder - The database will assign the proper ID
                customerName,
                address,
                postalCode,
                phone,
                divisionId);
        CustomerQueries.addCustomer(addedCustomer);
        CustomerQueries.addInsertedCustomer();

        Stage stage;
        Parent scene;

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }

    @FXML
    void displayCustomerView(ActionEvent event) throws IOException {
        Stage stage;
        Parent scene;

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void fillDivisionCombo(ActionEvent event) {
        ObservableList<Division> countryDivisions = FXCollections.observableArrayList();
        Country selectedCountry = countryCombo.getSelectionModel().getSelectedItem();

        for (Division division : Division.getAllDivisions()) {
            if (selectedCountry.getCountryId() == division.getCountryId()) {
                countryDivisions.add(division);
            }
        }

        divisionCombo.setItems(countryDivisions);
    }

}