// Package: components.dashboard for functionality related to the dashboard
package components.dashboard;

// AWT imports for layout and colors
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

// DAO import for project-related database operations
import dao.ProjectDAO;
import dao.TimelogDAO;  // DAO for handling time log operations in the database

// User model import for user-specific data
import models.User;

// SQL imports for database connection
import java.sql.Connection;
import java.sql.SQLException;   // For handling SQL exceptions
import java.sql.Timestamp;  // For handling timestamp data from the database

// Time imports for date and time handling
import java.time.LocalDate;

// Util imports for data structures
import java.util.HashMap;
import java.util.List;  // For handling lists of projects
import java.util.Map;

// Swing imports for UI components
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;   // For dropdown selection of projects
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

// Importing the Dashboard class for interaction with the main dashboard
import displayCards.Dashboard;
import displayCards.Projects.Project;   // For handling project-related data

public class ClockPanel extends JPanel {

    //  UI Components
    private JLabel statusLabel;
    private long clockInTime = 0;
    private long breakStartTime = 0;
    private long totalBreakMillis = 0;

    // State tracking for break status
    private boolean onBreak = false;

    // Reference to the main Dashboard for updating status across the dashboard
    private final Dashboard dashboard;

    // Map to track daily worked time in milliseconds
    private Map<LocalDate, Long> dailyWorkedTime = new HashMap<>();

    // Buttons
    private JButton clockInButton;
    private JButton clockOutButton;
    private JButton breakButton;

    // ComboBox for selecting projects (if applicable)
    private JComboBox<Project> projectComboBox;

    // Currently active project for time tracking
    private Project activeProject;

    // Listener for time updates to notify other components (e.g., TimeGraphPanel)
    private TimeUpdateListener listener;

    // Database connection for persisting time tracking data
    private final Connection conn;
    private final TimelogDAO timelogDAO;    // DAO for handling time log operations in the database

    // User object representing the current user for personalized time tracking
    private final User user;

    // Timer for UI updates
    private Timer uiTimer;

    public ClockPanel(Dashboard dashboard, Connection conn, User user) {

        // Dashboard reference for updating status across the dashboard
        this.dashboard = dashboard;
        this.conn = conn;
        this.user = user;
        this.timelogDAO = new TimelogDAO(conn); // Initialize TimelogDAO for time log operations

        // UI setup
        setLayout(new BorderLayout(10, 10));
        setPreferredSize(new Dimension(300, 170));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createTitledBorder("Time Tracking"));

        // Initialize buttons and status label
        clockInButton = new JButton("Clock In");
        clockOutButton = new JButton("Clock Out");
        breakButton = new JButton("Start Break");

        // Status label to show current status and worked time
        statusLabel = new JLabel("Status: Off the clock");

        // Initialize ProjectDAO for project-related operations
        ProjectDAO projectDAO = new ProjectDAO(conn);

        // Fetch all projects for the current user from the database
        List<Project> projects = projectDAO.getProjectsForUser(user);

        // Create the project dropdown from those projects
        projectComboBox = new JComboBox<>(projects.toArray(new Project[0]));

        projectComboBox.setSelectedIndex(-1); // No project selected by default


        // =========================
        // CLOCK IN
        // =========================
        // Clock in button action listener
        clockInButton.addActionListener(e -> {

            // if statement to check if project is selected before clock in
            if (projectComboBox.getSelectedItem() == null) {
                // if no project is selected, show a warning
                statusLabel.setText("Please select a project first");


                return; // Exit the action listener without clocking in
            }

            // Set the active project to the selected project (if any) and exit the action listener
            activeProject = (Project) projectComboBox.getSelectedItem();

            // Record the clock-in time
            clockInTime = System.currentTimeMillis();

            // Reset break tracking
            totalBreakMillis = 0;
            breakStartTime = 0;

            // Update status label
            statusLabel.setText("Status: Clocked In");

            // Update dashboard status and button states
            dashboard.updateStatus("in");
            updateButtonState("in");
        });

