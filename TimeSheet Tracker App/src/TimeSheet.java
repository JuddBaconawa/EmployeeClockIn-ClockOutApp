// Packages

// IMPORTS
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;




// TimeSheet class
public class TimeSheet extends JFrame{

	public void initialize(User user) {
		
		/*********************** Time Sheet UI ***********************/
		/*********************** Frame Panel *********************************/

		setTitle("Time Sheet Home");
		setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setLayout(null);

		/*********************** (Side) Menu Bar/Panel ************************************/
		// Create a menu bar
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(null);
		menuBar.setBounds(0, 0, 200, 761);									// Menu bar size
		menuBar.setBorder(javax.swing.BorderFactory.createCompoundBorder(		// Menu bar border
			BorderFactory.createLineBorder(Color.GRAY, 5), 
			BorderFactory.createEmptyBorder(5, 5, 5, 5)
		));
		menuBar.setBackground(new Color(116,65,62));
		menuBar.setVisible(true);	

		// Add a logo to the top of the menu bar
		JLabel logoLabel = new JLabel("Logo");
		ImageIcon logoIcon = new ImageIcon ("resources/logo.png");
		logoLabel.setIcon(logoIcon);
		logoLabel.setBounds(20, 10, 160, 60);
		logoLabel.setBackground(new Color(255, 255, 255));
		logoLabel.setOpaque(true);
		logoLabel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(Color.GRAY, 5), 
			BorderFactory.createEmptyBorder(5, 5, 5, 5)
		));

		logoLabel.setVisible(true);



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
		searchButton.setBounds(20, 80, 160, 40);
		dashboardButton.setBounds(20, 130, 160, 40);
		timesheetButton.setBounds(20, 180, 160, 40);
		homeButton.setBounds(20, 230, 160, 40);
		profileButton.setBounds(20, 280, 160, 40);
		settingsButton.setBounds(20, 650, 160, 40);
		logoutButton.setBounds(20, 700, 160, 40);

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
		topInfoPanel.setLayout(null);
		topInfoPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(Color.GRAY, 5), 
			BorderFactory.createEmptyBorder(5, 5, 5, 5)
		));

		// 1. User + Role Panel
		JPanel userPanel = new JPanel(null);
		userPanel.setBounds(10, 10, 250, 55);
		userPanel.setBackground(new Color(0, 0, 255));
		JLabel usernameLabel = new JLabel("User: " + user.username);
		usernameLabel.setBounds(10, 10, 200, 20);
		JLabel userRoleLabel = new JLabel("Role: " + user);
		userRoleLabel.setBounds(10, 30, 200, 20);

		userPanel.add(usernameLabel);
		//userRolePanel.add(userRoleLabel);

		// Top info Panel contents

		// 2. Hours total Panel with period selection (drop down window or cycle through button)


		// 3. current time and (datae panel)

		// 4. current date panel

		// Add all panel to info panel
		topInfoPanel.add(userPanel);
		


		/*********************************** Top Info Panel Settings *******************************/
		topInfoPanel.setBounds(200, 0, 984, 75);
		topInfoPanel.setBackground(new Color(250, 247, 249)); 

		/*********************** Main Panel Settings *************************/
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 1200, 800);
		mainPanel.setBackground(new Color(255, 255, 255));

		// Sample label test to verify its working and placements
		JLabel welcomeLabel = new JLabel("Welcome, " + user.username);
		welcomeLabel.setBounds(500, 150, 200, 30);
		mainPanel.add(welcomeLabel);
		mainPanel.add(topInfoPanel);

		/*********************** Main Panel Settings *************************/

		



		/*********************** Action Button Panel ********************************/
		JButton clockInButton = new JButton("Clock In");

		JButton clockOutButton = new JButton("Clock Out");

		JButton breakButton = new JButton("Break/Lunch");

		/*********************** Button Panel Settings ***********************/



		/*********************** Add Panel to Frame **************************/
		// Add menu bar to the frame
		add(topInfoPanel, BorderLayout.NORTH);
		add(menuBar, BorderLayout.WEST);

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
