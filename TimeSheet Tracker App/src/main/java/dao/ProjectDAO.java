// Package
package dao;

// IMPORTS
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// PROJECT IMPORTS
import displayCards.Projects.Project;
import models.User;

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
        String sql = "INSERT INTO projects (user_id, name, max_hours, start_date,end_date) VALUES (?, ?, ?, ?, ?)";
        
        // Use try-with-resources to ensure the PreparedStatement is closed automatically
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, project.userId);
            ps.setString(2, project.name);
            ps.setInt(3, project.maxHours);
            ps.setDate(4, java.sql.Date.valueOf(project.startDate));
            ps.setDate(5, java.sql.Date.valueOf(project.deadline));

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =====================================
    // READ
    // =====================================

    // Get all projects for a specific user
    public List<Project> getProjectsForUser(User user) {

        // List to hold the projects    
        List<Project> projects = new ArrayList<>();

        // SQL query to fetch projects based on user role
        String sql;

        // Check if the user is an admin or manager
        boolean isPrivileged = user.getRole().equalsIgnoreCase("admin") || user.getRole().equalsIgnoreCase("manager");

        if (isPrivileged) {
            sql = "SELECT project_id, user_id, name, hours_logged, max_hours, start_date, end_date FROM projects";
        } else {
            sql = "SELECT project_id, user_id, name, hours_logged, max_hours, start_date, end_date FROM projects WHERE user_id = ?";
        }

        // Prepare the statement and set parameters
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            
            
            if (!isPrivileged) {
                ps.setInt(1, user.getUserId());
            }

            // Execute the query and process the results
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Project project = new Project (
                        rs.getInt("project_id"),
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getInt("max_hours"),
                        rs.getInt("hours_logged"),
                        rs.getString("start_date"),
                        rs.getString("end_date")
                    );
                    // Add the project to the list
                    projects.add(project);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }

        // Return the list of projects
        return projects;
    }

    public List<Project.TimeEntry> getTimeEntriesForProject() {

        

    }
  
}