        // =========================
        // CLOCK OUT
        // =========================
        // Clock out button
        clockOutButton.addActionListener(e -> {

            // Calculate worked time for the current session
            long sessionWorkedMillis =
                     getCurrentSessionWorkedMillis();

            // Record the clock-out time
            long clockOutTime = System.currentTimeMillis();

            // include a break that is still active when clocking out
            long breakMillisToSave = totalBreakMillis;

            // If the user is on a break when clocking out, calculate the break time up to the clock-out moment
            if (onBreak) {

                // If the user is on a break when clocking out, calculate the break time up to the clock-out moment
                breakMillisToSave += clockOutTime - breakStartTime;
            }

            // (save time) convert milliseconds to minutes for storage in the database
            int breakMinutes = (int) (breakMillisToSave / 60_000);  // Convert milliseconds to minutes


            // Save the time log to the database using TimelogDAO
            try {

                // Ensure that the active project is not null before attempting to save the time log
                timelogDAO.createTimeEntry(
                    user.getUserId(),
                    activeProject.projectId,
                    new Timestamp(clockInTime),
                    new Timestamp(clockOutTime),
                    breakMinutes
                );

            
            // catch block to handle SQL exceptions that may occur during the database operation
            } catch (SQLException sqlException) {

                // Handle SQL exception (e.g., log the error, show a message to the user)
                statusLabel.setText("Could not save time entry");
                // Log the exception for debugging purposes
                sqlException.printStackTrace();
                return; // Exit the action listener if saving fails
            }

            // Update the daily worked time map
            LocalDate today = LocalDate.now();

            // update dailyWorkedTime map
            dailyWorkedTime.put(
                    today,
                    dailyWorkedTime.getOrDefault(today, 0L)
                    + sessionWorkedMillis
            );

            // Update status label
            statusLabel.setText(
                    "Worked: "
                    + sessionWorkedMillis / 1000
                    + "s"
            );

            // Reset session
            clockInTime = 0;
            totalBreakMillis = 0;
            breakStartTime = 0;
            onBreak = false;

            // Update UI
            dashboard.updateStatus("out");
            updateButtonState("out");
        });


        // =========================
        // BREAK TOGGLE (FIXED)
        // =========================        
        // Break button
        breakButton.addActionListener(e -> {

            // Toggle break state
            if (!onBreak) {


                // Starting state - clear intent
                onBreak = true;

                // Starting break
                breakStartTime = System.currentTimeMillis();

                

                // Update UI
                breakButton.setText("End Break");
                statusLabel.setText("On Break");

                // Update dashboard status for break
                dashboard.updateStatus("break");
                updateButtonState("break");

            } else {

                // Ending break
                long breakEndTime = System.currentTimeMillis();

                // Accumulate break time
                totalBreakMillis +=
                        breakEndTime - breakStartTime;
                // Reset break start time
                onBreak = false;

                // Update UI
                breakButton.setText("Start Break");
                statusLabel.setText("Clocked In");

                // Update dashboard status back to clocked in
                dashboard.updateStatus("in");
                updateButtonState("in");
            }
        });

        // project selector panel
        JPanel projectPanel = new JPanel(new FlowLayout());
        projectPanel.add(new JLabel("Working on: "));   // Label for project selection
        projectPanel.add(projectComboBox);  // Add the project dropdown to the panel

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        

        // Add buttons to the panel
        buttonPanel.add(clockInButton);
        buttonPanel.add(clockOutButton);
        buttonPanel.add(breakButton);



        // Add components
        add(projectPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        // Initial button state
        updateButtonState("out");

        // UI timer to refresh dashboard
        uiTimer = new Timer(1000, e -> {

            // Every second force UI panels to update
            dashboard.repaint();

            // Notify listener (e.g., TimeGraphPanel) to update when time changes
            if (listener != null) {
                // Notify the listener to update its display based on the new time
                listener.onTimeUpdate();
            }

        });

        // Start the timer to update the UI every second
        uiTimer.start();

    }

