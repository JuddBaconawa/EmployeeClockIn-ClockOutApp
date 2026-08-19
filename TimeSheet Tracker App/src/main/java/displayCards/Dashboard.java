package displayCards;

// IMPORTS
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import components.DisplayCard;
import components.TitlePanel;
import components.dashboard.AveragePanel;
import components.dashboard.ClockPanel;
import components.dashboard.ProfilePanel;
import components.dashboard.StatusIndicator;
import components.dashboard.StatusManager;
import components.dashboard.TimeGraphPanel;
import components.dashboard.TimeStatsPanel;
import models.User;


// Dashboard class
public class Dashboard extends DisplayCard {

    private StatusIndicator statusIndicator;
    private JLabel statusText;
    private TitlePanel titlePanel;
    private StatusManager statusManager;
    
    public Dashboard(Connection conn, User user, StatusManager statusManager) {
        
        // Card Title
        super("Dashboard");

        // statusManager declared to current statusManager
        this.statusManager = statusManager;

        setBackground(new Color(62, 92, 118));
        setLayout(new BorderLayout());

        // Initialize status indicator
        statusIndicator = new StatusIndicator();
        statusManager.register(statusIndicator);

        // === Title Panel ===
        // Create title panel with status indicator above
        titlePanel = new TitlePanel("Dashboard", statusIndicator);
        titlePanel.setBackgroundColor(new Color(75, 23, 44));
        add(titlePanel, BorderLayout.NORTH);


        // === Content Panel === 
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(2, 2, 10, 50));
        contentPanel.setOpaque(true);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 20));

        // instantiate clock panel
        ClockPanel clockPanel = new ClockPanel(this, conn, user);

        // timestatpanel reference to clockpanel
        TimeStatsPanel timeStatsPanel = new TimeStatsPanel(clockPanel);

        // New top panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40,40));
        topPanel.setOpaque(false);
        


        //profilepanel leaves a small white square for spacing
        
        topPanel.add(wrapTopAligned(new ProfilePanel(user)));

        // TimeGraphPanel needs to be created before TimeStatsPanel so it can register as a listener for time updates
        TimeGraphPanel timeGraphPanel = new TimeGraphPanel(clockPanel);

        // Register TimeGraphPanel to receive time updates from ClockPanel
        clockPanel.setTimeUpdateListener(timeGraphPanel); 

        // Register TimeGraphPanel to receive time updates from ClockPanel
        topPanel.add(wrapTopAligned(timeGraphPanel));
        
        topPanel.add(wrapTopAligned(timeStatsPanel));
        topPanel.add(wrapTopAligned(clockPanel));   // clock panel is last to ensure timeStatsPanel can reference it without null issues

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        bottomPanel.setPreferredSize(new Dimension(1200, 300));
        bottomPanel.setOpaque(false);

        // add streak panel to the bottom panel
        // bottomPanel.add(new StreakPanel());
        
        // pass connection and the logged in user id to average panel
        bottomPanel.add(new AveragePanel(conn, user.getUserId()));

        // add content panels to the main content panel
        contentPanel.add(topPanel);
        contentPanel.add(bottomPanel);



        // Add panels to layout
        // add(titlePanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

    }

    // wraps a panel to align it to the top
    private JPanel wrapTopAligned(JPanel inner) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.add(inner, BorderLayout.NORTH);
        return wrapper;
    }

    // updates StatusManager for clock in, clockout, and breaks
    public void updateStatus(String status) {
        statusManager.updateStatus(status);
    }
}
