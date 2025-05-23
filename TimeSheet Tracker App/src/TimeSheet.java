// Packages

// IMPORTS
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.geom.RoundRectangle2D;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;





// TimeSheet class
public class TimeSheet extends JFrame{

	public void initialize(User user) {
		
		/*********************** Time Sheet UI ***********************/
		/*********************** Frame Panel *********************************/

		setTitle("Time Sheet Home");
		setUndecorated(true);						// Takes out the TimeSheet title bar
		setSize(1200, 801);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setLayout(null);

		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 40, 40));	// Rounded corners

		/*********************** (Side) Menu Bar/Panel ************************************/
		// Create a menu bar
		JPanel menuBar = new JPanel();
		menuBar.setLayout(null);
		menuBar.setBounds(0, 0, 200, 803);									// Menu bar size   763 with a titlebar
		// menuBar.setBorder(javax.swing.BorderFactory.createCompoundBorder(		// Menu bar border
		// 	BorderFactory.createLineBorder(Color.GRAY, 5, true), 
		// 	BorderFactory.createEmptyBorder(20, 10, 20, 10)
		// ));
		menuBar.setBackground(new Color(29, 45, 68));
		menuBar.setVisible(true);	

		// Add a logo to the top of the menu bar  NORTH position
		JPanel logoPanel = new JPanel();
		logoPanel.setBackground(new Color(29, 45, 68));
		logoPanel.setLayout(new java.awt.GridBagLayout());
		logoPanel.setPreferredSize(new java.awt.Dimension(200, 100));
		JLabel logoLabel = new JLabel("\"Brand/Logo\"");
		ImageIcon logoIcon = new ImageIcon ("resources/logo.png");
		logoLabel.setIcon(logoIcon);
		// logoLabel.setBounds(20, 30, 160, 60);
		logoLabel.setBackground(new Color(255, 255, 255));
		// logoLabel.setOpaque(true);
		logoLabel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(Color.GRAY, 7/* , true*/), 
			BorderFactory.createEmptyBorder(5, 5, 5, 5)
		));

		logoLabel.setVisible(true);

		menuBar.add(logoPanel);



		// Create menu items
		JButton searchButton = new JButton("Search");
		JButton dashboardButton = new JButton("Dashboard");
		JButton timesheetButton = new JButton("Time Sheet");
		JButton homeButton = new JButton("Home");
		JButton profileButton = new JButton("Profile");
		JButton settingsButton = new JButton("Settings");
		JButton logoutButton = new JButton("Logout");
		  

		/*********************** (Side) Menu Bar/Panel Settings ***************************/
		// Set the bounds for each menu item
		searchButton.setBounds(20, 130, 160, 40);
		dashboardButton.setBounds(20, 180, 160, 40);
		timesheetButton.setBounds(20, 230, 160, 40);
		homeButton.setBounds(20, 280, 160, 40);
		// profileButton.setBounds(20, 340, 160, 40);
		settingsButton.setBounds(20, 630, 160, 40);
		logoutButton.setBounds(20, 680, 160, 40);

		// Logo label
		menuBar.add(logoLabel);

		// Add menu items to the menu bar
		menuBar.add(searchButton);
		menuBar.add(dashboardButton);
		menuBar.add(timesheetButton);
		menuBar.add(homeButton);
		menuBar.add(profileButton);
		menuBar.add(settingsButton);
		menuBar.add(logoutButton);
		// Set the background color for each menu item
		// First button theme check ============================
		searchButton.setBackground(new Color(250, 247, 240));
		searchButton.setForeground(new Color(251, 160, 157)); // Set text color to black

		// second button theme check =====================================
		dashboardButton.setBackground(new Color(247, 204, 173));
		dashboardButton.setForeground(new Color(251, 160, 157)); // Set text color to black
		// third button theme check	 =====================================
		timesheetButton.setBackground(new Color(247, 204, 173));
		timesheetButton.setForeground(new Color(251, 160, 157)); // Set text color to black
		// fourth button theme check	=====================================
		homeButton.setBackground(new Color(255, 255, 255));
		homeButton.setForeground(new Color(251, 160, 157)); 
		// fifth button theme check	========================================
		profileButton.setBackground(new Color(116, 65, 62));
		profileButton.setForeground(new Color(251, 160, 157));
		// sixth button theme check	=========================================
		settingsButton.setBackground(new Color(0, 128, 128));
		settingsButton.setForeground(new Color(251, 160, 157)); // Set text color to black
		// seventh button theme check	==========================================
		logoutButton.setBackground(new Color(0, 102, 102));
		logoutButton.setForeground(new Color(251, 160, 157)); // Set text color to black

		/*********************************** (User) Top Info Panel *********************************/
		// Create a top info panel
		JPanel topInfoPanel = new JPanel();
		topInfoPanel.setLayout(new GridLayout(1, 6, 15, 50));
		topInfoPanel.setBorder(BorderFactory.createEmptyBorder(40, 10, 20, 10));
		// topInfoPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
		// 	BorderFactory.createLineBorder(Color.GRAY, 5 /*, true*/), 
		// 	BorderFactory.createEmptyBorder(5, 5, 5, 5)
		// ));

		// 1. User + Role Panel
		JPanel userPanel = new JPanel(null);
		userPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
			BorderFactory.createBevelBorder(1, Color.BLACK, Color.GRAY), 
			BorderFactory.createEmptyBorder(10, 5, 10, 5)
		));
		userPanel.setBounds(15, 10, 250, 55);
		userPanel.setBackground(new Color(0, 0, 255));
		userPanel.setForeground(new Color(255, 255, 255));
		JLabel usernameLabel = new JLabel("User: " + user.username);
		usernameLabel.setForeground(new Color(0, 0, 0));
		usernameLabel.setBounds(17, 10, 200, 20);
		JLabel userRoleLabel = new JLabel("Role: " + user);
		userRoleLabel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
			BorderFactory.createBevelBorder(1), 
			BorderFactory.createEmptyBorder(10, 5, 10, 5)
		));
		userRoleLabel.setForeground(new Color(0, 0, 0));
		userRoleLabel.setBounds(10, 30, 200, 20);

		userPanel.add(usernameLabel);
		userPanel.add(userRoleLabel);

		// Top info Panel contents
		// 2. Hours total Panel with period selection (drop down window or cycle through button)
		JPanel hoursPanel = new JPanel(null);
		hoursPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
			BorderFactory.createBevelBorder(1, Color.BLACK, Color.GRAY), 
			BorderFactory.createEmptyBorder(10, 5, 10, 5)
		));
		hoursPanel.setBounds(270, 10, 200, 55);
		hoursPanel.setBackground(new Color(240, 235, 216));
		JLabel hoursLabel = new JLabel("Hours: 0");
		hoursLabel.setBounds(10, 15, 200, 20);
		String[] periods = {"Day", "Week", "Month"};
		//JComboBox<String> periodCombo = new JComboBox<>(periods);				//this line is throwing error
		//periodCombo.setBounds(10, 30, 100, 20);
		
		hoursPanel.add(hoursLabel);

		//hoursPanel.add(periodCombo);
		//hoursPanel.add(periodCombo);

		// Example: update hoursLabel when period changes(replace with actual logic)
		


		// 3. current time and (datae panel)
		JPanel currentTimePanel = new JPanel();
		currentTimePanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
			BorderFactory.createBevelBorder(1, Color.BLACK, Color.GRAY),
			BorderFactory.createEmptyBorder(10, 5, 10, 5)
		));
		currentTimePanel.setBounds(480, 10, 200, 55);
		currentTimePanel.setBackground(new Color(240, 235, 216));
		JLabel currentTimeLabel = new JLabel("Time: --:--:--");
		currentTimeLabel.setBounds(10, 5, 200, 20);
		currentTimePanel.add(currentTimeLabel);
		


		// 4. current date panel
		JPanel currentDatePanel = new JPanel();
		currentDatePanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
			BorderFactory.createBevelBorder(1, Color.BLACK, Color.GRAY),				// type 1 bevel
			BorderFactory.createEmptyBorder(10, 5, 10, 5)
		));
		currentDatePanel.setBounds(690, 10, 280, 55);
		currentDatePanel.setBackground(new Color(240, 235, 216));
		JLabel currentDateLabel = new JLabel("Date: --/--/----");
		currentDateLabel.setBounds(10, 5, 200, 20);
		currentDatePanel.add(currentDateLabel);

		// Add all panel to Top Info Panel
		topInfoPanel.add(userPanel);
		topInfoPanel.add(hoursPanel);
		topInfoPanel.add(currentTimePanel);
		topInfoPanel.add(currentDatePanel);

		// Set the background color for each menu item
		userPanel.setBackground(new Color(240, 235, 216));



		/*********************************** Top Info Panel Settings *******************************/
		topInfoPanel.setBounds(200, 0, 1000, 120);
		topInfoPanel.setBackground(new Color(62, 92, 118)); 

		/*********************** Main Panel Settings *************************/
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 1200, 800);
		mainPanel.setBackground(new Color(255, 255, 255));

		// Sample label test to verify its working and placements
		JLabel welcomeLabel = new JLabel("Welcome, " + user.username);
		welcomeLabel.setBounds(205, 150, 200, 30);



		/*********************** Main Panel Settings *************************/
		// Used to add sections into the main panel
		mainPanel.add(welcomeLabel);
		mainPanel.add(topInfoPanel);
		



		/*********************** Action Button Panel ********************************/
		JButton clockInButton = new JButton("Clock In");

		JButton clockOutButton = new JButton("Clock Out");

		JButton breakButton = new JButton("Break/Lunch");

		/*********************** Button Panel Settings ***********************/
		// Have to add to the Main info panel thats yet to be created
		JPanel actionButtonPanel = new JPanel();


		/*********************** Add Panel to Frame **************************/
		// Add menu bar to the frame
		add(menuBar);

		add(topInfoPanel);

		add(mainPanel);
		

		setVisible(true);
		

	}

	public static void main(String[] args) {
		// Create a saple user Instance to test
			User user = new User();

			// Initialize the TimeSheet UI
			TimeSheet timesheet = new TimeSheet();
			timesheet.initialize(user);
	}

}
