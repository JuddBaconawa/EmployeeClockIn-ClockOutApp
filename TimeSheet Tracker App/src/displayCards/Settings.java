package displayCards;
import java.awt.BorderLayout;
//IMPORTS
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import components.DisplayCard;



// Home Class
public class Settings extends DisplayCard {
    public Settings() {

      // Card Title
      super("Setting");

      // Set properties for the Home JPanel
      setBackground(new Color(255, 255, 255));
      setLayout(new BorderLayout());
      

      // TITLE Panel
      JPanel titlePanel = new JPanel(new BorderLayout());
      titlePanel.setBackground(new Color(62, 92, 118));
      titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

      JLabel titleLabel = new JLabel("Settings");
      titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
      titleLabel.setForeground(new Color(100, 100, 100));
      



      // additional initialization if needed
      JLabel usernameLabel = new JLabel ("Welcome, " + "User!" + "This is the settings"); // Replace "User" with actual username
      usernameLabel.setBounds(25, 150, 200, 30);
      this.add(usernameLabel);


      add(titlePanel, BorderLayout.NORTH);

    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
      super.paintComponent(g);
      g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 32));
      g.setColor(java.awt.Color.LIGHT_GRAY);
      g.drawString("Settings", 30, 60); // x, y
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