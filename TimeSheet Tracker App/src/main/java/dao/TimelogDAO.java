package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

import models.TimelogEntry;

public class TimelogDAO {

    private Connection connection;

    public TimelogDAO(Connection connection) {
        this.connection = connection;
    }

    public List<TimelogEntry> getAllLogs(int userId) {
        List<TimelogEntry> logs = new ArrayList<>();

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

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, userId);
        
            try (ResultSet rs = stmt.executeQuery()) {

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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return logs;
    }

    // createTimeEntry method to insert a new time entry into the databse
    public void createTimeEntry(int userId, int projectId, Timestamp clockIn, Timestamp clockOut) throws SQLException {
        
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
            ps.setInt(5, 0);
            ps.setDate(6, new java.sql.Date(clockIn.getTime()));

            // Execute the update to insert the new time entry
            ps.executeUpdate();

        }

    }

}
