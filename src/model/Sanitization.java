package model;

import javafx.scene.control.Alert;

import java.util.Locale;

public class Sanitization {

    public static boolean sanitizeLogin(String string1, String string2) {
        if (string1.isEmpty() || string2.isEmpty()) {
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
