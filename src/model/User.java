package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class is responsible for modeling User objects that represent user accounts stored in the database. */
public class User {
    private int userId;
    private String userName;
    private String password;

    private static ObservableList<User> allUsers = FXCollections.observableArrayList();

    /** User constructor */
    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    /** Gets userId attribute. */
    public int getUserId() {
        return userId;
    }

    /** Sets userId attribute. */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** Gets userName attribute. */
    public String getUserName() {
        return userName;
    }

    /** Sets userName attribute. */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** Gets the static ObservableList allUsers
     * @return allUsers*/
    public static ObservableList getAllUsers() {
        return allUsers;
    }

    /** Sets the static ObservableList allUsers
     * @param allUsers */
    public static void setAllUsers(ObservableList allUsers) {
        User.allUsers = allUsers;
    }

    /** Adds a new user to the allUsers ObservableList
     * @param newUser */
    public static void addUser(User newUser) {
        allUsers.add(newUser);
    }

    public static User findUserByUserId(int userId) {
        for (User user : allUsers) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }

    /** Searches for a user in allUsers by a provided userName.
     * @param userName userName to be searched.
     * @return User object if match found, else null. */
    public static User findUserByUserName(String userName) {
        for (User user : allUsers) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    /** Returns string representation of userId when User object is printed. */
    @Override
    public String toString() {
        return String.valueOf(userId);
    }
}
