// Package: components.dashboard for functionality related to the dashboard
package components.dashboard;

// AWT imports for layout and colors
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import displayCards.Dashboard;

public class ClockPanel extends JPanel {

    private JLabel statusLabel;

    private long clockInTime = 0;
    private long breakStartTime = 0;
    private long totalBreakMillis = 0;

    private boolean onBreak = false;

    private Dashboard dashboard;

    private Map<LocalDate, Long> dailyWorkedTime = new HashMap<>();

    // Buttons
    private JButton clockInButton;
    private JButton clockOutButton;
    private JButton breakButton;

    private TimeUpdateListener listener;

    private final Connection conn;
    private final int userId;

    // Timer for UI updates
    private Timer uiTimer;

    public ClockPanel(Dashboard dashboard, Connection conn, int userId) {

        // Dashboard reference for updating status across the dashboard
        this.dashboard = dashboard;
        this.conn = conn;
        this.userId = userId;

        // UI setup
        setLayout(new BorderLayout(10, 10));
        setPreferredSize(new Dimension(150, 150));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createTitledBorder("Time Tracking"));

        // Initialize buttons and status label
        clockInButton = new JButton("Clock In");
        clockOutButton = new JButton("Clock Out");
        breakButton = new JButton("Start Break");

        // Status label to show current status and worked time
        statusLabel = new JLabel("Status: Off the clock");

        // =========================
        // CLOCK IN
        // =========================
        // Clock in button
        clockInButton.addActionListener(e -> {

            clockInTime = System.currentTimeMillis();

            // Reset break tracking
            totalBreakMillis = 0;
            breakStartTime = 0;

            statusLabel.setText("Status: Clocked In");

            dashboard.updateStatus("in");
            updateButtonState("in");
        });

        // =========================
        // CLOCK OUT
        // =========================
        // Clock out button
        clockOutButton.addActionListener(e -> {

            long sessionWorkedMillis =
                    System.currentTimeMillis()
                    - clockInTime
                    - totalBreakMillis;

            LocalDate today = LocalDate.now();

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

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());

        buttonPanel.add(clockInButton);
        buttonPanel.add(clockOutButton);
        buttonPanel.add(breakButton);

        // Add components
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

        return String.format(
                "%02d:%02d:%02d",
                hours,
                minutes,
                seconds
        );
    }

    // Update button states
    private void updateButtonState(String status) {

        switch (status) {

            case "in":

                clockInButton.setEnabled(false);
                clockOutButton.setEnabled(true);
                breakButton.setEnabled(true);

                break;

            case "out":

                clockInButton.setEnabled(true);
                clockOutButton.setEnabled(false);

                breakButton.setEnabled(false);
                breakButton.setText("Start Break");

                break;

            case "break":

                clockInButton.setEnabled(false);
                clockOutButton.setEnabled(true);

                breakButton.setEnabled(true);
                breakButton.setText("End Break");

                break;

            default:

                clockInButton.setEnabled(true);
                clockOutButton.setEnabled(false);

                breakButton.setEnabled(false);
                breakButton.setText("Start Break");

                break;
        }
    }


    // Method to calculate total worked milliseconds for the current day, including active session
    public long getDailyWorkedMillis() {

        LocalDate today = LocalDate.now();

        long saved =
                dailyWorkedTime.getOrDefault(today, 0L);

        if (clockInTime > 0) {

            long currentSession =
                    System.currentTimeMillis()
                    - clockInTime
                    - totalBreakMillis;

            return saved + currentSession;

        } else {

            // No active session
            return saved;
        }
    }

    public long getWeeklyWorkedMillis() {

        LocalDate today = LocalDate.now();

        // Monday as start of week
        LocalDate startOfWeek =
                today.with(java.time.DayOfWeek.MONDAY);

        return dailyWorkedTime.entrySet()
                .stream()
                .filter(entry ->
                        !entry.getKey().isBefore(startOfWeek)
                )
                .mapToLong(Map.Entry::getValue)
                .sum();
    }

    public long getMonthlyWorkedMillis() {

        LocalDate today = LocalDate.now();

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