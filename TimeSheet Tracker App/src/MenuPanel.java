// IMPORTS
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;

import javax.swing.JPanel;
import javax.swing.JLabel;

// Main Class
public class MenuPanel extends JPanel {

    // Constructor - used to initialize an object (the Menu panel)
    public MenuPanel() {

        setLayout(new GridLayout(12, 1, 0, 5));
        setBackground(new Color(29, 46, 68));
        setBounds(0, 0, 200, 600);

        // ============== LOGO =============
        JLabel logoLabel = new JLabel("\" Brand Logo \"");
        logoLabel.setIcon(new ImageIcon("path/to/logo.png")); // Set your logo path here
        logoLabel.setBounds(20, 40, 160, 60);

        // ============== Menu Header =============

        // ============== Buttons ==============

        // ============== Logout Button ==============

        // ============== Exit Button ===============

        // ============== Add All Components ==============
        
        
    }
  
}
