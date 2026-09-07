// Package
package dao;

// IMPORTS

// SQL IMPORTS
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// UTIL IMPORTS
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

            // Execute the update to insert the new project into the database
            ps.executeUpdate();

        // Handles any sql Exception    
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

                    // Load time entries for the project
                    project.timeEntries = getTimeEntriesForProject(project.projectId);

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

    // =====================================
    // READ TIME ENTRIES
    // =====================================
    public List<Project.TimeEntry> getTimeEntriesForProject(int projectId) {

        // List to hold the time entries
        List<Project.TimeEntry> entries = new ArrayList<>();

        // SQL query to fetch time entries
        String sql = """
                SELECT work_date,
                    ROUND(
                        (TIMESTAMPDIFF(MINUTE, clock_in, clock_out) - break_minutes)
                        / 60.0, 2
                    ) AS hours
                FROM timesheets
                WHERE project_id = ?
                ORDER BY work_date DESC, clock_in DESC
        """;
     
        // Prepare the statement and set parameters
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            // Set the project ID parameter
            ps.setInt(1, projectId);

            // Execute the query and process the results
            try (ResultSet rs = ps.executeQuery()) {

                // Loop through the result set and create TimeEntry objects
                while (rs.next()) {
                    Project.TimeEntry entry = new Project.TimeEntry(
                        rs.getString("work_date"),
                        rs.getDouble("hours")
                    );

                    // Add the time entry to the list
                    entries.add(entry);
                }
            }
        
        } catch (SQLException e) {
        // Handle exception
        e.printStackTrace();
        }

    // return the list of time entries
    return entries;
  
    }
}
