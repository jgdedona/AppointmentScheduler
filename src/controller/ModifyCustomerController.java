package controller;

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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyCustomerController implements Initializable {

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
    void updateCustomer(ActionEvent event) {

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