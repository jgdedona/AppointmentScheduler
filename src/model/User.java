package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class is responsible for modeling User objects that represent user accounts stored in the database. */
public class User {
    private int userId;
    private String userName;
    private String password;

//    private static ObservableList allUsers = FXCollections.observableArrayList();

    /** User constructor */
    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    /** Gets userId
     * @return userId*/
    public int getUserId() {
        return userId;
    }

    /** Sets userId
     * @param userId*/
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** Gets userName
     * @return userName*/
    public String getUserName() {
        return userName;
    }

    /** Sets userName
     * @param userName */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** Gets password
     * @return  password*/
    public String getPassword() {
        return password;
    }

    /** Sets password
     * @param password */
    public void setPassword(String password) {
        this.password = password;
    }

//    /** Gets the static ObservableList allUsers
//     * @return allUsers*/
//    public static ObservableList getAllUsers() {
//        return allUsers;
//    }
//
//    /** Sets the static ObservableList allUsers
//     * @param allUsers */
//    public static void setAllUsers(ObservableList allUsers) {
//        User.allUsers = allUsers;
//    }
//
//    /** Adds a new user to the allUsers ObservableList
//     * @param newUser */
//    public static void addUser(User newUser) {
//        allUsers.add(newUser);
//    }
}
