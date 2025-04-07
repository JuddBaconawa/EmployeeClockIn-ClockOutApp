//packages


//imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//Main class
public class LogInGUI extends BaseGUI implements ActionListener {

	private JTextField usernameField;
	private JPasswordField passwordField;

	public LogInGUI() {
		//inhereted declarations
		super("Clock In/Out App: Login/Create Account", 325, 275);

		JFrame frame = new JFrame();

		ImageIcon image = new ImageIcon("src/images/timesheet-icon (small).png");
		frame.setIconImage(image.getImage());


		//username label
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(20, 180, 80, 25); 
		panel.add(usernameLabel);

		//username input
		JTextField usernameInput = new JTextField();
		usernameInput.setBounds(90, 20, 125, 25);
		panel.add(usernameInput);

		//password label
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(20, 50, 80, 25);
		panel.add(passwordLabel);

		//password input
		JPasswordField passwordInput = new JPasswordField();
		passwordInput.setBounds(90, 50, 125, 25);
		panel.add(passwordInput);

		//login button
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(20, 200, 85, 25);
		panel.add(loginButton);

		//create and account button
		JButton createAButton = new JButton("Create Account");
		createAButton.setBounds(170, 200, 125, 25);
		panel.add(createAButton);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		//throm message under
		throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
	}



	
}