    // Method to set the time update listener (e.g., TimeGraphPanel)
    public void setTimeUpdateListener(TimeUpdateListener listener) {
        // This allows the TimeGraphPanel to register itself to receive updates when time changes
        this.listener = listener;
    }    

    // =========================
    // FORMAT TIME (UNCHANGED)
    // =========================
    // Helper method to format milliseconds into HH:mm:ss
    private String formatTime(long millis) {

        long seconds = (millis / 1000) % 60;
        long minutes = (millis / (1000 * 60)) % 60;
        long hours = (millis / (1000 * 60 * 60)) % 24;

        // return formated string in Hours:Minutes:Seconds format with leading zeros
        return String.format(
                "%02d:%02d:%02d",
                hours,
                minutes,
                seconds
        );
    }

    // Update button states
    private void updateButtonState(String status) {

        // Enable or disable buttons based on the current status
        switch (status) {

            // User is Clocked in
            case "in":

                clockInButton.setEnabled(false);
                clockOutButton.setEnabled(true);
                breakButton.setEnabled(true);

                break;

            // User is Clocked out
            case "out":

                clockInButton.setEnabled(true);
                clockOutButton.setEnabled(false);

                breakButton.setEnabled(false);
                breakButton.setText("Start Break");

                break;

            // User is on Break
            case "break":

                clockInButton.setEnabled(false);
                clockOutButton.setEnabled(true);

                breakButton.setEnabled(true);
                breakButton.setText("End Break");

                break;

            // Default case (should not occur)
            default:

                clockInButton.setEnabled(true);
                clockOutButton.setEnabled(false);

                breakButton.setEnabled(false);
                breakButton.setText("Start Break");

                break;
        }
    }


    // added method to calculate worked time and pauses it during active break
    private long getCurrentSessionWorkedMillis() {

        // If the user is currently on a break, we need to account for the time spent on break and exclude it from the worked time calculation.
        long activeBreakMillis = 0;

        // While on break, calculate the active break time
        if (onBreak) {
            activeBreakMillis = System.currentTimeMillis() - breakStartTime;
        }

        // Calculate the total worked time for the current session, excluding breaks
        return System.currentTimeMillis()
                - clockInTime
                - totalBreakMillis
                - activeBreakMillis;

    }


    // Method to calculate total worked milliseconds for the current day, including active session
    public long getDailyWorkedMillis() {

        // Get today's date
        LocalDate today = LocalDate.now();

        // Get the saved worked time for today, defaulting to 0 if not present
        long saved =
                dailyWorkedTime.getOrDefault(today, 0L);

        // If statement for when the user is clocked in to save per session
        if (clockInTime > 0) {

            // calculate current session
            long currentSession = getCurrentSessionWorkedMillis();

            // return today's saved time plus the current active session
            return saved + currentSession;

        } else {

            // No active session
            return saved;
        }
    }

    // method to calculate total worked milliseconds for the current week
    public long getWeeklyWorkedMillis() {

        // get today's date
        LocalDate today = LocalDate.now();

        // Monday as start of week
        LocalDate startOfWeek =
                today.with(java.time.DayOfWeek.MONDAY);

        // Sum up the worked time for each day in the current week
        return dailyWorkedTime.entrySet()
                .stream()
                .filter(entry ->
                        !entry.getKey().isBefore(startOfWeek)
                )
                .mapToLong(Map.Entry::getValue)
                .sum();
    }

    // Method to calculate total worked milliseconds for the current month
    public long getMonthlyWorkedMillis() {

        // get today's date
        LocalDate today = LocalDate.now();

        // sum up the worked time for each day in the current month
        return dailyWorkedTime.entrySet()
                .stream()
                .filter(entry ->
                        entry.getKey().getMonth()
                                .equals(today.getMonth())
                        &&
                        entry.getKey().getYear()
                                == today.getYear()
                )
                .mapToLong(Map.Entry::getValue)
                .sum();
    }
}