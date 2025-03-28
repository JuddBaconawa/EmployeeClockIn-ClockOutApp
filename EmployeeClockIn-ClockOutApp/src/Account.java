//packages



//imports



//Account class - will contain the users to test the login and its functions
public class Account {
	
	//Account's attributes set private for encapsulation
	private String username;
	private char[] password;
	private int userId;

	//account constructor - responsible for creating 
	public Account(String username, String password, int userId) {
		//Account's attributes
		this.username = username;
		this.password = password.toCharArray();
		this.userId = userId;
	}



	//=====================================Getters========================================================
	public String getUsername() {	//getters username
		return username;
	}

	public char[] getPassword() {	//getters password
		return password;
	}

	public int getUserId() {		//getters userId
		return userId;
	}

	
	
}
