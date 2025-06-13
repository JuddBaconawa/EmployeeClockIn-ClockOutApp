//IMPORTS
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;



// Home Class
public class Home extends DisplayCard {
    public Home() {

      // Card Title
      super("Home");
      setLayout(null);
      setBackground(new Color(116, 140, 171));
      setBounds(200, 120, 1000, 680);
      // additional initialization if needed



    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
      super.paintComponent(g);
      g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 32));
      g.setColor(java.awt.Color.LIGHT_GRAY);
      g.drawString("Home", 30, 60); // x, y
    }


    public static void main(String[] args) {
      //Home instance created for testing
      Home home = new Home();

      javax.swing.JFrame frame = new javax.swing.JFrame("Home Example");
      frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
      frame.add(home);
      frame.setSize(1000, 680);
      frame.setVisible(true);

    }
}  