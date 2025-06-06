// IMPORTS
import java.awt.Color;

// Dashboard class
public class Dashboard extends DisplayCard {
    public Dashboard() {
        super("Dashboard");
        setLayout(null);
        setBackground(new Color(240, 235, 216));
        setBounds(200, 120, 1000, 680); // location and size of a card

        
    }
  
    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 32));
        g.setColor(java.awt.Color.BLACK);
        g.drawString("Dashboard", 30, 60); // x, y
    }

    public static void main(String[] args) {
        // Create an instance of Dashboard to test
        Dashboard dashboard = new Dashboard();
        // You can add the dashboard to a frame or panel to visualize it
        // For example:
        javax.swing.JFrame frame = new javax.swing.JFrame("Dashboard Example");
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.add(dashboard);
        frame.setSize(1000, 680);
        frame.setVisible(true);
    }

}
