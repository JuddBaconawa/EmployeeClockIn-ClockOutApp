package dao;

import models.TimelogEntry;

import java.sql.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
            t.total_hours,
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
}
