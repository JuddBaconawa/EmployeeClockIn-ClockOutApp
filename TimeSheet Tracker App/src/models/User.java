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
			// this. uses the current
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

		// ********************** setters ************************
		public void setUserId(int userId) {this.userId = userId;}
		public void setUsername(String username) {this.username = username;}
		public void setPassword(String password) {this.password = password;}
		public void setFirstName(String firstName) {this.firstName = firstName;}
		public void setLastName(String lastName) {this.lastName = lastName;}
		public void setEmail(String email) {this.email = email;}
		public void setRole(String role) {this.role = role;}
		public void setDepartment(String department) {this.department = department;}
		public void setLocation(String location) {this.location = location;}
		public void setPhone(String phone) {this.phone = phone;}
		public void setAddress(String address) {this.address = address;}
		public void setProfilePicture(Image profilePicture) {this.profilePicture = profilePicture;}
		public void setRemote(boolean isRemote) {this.isRemote = isRemote;}
		public String getFullName() {return lastName + ", " + firstName;}
		
}
