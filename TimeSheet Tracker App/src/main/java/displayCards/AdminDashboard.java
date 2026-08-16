// PACKAGE
package displayCards;

// IMPORTS
import components.DisplayCard;
import components.dashboard.StatusIndicator;
import components.dashboard.StatusManager;
import components.TitlePanel;



// awt imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;


// swing imports
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;




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

        setLayout(new BorderLayout());

        // initialize status indicator
        statusIndicator = new StatusIndicator();
        statusManager.register(statusIndicator);

        // === Title Panel ===
        // Create title panel with status indicator above
        titlePanel = new TitlePanel("Admin Dashboard", statusIndicator);
        titlePanel.setBackgroundColor(new Color(75, 24, 55));
        add(titlePanel, BorderLayout.NORTH);

        // temporary content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(250, 235, 40));

        //  message label for the content panel
        JLabel message = new JLabel(
                "Team overview and management tools",
                SwingConstants.CENTER
        );
        message.setFont(new Font("Arial", Font.BOLD, 24));
        contentPanel.add(message, BorderLayout.CENTER);

        // add content panel to the center of the AdminDashboard
        
        add(contentPanel, BorderLayout.CENTER);


    }
}
