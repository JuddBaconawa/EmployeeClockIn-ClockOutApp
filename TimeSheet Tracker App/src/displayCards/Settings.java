package displayCards;
//IMPORTS
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import components.DisplayCard;



// Home Class
public class Settings extends DisplayCard {
    public Settings() {

      // Card Title
      super("Setting");

      // Set properties for the Home JPanel
      setLayout(null);
      setBackground(new Color(116, 140, 171));
      // setBounds(200, 120, 1000, 680);


      // additional initialization if needed
      JLabel usernameLabel = new JLabel ("Welcome, " + "User!" + "This is the settings"); // Replace "User" with actual username
      usernameLabel.setBounds(25, 150, 200, 30);
      this.add(usernameLabel);


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