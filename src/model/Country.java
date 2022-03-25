package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** The Country class models a country based on the country table found in the database. */
public class Country {
    private int countryId;
    private String country;

    private static ObservableList<Country> allCountries = FXCollections.observableArrayList();

    public Country(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static ObservableList<Country> getAllCountries() {
        return allCountries;
    }

    /** Adds a Country object to the allCountries ObservableList.
     * @param country Country object to be added */
    public static void addCountry(Country country) {
        allCountries.add(country);
    }

    /** Removes a Country object from the allCountries ObservableList.
     * @param country Country object to be removed */
    public static void removeCustomer(Country country) {
        allCountries.remove(country);
    }

    /** Returns a Country object from allCountries if the object's ID matches the supplied countryID.
     * @param countryId Country ID to be checked
     * @return Matching country if found, else null */
    public static Country selectCountry(int countryId) {
        for (Country country : allCountries) {
            if (country.getCountryId() == countryId) {
                return country;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return (this.getCountry());
    }
}
