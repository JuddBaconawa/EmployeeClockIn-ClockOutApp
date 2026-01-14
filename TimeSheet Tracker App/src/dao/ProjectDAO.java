package dao;

// IMPORTS
import models.User;
import displayCards.Projects.Project;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProjectDAO {

    // constructor for the connection
    private Connection conn;

    // initialize connection
    public ProjectDAO(Connection conn) {
        this.conn = conn;
    }

    //Create a new project in the database
    public void createProject(Project project) {
        // SQL Insert Statement
        String sql = "INSERT INTO projects (user_id, name, max_hours, deadline) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, project.userId);
            ps.setString(2, project.name);
            ps.setInt(3, project.maxHours);
            ps.setString(4, project.deadline);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =====================================
    // READ
    // =====================================

    public List<Project> getProjectsForUser(User user) {

        List<Project> projects = new ArrayList<>();

        String sql;

        boolean isPrivileged = user.getRole().equalsIgnoreCase("admin") || user.getRole().equalsIgnoreCase("manager");

        if (isPrivileged) {
            sql = "SELECT project_id, user_id,name, hours_logged, max_hours, start_date, end_date FROM projects";
        } else {
            sql = "SELECT project_id, user_id,name, hours_logged, max_hours, start_date, end_date FROM projects WHERE user_id = ?";
        }

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            
            if (!isPrivileged) {
                ps.setInt(1, user.getUserId());
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Project project = new Project (
                        rs.getInt("project_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getInt("user_id"),
                        rs.getInt("hours_logged"),
                        rs.getInt("max_hours"),
                        rs.getString("start_date"),
                        rs.getString("end_date")
                    );
                    projects.add(project);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }

        return projects;
    }
  
}
