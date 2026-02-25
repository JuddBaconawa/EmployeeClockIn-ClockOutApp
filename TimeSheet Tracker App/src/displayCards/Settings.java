package displayCards;
import java.awt.BorderLayout;
//IMPORTS
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


import components.DisplayCard;
import components.dashboard.StatusIndicator;
import components.TitlePanel;





// Home Class
public class Settings extends DisplayCard {

    private StatusIndicator statusIndicator;
    private JLabel statusText;

    public Settings() {

      // Card Title
      super("");

      // Set properties for the Home JPanel
      setBackground(new Color(255, 255, 255));
      setLayout(new BorderLayout());
      

      // TITLE Panel
      statusIndicator = new StatusIndicator();
      TitlePanel titlePanel = new TitlePanel("Settings", new StatusIndicator());
      titlePanel.setBackgroundColor(new Color(89, 92, 118));
      add(titlePanel, BorderLayout.NORTH);

      
      // Content Panel: 3 column, 3 rows: Interactive | Description | State
      JPanel contentPanel = new JPanel();
      contentPanel.setLayout(new GridLayout(3, 3, 10, 50));
      contentPanel.setOpaque(true);
      contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 20));
      add(contentPanel, BorderLayout.CENTER);

      // FOOTER Panel
      JPanel footerPanel = new JPanel();
      footerPanel.setBackground(new Color(35, 177, 85));
      footerPanel.setOpaque(true);
      footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      add(footerPanel, BorderLayout.SOUTH);

      // Row 1 : Change Password
      JButton changePasswordButton = new JButton("Change Password");
      

      
      // Add components to the Home JPanel



      // additional initialization if needed
      JLabel usernameLabel = new JLabel ("Welcome, " + "User!" + "This is the settings"); // Replace "User" with actual username
      this.add(usernameLabel);

      // settings panel
      JPanel passwordPanel = new JPanel();
      passwordPanel.setBackground(new Color(5, 199, 242));
      passwordPanel.setOpaque(true);
      passwordPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      passwordPanel.add(new JLabel("Change Password:"));
      contentPanel.add(passwordPanel);

      


      

    }




    public static void main(String[] args) {
      //Settings instance created for testing
      Settings settings = new Settings();

      javax.swing.JFrame frame = new javax.swing.JFrame("Settings Example");
      frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
      frame.add(settings);
      frame.setSize(1000, 680);
      frame.setVisible(true);

    }
}  