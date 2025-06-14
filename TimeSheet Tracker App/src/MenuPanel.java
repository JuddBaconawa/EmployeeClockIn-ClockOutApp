// PACKAGE

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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
// import javax.swing.border.Border;
import javax.swing.SwingConstants;

import org.w3c.dom.events.MouseEvent;

import auth.LoginForm;

// Main Class
public class MenuPanel extends JPanel {

    // Constructor - used to initialize an object (the Menu panel)
    public MenuPanel(JFrame parentFrame) {
        // Set the properties of the panel
        setLayout(new GridLayout(12, 1, 0, 5));
        setBackground(new Color(29, 46, 68));
        setBounds(0, 0, 200, 800);

        // ============== LOGO =============
        JLabel logoLabel = new JLabel("\" Brand Logo \"");
        logoLabel.setIcon(new ImageIcon("path/to/logo.png")); // Set your logo path here
        logoLabel.setBounds(20, 40, 160, 60);
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 3),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        // ============== Menu Header =============
        JLabel menuItemLabel = new JLabel("Menu Items");
        menuItemLabel.setFont(new Font("null", Font.BOLD, 16));
        menuItemLabel.setForeground(new Color(251, 160, 157));
        menuItemLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // ============== Buttons ==============
        addButton("Home");

        // ============== Logout Button ==============

        // ============== Exit Button ===============

        // ============== Add All Components ==============
        add(logoLabel);
        add(Box.createVerticalStrut(20));
        add(menuItemLabel);
        add(Box.createVerticalStrut(10));
        add(logoutButton);
        add(exitButton);
    }

    private void addButton(String title) {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(29, 45, 68));
        panel.setPreferredSize(new Dimension(200, 40));

        JLabel label = new JLabel(title);
        label.setFont(new Font("null", Font.BOLD, 16));
        label.setForeground(new Color(251, 160, 157));
        label.setBounds(20, 0, 140, 40);
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
              g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
              super.paintComponent(g2d);
              g2d.dispose();
            }
        }
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