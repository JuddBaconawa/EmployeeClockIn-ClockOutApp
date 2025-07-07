package components;
// IMPORTS

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class DisplayCard extends JPanel {
    
    public DisplayCard(String name) {
        setName(name);
        
        // Styling
        setBackground(new Color(240, 235, 216));
        setBounds(200, 120, 1200, 680);         //location and size of a card

        // Layout
        setLayout(new BorderLayout());

        // Set Preferred size hint
        setPreferredSize(new Dimension(1200, 680));
        
  
    }
}