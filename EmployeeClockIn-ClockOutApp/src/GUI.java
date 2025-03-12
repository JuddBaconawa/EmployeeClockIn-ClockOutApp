//packages


//imports
import javax.swing.JButton;          //makes the window box
import javax.swing.JFrame;          //formats the design and spacing withing the GUI window
import javax.swing.JLabel;          //JLabel for the label such as the title for "username" and "password"
import javax.swing.JPanel;      //for the username and password input into the textbox
import javax.swing.JTextField;         //for button created - such as the login button



//main class - App
public class GUI {



    public GUI() {

        //framing for the GUI
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();    //should be set before the use of panel within the frame object
        
        //GUI-frame visual settings
        frame.setSize(300, 175);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);     //used to display the GUI or to set it visible for the user
        frame.add(panel);
        
        panel.setLayout(null);

        //username label
        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        //username-input textbox
        JTextField userText = new JTextField();
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        //password label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        //password-input textbox
        JTextField passwordText = new JTextField();
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        //login button
        JButton login = new JButton("Login");
        login.setBounds( 100, 90, 75, 25);
        panel.add(login);








        

    }

    //main method - where the first code is actually executed.
    public static void main(String[] args) throws Exception {

        //activating or calling upon the method GUI
        new GUI();
        


    }


}
