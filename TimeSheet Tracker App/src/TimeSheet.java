// Packages

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.Box;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;





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

		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 10, 10));	// Rounded corners 10 radius

		/*********************** (Side) Menu Bar/Panel ************************************/
		// Create a menu bar
		JPanel menuBar = new JPanel(new GridLayout(13, 1, 0,  5)); // 9 rows, 1 column, 10px vertical gap
		menuBar.setBounds(0, 0, 200, getHeight());									// Menu bar size   763 with a titlebar
		// Set menuBar width to 25% of the window width dynamically
		int menuBarWidth = (int) (getWidth() * 0.167);
		menuBar.setBounds(0, 0, menuBarWidth, getHeight());
		menuBar.setBackground(new Color(29, 45, 68));
		menuBar.setVisible(true);	

		// Add a logo to the top of the menu bar  NORTH position
		JPanel logoPanel = new JPanel();
		logoPanel.setBackground(new Color(29, 45, 68));
		logoPanel.setLayout(null);
		logoPanel.setPreferredSize(new java.awt.Dimension(300, 00));
		//logoPanel.setBounds(0, 0, 200, 120);

		// logoPanel.setBorder(new MatteBorder(0, 0, 2, 2, Color.LIGHT_GRAY));
		logoPanel.setBorder(
			new CompoundBorder(
				new LineBorder(new Color(29,45, 68), 3), // Outer border
				new CompoundBorder(
					new MatteBorder(0, 0, 2, 2, Color.LIGHT_GRAY), // Inner border
					new MatteBorder(0, 0, 2, 0, (new Color(29, 45, 68))) // Inner border
				)
			)
		);

		JPanel menuSpacer = new JPanel();
		menuSpacer.setOpaque(false);

		// set space for the company logo
		JLabel logoLabel = new JLabel("\"Brand/Logo\"");
		ImageIcon logoIcon = new ImageIcon ("resources/logo.png");
		logoLabel.setIcon(logoIcon);

		logoLabel.setBounds(20, 40, 160, 60);
		logoLabel.setBackground(new Color(255, 255, 255));
		logoLabel.setForeground(new Color(255, 255, 255));
		// logoLabel.setOpaque(true);
		logoLabel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(Color.GRAY, 3/* , true*/),
			BorderFactory.createEmptyBorder(5, 5, 5, 5)
		));

		logoLabel.setVisible(true);

		// Add the logo label to the logo panel
		logoPanel.add(logoLabel);
		// menuBar.add(logoPanel);



		// Create menu items (Buttons)
		// Menu Label
		JLabel menuLabel = new JLabel();
		menuLabel.setLayout(null);
		menuLabel.setBackground(new Color(29,45, 68));

		// Menu Label
		JLabel menuItemLabel = new JLabel("Menu Items");
		menuItemLabel.setFont(new Font("null", Font.BOLD, 20));
		menuItemLabel.setForeground(new Color(251, 160, 157));
		menuItemLabel.setBounds(10, 0, 140, 40);
		menuLabel.add(menuItemLabel);

		// Home Panel
		JPanel homePanel = new JPanel();
		homePanel.setLayout(null);
		homePanel.setBounds(0, 180, 200, 40); // moved up
		homePanel.setBackground(new Color(255, 255, 255));

		// Home label
		JLabel homeLabel = new JLabel("Home");
		homeLabel.setFont(new Font("null", Font.BOLD, 16));
		homeLabel.setForeground(new Color(251, 160, 157));
		homeLabel.setBounds(20, 0, 140, 40);
		homePanel.add(homeLabel);

		// Dashboard Panel
		JPanel dashboardPanel = new JPanel();
		dashboardPanel.setLayout(null);
		dashboardPanel.setBounds(0, 230, 200, 40); // moved down
		dashboardPanel.setBackground(new Color(247, 204, 173));

		// Dashboard label
		JLabel dashboardLabel = new JLabel("Dashboard");
		dashboardLabel.setFont(new Font("null", Font.BOLD, 16));
		dashboardLabel.setForeground(new Color(251, 160, 157));
		dashboardLabel.setBounds(20, 0, 140, 40);
		dashboardPanel.add(dashboardLabel);

		// Time Log Panel (was Timesheet Panel)
		JPanel timeLogPanel = new JPanel();
		timeLogPanel.setLayout(null);
		timeLogPanel.setBounds(0, 280, 200, 40); // moved down
		timeLogPanel.setBackground(new Color(247, 204, 173));

		// Time Log label
		JLabel timeLogLabel = new JLabel("Time Log");
		timeLogLabel.setFont(new Font("null", Font.BOLD, 16));
		timeLogLabel.setForeground(new Color(251, 160, 157));
		timeLogLabel.setBounds(20, 0, 140, 40);
		timeLogPanel.add(timeLogLabel);

		// Profile Panel
		JPanel profilePanel = new JPanel();
		profilePanel.setLayout(null);
		profilePanel.setBounds(20, 340, 160, 40);
		profilePanel.setBackground(new Color(116, 65, 62));

		// Profile label
		JLabel profileLabel = new JLabel("Profile");
		profileLabel.setFont(new Font("null", Font.BOLD, 16));
		profileLabel.setForeground(new Color(251, 160, 157));
		profileLabel.setBounds(20, 0, 140, 40);
		profilePanel.add(profileLabel);

		// Add mouse listeners for highlight/click effect (example for one panel)
		java.awt.event.MouseAdapter highlightEffect = new java.awt.event.MouseAdapter() {
			Color original;
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				JPanel panel = (JPanel) e.getSource();
				original = panel.getBackground();
				panel.setBackground(new Color(13, 19, 33));
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				JPanel panel = (JPanel) e.getSource();
				panel.setBackground(original);
			}
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				JPanel panel = (JPanel) e.getSource();
				panel.setBackground(new Color(180, 180, 180));
			}
			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {
				JPanel panel = (JPanel) e.getSource();
				panel.setBackground(original);
			}
		};
		// Add mouse listeners for highlight/click effect
		menuLabel.addMouseListener(highlightEffect);
		homePanel.addMouseListener(highlightEffect);
		dashboardPanel.addMouseListener(highlightEffect);
		timeLogPanel.addMouseListener(highlightEffect);
		profilePanel.addMouseListener(highlightEffect);




		// Logout and Exit buttons
		JButton logoutButton = new JButton("Logout") {

			
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
		logoutButton.setContentAreaFilled(false);
		logoutButton.setFocusPainted(false);
		logoutButton.setBorderPainted(false);
		logoutButton.setOpaque(false);
		logoutButton.setFont(new Font("null", Font.BOLD, 20));

		logoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    Color original = logoutButton.getBackground();
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent e) {
		        logoutButton.setBackground(new Color(173, 40, 49)); // Highlight color
		    }
		    @Override
		    public void mouseExited(java.awt.event.MouseEvent e) {
		        logoutButton.setBackground(original);
		    }
		    @Override
		    public void mousePressed(java.awt.event.MouseEvent e) {
		        logoutButton.setBackground(new Color(100, 13, 20)); // Clicked color
		    }
		    @Override
		    public void mouseReleased(java.awt.event.MouseEvent e) {
		        logoutButton.setBackground(original);
		    }
		});


		logoutButton.addActionListener(e -> {
			// Close the current TimeSheet window
			this.dispose();
			// Open the login form
			new LoginForm().setVisible(true);
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
		 
		

		exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    Color original = exitButton.getBackground();
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent e) {
		        exitButton.setBackground(new Color(116, 65, 62)); // Highlight color
		    }
		    @Override
		    public void mouseExited(java.awt.event.MouseEvent e) {
		        exitButton.setBackground(original);
		    }
		    @Override
		    public void mousePressed(java.awt.event.MouseEvent e) {
		        exitButton.setBackground(new Color(13, 19, 33)); // Clicked color
		    }
		    @Override
		    public void mouseReleased(java.awt.event.MouseEvent e) {
		        exitButton.setBackground(original);
		    }
		});

		exitButton.addActionListener(e -> this.dispose());

		/******** Add Panels and Buttons to menuBar (update order and variable names) *******/
		menuBar.add(logoLabel);      // Logo at the top

		menuBar.add(Box.createVerticalStrut(40)); // 30px space
		menuBar.add(menuLabel);      // "Menu Items" label title

		// menu Items
		menuBar.add(homePanel);
		menuBar.add(dashboardPanel);
		menuBar.add(timeLogPanel);
		menuBar.add(profilePanel);

		//buttons
		menuBar.add(Box.createVerticalStrut(40)); // 30px space
		menuBar.add(Box.createVerticalStrut(40));
		// menuBar.add(Box.createVerticalStrut(5));
		menuBar.add(logoutButton);
		menuBar.add(exitButton);
		


		/*********************** (Side) Menu Bar/Panel Settings ***************************/
		// Set the bounds for each menu item (update order and variable names)
		// menuLabel.setBounds(0, 130, 200, 40);
		// homePanel.setBounds(0, 180, 200, 40);
		// dashboardPanel.setBounds(0, 230, 200, 40);
		// timeLogPanel.setBounds(0, 280, 200, 40);
		// profilePanel.setBounds(0, 330, 200, 40);
		// Set the bounds for the buttons
		// logoutButton.setBounds(20, 680, 160, 40);
		// exitButton.setBounds(20, 740, 160, 40);

		// Logo label
		// menuBar.add(logoLabel);

		// Add menu items to the menu bar
		// menuBar.add(menuLabel);
		// menuBar.add(homePanel);
		// menuBar.add(dashboardPanel);
		// menuBar.add(timeLogPanel);
		// menuBar.add(profilePanel);


		//last two buttons
		// menuBar.add(exitButton);
		// menuBar.add(logoutButton);
		// menuBar.add(logoPanel);

		// Set the background color for each menu item and their TEXT color via setForeground
		// First panel label theme check ============================
		menuLabel.setBackground(new Color(29, 45, 68));
		menuLabel.setForeground(new Color(251, 160, 157)); // Set text color to black

		// second button theme check =====================================
		dashboardPanel.setBackground(new Color(29, 45, 68));
		dashboardPanel.setForeground(new Color(251, 160, 157)); // Set text color to black
		// third button theme check	 =====================================
		timeLogPanel.setBackground(new Color(29, 45, 68));
		timeLogPanel.setForeground(new Color(251, 160, 157)); // Set text color to black
		// fourth button theme check	=====================================
		homePanel.setBackground(new Color(29, 45, 68));
		homePanel.setForeground(new Color(251, 160, 157)); 
		// fifth button theme check	========================================
		profilePanel.setBackground(new Color(29, 45, 68));
		profilePanel.setForeground(new Color(251, 160, 157));
		// sixth button theme check	=========================================
		logoutButton.setBackground(new Color(240, 235, 216));
		logoutButton.setForeground(new Color(251, 160, 157)); // Set text color to black
		logoutButton.setFont(new Font("null", Font.BOLD, 20)); // "null" is not a valid font name, use a real font or Font.DIALOG
		logoutButton.setPreferredSize(new Dimension(140, 30));

		// seventh button theme check	==========================================
		exitButton.setBackground(new Color(240, 235, 216));
		exitButton.setForeground(new Color(251, 160, 157)); // Set text color to black
		exitButton.setPreferredSize(new Dimension(120, 20));

		/*********************************** (User) Top Info Panel *********************************/
		// Create a top info panel
		JPanel topInfoPanel = new JPanel();
		topInfoPanel.setLayout(new GridLayout(1, 6, 15, 50));
		topInfoPanel.setBorder(BorderFactory.createEmptyBorder(40, 10, 20, 10));
		// The commented-out border below does nothing and can be removed
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
		// The border below is not visible unless the label is added to a visible container
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
		hoursLabel.setBounds(10, 10, 200, 20);
		String[] periods = {"Day", "Week", "Bi-month"};
		JComboBox<String> periodCombo = new JComboBox<>(periods);
		periodCombo.setBounds(10, 35, 100, 20);
		
		hoursPanel.add(hoursLabel);
		hoursPanel.add(periodCombo);

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
		currentTimeLabel.setBounds(10, 10, 200, 20);
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
		currentDateLabel.setBounds(10, 10, 200, 20);
		currentDatePanel.add(currentDateLabel);

		// Add all panel to Top Info Panel
		topInfoPanel.add(userPanel);
		topInfoPanel.add(hoursPanel);
		topInfoPanel.add(currentTimePanel);
		topInfoPanel.add(currentDatePanel);

		// Set the background color for each menu item
		userPanel.setBackground(new Color(240, 235, 216));

		/******************************* (Views) Display Panel ***********************************/
		JPanel displayPanel = new JPanel(new java.awt.CardLayout());
		displayPanel.setBounds(200, 120, 1000, 681);
		displayPanel.setBackground(new Color(240, 235, 216));

		displayPanel.add(new Home(), "Home");
		displayPanel.add(new Dashboard(), "Dashboard");
		displayPanel.add(new Profile(), "Profile");
		displayPanel.add(new Timelog(), "Time Log");


		/*********************************** Menu Bar Panel ****************************************/



		/*********************************** Top Info Panel Settings *******************************/
		topInfoPanel.setBounds(200, 0, 1000, 120);
		topInfoPanel.setBackground(new Color(62, 92, 118)); 

		/*********************** Main Panel Settings *************************/
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 1200, 800);			//panel size is 1200 x 800
		mainPanel.setBackground(new Color(255, 255, 255));

		// Sample label test to verify its working and placements
		JLabel welcomeLabel = new JLabel("If seeing this, something is wrong!" + user.username);
		welcomeLabel.setBounds(205, 150, 200, 30);



		/*********************** Main Panel Settings *************************/
		// Used to add sections into the main panel
		mainPanel.add(welcomeLabel);
		mainPanel.add(topInfoPanel);
		

		/*********************** Action Button Panel ********************************
		JButton clockInButton = new JButton("Clock In");

		JButton clockOutButton = new JButton("Clock Out");

		JButton breakButton = new JButton("Break/Lunch");

		/*********************** Button Panel Settings ***********************/
		// Have to add to the Main info panel thats yet to be create


		/*********************** Add Panel to Frame **************************/
		// Add menu bar to the frame
		add(menuBar);															//Menu bar on the left side  

		add(topInfoPanel);												// Add top info panel to the frame		
		add(displayPanel);
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
