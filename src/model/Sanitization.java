package model;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.Locale;

public class Sanitization {

    private static boolean isValid = true;

    /**
     * Sets the static isValid boolean to true.
     * This is used to determine if an addition or update should take place. */
    public static void setIsValidTrue() {
        isValid = true;
    }

    /**
     * Sets the static isValid boolean to false.
     * This is used to determine if an addition or update should take place. */
    public static void setIsValidFalse() {
        isValid = false;
    }

    /** Displays an alert if a username or password string is empty.
     * @param username Username string
     * @param password Password string
     * @return true if both strings are valid, otherwise false. */
    public static boolean sanitizeLogin(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            if (Locale.getDefault().getLanguage() == "fr") {
                displayAlert(4);
                return false;
            } else {
                displayAlert(3);
                return false;
            }
        }
        return true;
    }

    /**
     * Determines if a string is empty and displays an alert if so.
     * @param value The name string to be checked. */
    public static void sanitizeString(String value) {
        if (value.isEmpty()) {
//            displayAlert(1);
            setIsValidFalse();
        }
    }

    /**
     * Determines if an integer entered in a text field entry is a valid int and displays an alert if not.
     * @param intTxt The stock text field to be checked.
     * @return int */
    public static int sanitizeStock(TextField intTxt) {
        try {
            int value = Integer.parseInt(intTxt.getText());
            if (value >= 0) {
                return value;
            } else {
                setIsValidFalse();
//                displayAlert(2);
                return 0;
            }
        } catch (NumberFormatException e) {
            setIsValidFalse();
//            displayAlert(2);
            return 0;
        }
    }

    /** Displays various alerts depending on the case called.
     * @param alertType Identifies which alert to call*/
    public static void displayAlert(int alertType) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");

        switch(alertType) {
            case 1:
                alert.setHeaderText("Invalid username and password");
                alert.setContentText("Please enter a valid username and password");
                alert.showAndWait();
                break;
            case 2:
                alert.setHeaderText("Nom d'utilisateur et mot de passe invalides");
                alert.setContentText("Veuillez saisir un nom d'utilisateur et un mot de passe valides");
                alert.showAndWait();
                break;
            case 3:
                alert.setHeaderText("Username or password field is blank");
                alert.setContentText("All fields must contain valid data");
                alert.showAndWait();
                break;
            case 4:
                alert.setHeaderText("Le champ Nom d'utilisateur ou mot de passe est vide");
                alert.setContentText("Tous les champs doivent contenir des données valides");
                alert.showAndWait();
                break;
        }
    }
}
