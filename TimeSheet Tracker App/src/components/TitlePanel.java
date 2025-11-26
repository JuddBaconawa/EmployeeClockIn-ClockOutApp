// Package
package components;

// IMPORTS
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;


import components.dashboard.StatusIndicator;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitlePanel extends JPanel{

    // Declaration
    private JLabel titleLabel;
    private StatusIndicator statusIndicator;
    private JLabel statusText;

    // Title Panel Constructor
    public TitlePanel(String title, JComponent statusIndicator) {
        

        // Title Panel
        setLayout(new BorderLayout());
        setBackground(new Color(66, 217, 200));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // set title label + format and color (left side)
        titleLabel = new JLabel(title); 
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(new Color(255, 255, 255));
        setPreferredSize(new Dimension(0, 60)); // ensure height
        add(titleLabel, BorderLayout.WEST);

        // Add the title label to the far right
        statusIndicator = new StatusIndicator();
        statusText = new JLabel("Status");
        statusText.setFont(new Font("Arial", Font.PLAIN, 16));
        statusText.setForeground(Color.WHITE);

        
    }

    // Method to update the status indicator and status text
    public void setStatus(String status) {
        statusIndicator.setStatus(status);

        switch (status) {
            case "Break": statusText.setText("One Break");
            case "In": statusText.setText("Logged In");
            case "Out": 
            default: statusText.setText("Logged Out");
            
        }
    }

    // Method to update the title based on the DisplayCard used
    public void setTitle(String title) {
        titleLabel.setText(title);
    }
  
}
