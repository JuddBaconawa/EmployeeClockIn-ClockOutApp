package util;

import displayCards.Projects;
import displayCards.Projects.Project;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoadProjectsFromDB {

        // Database connection
        private Connection conn;

        // Constructor
        public LoadProjectsFromDB(Connection conn) {
            this.conn = conn;
        }

            // Method to load projects from the database


            public List<Project> loadProjectsForUser(User user) {
                List<Project> projectList = new ArrayList<>();
                String sql;

                // Determine SQL based on user role
                if (user.getRole().equalsIgnoreCase("admin") || user.getRole().equalsIgnoreCase("manager")) {
                    sql = "SELECT name, hours_logged, max_hours FROM projects";
                } else {
                    sql = "SELECT name, hours_logged, max_hours FROM projects WHERE user_id = ?";
                }

                try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                    // If employee, set their user_id
                    if (!(user.getRole().equalsIgnoreCase("admin") || user.getRole().equalsIgnoreCase("manager"))) {
                        stmt.setInt(1, user.getUserId());
                    }

                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            projectList.add(new Project(
                                rs.getString("name"),
                                rs.getInt("hours_logged"),
                                rs.getInt("max_hours"),
                                new ArrayList<>()
                            ));
                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                return projectList;
            }


}
