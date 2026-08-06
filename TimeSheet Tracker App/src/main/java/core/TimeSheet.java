package core;
// Packages

// IMPORTS
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import auth.LoginForm;
import components.MenuPanel;
import components.TopInfoPanel;
import components.dashboard.StatusManager;
import dao.TimelogDAO;
import displayCards.Dashboard;
import displayCards.Profile;
import displayCards.Projects;
import displayCards.Settings;
import displayCards.Timelog;
import models.User;



// TimeSheet class
public class TimeSheet extends JFrame{

	// variable for the display panel
	private JPanel displayPanel;

	//private connection variable
	private Connection conn;

	// variable for dragging the window
	private int mouseX;
	private int mouseY;

	// statusmanager created
	private StatusManager statusManager = new StatusManager();

	// timesheet connection
	public TimeSheet(Connection conn) {
		this.conn = conn;
	}

	// showcard for 
	public void showCard(String cardname) {
		CardLayout cl = (CardLayout) displayPanel.getLayout();
		cl.show(displayPanel, cardname);
	}

	public void initialize(User user) {

		// DB connection

		// try {

		// 	Class.forName("com.mysql.cj.jdbc.Driver");

		// 	conn = DriverManager.getConnection(
    //     "jdbc:mysql://127.0.0.1:3306/timesheetappdatabase",	// database URL
    //     "root",	// username
    //     "DB_password1301"	// password
		// 	);		
		// } catch (Exception e) {
		// 	e.printStackTrace();
		// }
		
		/**************** Frame Setup ****************************/

		setTitle("Time Sheet Home");
		setUndecorated(true);						// Takes out the TimeSheet title bar
		setSize(1400, 800);						// Set the size of the frame	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// sets the icon for the App that can be seen in the taskbar
		setIconImage(new ImageIcon("src/images/app-file-icon.png").getImage());
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setLayout(null);

		// Rounds the window's corner
		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));	// Rounded corners 10 radius

		// Enable the ability for the user to drag the window around
		enableWindowDrag(this);

		/********************* Main Panel Settings ***********************/
		JPanel mainPanel = new JPanel(null);
		mainPanel.setBounds(0, 0, 1400, 800);			//panel size is 1200 x 800


		/*********************** Menu Bar Panel *********************************/
		JPanel menuPanel = new MenuPanel(this, user, conn);
		menuPanel.setBounds(0, 0, 200, getHeight()); // Example bounds, adjust as needed
		mainPanel.add(menuPanel);
		// Use the custom MenuPanel instead of TopInfoPanel for the menu



		/********************* Top Info Panel Settings ***************************/
		// Used to add sections into the main panel
		JPanel topInfoPanel = new TopInfoPanel(user);


		topInfoPanel.setBorder(new MatteBorder(0, 0, 1, 0, new Color(220, 220, 220))); // Example border, adjust as needed
		//mainPanel.add(topInfoPanel); // Add top info panel to the main panel

		/********************** (Views) Display Panel *********************/
		displayPanel = new JPanel(new java.awt.CardLayout());
		displayPanel.setBounds(200, 0, 1200, 800);
		displayPanel.setBackground(new Color(240, 235, 216));

		displayPanel.add(new Dashboard(conn, user, statusManager), "Dashboard");
		displayPanel.add(new Profile(user, statusManager), "Profile");
		displayPanel.add(new Timelog(new TimelogDAO(conn), user.getUserId(), statusManager), "Timelog");
		displayPanel.add(new Projects(this, conn, user, statusManager), "Projects");
		
		displayPanel.add(new Settings(statusManager), "Settings");
		
		/*********************** Add Panel to Frame ************************/
		mainPanel.add(displayPanel);					// Add display panel to the main panel
		// add(displayPanel);												// Add display panel to the frame		
		add(mainPanel);														// Add main panel to the frame					
		// add(menuPanel);														// Add menu panel to the frame
		setVisible(true);
	}

	private void enableWindowDrag(JFrame frame) {
		frame.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});

		frame.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			@Override
			public void mouseDragged(java.awt.event.MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				frame.setLocation(x - mouseX, y - mouseY);
			}

		});
	}

	// public static void main(String[] args) {
	// 	// Create a saple user Instance to test
	// 		User user = new User();

	// 		// Initialize the TimeSheet UI
	// 		TimeSheet timesheet = new TimeSheet();
	// 		timesheet.initialize(user);
	// }

	public static void main(String[] args) {
    Connection conn = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        conn = DriverManager.getConnection(
            "jdbc:mysql://127.0.0.1:3306/timesheetappdatabase",
            "root",
            "DB_password1301"
        );

    } catch (Exception e) {
        e.printStackTrace();
    }

    new LoginForm(conn); // ✅ Start app here
}


}
