package core;
//packages

//imports
import javax.swing.SwingUtilities;
import models.User;
import auth.LoginForm;


public class App {

	// public static void main(String[] args) {
	// 	// Run the UI on the Event Dispatch thread for smooth performance
	// 	SwingUtilities.invokeLater(() -> {

	// 		// Create a saple user Instance to test
	// 		User user = new User();

	// 		// Initialize the TimeSheet UI
	// 		TimeSheet timesheet = new TimeSheet();
	// 		timesheet.initialize(user);
				


	// 	});

	// }

	public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {

        java.sql.Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = java.sql.DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/timesheetappdatabase",
                "root",
                "DB_password1301"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        new LoginForm(conn); // ✅ only entry point
    });
}

}