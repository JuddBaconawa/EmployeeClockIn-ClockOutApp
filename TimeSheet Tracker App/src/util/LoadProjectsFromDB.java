package util;

public class LoadProjectsFromDB {

  List<Project> projectList = new ArrayList<>();
  try{
      String sql = "SELECT name, hours_logged, max_hours FROM projects";
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();
      
  }
  
}
