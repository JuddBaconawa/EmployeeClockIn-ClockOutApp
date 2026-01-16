package displayCards;

// IMPORTS
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import components.DisplayCard;
import components.TitlePanel;
import components.dashboard.ClockPanel;
import components.dashboard.TimeStatsPanel;
import components.dashboard.ProfilePanel;
import components.dashboard.StatusIndicator;
import components.dashboard.TimeGraphPanel;
import components.dashboard.StreakPanel;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.User;


// Dashboard class
public class Dashboard extends DisplayCard {

    private StatusIndicator statusIndicator;
    private JLabel statusText;
    
    public Dashboard() {
        // Card Title
        super("Dashboard");

        setBackground(new Color(240, 235, 216));
        setLayout(new BorderLayout());

        // // === Title Panel ===
        // JPanel titlePanel = new JPanel(new BorderLayout());
        // titlePanel.setBackground(new Color(62, 92, 118));
        // titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // JLabel titleLabel = new JLabel("Dashboard");
        // titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        // titleLabel.setForeground(new Color(255, 255, 255));

        // // Status Indicator (small Colered Circle)
        // statusIndicator = new StatusIndicator();

        // statusText = new JLabel("Logged Out");
        // statusText.setFont(new Font("Arial", Font.PLAIN, 16));
        // statusText.setForeground(Color.WHITE);


        // // Group Indicator + Text
        // JPanel indicatorPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 15));
        // indicatorPanel.setOpaque(false);
        // indicatorPanel.add(statusIndicator);
        // indicatorPanel.add(statusText);

        // titlePanel.add(titleLabel, BorderLayout.WEST);
        // titlePanel.add(indicatorPanel, BorderLayout.EAST);

        // === Content Panel === 
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(2, 2, 10, 50));
        contentPanel.setOpaque(true);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 20)); // Add padding around the content panel
        // contentPanel.setBackground(new Color(0, 0 ,0));

        // instantiate clock panel
        ClockPanel clockPanel = new ClockPanel(this);

        // timestatpanel reference to clockpanel
        TimeStatsPanel timeStatsPanel = new TimeStatsPanel(clockPanel);

        // New top panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40,40));
        topPanel.setOpaque(false);
        


        //profilepanel leaves a small white square for spacing
        
        topPanel.add(wrapTopAligned(new ProfilePanel(User.getCurrentUser())));
        topPanel.add(wrapTopAligned(new TimeGraphPanel(clockPanel)));
        topPanel.add(wrapTopAligned(new TimeStatsPanel(clockPanel)));
        topPanel.add(wrapTopAligned(new ClockPanel(this)));

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        bottomPanel.setPreferredSize(new Dimension(1200, 300));
        bottomPanel.setOpaque(false);


        // Spacing
        // bottomPanel.add(new JPanel()); // Empty panel for spacing
        // bottomPanel.add(new JPanel()); // Empty panel for spacing
        // bottomPanel.add(new JPanel()); // Empty panel for spacing
        bottomPanel.add(new StreakPanel());
        bottomPanel.add(new JPanel());


        contentPanel.add(topPanel);
        contentPanel.add(bottomPanel);

        statusIndicator = new StatusIndicator();
        TitlePanel titlePanel = new TitlePanel("Dashboard", statusIndicator);

        // Add panels to layout
        add(titlePanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
    javax.swing.JFrame frame = new javax.swing.JFrame("Dashboard Test");
    frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    
    Dashboard dashboard = new Dashboard();
    frame.add(dashboard); // add your panel
    
    frame.setSize(1400, 680);
    frame.setVisible(true);
}

    
    // public void updateStatus(String status) {
    // statusIndicator.setStatus(status);
    // switch (status) {
    //     case "in":
    //         statusText.setText("Clocked In");
    //         break;
    //     case "break":
    //         statusText.setText("On Break");
    //         break;
    //     case "out":
    //     default:
    //         statusText.setText("Clocked Out");
    //     }
    // }

    private JPanel wrapTopAligned(JPanel inner) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.add(inner, BorderLayout.NORTH);
        return wrapper;
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
