package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private Label locationLabel;

    @FXML
    private Label loginLabel;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button submitButton;

    @FXML
    private Label timeZoneLabel;

    @FXML
    private Label userLocationLabel;

    @FXML
    private Label userTimeZoneLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    void checkCredentials(ActionEvent event) {

    }

}