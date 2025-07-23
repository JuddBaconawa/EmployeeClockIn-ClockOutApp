package displayCards;

// IMPORTS
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import components.DisplayCard;
import components.dashboard.ClockPanel;
import components.dashboard.TimeStatsPanel;
import models.User;
import components.dashboard.ProfilePanel;
import components.dashboard.StatusIndicator;
import components.dashboard.TimeGraphPanel;


// Dashboard class
public class Dashboard extends DisplayCard {

    private StatusIndicator statusIndicator;
    private JLabel statusText;
    
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
        statusIndicator = new StatusIndicator();
        statusText = new JLabel("Logged Out");
        statusText.setFont(new Font("Arial", Font.PLAIN, 16));
        statusText.setForeground(Color.WHITE);

        // Group Indicator + Text
        JPanel indicatorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        indicatorPanel.setOpaque(false);
        indicatorPanel.add(statusIndicator);
        indicatorPanel.add(statusText);

        titlePanel.add(titleLabel);
        titlePanel.add(indicatorPanel);

        // === Content Panel === 
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(2, 2, 25, 25));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 70, 40, 70)); // Add padding around the content panel

        // instantiate clock panel
        ClockPanel clockPanel = new ClockPanel(this);

        // timestatpanel reference to clockpanel
        TimeStatsPanel timeStatsPanel = new TimeStatsPanel(clockPanel);

        //profilepanel leaves a small white square for spacing
        contentPanel.add(new TimeGraphPanel(clockPanel));
        contentPanel.add(new ProfilePanel(User.getCurrentUser()));
        contentPanel.add(new TimeStatsPanel(clockPanel));
        contentPanel.add(new ClockPanel(this));

        // Spacing
        contentPanel.add(new JPanel()); // Empty panel for spacing
        contentPanel.add(new JPanel()); // Empty panel for spacing
        contentPanel.add(new JPanel()); // Empty panel for spacing


        // Add panels to layout
        add(titlePanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        

        
        

        titlePanel.add(titleLabel);
        // add(titlePanel);    == double error
    }
    
    public void updateStatus(String status) {
    statusIndicator.setStatus(status);
    switch (status) {
        case "in":
            statusText.setText("Clocked In");
            break;
        case "break":
            statusText.setText("On Break");
            break;
        case "out":
        default:
            statusText.setText("Clocked Out");
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
