/********************* Packages **********************/


/****************** imports **************************/
// Toolkit for GUI
import java.awt.BorderLayout;						//Border layout
import java.awt.Color;									//used to change color - using RGB
import java.awt.Dimension;							//setting up the size of an object within the window
import java.awt.GridLayout;							//layout of the form in a grid manner
import java.awt.Font;									//font for the text
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;		// Listens for the action - button clicked
import java.awt.geom.RoundRectangle2D;
import java.awt.event.ActionEvent;			// Effect or function of when clicked

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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

	public LoginForm() {
        initialize();
    }

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
		leftTitle.setFont(new Font("null", Font.BOLD, 30));
		
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
		loginLabel.setFont(new Font("null", Font.BOLD, 30));

		JLabel usernameLabel = new JLabel("Username");
		// usernameLabel.setBounds(25, 50, 75, 25);
		usernameLabel.setFont(new Font("null", Font.BOLD, 20));

		usernameInput = new JTextField();
		// usernameInput.setBounds(50, 50, 75, 25);

		JLabel passwordLabel = new JLabel("Password");
		// passwordLabel.setBounds(25, 90, 25, 25);
		passwordLabel.setFont(new Font("null", Font.BOLD, 20));

		userPasswordInput = new JPasswordField();
		// userPasswordInput.setBounds(75, 90, 25, 25);



		/*********************** Buttons panel ********************/
		// Login Button created
		JButton loginButton = new JButton("Log In") {
		    @Override
		    protected void paintComponent(Graphics g) {
		        Graphics2D g2 = (Graphics2D) g.create();
		        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        g2.setColor(getBackground());
		        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20)); // 20 = corner radius
		        super.paintComponent(g);
		        g2.dispose();
		    }
		};
		loginButton.setContentAreaFilled(false);
		loginButton.setFocusPainted(false);
		loginButton.setBorderPainted(false);
		loginButton.setOpaque(false);
		loginButton.setFont(new Font("null", Font.BOLD, 20));

		// Set the default background color
		loginButton.setBackground(new Color(0, 204, 153));

		// Add mouse listener for highlight/click effect
		loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    Color original = loginButton.getBackground();
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent e) {
		        loginButton.setBackground(new Color(255, 200, 200)); // Highlight color
		    }
		    @Override
		    public void mouseExited(java.awt.event.MouseEvent e) {
		        loginButton.setBackground(original);
		    }
		    @Override
		    public void mousePressed(java.awt.event.MouseEvent e) {
		        loginButton.setBackground(new Color(200, 100, 100)); // Clicked color
		    }
		    @Override
		    public void mouseReleased(java.awt.event.MouseEvent e) {
		        loginButton.setBackground(original);
		    }
		});

		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameInput.getText();
				String password = String.valueOf(userPasswordInput.getPassword());

				User user = getAuthenticatedUser(username, password);

				if (user != null) {
					TimeSheet timesheet = new TimeSheet();
					TimeSheet timeSheet = new TimeSheet();
					timeSheet.initialize(user);
					dispose();
				} else {
					JOptionPane.showMessageDialog(LoginForm.this,
											"Email or password Invalid",
											"Try Againt",
											JOptionPane.ERROR_MESSAGE);
				}
			}

		});


		JButton exitButton = new JButton("Exit") {
		    @Override
		    protected void paintComponent(Graphics g) {
		        Graphics2D g2 = (Graphics2D) g.create();
		        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        g2.setColor(getBackground());
		        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20)); // 20 = corner radius
		        super.paintComponent(g);
		        g2.dispose();
		    }
		};
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.setBorderPainted(false);
		exitButton.setOpaque(false);
		exitButton.setFont(new Font("null", Font.BOLD, 20));

		// Set the default background color
		exitButton.setBackground(new Color(221, 17, 85));

		// Add mouse listener for highlight/click effect
		exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    Color original = exitButton.getBackground();
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent e) {
		        exitButton.setBackground(new Color(220, 220, 220)); // Highlight color
		    }
		    @Override
		    public void mouseExited(java.awt.event.MouseEvent e) {
		        exitButton.setBackground(original);
		    }
		    @Override
		    public void mousePressed(java.awt.event.MouseEvent e) {
		        exitButton.setBackground(new Color(150, 150, 150)); // Clicked color
		    }
		    @Override
		    public void mouseReleased(java.awt.event.MouseEvent e) {
		        exitButton.setBackground(original);
		    }
		});


		exitButton.addActionListener(e -> dispose());

		/*********************** Button-Form panel ********************/
		JPanel buttonPanel = new JPanel(new GridLayout());
		buttonPanel.setLayout(new GridLayout(1, 2, 10, 0));
		// buttonPanel.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));


		buttonPanel.add(loginButton);				// Login Button added to the panel
		buttonPanel.add(exitButton);				// Exit Button added to the panel

		/*********************** Form panel RIGHT ********************/
		rightPanel.add(loginLabel);								// Login label added to the  right panel
		rightPanel.add(usernameLabel);						// Username label added to the right panel
		rightPanel.add(usernameInput);						// Username input added to the right panel
		rightPanel.add(passwordLabel);						// Password label added to the right panel
		rightPanel.add(userPasswordInput);				// Password input added to the right panel
		rightPanel.add(buttonPanel);							// Button panel added to the right panel

		/*********************** Inner Main panel ********************/
		innerMainPanel.add(leftPanel);
		innerMainPanel.add(rightPanel);
		

		/*********************** Outer Main panel ********************/
		outerMainPanel.add(innerMainPanel, BorderLayout.CENTER);
		// setSize(600, 400);

		/*********************** Frame Settings ********************/
		setTitle("Login to Clock In");
		setUndecorated(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(800, 500);
		setLocationRelativeTo(null);
		add(outerMainPanel);
		setVisible(true);

		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 10, 10));	// Rounded corners 10 radius

	}

	// Authenticator for log in
	private User getAuthenticatedUser (String usernameInput, String passwordInput) {

		//set Empty for the program to check for credential
		User user = null;

		// Database url, username, and password for MySQL access
		final String DB_URL = "jdbc:mysql://localhost:3306/timesheetappdatabase";
		final String USERNAME = "root";
		final String PASSWORD = "DB_password";

		try {
				
			// Load MySQl JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Connect to the database
			Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);


			// SQL statement to pull the list of files from the user table for credential checks
			String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

			// Used preparedStatements to prevent SQL injection Attacks
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, usernameInput);
			preparedStatement.setString(2, passwordInput);


		} catch (Exception e) {
			System.err.println("Database connection failed" + e.getLocalizedMessage());
		}

		return user;

	}

}
