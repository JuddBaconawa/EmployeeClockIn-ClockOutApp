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
  
}
