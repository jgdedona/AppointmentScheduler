package model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.time.*;
import java.util.Locale;
import java.util.Optional;

/** Contains static methods and attributes responsible for sanitizing input and displaying alerts. */
public class Sanitization {

    private static boolean isValid = true;

    /** Gets static isValid attribute. */
    public static boolean getIsValid() {
        return isValid;
    }

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
    public static void sanitizeString(int alertIndex, String value) {
        if (value.isEmpty()) {
            displayAlert(alertIndex);
            setIsValidFalse();
        }
    }

    /**
     * Determines if an integer entered in a text field entry is a valid int and displays an alert if not.
     * @param intTxt The int text field to be checked.
     * @return int */
    public static int sanitizeInt(TextField intTxt) {
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

    /** Converts appointment time to EST and checks if the appointment is valid and within business hours.
     * @param startLdt The appointment start time.
     * @param endLdt The appointment end time. */
    public static void checkBusinessHours(LocalDateTime startLdt, LocalDateTime endLdt) {
        ZonedDateTime startZdt = startLdt.atZone(ZoneId.systemDefault());
        ZonedDateTime startTargetZdt = startZdt.withZoneSameInstant(ZoneId.of("America/New_York"));
        LocalDateTime startTargetLdt = startTargetZdt.toLocalDateTime();
        LocalTime startTime = startTargetLdt.toLocalTime();
        LocalDate startDate = startTargetLdt.toLocalDate();

        ZonedDateTime endZdt = endLdt.atZone(ZoneId.systemDefault());
        ZonedDateTime endTargetZdt = endZdt.withZoneSameInstant(ZoneId.of("America/New_York"));
        LocalDateTime endTargetLdt = endTargetZdt.toLocalDateTime();
        LocalTime endTime = endTargetLdt.toLocalTime();
        LocalDate endDate = endTargetLdt.toLocalDate();

        if (startTime.isBefore(LocalTime.of(8, 00))
                || startTime.isAfter(LocalTime.of(22, 00))) {
            displayAlert(11);
            setIsValidFalse();
        }

        if (endTime.isBefore(LocalTime.of(8, 00))
                || endTime.isAfter(LocalTime.of(22, 00))) {
            displayAlert(12);
            setIsValidFalse();
        }

        if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
            displayAlert(17);
            setIsValidFalse();
        }

        if (!(startDate.equals(endDate))) {
            displayAlert(16);
            setIsValidFalse();
        }

        if (startDate.isBefore(LocalDate.now()) && startDate.equals(endDate)) {
            displayAlert(19);
            setIsValidFalse();
        }
    }

    /** Displays a confirmation alert on deletion of objects.
     * @return true if OK is pressed, else false. */
    public static boolean deletionConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("All deletions are final. Please confirm that you would like to proceed.");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    /** Checks for any overlapping customer appointments and displays an alert if any overlap is found. */
    public static boolean customerOverlapCheck(int appointmentId, int customerId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        for (Appointment appointment : Appointment.getAllAppointments()) {
            if (appointment.getCustomerId() == customerId && appointment.getAppointmentId() != appointmentId) {
                if ((startDateTime.isBefore(appointment.getStartDateTime()) || startDateTime.isEqual(appointment.getStartDateTime()))
                        && (endDateTime.isEqual(appointment.getEndDateTime()) || endDateTime.isAfter(appointment.getStartDateTime()))) {
                    displayAlert(23);
                    return true;
                } else if (endDateTime.isAfter(appointment.getStartDateTime()) &&
                        (endDateTime.isBefore(appointment.getEndDateTime()) || endDateTime.isEqual(appointment.getEndDateTime()))) {
                    displayAlert(23);
                    return true;
                } else if ((startDateTime.isEqual(appointment.getStartDateTime()) || startDateTime.isAfter(appointment.getStartDateTime()))
                        && startDateTime.isBefore(appointment.getEndDateTime())) {
                    displayAlert(23);
                    return true;
                }
            }
        }
        return false;
    }

