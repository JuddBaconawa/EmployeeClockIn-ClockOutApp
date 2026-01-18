// PACKAGES
package displayCards;


import java.awt.BorderLayout;
// IMPORTS
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import components.DisplayCard;
import components.TitlePanel;
import components.dashboard.StatusIndicator;
import components.dashboard.ProfilePanel;
import models.User;


// Profile Class
public class Profile extends DisplayCard {

    private StatusIndicator statusIndicator;
    private JLabel statusText;

    public Profile(User user) {
        super("Profile");
        // setBackground(new Color(62, 92, 118));
        // setLayout(new BorderLayout(2, 2, 2, 2));

        setBackground(new Color(240, 235, 216));
        setLayout(new BorderLayout());


        // TITLE Panel
        statusIndicator = new StatusIndicator();
        statusIndicator.setStatus("out");
        TitlePanel titlePanel = new TitlePanel("Profile", statusIndicator);
        titlePanel.setBackgroundColor(new Color(62, 92, 118));
        add(titlePanel, BorderLayout.NORTH);

        // Profile Panel - content
        ProfilePanel profilePanel = new ProfilePanel(user);
        profilePanel.setBackground(Color.LIGHT_GRAY);
        profilePanel.setLayout(new GridLayout(5, 5, 0, 0));
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

    public void updateStatus(String status) {
        if (statusIndicator != null) {
            statusIndicator.setStatus(status);
        }
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