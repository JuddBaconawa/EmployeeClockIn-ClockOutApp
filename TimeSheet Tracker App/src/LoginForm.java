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

		//create the main panel to hold the left and right panels
		JPanel innerMainPanel = new JPanel(new GridLayout(1, 2));
	

		/*********************** Left Panel ************************/
		JPanel leftPanel = new JPanel(new BorderLayout());
		JLabel leftTitle = new JLabel("Welcome Back!", JLabel.CENTER);
		leftPanel.setBackground(RGB(243, 344, 445));
		
		//future image
		//image label
		//leftPanel.add(imageLabel, BorderLayout.CENTER);


		/******************** Form Panel Left **********************/
		leftPanel.add(leftTitle, BorderLayout.CENTER);


		/*********************** Right Form panel ********************/
		/**************** Labels and Inputs panel *****************/
		JPanel rightPanel = new JPanel(new GridLayout(3, 3, 10, 10));

		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(25, 50, 75, 25);

		usernameInput = new JTextField();
		usernameInput.setBounds(50, 50, 75, 25);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(25, 90, 75, 25);

		userPasswordInput = new JPasswordField();
		userPasswordInput.setBounds(75, 90, 75, 25);

		/*********************** Form panel RIGHT ********************/
		JPanel formPanel = new JPanel();
		rightPanel.add(usernameLabel);
		rightPanel.add(usernameInput);
		rightPanel.add(passwordLabel);
		rightPanel.add(userPasswordInput);

		


		/*********************** Buttons panel ********************/
		JButton loginButton = new JButton("Log In");
		loginButton.setBounds(75, 25, 50, 25);

		JButton windowExit = new JButton("Exit");
		windowExit.setBounds(25, 25, 50, 25);


		/*********************** Button-Form panel ********************/
		rightPanel.add(loginButton);
		rightPanel.add(windowExit);

		/*********************** Inner Main panel ********************/
		innerMainPanel.add(leftPanel);
		innerMainPanel.add(rightPanel);
		

		/*********************** Frame Settings ********************/
		setTitle("Login to Clock In");
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);
		add(innerMainPanel);
		setVisible(true);

		

	}

    private Color RGB(int i, int i0, int i1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
