//packages

//imports
import java.util.ArrayList;
import java.util.Arrays;

//UserService class
public class UserService {

	//ArrayList for accounts to be populated in
	static ArrayList <Account> accounts = new ArrayList<>();
	
	//used to add the users credentials for logging in
	public static void initializeUsers() {
		accounts.add(new Account("admin", "password01", 000001));
		accounts.add(new Account("user1", "password1", 000002));
		accounts.add(new Account("dulce", "aswang", 0000003));
	}

	//login method to check for correct credentials
	public static boolean login(String usernameInput, char[] passwordInput, ArrayList<Account> account) {
		for (Account accounts : account) {

			if (accounts.getUsername().equals(usernameInput) && Arrays.equals(accounts.getPassword(), passwordInput)) {
				//confirmed credentials
				return true;
			}
		}
		//when credentials is not good
		return false;
	}

	// public static ArrayList<Account> getAccounts() {
	// 	return UserServices.accounts;
	// }

}
