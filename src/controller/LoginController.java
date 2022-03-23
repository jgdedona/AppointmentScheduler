package controller;

import DBHelper.DBQuery;
import DBHelper.UsersQueries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    private Button exitButton;

    @FXML
    private Label timeZoneLabel;

    @FXML
    private Label userLocationLabel;

    @FXML
    private Label userTimeZoneLabel;

    @FXML
    private TextField usernameTextField;

    /** Authenticates the user by checking the credentials supplied in the username and password
     * text fields against those stored in the database.
     * @param event
     * @throws SQLException from database lookup
     * @throws IOException from setting and loading new scene*/
    @FXML
    void checkCredentials(ActionEvent event) throws SQLException, IOException {
        String userName = usernameTextField.getText();
        String password = passwordTextField.getText();

        if (UsersQueries.authenticateUser(userName, password)) {
            Stage stage;
            Parent scene;

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

        @FXML
        void exitApplication(ActionEvent event) {
            System.exit(0);
        }

}