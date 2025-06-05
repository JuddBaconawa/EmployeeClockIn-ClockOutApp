//IMPORTS
import java.awt.Color;

import javax.swing.JPanel;



// Home Class
public class Home extends DisplayCard {
    public Home() {
      super("Home");
      setLayout(null);
      setBackground(new Color(0, 40, 0));
      setBounds(200, 120, 1000, 680);
      // additional initialization if needed
    }


    public static void main(String[] args) {
      //Home instance created for testing
      Home home = new Home();

      javax.swing.JFrame frame = new javax.swing.JFrame("Home Example");
      setDefaultOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
      frame.add(home);
      frame.setSize(1000, 680);
      frame.setVisible(true);

    }
}  