package core;
//packages

//imports
import javax.swing.SwingUtilities;
import models.User;
import auth.LoginForm;


public class App {

	public static void main(String[] args) {
		// Run the UI on the Event Dispatch thread for smooth performance
		SwingUtilities.invokeLater(() -> {

			// Create a saple user Instance to test
			User user = new User();

			// Initialize the TimeSheet UI
			TimeSheet timesheet = new TimeSheet();
			timesheet.initialize(user);
				


		});

	}

}