// PACKAGE
package displayCards;

// IMPORTS
import components.DisplayCard;
import components.dashboard.StatusIndicator;
import components.dashboard.StatusManager;
import components.TitlePanel;

// dao imports
import dao.ProjectDAO;

// awt imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

// model imports
import models.User;


// swing imports
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

// sql imports
import java.sql.Connection;






// Admin Dashboard class
public class AdminDashboard extends DisplayCard{

    private StatusIndicator statusIndicator;
    private JLabel statusText;
    private TitlePanel titlePanel;
    private StatusManager statusManager;
    
    public AdminDashboard (Connection conn, User user, StatusManager statusManager) {

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

        // Load real number of projects visible to this admin
        ProjectDAO projectDAO = new ProjectDAO(conn);   // ProjectDAO instance
        //  get the number of projects for the user
        int projectCount = projectDAO.getProjectsForUser(user).size();
        //  message label for the content panel
        JLabel message = new JLabel(
                "Team overview and management tools",
                SwingConstants.CENTER
        );
        // set font for the message label
        message.setFont(new Font("Arial", Font.BOLD, 24));
        // set color for the message label
        message.setForeground(new Color(75, 24, 55));
        
        // content panel added to the center
        contentPanel.add(message, BorderLayout.CENTER);

        // set border for the content panel
        contentPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // add content panel to the center of the AdminDashboard
        add(contentPanel, BorderLayout.CENTER);


    }
}
