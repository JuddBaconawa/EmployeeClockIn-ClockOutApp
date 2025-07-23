package models;
// PACKAGES


// IMPORTS
import java.awt.Image;

// USER - class
public class User {
	
		// User class' Attributes
		public java.awt.Image profilePicture;
		public String firstName;
		public String lastName;
		public int userId;
		public String username;
		public String location;
		public String department;
		public String role;
		public String email;
		public String phone;
		public String address;
		public String password;
    public boolean isRemote;

		private static User currentUser = new User();

		public User() {
			this.username = "SampleUser";
			this.role = "Employee";
			this.department = "General";
			
		}

		public static User getCurrentUser() {
			return currentUser;
		}

		// Optionally, add a setter if you want to change the current user:
		public static void setCurrentUser(User user) {
			currentUser = user;
		}

}
