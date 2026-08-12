package displayCards;

// IMPORTS
import components.dashboard.StatusIndicator;
import components.dashboard.StatusManager;
import components.TitlePanel;

import javax.swing.JLabel;



// Admin Dashboard class
public class AdminDashboard {

    private StatusIndicator statusIndicator;
    private JLabel statusText;
    private TitlePanel titlePanel;
    private StatusManager statusManager;
    
    public AdminDashboard(StatusManager statusManager) {

        // card title
        super("Admin Dashboard");

        // statusManager declared to current statusManager
        this.statusManager = statusManager;

    }
}
