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

public class ModifyCustomerController implements Initializable {

    /**
     * The initialize method sets the starting state for the scene.
     * Country combo box is populated.
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.*/
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

    /** Updates a customer in the database and allCustomers ObservableList utilizing provided input.
     * @param event Utilized to change views upon completion.
     * @throws IOException If scene not found. */
    @FXML
    void updateCustomer(ActionEvent event) throws IOException {
        Sanitization.setIsValidTrue();

        int customerId = Integer.parseInt(customerIdText.getText());
        String name = nameText.getText();
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

        Sanitization.sanitizeString(5, name);
        Sanitization.sanitizeString(6, address);
        Sanitization.sanitizeString(7, postalCode);
        Sanitization.sanitizeString(8, phone);

        if (!(Sanitization.getIsValid())) {
            return;
        }

        Customer updatedCustomer = new Customer(
                customerId,
                name,
                address,
                postalCode,
                phone,
                divisionId);
        Customer.updateCustomer(updatedCustomer);
        CustomerQueries.modifyCustomer(updatedCustomer);

        Stage stage;
        Parent scene;

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Displays Customer scene.
     * @param event Used to capture button click and change scene.
     * @throws IOException If scene not found. */
    @FXML
    void displayCustomerView(ActionEvent event) throws IOException {
        Stage stage;
        Parent scene;

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Fills the division combo box with appropriate divisions when the country is selected in the country combo box. */
    @FXML
    void fillDivisionCombo() {
        ObservableList<Division> countryDivisions = FXCollections.observableArrayList();
        Country selectedCountry = countryCombo.getSelectionModel().getSelectedItem();

        for (Division division : Division.getAllDivisions()) {
            if (selectedCountry.getCountryId() == division.getCountryId()) {
                countryDivisions.add(division);
            }
        }
        divisionCombo.setItems(countryDivisions);
    }

    /** Called in Customers view to send selected customer data to ModifyCustomer view and populate input fields.
     * @param customer Customer object that will be updated. Used to populate input fields. */
    public void sendCustomer(Customer customer) {
        customerIdText.setText(String.valueOf(customer.getCustomerId()));
        nameText.setText(customer.getCustomerName());
        addressText.setText(customer.getAddress());
        postalCodeText.setText(customer.getPostalCode());
        phoneText.setText(customer.getPhone());
        countryCombo.getSelectionModel().select(Country.selectCountry(Division.findCountryId(customer.getDivisionId())));
        fillDivisionCombo();
        divisionCombo.getSelectionModel().select(Division.findDivisionId(customer.getDivisionId()));
    }

}