package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Division {
    private int divisionID;
    private String divisionName;
    private int countryId;

    private static ObservableList<Division> allDivisions = FXCollections.observableArrayList();

    public Division(int divisionID, String divisionName, int countryId) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

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

    public static Division findDivisionId(int divisionID) {
        for (Division division: allDivisions) {
            if (division.getDivisionID() == divisionID) {
                return division;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return (this.getDivisionName());
    }
}
