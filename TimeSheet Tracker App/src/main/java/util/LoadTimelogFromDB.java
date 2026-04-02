package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import models.TimelogEntry;


public class LoadTimelogFromDB {

    public static List<TimelogEntry> getTimelog(Connection conn, int userId) {
        List<TimelogEntry> entries = new ArrayList<>();

        String sql = """
            SELECT p.name AS project,
                   t.clock_in,
                   t.clock_out,
                   t.total_hours,
                   t.work_date
            FROM timesheets t
            JOIN projects p ON t.project_id = p.project_id
            WHERE t.user_id = ?
            ORDER BY t.work_date DESC
        """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                entries.add(new TimelogEntry(
                    rs.getString("project"),
                    rs.getTimestamp("clock_in"),
                    rs.getTimestamp("clock_out"),
                    rs.getDouble("total_hours"),
                    rs.getDate("work_date")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entries;
    }
}
