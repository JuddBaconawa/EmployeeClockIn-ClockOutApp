// PACKAGES
package components;

// IMPORTS
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;


import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import models.User;


public class TopInfoPanel extends JPanel {
    public JLabel titleLabel;

    public TopInfoPanel(User user) {

        setLayout(new FlowLayout(4, 15, 30));
        setBackground(new Color(62, 92, 118));
        setBounds(200, 0, 1000, 120); // Example bounds, adjust as neede
        // setBorder(new MatteBorder(3, 3, 0, 3, Color.WHITE));
        


    }

    

}
