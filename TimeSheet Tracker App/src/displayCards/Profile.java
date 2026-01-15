package displayCards;
import java.awt.BorderLayout;
// IMPORTS
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import components.DisplayCard;
import components.dashboard.ProfilePanel;
import models.User;


// Profile Class
public class Profile extends DisplayCard {

    public Profile(User user) {
        super("Profile");
        // setBackground(new Color(62, 92, 118));
        // setLayout(new BorderLayout(2, 2, 2, 2));

        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(62, 92, 118));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(titlePanel, BorderLayout.NORTH);

        ProfilePanel profilePanel = new ProfilePanel(user);
        profilePanel.setBackground(Color.LIGHT_GRAY);
        profilePanel.setLayout(new GridLayout(3, 5, 20, 20));
        profilePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(profilePanel, BorderLayout.CENTER);
  
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.setFont(new java.awt.Font("arial", java.awt.Font.BOLD, 32));
        g.setColor(java.awt.Color.LIGHT_GRAY);
        g.drawString("Profile", 30, 60);
    }

    // public static void main(String[] args) {

    //     Profile profile = new Profile();
    //     javax.swing.JFrame frame = new javax.swing.JFrame("Profile Example");
    //     frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    //     frame.add(profile);
    //     frame.setSize(1200, 680);
    //     frame.setVisible(true);
    // }

}