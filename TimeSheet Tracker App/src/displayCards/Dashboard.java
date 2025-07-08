package displayCards;

// IMPORTS
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import components.DisplayCard;
import components.dashboard.ClockPanel;
import components.dashboard.TimeStatsPanel;
import components.dashboard.ProfilePanel;


// Dashboard class
public class Dashboard extends DisplayCard {
    
    public Dashboard() {
        // Card Title
        super("Dashboard");

        setBackground(new Color(240, 235, 216));
        setLayout(new BorderLayout());

        // === Title Panel ===
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(62, 92, 118));

        JLabel titleLabel = new JLabel("Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(new Color(255, 255, 255));

        // Status Indicator (small Colered Circle)
        

        titlePanel.add(titleLabel);
        titlePanel.add(indicatorPanel);

        // === Content Panel === 
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        contentPanel.setOpaque(false);

        contentPanel.add(new TimeStatsPanel());
        contentPanel.add(new ClockPanel());


        // Add panels to layout
        add(titlePanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        

        
        

        titlePanel.add(titleLabel);
        add(titlePanel);
    }
    
    public void updateStatus(String status) {
    statusIndicator.setStatus(status);
    switch (status) {
        case "in":
            statusText.setText("Logged In");
            break;
        case "break":
            statusText.setText("On Break");
            break;
        case "out":
        default:
            statusText.setText("Logged Out");
        }
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
