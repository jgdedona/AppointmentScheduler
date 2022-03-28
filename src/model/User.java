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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return String.valueOf(userId);
    }
}
