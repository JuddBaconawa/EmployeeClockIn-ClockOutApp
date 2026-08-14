package displayCards;

// IMPORTS
import components.DisplayCard;
import components.dashboard.StatusIndicator;
import components.dashboard.StatusManager;
import components.TitlePanel;

import javax.swing.JLabel;



// Admin Dashboard class
public class AdminDashboard extends DisplayCard{

    private StatusIndicator statusIndicator;
    private JLabel statusText;
    private TitlePanel titlePanel;
    private StatusManager statusManager;
    
    public AdminDashboard (StatusManager statusManager) {

        // card title
        super("Admin Dashboard");

        // statusManager declared to current statusManager
        this.statusManager = statusManager;

        // initialize status indicator
        statusIndicator = new StatusIndicator();
        statusManager.register(statusIndicator);

        // === Title Panel ===
        // Create title panel with status indicator above
        titlePanel = new TitlePanel("Admin Dashboard", statusIndicator);
        

    }
}
