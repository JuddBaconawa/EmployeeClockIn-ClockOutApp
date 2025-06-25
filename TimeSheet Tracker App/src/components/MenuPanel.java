// PACKAGE
package components;


// IMPORTS
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
// import javax.swing.border.Border;
import javax.swing.SwingConstants;

// import javax.swing.border.CompoundBorder;
import auth.LoginForm;
import models.User;

// Main Class
public class MenuPanel extends JPanel {

    // Constructor - used to initialize an object (the Menu panel)
    public MenuPanel(JFrame parentFrame, User user) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(13, 19, 33));
        setBounds(0, 0, 200, getHeight());
        setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 40));
        
        
        // JLabel label = new JLabel("TEST MENU");
        // label.setBounds(20, 20, 100, 30);    
        // add(label);

        // ============== LOGO =============
        JPanel logoPanel = new JPanel();
        logoPanel.setPreferredSize(new Dimension(150, 150));
        logoPanel.setMaximumSize(new Dimension(150, 150));
        logoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(0, 0, 0, 0), // 20px left margin
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4)
        ));



        JLabel logoLabel = new JLabel("\" Brand Logo \"");
        // logoLabel.setBounds(0 , 25, 120, 120);
        logoLabel.setForeground(new Color(251, 160, 157));
        logoLabel.setBackground(new Color(75, 75, 85));
        

        logoPanel.add(logoLabel);

        // ============== Menu Header =============
        JLabel menuItemLabel = new JLabel("Menu Items");
        menuItemLabel.setFont(new Font("null", Font.BOLD, 20));
        menuItemLabel.setAlignmentX(BOTTOM_ALIGNMENT);
        // menuItemLabel.setHorizontalAlignment(SwingConstants.EAST);


        // ============== Add Components =============
        
        add(Box.createVerticalStrut(25));

        add(logoPanel);
        add(Box.createVerticalStrut(20));
        add(menuItemLabel);
        add(Box.createVerticalStrut(10));


    }
    
  
}