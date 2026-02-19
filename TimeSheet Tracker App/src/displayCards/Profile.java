// PACKAGES
package displayCards;


import java.awt.BorderLayout;
// IMPORTS
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;
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
    private TitlePanel titlePanel;

    public Profile(User user) {

        super("Profile");
        // setBackground(new Color(62, 92, 118));
        // setLayout(new BorderLayout(2, 2, 2, 2));

        setBackground(new Color(240, 235, 216));

        // === Layout ===
        setLayout(new BorderLayout());


        // TITLE Panel
        titlePanel = new TitlePanel("Profile", new StatusIndicator());
        titlePanel.setBackgroundColor(new Color(89, 92, 118));
        add(titlePanel, BorderLayout.NORTH);

        // === Content Panel ===
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(2, 2, 10, 50));
        contentPanel.setOpaque(true);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 20));
        add(contentPanel, BorderLayout.CENTER);

        // Top Panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 40));
        topPanel.setOpaque(false);

        topPanel.add(wrapTopAligned(new ProfilePanel(user)));

        // Top panel added to content panel
        contentPanel.add(topPanel);

        // // Profile Panel - content
        // ProfilePanel profilePanel = new ProfilePanel(user);
        // profilePanel.setBackground(Color.LIGHT_GRAY);
        // profilePanel.setLayout(new GridLayout(5, 5, 0, 0));
        // profilePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // topPanel.add(profilePanel, BorderLayout.CENTER);
  
    }

    private JPanel wrapTopAligned(JPanel inner) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.add(inner, BorderLayout.NORTH);
        return wrapper;
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


    // }

}