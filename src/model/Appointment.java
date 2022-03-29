package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
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

    /** Constructor for Appointment class. */
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

    /** Sets allAppointments ObservableList. */
    public static void setAllAppointments(ObservableList<Appointment> allAppointments) {
        Appointment.allAppointments = allAppointments;
    }

    /** Gets startDateTime attribute. */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /** Sets startDateTime attribute. */
    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    /** Gets endDateTime attribute. */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /** Sets endDateTime attribute. */
    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    /** Gets appointmentId attribute. */
    public int getAppointmentId() {
        return appointmentId;
    }

    /** Sets appointmentId attribute. */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /** Gets title attribute. */
    public String getTitle() {
        return title;
    }

    /** Sets title attribute. */
    public void setTitle(String title) {
        this.title = title;
    }

    /** Gets description attribute. */
    public String getDescription() {
        return description;
    }

    /** Sets description attribute. */
    public void setDescription(String description) {
        this.description = description;
    }

    /** Gets location attribute. */
    public String getLocation() {
        return location;
    }

    /** Sets location attribute. */
    public void setLocation(String location) {
        this.location = location;
    }

    /** Gets type attribute. */
    public String getType() {
        return type;
    }

    /** Sets type attribute. */
    public void setType(String type) {
        this.type = type;
    }

    /** Gets customerId attribute. */
    public int getCustomerId() {
        return customerId;
    }

    /** Sets customerId attribute. */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /** Gets userId attribute. */
    public int getUserId() {
        return userId;
    }

    /** Sets userId attribute. */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** Gets contactId attribute. */
    public int getContactId() {
        return contactId;
    }

    /** Sets contactId attribute. */
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

    /** Searches for appointment in allAppointments by title attribute.
     * @param title Title to be searched for.
     * @param appointments ObservableList to be return if no results are found.
     * @return filteredAppointments with appointments matching the title OR provided ObservableList if no appointments found. */
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

    /** Updates an appointment in the allAppointments ObservableList.
     * @param appointment Appointment object to be updated. */
    public static void updateAppointment(Appointment appointment) {
        for (int i = 0; i < allAppointments.size(); i++) {
            if (allAppointments.get(i).getAppointmentId() == appointment.getAppointmentId()) {
                allAppointments.set(i, appointment);
                return;
            }
        }
    }

    /** Checks if a customer has any assigned appointments.
     * @param customerId customerId to be checked.
     * @return True if appointment found, else false. */
    public static boolean customerHasAppointments(int customerId) {
        for (Appointment appointment : allAppointments) {
            if (appointment.getCustomerId() == customerId) {
                return true;
            }
        }
        return false;
    }

    /** Checks if a contact has any assigned appointments and adds those appointments to an ObservableList.
     * @param contactId customerId to be checked.
     * @return appointmentsByCustomer observable lists with all customer appointments. */
    public static ObservableList<Appointment> appointmentsByContact(int contactId) {
        ObservableList<Appointment> contactAppointments = FXCollections.observableArrayList();
        for (Appointment appointment : allAppointments) {
            if (appointment.getContactId() == contactId) {
                contactAppointments.add(appointment);
            }
        }
        return contactAppointments;
    }

    /** Checks if a user has an appointment starting within fifteen minutes of the current time.
     * @param userId userId to be checked. */
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
