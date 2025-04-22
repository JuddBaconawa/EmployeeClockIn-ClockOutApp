/********************* Packages **********************/


/****************** imports **************************/
// Toolkit for GUI
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

// LoginForm
public class LoginForm extends JFrame {
	
	// Global variables are declared that they could be used
	
	// Required to be declared and made global for credential checks
	JTextField usernameInput;
	JPasswordField userPasswordInput;

	public void initialize() {

		//create the outer main panel as a background
		JPanel outerMainPanel = new JPanel(new BorderLayout());
		outerMainPanel.setBackground(new Color(220, 220, 220));

		//create the main panel to hold the left and right panels
		JPanel innerMainPanel = new JPanel(new GridLayout(1, 2));

		/*********************** Left Panel ************************/
		JPanel leftPanel = new JPanel(new BorderLayout());
		JLabel leftTitle = new JLabel("Welcome Back!", JLabel.CENTER);
		
		/******************** Form Panel Left **********************/
		leftPanel.setBackground(new Color(245, 245, 240));
		leftPanel.add(leftTitle, BorderLayout.CENTER);
		
		//future image
		//image label
		//leftPanel.add(imageLabel, BorderLayout.CENTER);




		/*********************** Right Form panel ********************/
		/**************** Labels and Inputs panel *****************/
		JPanel rightPanel = new JPanel();

		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(25, 50, 75, 25);

		usernameInput = new JTextField();
		usernameInput.setBounds(50, 50, 75, 25);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(25, 90, 75, 25);

		userPasswordInput = new JPasswordField();
		userPasswordInput.setBounds(75, 90, 75, 25);

		/*********************** Form panel RIGHT ********************/
		rightPanel.add(usernameLabel);
		rightPanel.add(usernameInput);
		rightPanel.add(passwordLabel);
		rightPanel.add(userPasswordInput);

		


		/*********************** Buttons panel ********************/
		JButton loginButton = new JButton("Log In");


		JButton windowExit = new JButton("Exit");



		/*********************** Button-Form panel ********************/
		rightPanel.add(loginButton);
		rightPanel.add(windowExit);

		/*********************** Inner Main panel ********************/
		innerMainPanel.add(leftPanel);
		innerMainPanel.add(rightPanel);
		

		/*********************** Outer Main panel ********************/
		outerMainPanel.add(innerMainPanel, BorderLayout.CENTER);
		// setSize(600, 400);

		/*********************** Frame Settings ********************/
		setTitle("Login to Clock In");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(800, 500);
		setLocationRelativeTo(null);
		add(outerMainPanel);
		setVisible(true);

		

	}

}
