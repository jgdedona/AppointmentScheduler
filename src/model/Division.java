package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** The Division class models a division based on the first_level_divisions table found in the database. */
public class Division {
    private int divisionID;
    private String divisionName;
    private int countryId;

    private static ObservableList<Division> allDivisions = FXCollections.observableArrayList();

    /** Constructor for the Division class. */
    public Division(int divisionID, String divisionName, int countryId) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }

    /** Gets the divisionId attribute. */
    public int getDivisionID() {
        return divisionID;
    }

    /** Sets the divisionId attribute. */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /** Gets the divisionName attribute. */
    public String getDivisionName() {
        return divisionName;
    }

    /** Sets the divisionName attribute. */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /** Gets the countryId attribute. */
    public int getCountryId() {
        return countryId;
    }

    /** Sets the countryId attribute. */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /** Gets the static allDivisions ObservableList attribute. */
    public static ObservableList<Division> getAllDivisions() {
        return allDivisions;
    }

    public static void setAllDivisions(ObservableList<Division> allDivisions) {
        Division.allDivisions = allDivisions;
    }

    /** Adds a new Division object to the allDivisions ObservableList.
     * @param division Division object to be added */
    public static void addDivision(Division division) {
        allDivisions.add(division);
    }

    /** Removes a specified Division object from allDivisions.
     * @param division Division object to be removed */
    public static void removeDivision(Division division) {
        allDivisions.remove(division);
    }

    /** Finds the countryId for a specific division in allDivisions.
     * @param divisionID Division ID to be matched
     * @return The countryID that belongs to the supplied divsionID. 0 if no match found. */
    public static int findCountryId(int divisionID) {
        for (Division division : allDivisions) {
            if (division.getDivisionID() == divisionID) {
                return division.getCountryId();
            }
        }
        return 0;
    }

    /** Finds a Division object using the provided divisionID.
     * @param divisionID divisionID to be searched for.
     * @return Division object if match found, else null. */
    public static Division findDivisionId(int divisionID) {
        for (Division division: allDivisions) {
            if (division.getDivisionID() == divisionID) {
                return division;
            }
        }
        return null;
    }

    /** Returns the divisionName attribute when a Division object is printed. */
    @Override
    public String toString() {
        return (this.getDivisionName());
    }
}
