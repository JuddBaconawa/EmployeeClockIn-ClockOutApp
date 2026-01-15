// PACKAGES
package components.dashboard;

// IMPORTS
import javax.swing.BorderFactory;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;


// CLASS
public class TitlePanel extends JPanel{
  
    private JLabel statusText;
    private JLabel titleText;
    private StatusIndicator statusIndicator;


    public TitlePanel(String titleText) {

        setLayout(new BorderLayout());
        setBackground(new Color(62, 92, 118));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        titleText = new JLabel(titleText);

        JPanel titleContainer = new JPanel(new BorderLayout());

    }
}
