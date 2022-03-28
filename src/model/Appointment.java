package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;

/** The Appointment class models a customer based on the appointment table found in the database. */
public class Appointment {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int customerId;
    private int userId;
    private int contactId;

    public Appointment(int appointmentId, String title, String description, String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime, int customerId, int userId, int contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    public static ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    }

    public static void setAllAppointments(ObservableList<Appointment> allAppointments) {
        Appointment.allAppointments = allAppointments;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Adds an Appointment object to the allAppointments ObservableList.
     * @param appointment Appointment object to be added */
    public static void addAppointment(Appointment appointment) {
        allAppointments.add(appointment);
    }

    /**
     * Removes an Appointment object from the allAppointments ObservableList.
     * @param appointment Appointment object to be removed */
    public static void removeAppointment(Appointment appointment) {
        allAppointments.remove(appointment);
    }

    public static Appointment lookupAppointment(int appointmentId, ObservableList<Appointment> appointments) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId() == appointmentId) {
                return appointment;
            }
        }
        return null;
    }

    public static ObservableList<Appointment> lookupAppointment(String title, ObservableList<Appointment> appointments) {
        ObservableList<Appointment> filteredAppointments = FXCollections.observableArrayList();
        for (Appointment appointment : appointments) {
            if (appointment.getTitle().contains(title)) {
                filteredAppointments.add(appointment);
            }
        }
        if (filteredAppointments.size() > 0) {
            return filteredAppointments;
        } else {
            return appointments;
        }
    }

    public static void updateAppointment(Appointment appointment) {
        for (int i = 0; i < allAppointments.size(); i++) {
            if (allAppointments.get(i).getAppointmentId() == appointment.getAppointmentId()) {
                allAppointments.set(i, appointment);
                return;
            }
        }
    }

    public static boolean customerHasAppointments(int customerId) {
        for (Appointment appointment : allAppointments) {
            if (appointment.getCustomerId() == customerId) {
                return true;
            }
        }
        return false;
    }

    public static void userHasAppointmentWithinFifteen(int userId) {
        boolean appointmentFound = false;

        for (Appointment appointment : allAppointments) {
            if (appointment.getUserId() == userId
                    && appointment.getStartDateTime().isAfter(LocalDateTime.now())
                    && appointment.getStartDateTime().isBefore(LocalDateTime.now().plusMinutes(15))) {
                if (Locale.getDefault().getLanguage() == "fr") {
                    Sanitization.appointmentNotice(2, appointment);
                } else {
                    Sanitization.appointmentNotice(1, appointment);
                }
                appointmentFound = true;
            }
        }

        if (appointmentFound == true) {
            return;
        } else {
            if (Locale.getDefault().getLanguage() == "fr") {
                Sanitization.appointmentNotice(4, null);
            } else {
                Sanitization.appointmentNotice(3, null);
            }
        }
    }

}
