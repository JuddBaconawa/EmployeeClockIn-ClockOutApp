package components.dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import models.User;

public class ProfilePanel extends JPanel {

  public ProfilePanel(User user) {

    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(150, 150));
    setBackground(new Color(255, 255, 255));
    setBorder(BorderFactory.createTitledBorder("Profile"));

    // ***************** Profile Picture and Info Panel *****************
    ImageIcon icon = user.profilePicture != null 
            ? new ImageIcon(user.profilePicture) 
            : new ImageIcon("default_profile.png");

    JLabel profilePictureLabel = new JLabel(scaleIcon(icon, 64, 64));
    profilePictureLabel.setBorder(BorderFactory.createBevelBorder(1));
    profilePictureLabel.setPreferredSize(new Dimension(75, 75));


    JPanel profilePicturePanel = new JPanel();
    profilePicturePanel.setOpaque(false);
    profilePicturePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.DARK_GRAY));
    profilePicturePanel.add(profilePictureLabel, BorderLayout.NORTH);
    
    // ***************** Top Info Panel *****************
    JPanel topInfoPanel = new JPanel();
    topInfoPanel.setLayout(new BoxLayout(topInfoPanel, BoxLayout.Y_AXIS));
    topInfoPanel.setOpaque(false);
    topInfoPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));


    topInfoPanel.add(new JLabel("ID: " + user.userId));
    topInfoPanel.add(new JLabel("Name: " + user.lastName + ", " + user.firstName));
    topInfoPanel.add(new JLabel("Role: " + user.role));

    JPanel topSection = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    topSection.setOpaque(false);
    topSection.add(profilePicturePanel);
    topSection.add(topInfoPanel);

    // ***************** Bottom Info Panel *****************
    JPanel bottomInfoPanel = new JPanel();
    bottomInfoPanel.setLayout(new BoxLayout(bottomInfoPanel, BoxLayout.Y_AXIS));
    bottomInfoPanel.setOpaque(false);
    bottomInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    bottomInfoPanel.add(new JLabel("Location: " + user.location)); // shows in office or remote
    bottomInfoPanel.add(new JLabel("Email: " + safe(user.email)));
    bottomInfoPanel.add(new JLabel("Department: " + user.department));
    
    bottomInfoPanel.add(new JLabel("Remote/In-Office: " + (user.isRemote ? "Remote" : "In-Office")));


    add(profilePicturePanel, BorderLayout.NORTH);
    add(bottomInfoPanel, BorderLayout.CENTER);
    
  }

  private String safe(String s) {
    return s != null ? s : "N/A";
  }

  private ImageIcon scaleIcon(ImageIcon icon, int width, int height) {
    Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return new ImageIcon(img);
  }
  
}
