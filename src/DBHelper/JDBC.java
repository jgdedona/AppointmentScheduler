package DBHelper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/** Provides static methods for establishing connection to the database. */
public class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password
    private static Connection connection = null;  // Connection Interface
    private static PreparedStatement preparedStatement;

    /** Establishes a connection to the database. */
    public static void makeConnection() {
        try {
            Class.forName(driver); // Locate Driver
            // password = Details.getPassword(); // Assign password
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // reference Connection object
            // System.out.println("Connection successful!");
            }
        catch(ClassNotFoundException e) {
            System.out.println("Error:" + e.getMessage());
        } catch(SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    /** Returns the established connection.
     * @return Connection object. */
    public static Connection getConnection() {
        return connection;
    }

    /** Closes connnection. */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /** Initializes the prepared statement with a provided query string.
     * @param sqlStatement Provided query string.
     * @param conn The established database Connection object. */
    public static void makePreparedStatement(String sqlStatement, Connection conn) throws SQLException {
        if (conn != null)
            preparedStatement = conn.prepareStatement(sqlStatement);
        else
            System.out.println("Prepared Statement Creation Failed!");
    }

    /** Returns the prepared statement.
     * @return Prepared statement if not null, else returns null. */
    public static PreparedStatement getPreparedStatement() {
        if (preparedStatement != null)
            return preparedStatement;
        else System.out.println("Null reference to Prepared Statement");
        return null;
    }



}