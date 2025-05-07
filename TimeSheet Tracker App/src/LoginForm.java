/********************* Packages **********************/


/****************** imports **************************/
// Toolkit for GUI
import java.awt.BorderLayout;						//Border layout
import java.awt.Color;									//used to change color - using RGB
import java.awt.Dimension;							//setting up the size of an object within the window
import java.awt.GridLayout;							//layout of the form in a grid manner

import java.awt.event.ActionListener;		// Listens for the action - button clicked
import java.awt.event.ActionEvent;			// Effect or function of when clicked

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;							//for buttons within the forms
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;			//password input
import javax.swing.JTextField;					//username input
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;			//constants

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
		innerMainPanel.setPreferredSize(new Dimension(600, 400));
		innerMainPanel.setBackground(Color.CYAN);

		/*********************** Left Panel ************************/
		JPanel leftPanel = new JPanel(new BorderLayout());
		JLabel leftTitle = new JLabel("Welcome Back!", JLabel.CENTER);
		
		/******************** Form Panel Left **********************/
		leftPanel.setBackground(new Color(230, 235, 230));
		leftPanel.add(leftTitle, BorderLayout.NORTH);

		leftPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(40, 20, 20, 20));
		
		//future image
		//image label
		//leftPanel.add(imageLabel, BorderLayout.CENTER);




		/*********************** Right Form panel ********************/
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(new Color(250, 250, 250)); // Slightly lighter
		rightPanel.setLayout(new GridLayout(0, 1, 10, 10)); 
		rightPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 50, 30, 50));		


		/**************** Labels and Inputs panel *****************/
		JLabel loginLabel = new JLabel("Login to Clock In", SwingConstants.CENTER);

		JLabel usernameLabel = new JLabel("Username");
		// usernameLabel.setBounds(25, 50, 75, 25);

		usernameInput = new JTextField();
		// usernameInput.setBounds(50, 50, 75, 25);

		JLabel passwordLabel = new JLabel("Password");
		// passwordLabel.setBounds(25, 90, 25, 25);

		userPasswordInput = new JPasswordField();
		// userPasswordInput.setBounds(75, 90, 25, 25);




		


		/*********************** Buttons panel ********************/
		// Login Button created
		JButton loginButton = new JButton("Log In");

		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameInput.getText();
				String password = String.valueOf(userPasswordInput.getPassword());

				User user = getAuthenticated(username, password);

				if (user != null) {
					MainFrame mainFrame = new MainFrame();
					mainFrame.initialize(user);
					dispose();
				} else {
					JOptionPane.showMessageDialog(LoginForm.this,
											"Email or password Invalid",
											"Try Againt",
											JOptionPane.ERROR_MESSAGE);
				}
			}

		});


		JButton exitButton = new JButton("Exit");

		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});



		/*********************** Button-Form panel ********************/
		JPanel buttonPanel = new JPanel(new GridLayout());
		buttonPanel.setLayout(new GridLayout(1, 2, 10, 0));
		// buttonPanel.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));


		buttonPanel.add(loginButton);
		buttonPanel.add(exitButton);

		/*********************** Form panel RIGHT ********************/
		rightPanel.add(loginLabel);
		rightPanel.add(usernameLabel);
		rightPanel.add(usernameInput);
		rightPanel.add(passwordLabel);
		rightPanel.add(userPasswordInput);
		rightPanel.add(buttonPanel);



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

	// Authenticator for log in
	private User getAuthenticatedUser (String usernameInput, String passwordInput) {

		//set Empty for the program to check for credential
		User user = null;

		// Database url, username, and password for MySQL access
		final String DB_URL = "jdbc:mysql://localhost:3306/timesheetapp";
		final String USERNAME = "root";
		final String PASSWORD = "#MCMXCii1301!";

		try {
				
			// Load MySQl JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Connect to the database
			Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);


		} catch (Exception e) {
			System.err.println("Database connection failed" + e.getLocalizedMessage());
		}

		return user;

	}

}
