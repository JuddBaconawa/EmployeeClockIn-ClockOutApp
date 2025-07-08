package displayCards;
// IMPORTS
import java.awt.Color;

import components.DisplayCard;


// Profile Class
public class Profile extends DisplayCard {
    public Profile() {
        super("Profile");
        setBackground(new Color(62, 92, 118));
  
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.setFont(new java.awt.Font("arial", java.awt.Font.BOLD, 32));
        g.setColor(java.awt.Color.LIGHT_GRAY);
        g.drawString("Profile", 30, 60);
    }

    public static void main(String[] args) {

        Profile profile = new Profile();
        javax.swing.JFrame frame = new javax.swing.JFrame("Profile Example");
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.add(profile);
        frame.setSize(1200, 680);
        frame.setVisible(true);
    }

}