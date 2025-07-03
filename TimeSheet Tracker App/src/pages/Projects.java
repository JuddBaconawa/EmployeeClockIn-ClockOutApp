// PACKAGES
package pages;

// IMPORTS
import  components.DisplayCard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;



public class Projects extends DisplayCard {


     public Projects() {
        super("Projects");

        setBackground(new Color(62, 92, 118));
        setLayout(new BorderLayout());

        // ==== Title Panel ====
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(new Color(62, 92, 118));
        JLabel titleLabel = new JLabel("Projects");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.DARK_GRAY);
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // ==== Content Area (center stack) ====
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(240, 240, 240)); // light bg for contrast
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); // Stack vertically

        // Add multiple panels to center stack
        for (int i = 0; i < 3; i++) {
            JPanel row = new JPanel();
            row.setBackground(new Color(200, 220, 255));
            row.setPreferredSize(new Dimension(1100, 50));
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); // make it stretch horizontally
            row.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            row.setLayout(new FlowLayout(FlowLayout.LEFT));
            row.add(new JLabel("Project row " + (i + 1)));
            contentPanel.add(row);
        }

        add(contentPanel, BorderLayout.CENTER);

        // ==== Bottom Panel (optional footer) ====
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setPreferredSize(new Dimension(1150, 40));
        footerPanel.setBackground(new Color(180, 180, 180));
        footerPanel.add(new JLabel("Footer Panel"));
        add(footerPanel, BorderLayout.SOUTH);
    }
  
}
