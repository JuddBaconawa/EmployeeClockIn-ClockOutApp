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
    setLayout(null);
    setBackground(Color.GREEN);
    setBounds(0, 0, 200, getHeight());
    setBorder(BorderFactory.createBevelBorder(5, Color.GRAY, Color.WHITE));
    JLabel label = new JLabel("TEST MENU");
    label.setBounds(20, 20, 100, 30);
    add(label);
}
    
  
}