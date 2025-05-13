// Packages

// IMPORTS
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
		/*********************** Main Panel *********************************/
		JPanel mainPanel = new JPanel();
		setTitle("Time Sheet Home");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setLayout(null);



		/*********************** Main Panel Settings *************************/
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 1000, 800);
		mainPanel.setBackground(new Color(255, 255, 255));

		// Sample label to verify its working
		JLabel welcomeLabel = new JLabel("Welcome, " + user.username);
		welcomeLabel.setBounds(50, 50, 200, 30);
		mainPanel.add(welcomeLabel);

		/*********************** Main Panel Settings *************************/
		

		/*********************** (Side) Menu Bar/Panel ************************************/
		// Create a menu bar
		JMenuBar menuBar = new JMenuBar();

		/*********************** Menu Bar Settings ***************************/

		/*********************** Action Button Panel ********************************/
		JButton clockInButton = new JButton("Clock In");

		JButton clockOutButton = new JButton("Clock Out");

		JButton breakButton = new JButton("Break/Lunch");

		/*********************** Button Panel Settings ***********************/



		/*********************** Add Panel to Frame **************************/
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