    /** Displays a customized message upon Customer object deletion.
     * @param objectDeleted Name of deleted object. */
    public static void customerDeletionSuccessful(String objectDeleted) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notice");
        alert.setHeaderText("Deletion Successful");
        alert.setContentText(objectDeleted + " has been deleted");
        alert.showAndWait();
    }

    /** Displays a customized message upon Appointment object deletion.
     * @param appointmentId ID of deleted appointment.
     * @param objectDeleted Name of deleted appointment. */
    public static void appointmentDeletionSuccessful(int appointmentId, String objectDeleted) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notice");
        alert.setHeaderText("Deletion Successful");
        alert.setContentText("Appointment " + String.valueOf(appointmentId) + ": " + objectDeleted + " has been deleted");
        alert.showAndWait();
    }

    /** Displays an alert stating an appointment starts within 15 minutes OR there are no appointments within 15 minutes.
     * Can display the alerts in both English and French.
     * @param index Determines which alert to display.
     * @param appointment Appointment object used to customize alert. */
    public static void appointmentNotice(int index, Appointment appointment) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Appointment Notice");

        switch (index) {
            case 1:
                alert.setHeaderText("Appointment begins within 15 minutes");
                alert.setContentText("Appointment ID: " + appointment.getAppointmentId()
                        + "\nDate: " + appointment.getStartDateTime().toLocalDate()
                        + "\nStart Time: " + appointment.getStartDateTime().toLocalTime() + " " + ZoneId.systemDefault());
                alert.showAndWait();
                break;
            case 2:
                alert.setHeaderText("Le rendez-vous commence dans 15 minutes");
                alert.setContentText("ID de rendez-vous: " + appointment.getAppointmentId()
                        + "\nDate: " + appointment.getStartDateTime().toLocalDate()
                        + "\nHeure de début: " + appointment.getStartDateTime().toLocalTime() + " " + ZoneId.systemDefault());
                alert.showAndWait();
                break;
            case 3:
                alert.setHeaderText("No appointments within the next 15 minutes");
                alert.setContentText("There are no upcoming appointments");
                alert.showAndWait();
                break;
            case 4:
                alert.setHeaderText("Aucun rendez-vous dans les 15 prochaines minutes");
                alert.setContentText("Il n'y a pas de rendez-vous à venir");
                alert.showAndWait();
                break;
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
            case 5:
                alert.setHeaderText("Empty Name Text Field");
                alert.setContentText("All text fields must contain data");
                alert.showAndWait();
                break;
            case 6:
                alert.setHeaderText("Empty Address Text Field");
                alert.setContentText("All text fields must contain data");
                alert.showAndWait();
                break;
            case 7:
                alert.setHeaderText("Empty Postal Code Text Field");
                alert.setContentText("All text fields must contain data");
                alert.showAndWait();
                break;
            case 8:
                alert.setHeaderText("Empty Phone Number Text Field");
                alert.setContentText("All text fields must contain data");
                alert.showAndWait();
                break;
            case 9:
                alert.setHeaderText("Must Select a Country");
                alert.setContentText("Please select a country from the drop down list");
                alert.showAndWait();
                break;
            case 10:
                alert.setHeaderText("Must Select a Division");
                alert.setContentText("Please select a division from the drop down list");
                alert.showAndWait();
                break;
            case 11:
                alert.setHeaderText("Appointments must be within the hours of 8AM - 10PM EST");
                alert.setContentText("Please select a valid start time");
                alert.showAndWait();
                break;
            case 12:
                alert.setHeaderText("Appointments must be within the hours of 8AM - 10PM EST");
                alert.setContentText("Please select a valid end time");
                alert.showAndWait();
                break;
            case 13:
                alert.setHeaderText("Empty Title Text Field");
                alert.setContentText("All text fields must contain data");
                alert.showAndWait();
                break;
            case 14:
                alert.setHeaderText("Empty Description Text Field");
                alert.setContentText("All text fields must contain data");
                alert.showAndWait();
                break;
            case 15:
                alert.setHeaderText("Empty Location Text Field");
                alert.setContentText("All text fields must contain data");
                alert.showAndWait();
                break;
            case 16:
                alert.setHeaderText("Invalid start and end dates");
                alert.setContentText("Start and end dates must be on the same day");
                alert.showAndWait();
                break;
            case 17:
                alert.setHeaderText("Invalid start and end times");
                alert.setContentText("Start time must be before end time");
                alert.showAndWait();
                break;
            case 18:
                alert.setHeaderText("Null combo box or date picker selections");
                alert.setContentText("All combo boxes and date pickers must contain selected data");
                alert.showAndWait();
                break;
            case 19:
                alert.setHeaderText("Invalid date selection");
                alert.setContentText("The appointment date must be on or after the current date");
                alert.showAndWait();
                break;
            case 20:
                alert.setHeaderText("Cannot delete customers with scheduled appointments");
                alert.setContentText("You must delete all appointments assigned to the customer before deleting the customer");
                alert.showAndWait();
                break;
            case 21:
                alert.setHeaderText("No Type or Month Selected");
                alert.setContentText("You must select both a type and a month");
                alert.showAndWait();
                break;
            case 22:
                alert.setHeaderText("No Customer ID Selected");
                alert.setContentText("You must select a Customer ID");
                alert.showAndWait();
                break;
            case 23:
                alert.setHeaderText("Invalid Date/Time Selections");
                alert.setContentText("Selected appointment time overlaps with a previously scheduled appointment");
                alert.showAndWait();
                break;
        }
    }
}
