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
import model.Sanitization;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class LoginController implements Initializable {

    /** Initializes all of the labels to the appropriate language. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userTimeZoneLabel.setText(String.valueOf(TimeZone.getDefault().getID()));
        if (Locale.getDefault().getLanguage() == "fr") {
            loginLabel.setText("Connexion");
            usernameTextField.setPromptText("Nom d'utilisateur");
            passwordTextField.setPromptText("Mot de passe");
            submitButton.setText("Soumettre");
            exitButton.setText("Sortir");
            timeZoneLabel.setText("Fuseau Horaire");
        }
    }

    /** Logs login attempts to a file named login_activity.txt.
     * If the file does not exist, it is created. If the file already exists, it is appended to.
     * @param userName Username used in login attempt
     * @param password Password used in login attempt
     * @param index Indicates which log message (success v. failure) to write
     * @throws IOException from file access/write */
    private void logger(String userName, String password, int index) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String filename = "src/../login_activity.txt";
        try {
            FileWriter fwriter = new FileWriter(filename, true);
            PrintWriter outputFile = new PrintWriter(fwriter);
            switch(index) {
                case 1:
                    outputFile.println("Successful Login Attempt - Username: " + userName + " Password: " + password + " Timestamp: " + timestamp);
                    outputFile.close();
                case 2:
                    outputFile.println("Failed Login Attempt - Username: " + userName + " Password: " + password + " Timestamp: " + timestamp);
                    outputFile.close();
            }
            System.out.println("Attempt logged");
        } catch (IOException e) {
            System.out.println("Error: " + e.getStackTrace());
        }
    }

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

        if (!(Sanitization.sanitizeLogin(userName, password))) {
            return;
        }

        if (UsersQueries.authenticateUser(userName, password)) {
            logger(userName, password, 1);
            Stage stage;
            Parent scene;

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } else {
            logger(userName, password, 2);
            if (Locale.getDefault().getLanguage() == "fr") {
                Sanitization.displayAlert(2);
            } else {
                Sanitization.displayAlert(1);
            }
        }
    }

        @FXML
        void exitApplication(ActionEvent event) {
            System.exit(0);
        }

}