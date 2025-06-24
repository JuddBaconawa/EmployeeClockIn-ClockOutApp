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
        setLayout(new GridLayout(12, 1, 2, 5));
        setBackground(new Color(13, 19, 33));
        setBounds(0, 0, 200, getHeight());
        
        
        // JLabel label = new JLabel("TEST MENU");
        // label.setBounds(20, 20, 100, 30);    
        // add(label);

        // ============== LOGO =============
        JPanel logoPanel = new JPanel();
        logoPanel.setSize(getWidth(), getHeight());
        logoPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4));

        JLabel logoLabel = new JLabel("\" Brand Logo \"");
        logoLabel.setBounds(0 , 5, 150, 150);
        logoLabel.setForeground(new Color(251, 160, 157));

        logoPanel.add(logoLabel);

        // ============== Add Components =============
        
        add(Box.createVerticalStrut(1));
        add(logoPanel);


    }
    
  
}