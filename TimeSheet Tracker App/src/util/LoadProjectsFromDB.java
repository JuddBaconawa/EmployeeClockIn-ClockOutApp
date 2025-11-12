package util;

import displayCards.Projects;
import displayCards.Projects.Project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoadProjectsFromDB {

  private Connection conn;

  public LoadProjectsFromDB(Connection conn) {
      this.conn = conn;
  }

  public List<Project> loadProjects() {
      List<Project> projectList = new ArrayList<>();

  try{
      String sql = "SELECT name, hours_logged, max_hours FROM projects";
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();


      while (rs.next()) {
          projectList.add(new Project(
              rs.getString("name"),
              rs.getInt("hours_logged"),
              rs.getInt("max_hours"),
              new ArrayList<>()
          ));
      }
          rs.close();
          stmt.close();
      } catch (SQLException e) {
          e.printStackTrace();
      }

      return projectList;
      
  }

}
