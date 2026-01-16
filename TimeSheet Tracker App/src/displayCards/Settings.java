package displayCards;
import java.awt.BorderLayout;
//IMPORTS
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
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
      statusIndicator.setStatus("out");
      TitlePanel titlePanel = new TitlePanel("Settings", statusIndicator);
      titlePanel.setBackgroundColor(new Color(62, 92, 118));
      

      // Content Panel
      JPanel contentPanel = new JPanel();
      contentPanel.setLayout(new GridLayout(2, 2, 10, 50));
      contentPanel.setOpaque(true);
      contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 20));

      
      



      // additional initialization if needed
      JLabel usernameLabel = new JLabel ("Welcome, " + "User!" + "This is the settings"); // Replace "User" with actual username
      usernameLabel.setBounds(25, 150, 200, 30);
      this.add(usernameLabel);


      add(titlePanel, BorderLayout.NORTH);

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