// Packages
package dao;

// SQL imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

// Util imports
import java.util.ArrayList;
import java.util.List;

// model Imports
import models.TimelogEntry;

// TimelogDAO constructor and methods for database logging and retrieval
public class TimelogDAO {

    // Database connection
    private Connection connection;

    //  Constructor to initialize the TimelogDAO with a database connection
    public TimelogDAO(Connection connection) {
        this.connection = connection;
    }

    // Method to retrieve all time logs for a specific user from the database
    public List<TimelogEntry> getAllLogs(int userId) {
        List<TimelogEntry> logs = new ArrayList<>();

        // SQL query to select time log entries for the specified user, including project name and total hours worked
        String sql = """
            SELECT p.name as project_name,
            t.clock_in,
            t.clock_out,
            ROUND((TIMESTAMPDIFF(MINUTE, t.clock_in, t.clock_out) - t.break_minutes) / 60.0, 2) AS total_hours,
            t.work_date
            FROM timesheets t
            JOIN projects p on t.project_id = p.project_id
            WHERE t.user_id = ?
            ORDER BY t.work_date DESC, t.clock_in DESC
        """;

        // Use try-with-resources to ensure the PreparedStatement and ResultSet are closed automatically
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            //  Set the userId parameter in the SQL query
            stmt.setInt(1, userId);
        
            // Try statement to execute the query and retrieve the results
            try (ResultSet rs = stmt.executeQuery()) {

            // Iterate through the ResultSet and create TimelogEntry objects for each row, adding them to the logs list
            while (rs.next()) {
                logs.add(new TimelogEntry(
                        rs.getString("project_name"),
                        rs.getTimestamp("clock_in"),
                        rs.getTimestamp("clock_out"),
                        rs.getDouble("total_hours"),
                        rs.getDate("work_date")
                ));
            }

        }

        // catch statement
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // show logs
        return logs;
    }

    // Calculate the user's average worked hours for each day they worked
    public double getAverageWorkedHoursPerDay(int userId) {

        // sql query to calculate the average worked hours per day for a specific user
        String sql = """
            SELECT COALESCE(AVG(daily_worked_hours), 0) AS average_worked_hours
            FROM (
                SELECT work_date,
                    SUM(
                        (TIMESTAMPDIFF(MINUTE, clock_in, clock_out) - break_minutes)
                        / 60.0
                    ) AS daily_worked_hours
                FROM timesheets
                WHERE user_id = ? AND clock_out IS NOT NULL
                GROUP BY work_date
            ) AS daily_totals
        """;

        // try statement to execute the query and retrieve the average worked hours
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);

            // Use try-with-resources to ensure the ResultSet is closed automatically
            try (ResultSet rs = ps.executeQuery()) {

                // If there are results, return the average worked hours; otherwise, return 0
                if (rs.next()) {

                    //  Return the average worked hours from the ResultSet
                    return rs.getDouble("average_worked_hours");
                }
            }
        
        // catch statement to handle any SQL exceptions that may occur during the execution of the query
        } catch (SQLException e) {
            e.printStackTrace();    // Print the stack trace for debugging purposes
        }

        // Return zero if there are no completed sessions or a database error occurs
        return 0;
    }
    
    // Calculate the user's average break hours for each day they worked
    public double getAverageBreakHoursPerDay(int userId) {

        // SQL query statement to calculate the average break hours per day
        String sql = """
            SELECT COALESCE(AVG(daily_break_hours), 0) AS average_break_hours
            FROM (
                SELECT work_date,
                    SUM(break_minutes) / 60.0 AS daily_break_hours
                FROM timesheets
                WHERE user_id = ? AND clock_out IS NOT NULL
                GROUP BY work_date
            ) AS daily_totals
        """;

        // try statement that creates a PS statement and excute the query
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            // Set the userId parameter in the SQL query
            ps.setInt(1, userId);

            // try statement to execute the query and retrieve the results
            try (ResultSet rs = ps.executeQuery()) {

                // If there are results, return the average break hours; otherwise, return 0
                if (rs.next()) {

                    // Return the average break hours from the ResultSet
                    return rs.getDouble("average_break_hours");
                }
            }

        // catch statement to handle any SQL exceptions that may occur during the execution of the query
        } catch (SQLException e) {

            // Print the stack trace for debugging purposes
            e.printStackTrace();
        }

        // Return zero if there are no completed sessions or a database error occurs
        return 0;
    }

    // createTimeEntry method to insert a new time entry into the databse
    public void createTimeEntry (int userId,
                                int projectId, 
                                Timestamp clockIn, 
                                Timestamp clockOut, 
                                int breakMinutes
    ) throws SQLException {
        
        // SQL query to insert a new time entry into the timesheets table
        String sql = """
                INSERT INTO timesheets (user_id, project_id, clock_in, clock_out, break_minutes, work_date)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        // Use try-with-resources to ensure the PreparedStatement is closed automatically
        try(PreparedStatement ps = connection.prepareStatement(sql)) {

            // Set the parameters for the PreparedStatement
            ps.setInt(1, userId);
            ps.setInt(2, projectId);
            ps.setTimestamp(3, clockIn);
            ps.setTimestamp(4, clockOut);
            ps.setInt(5, breakMinutes);
            ps.setDate(6, new java.sql.Date(clockIn.getTime()));

            // Execute the update to insert the new time entry
            ps.executeUpdate();

        }

    }

}
