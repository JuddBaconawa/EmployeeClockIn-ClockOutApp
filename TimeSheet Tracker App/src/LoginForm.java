/********************* Packages **********************/


/****************** imports **************************/
// Toolkit for GUI
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
		innerMainPanel.setPreferredSize(new Dimension(300, 150));
		innerMainPanel.setBackground(Color.CYAN);

		/*********************** Left Panel ************************/
		JPanel leftPanel = new JPanel(new BorderLayout());
		JLabel leftTitle = new JLabel("Welcome Back!", JLabel.CENTER);
		
		/******************** Form Panel Left **********************/
		leftPanel.setBackground(new Color(245, 245, 240));
		leftPanel.add(leftTitle, BorderLayout.CENTER);

		leftPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		//future image
		//image label
		//leftPanel.add(imageLabel, BorderLayout.CENTER);




		/*********************** Right Form panel ********************/
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(new Color(250, 250, 250)); // Slightly lighter
		rightPanel.setLayout(new GridLayout(5, 1, 10, 10)); // 5 rows, 1 column, spacing
		rightPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 40, 20, 40));		


		/**************** Labels and Inputs panel *****************/


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
