package pages;

// IMPORTS
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import components.DisplayCard;


// Dashboard class
public class Dashboard extends DisplayCard {
    
    public Dashboard() {
        // Card Title
        super("Dashboard");

        setBackground(new Color(240, 235, 216));
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
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
