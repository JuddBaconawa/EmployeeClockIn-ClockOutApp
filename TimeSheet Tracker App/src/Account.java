//packages

//imports
public class Account {

	private String usernameInput;
	private char[] passwordInput;
	private int userId;

	public Account(String usernameInput, String passwordInput, int userId) {
		this.usernameInput = usernameInput;
		this.passwordInput = passwordInput.toCharArray();
		this.userId = userId;
	}

	//username getter
	public String getUsername() {
		return usernameInput;
	}

	//password getter
	public char[] getPassword() {
		return passwordInput;
	}

	//userId getter
	public int getUserId() {
		return userId;
	}
	
	
}
