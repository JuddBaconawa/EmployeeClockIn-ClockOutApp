// Packages

// IMPORTS
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JButton;




// TimeSheet class
public class TimeSheet extends JFrame{

	public void initialize(User user) {
		
		/*********************** Time Sheet UI ***********************/
		/*********************** Frame Panel *********************************/

		setTitle("Time Sheet Home");
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setLayout(null);

		/*********************** (Side) Menu Bar/Panel ************************************/
		// Create a menu bar
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(null);
		menuBar.setBounds(0, 0, 200, 800);
		menuBar.setBackground(new Color(247, 204, 173));
		menuBar.setVisible(true);	


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
		searchButton.setBounds(20, 30, 160, 40);
		dashboardButton.setBounds(20, 80, 160, 40);
		timesheetButton.setBounds(20, 130, 160, 40);
		homeButton.setBounds(20, 180, 160, 40);
		profileButton.setBounds(20, 230, 160, 40);
		settingsButton.setBounds(20, 650, 160, 40);
		logoutButton.setBounds(20, 700, 160, 40);
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
		// seventh button theme check	==========================================




		/*********************** Main Panel Settings *************************/
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 1000, 800);
		mainPanel.setBackground(new Color(255, 255, 255));

		// Sample label to verify its working
		JLabel welcomeLabel = new JLabel("Welcome, " + user.username);
		welcomeLabel.setBounds(50, 50, 200, 30);
		mainPanel.add(welcomeLabel);

		/*********************** Main Panel Settings *************************/
		



		/*********************** Action Button Panel ********************************/
		JButton clockInButton = new JButton("Clock In");

		JButton clockOutButton = new JButton("Clock Out");

		JButton breakButton = new JButton("Break/Lunch");

		/*********************** Button Panel Settings ***********************/



		/*********************** Add Panel to Frame **************************/
		// Add menu bar to the frame
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
