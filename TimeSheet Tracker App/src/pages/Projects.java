// PACKAGES
package pages;

// IMPORTS
import  components.DisplayCard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;



public class Projects extends DisplayCard {

  public Projects() {

    // Card Title
    super("Projects");
    // setLayout(null);
    setBounds(200, 120, 1200, 680);
    setBackground(new Color(62, 92, 118));

    // Displaycard Title that displays
    JPanel titlePanel = new JPanel();
    titlePanel.setBackground(new Color(62, 92, 118));
    titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel titleLabel = new JLabel("Projects");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
    titleLabel.setForeground(Color.DARK_GRAY);
    titlePanel.add(titleLabel);
    
    // this.setLayout(new BorderLayout());
    this.add(titlePanel);

    // Projects panel bar
    JPanel projectsPanel = new JPanel();
    projectsPanel.setBounds(25, 70, 1150, 20);
    // projectsPanel.setBorder(BorderFactory.createEtchedBorder(5, Color.GRAY, Color.LIGHT_GRAY));

    

    // =================== Add to Projects =======================
    add(projectsPanel);

    

  }
  
}
