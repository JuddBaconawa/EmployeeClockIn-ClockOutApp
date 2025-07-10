package components.dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import models.User;

public class ProfilePanel extends JPanel {

  public ProfilePanel(User user) {

    setLayout(new BorderLayout(10, 10));
    setPreferredSize(new Dimension(400, 200));
    setBackground(new Color(255, 255, 255));
    setBorder(BorderFactory.createTitledBorder("Profile"));

    ImageIcon icon = user.profilePicture != null 
    ? new ImageIcon(user.profilePicture) 
    : new ImageIcon("default_profile.png");

    JLabel profilePictureLabel = new JLabel(icon);

    JPanel profilePicturePanel = new JPanel();
    profilePicturePanel.setOpaque(false);
    profilePicturePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.DARK_GRAY));
    profilePicturePanel.add(profilePictureLabel);
    profilePicturePanel.setPreferredSize(new Dimension(150, 100));

    JPanel infoPanel = new JPanel();
    infoPanel.setOpaque(false);
    infoPanel.setLayout(new GridLayout(0, 1));
    infoPanel.add(new JLabel("Name: " + user.lastName + ", " + user.firstName));
    infoPanel.add(new JLabel("Location" + user.location)); // shows in office or remote
    infoPanel.add(new JLabel("Email: " + safe(user.email)));
    infoPanel.add(new JLabel("Department: " + user.department));
    infoPanel.add(new JLabel("Role: " + user.role));


    add(profilePicturePanel, BorderLayout.WEST);
    add(infoPanel, BorderLayout.CENTER);
    
  }

  private String safe(String s) {
    return s != null ? s : "N/A";
  }
  
}
