//packages


//imports
import java.awt.Button;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;          //makes the window box
import javax.swing.JLabel;          //formats the design and spacing withing the GUI window
import javax.swing.JPanel;          //JLabel for the label such as the title for "username" and "password"
import javax.swing.JPasswordField;      //for the username and password input into the textbox
import javax.swing.JTextField;
import org.w3c.dom.UserDataHandler;



//main class - App
public class GUI implements ActionListener {



    public GUI() {



        //framing + panel for the GUI
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();    //should be set before the use of panel within the frame object
        
        //GUI-frame visual settings
        frame.setSize(300, 270);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);     //used to display the GUI or to set it visible for the user
        frame.add(panel);
        frame.setTitle("ClockIn/Out App: Login/Create Account");
        
        panel.setLayout(null);

        //username label
        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        //username-input textbox
        JTextField username = new JTextField();
        username.setBounds(100, 20, 165, 25);
        panel.add(username);

        //password label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        //password-input textbox
        JPasswordField password = new JPasswordField();
        password.setBounds(100, 50, 165, 25);
        panel.add(password);

        //login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds( 100, 90, 75, 25);
        panel.add(loginButton);
        loginButton.addActionListener(this);

        //create an account button
        JButton createAccount = new JButton("Create Account");
        createAccount.setBounds(100, 140, 130, 25);
        panel.add(createAccount);

        
        
        

    }

    //main method - where the first code is actually executed.
    public static void main(String[] args) throws Exception {

        UserServices.initializeUser();  //adds the accounts for credential checks 

        //activating or calling upon the method GUI
        new GUI();
        


    }


}
