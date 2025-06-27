// PACKAGE
package components;


// IMPORTS
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
// import java.awt.GridLayout;
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
        setBackground(new Color(29, 45, 68));
        setBounds(0, 0, 200, getHeight());
        setBorder(BorderFactory.createEmptyBorder(13, 0, 0, 0));
        
        
        // JLabel label = new JLabel("TEST MENU");
        // label.setBounds(20, 20, 100, 30);    
        // add(label);

        // ============== LOGO =============
        JPanel logoPanel = new JPanel();
        logoPanel.setPreferredSize(new Dimension(125, 75));
        logoPanel.setMaximumSize(new Dimension(125, 75));
        logoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(0, 0, 0, 0), // 20px left margin
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4)
        ));



        JLabel logoLabel = new JLabel("\" Brand Logo \"");
        logoLabel.setBounds(0 , 0, 100, 100);
        logoLabel.setForeground(new Color(251, 160, 157));
        logoLabel.setBackground(new Color(75, 75, 85));
        

        logoPanel.add(logoLabel);

        // ============== Menu Header =============
        JLabel menuItemLabel = new JLabel("<html><u>Menu</u></html>");
        menuItemLabel.setFont(new Font("Ariel", Font.BOLD, 35));
        menuItemLabel.setForeground(new Color(251, 160, 157));
        menuItemLabel.setAlignmentX(RIGHT_ALIGNMENT);
        // menuItemLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // ============ Buttons =============
        JButton logoutButton = createRoundedButton("", new Color(240, 235, 216));
        setForeground(new Color(29, 45, 68));
        ImageIcon logoutIcon = new ImageIcon(new ImageIcon("src/images/sign-out-icon.png").getImage().getScaledInstance(45, 35, java.awt.Image.SCALE_SMOOTH));
        logoutButton.setIcon(logoutIcon);

        logoutButton.addActionListener(e -> {
            // Action to perform on logout
            parentFrame.dispose(); // Close the current frame
            new LoginForm().setVisible(true); // Open the login form
        });

        // ============== Exit Button ===============
        JButton exitButton = createRoundedButton("", new Color(240, 235, 216));
        exitButton.setForeground(new Color(29, 45, 68));
        exitButton.addActionListener(e -> parentFrame.dispose());
        ImageIcon exitIcon = new ImageIcon(new ImageIcon("src/images/exit-icon.png").getImage().getScaledInstance(45, 35, java.awt.Image.SCALE_SMOOTH));
        exitButton.setIcon(exitIcon);


        // ============== Add Components =============
        
        add(Box.createVerticalStrut(10));
        add(logoPanel);
        add(Box.createVerticalStrut(30));
        add(menuItemLabel);
        add(Box.createVerticalStrut(10));

        // ============== Add Menu Items =============
        
        addButton("Dashboard");
        add(Box.createVerticalStrut(5));
        addButton("Timelog");
        add(Box.createVerticalStrut(5));
        addButton("Profile");
        add(Box.createVerticalStrut(5));
        addButton("Home");

        add(Box.createVerticalStrut(10));

        // --- Add the two buttons side by side ---
        JPanel buttonRow = new JPanel();
        buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.X_AXIS));
        buttonRow.setOpaque(false); // Make background transparent to match parent


        buttonRow.add(Box.createHorizontalGlue()); // Fill remaining space
        buttonRow.add(logoutButton);
        buttonRow.add(Box.createHorizontalStrut(10)); // Space between buttons
        buttonRow.add(exitButton);
        buttonRow.add(Box.createHorizontalGlue()); // Fill remaining space

        buttonRow.setAlignmentX(CENTER_ALIGNMENT); // Center the buttons in the row
        buttonRow.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add vertical padding
        add(buttonRow);

        add(Box.createVerticalStrut(10));


    }

    private void addButton(String title) {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(29, 45, 68));
        panel.setPreferredSize(new Dimension(getWidth(), 20));
        
        JLabel label = new JLabel(title);
        label.setFont(new Font("null", Font.BOLD, 20));
        label.setForeground(new Color(251, 160, 157));
        label.setBounds(10, 0, 200, 40);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setVerticalAlignment(SwingConstants.CENTER);

        panel.add(label);

        panel.addMouseListener(highlightEffect(panel));

        add(panel);

    }

    private void addButton(String title, String iconPath) {
        JButton button = createRoundedButton(title, new Color(29, 45, 68));
        button.setForeground(new Color(251, 160, 157));
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setHorizontalAlignment(SwingConstants.LEFT); // Icon left, text right

        if (iconPath != null && !iconPath.isEmpty()) {
            ImageIcon icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH));
            button.setIcon(icon);
        }

        button.setPreferredSize(new Dimension(180, 40));
        button.setMaximumSize(new Dimension(180, 40));
        button.setAlignmentX(LEFT_ALIGNMENT);

        add(button);
    }

    private JButton createRoundedButton(String text, Color bg) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
              Graphics2D g2d = (Graphics2D) g.create();
              g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
              g2d.setColor(bg);
              g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
              super.paintComponent(g);
              g2d.dispose();
            }
        };
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBackground(new Color(255, 255, 255));;
        button.setForeground(new Color(29, 45, 68));
        button.setFont(new Font("null", Font.BOLD, 24));
        // button.setForeground(new Color(251, 160, 157));
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
              panel.setBackground(new Color(62, 92, 118));             
          }

          public void mouseReleased(MouseEvent e) {
              panel.setBackground(original);
          }
      };

    }


    
  
}