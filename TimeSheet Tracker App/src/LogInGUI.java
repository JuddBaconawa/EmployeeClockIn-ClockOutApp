//packages


//imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//Main class
public class LogInGUI extends BaseGUI implements ActionListener {

	private JTextField usernameField;
	private JPasswordField passwordField;

	public LogInGUI() {
		//inhereted declarations
		super("Clock In/Out App: Login/Create Account", 300, 275);


		//username label
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(20, 20, 80, 25); 
		panel.add(usernameLabel);

		//username input
		JTextField usernameInput = new JTextField();
		usernameInput.setBounds(90, 20, 125, 25);
		panel.add(usernameInput);

		//password label
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(20, 50, 80, 25);
		panel.add(passwordLabel);

		//password input
		JPasswordField passwordInput = new JPasswordField();
		passwordInput.setBounds(90, 50, 125, 25);
		panel.add(passwordInput);



		// JLabel userLabel = new JLabel("Username");
		// userLabel.setBounds(10, 20, 80, 25);
		// panel.add(userLabel);

		// usernameField = new JTextField();
		// usernameField.setBounds(100, 20, 165, 25);
		// panel.add(usernameField);

		// JLabel passwordLabel = new JLabel("Password");
		// passwordLabel.setBounds(10, 50, 80, 25);
		// panel.add(passwordLabel);

		// passwordField = new JPasswordField();
		// passwordField.setBounds(100, 50, 165, 25);
		// panel.add(passwordField);


	}


	@Override
	public void actionPerformed(ActionEvent e) {
		//throm message under
		throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
	}



	
}
