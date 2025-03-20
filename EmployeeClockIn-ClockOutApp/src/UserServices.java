//packages


//imports
import java.util.ArrayList;
import java.util.Arrays;

//main class - for backend functions hidden to the user
public class UserServices {

	//ArrayList to store each account credentials
	static ArrayList <Account> accounts = new ArrayList<>();
	
	//used for credential login, this adds the accounts beforehand
	public static void initializeUser() {
		accounts.add(new Account("admin", "password", 000001));
		accounts.add(new Account("user01", "password01", 0000002));
	}

	public static boolean login(String username, char[] password, ArrayList<Account> account) {
		for (Account accounts : account) {

			if (accounts.getUsername().equals(username) && Arrays.equals(accounts.getPassword(), password)) {
				return true;
			}
		}
		return false;
	}



}
