// IMPORTS
import java.awt.Color;


// Profile Class
public class Profile extends DisplayCard {
    public Profile() {
        super("Profile");
        setBackground(new Color(62, 92, 118));
  
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.setFont(new java.awt.Font("arial", java.awt.Font.BOLD, 32));
        g.setColor(java.awt.Color.LIGHT_GRAY);
        g.drawString("Profile", 30, 60);
    }
    