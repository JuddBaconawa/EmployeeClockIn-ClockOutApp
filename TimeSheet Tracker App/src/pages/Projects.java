// PACKAGES
package pages;

// IMPORTS
import  components.DisplayCard;
import java.awt.Color;
import javax.swing.JPanel;

public class Projects extends DisplayCard {

  public Projects() {

    // Card Title
    super("Projects");
    setLayout(null);
    setBounds(200, 120, 1200, 680);
    setBackground(new Color(255, 255, 255));


    // Projects panel
    JPanel projectsPanel = new JPanel();
    projectsPanel.setBounds(5, 10, 990, 20);
    projectsPanel.setBorder(new Border());

    

  }
  
}
