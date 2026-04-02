// PACKAGE - Folder name
package util;

// IMPORTS
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

  // Database credentions for log in
  private static final String URL = "jdbc:mysql://localhost:3306/timesheetappdatabase";
  private static final String USER = "root";
  private static final String PASSWORD = "";

  // Set private to prevent instantiation
  private DBConnection() {}

  // Returns a new Connection each time
  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }
  
}
