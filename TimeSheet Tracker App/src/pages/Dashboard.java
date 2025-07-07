package pages;
// IMPORTS
import java.awt.Color;

import components.DisplayCard;

// Dashboard class
public class Dashboard extends DisplayCard {
    
    public Dashboard() {
        // Card Title
        super("Dashboard");
        
        setBackground(new Color(240, 235, 216));

        
    }
  
    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;
        g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                             java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 32));
        g2d.setColor(java.awt.Color.DARK_GRAY);
        g2d.drawString("Dashboard", 30, 60);
    }

    // public static void main(String[] args) {
    //     // Create an instance of Dashboard to test
    //     Dashboard dashboard = new Dashboard();
    //     // You can add the dashboard to a frame or panel to visualize it
    //     // For example:
    //     javax.swing.JFrame frame = new javax.swing.JFrame("Dashboard Example");
    //     frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    //     frame.add(dashboard);
    //     frame.setSize(1400, 680);
    //     frame.setVisible(true);
    // }

}
