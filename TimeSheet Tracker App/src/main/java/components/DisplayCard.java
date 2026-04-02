package components;
// IMPORTS

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class DisplayCard extends JPanel {
    
    public DisplayCard(String name) {
        setName(name);
        
        // Styling
        setBackground(new Color(240, 235, 216));
        setBounds(200, 120, 1200, 700);         //location and size of a card
        // setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding around the card

        // Layout
        setLayout(new BorderLayout());

        // Set Preferred size hint
        setPreferredSize(new Dimension(1200, 680));
        
  
    }
}