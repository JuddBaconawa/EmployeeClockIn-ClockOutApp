// Package
package components;

// IMPORTS
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Font;


import components.dashboard.StatusIndicator;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitlePanel extends JPanel{

    // Declaration
    private static StatusIndicator statusIndicator;
    private JPanel titlePanel;
    private JLabel titleLabel;

    // Title Panel Constructor
    public TitlePanel(String title, StatusIndicator statusIndicator) {
        this.statusIndicator = statusIndicator;

        // Title Panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        setBackground(new Color(66, 217, 200));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(new Color(66, 217, 200));

        add(titleLabel, BorderLayout.WEST);
        add(statusIndicator, BorderLayout.EAST);
    }

    public void setTitle(String title) {
        titleLabel.setText(title);
    }
  
}
