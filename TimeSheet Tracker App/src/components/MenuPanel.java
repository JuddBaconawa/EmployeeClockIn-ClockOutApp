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
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        
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
        menuItemLabel.setFont(new Font("null", Font.BOLD, 15));
        menuItemLabel.setAlignmentX(BOTTOM_ALIGNMENT);
        menuItemLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // ============ Buttons =============
        JButton logoutButton = createRoundedButton("Logout", new Color(240, 235, 216));
        logoutButton.addActionListener(e -> {
            // Action to perform on logout
            parentFrame.dispose(); // Close the current frame
            new LoginForm().setVisible(true); // Open the login form
        });

        // ============== Exit Button ===============
        JButton exitButton = createRoundedButton("Exit", new Color(240, 235, 216));
        exitButton.addActionListener(e -> parentFrame.dispose());


        // ============== Add Components =============
        
        add(Box.createVerticalStrut(20));

        add(logoPanel);
        add(Box.createVerticalStrut(20));
        add(menuItemLabel);
        add(Box.createVerticalStrut(10));

        // ============== Add Menu Items =============
        addButton("Home");
        addButton("Dashboard");
        addButton("Timelog");
        addButton("Profile");

        add(Box.createVerticalStrut(10));
        add(logoutButton);
        add(exitButton);

        add(Box.createVerticalStrut(10));


    }

    private void addButton(String title) {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(29, 45, 68));
        panel.setPreferredSize(new Dimension(150, 20));
        // menuBar.add(logoPanel);
        JLabel label = new JLabel(title);
        label.setFont(new Font("null", Font.BOLD, 15));
        label.setForeground(new Color(251, 160, 157));
        label.setBounds(20, 0, 140, 40);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(label);

        panel.addMouseListener(highlightEffect(panel));

        add(panel);

    }

    private JButton createRoundedButton(String text, Color bg) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
              Graphics2D g2d = (Graphics2D) g.create();
              g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
              g2d.setColor(bg);
              g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
              super.paintComponent(g2d);
              g2d.dispose();
            }
        };
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setForeground(new Color(240, 235, 216));
        button.setFont(new Font("null", Font.BOLD, 24));
        button.setForeground(new Color(251, 160, 157));
        button.setVerticalAlignment(SwingConstants.CENTER);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        // button.setHorizontalAlignment(SwingConstants.CENTER);
        return button;
    }

    private MouseAdapter highlightEffect(JPanel panel) {
      return new MouseAdapter() {
          Color original = panel.getBackground();
          public void mouseEntered(MouseEvent e) {
              panel.setBackground(new Color(13, 19, 33));
          }

          public void mouseExited(MouseEvent e) {
              panel.setBackground(new Color(29, 45, 68));
          }

          public void mousePressed(MouseEvent e) {
              panel.setBackground(new Color(180, 180, 180));             
          }

          public void mouseReleased(MouseEvent e) {
              panel.setBackground(original);
          }
      };

    }


    
  
}