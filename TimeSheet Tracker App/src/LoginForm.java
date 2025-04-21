/********************* Packages **********************/


/****************** imports **************************/
// Toolkit for GUI
import java.awt.GridLayout;
import java.awt.BorderLayout;

// Events

// Database

// Swing objects
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

// LoginForm
public class LoginForm extends JFrame {
	
	// Global variables are declared that they could be used
	
	// Required to be declared and made global for credential checks
	JTextField usernameInput;
	JPasswordField userPasswordInput;

	public void initialize() {

		//create the main panel to hold the left and right panels
		JPanel innerMainPanel = new JPanel(new GridLayout(1, 2));

		/*********************** Left Panel ************************/
		JPanel leftPanel = new JPanel(new BorderLayout());
		JLabel lefttitle = new JLabel("Welcome Back!", JLabel.CENTER);


		/*********************** Right Form panel ********************/
		/**************** Labels and Inputs panel *****************/

		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds();

		usernameInput = new JTextField();

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds();

		userPasswordInput = new JPasswordField();
		userPasswordInput.setBounds();

		/*********************** Form panel ********************/
		JPanel formPanel = new JPanel();
		formPanel.add(usernameLabel);
		formPanel.add(usernameInput);
		formPanel.add(passwordLabel);
		formPanel.add(userPasswordInput);


		/*********************** Buttons panel ********************/


		/*********************** Button-Form panel ********************/


		/*********************** Frame Settings ********************/
		setTitle("Login");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setSize(400, 300);
		add(formPanel);

		

	}


}
