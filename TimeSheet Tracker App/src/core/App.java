package core;
//packages

//imports
import models.User;
import auth.LoginForm;

public class App {

	public static void main(String[] args) {
			
		// LoginForm loginForm = new LoginForm();		
		// loginForm.initialize();

		TimeSheet timeSheet = new TimeSheet(new User());
		timeSheet.initialize(new User());


		// Create a User object to pass to initialize
		// User user = new User(); // You may need to set properties or use a constructor
		// TimeSheet timeSheet = new TimeSheet(user);
		// timeSheet.initialize(user);

	}

}