// IMPORTS

import javax.swing.JPanel;
import java.awt.Color;

public class DisplayCard extends JPanel {
    public DisplayCard(String name) {
        setName(name);
        setLayout(null);
        setBackground(new Color(240, 235, 216));
        setBounds(200, 120, 1000, 680);         //location and size of a card
        
  
    }
  }