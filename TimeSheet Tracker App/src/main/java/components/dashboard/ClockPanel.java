// Package: components.dashboard for functionality related to the dashboard
package components.dashboard;

// AWT imports for layout and colors
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
private boolean onBreak = false;
private Dashboard dashboard;
private long breakStartTime = 0;
private long totalBreakMillis = 0;
private long todayTotalMillis = 0;

private Map<LocalDate, Long> dailyWorkedTime = new HashMap<>();

// Buttons
private JButton clockInButton;
private JButton clockOutButton;
private JButton breakButton;

// Timer for UI updates (e.g., to update the status label with worked time)
private Timer uiTimer;

public ClockPanel(Dashboard dashboard) {
// dashboard reference for updating status across the dashboard
this.dashboard = dashboard;

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


// Action listeners for buttons
clockInButton.addActionListener(e -> {
clockInTime = System.currentTimeMillis();
totalBreakMillis = 0; // Reset break time on clock in
breakStartTime = 0; // Reset break start time
statusLabel.setText("Status: Clocked In");
dashboard.updateStatus("in");
updateButtonState("in");
});

// When clocking out, calculate the total worked time for the session and update the daily total
clockOutButton.addActionListener(e -> {
long sessionWorkedMillis = System.currentTimeMillis() - clockInTime - totalBreakMillis;
LocalDate today = LocalDate.now();
dailyWorkedTime.put(today, dailyWorkedTime.getOrDefault(today, 0L) + sessionWorkedMillis);

// Update status label with total worked time for the session
statusLabel.setText("Worked: " + sessionWorkedMillis / 1000 + "s");

// reset session
clockInTime = 0;
totalBreakMillis = 0;
breakStartTime = 0;
onBreak = false;
dashboard.updateStatus("out");
updateButtonState("out");

});

// Break button toggles between starting and ending a break, updating the total break time accordingly
breakButton.addActionListener(e -> {
if (onBreak = !onBreak) {
// Starting break
breakStartTime = System.currentTimeMillis();
onBreak = true;
breakButton.setText("End Break");
statusLabel.setText("On Break");
dashboard.updateStatus("break");
updateButtonState("break");
} else {
// Ending break
long breakEndTime = System.currentTimeMillis();
totalBreakMillis += breakEndTime - breakStartTime;
onBreak = false;
breakButton.setText("Start Break");
statusLabel.setText("Clocked In");
dashboard.updateStatus("in");
updateButtonState("in");
}

});


// Timer to update the status label every second with the current worked time if clocked in
JPanel buttonPanel = new JPanel(new FlowLayout());
buttonPanel.add(clockInButton);
buttonPanel.add(clockOutButton);
buttonPanel.add(breakButton);

// Add components to the panel
add(buttonPanel, BorderLayout.CENTER);
add(statusLabel, BorderLayout.SOUTH);

// Initial button states
updateButtonState("out"); // Initial state

// UI timer to update status label every second
uiTimer = new Timer(1000, e -> {

// what this means:
// every second, force UI panels to update

// If clocked in, update the status label with the current worked time
dashboard.repaint();
});

uiTimer.start();
}

// Helper method to format milliseconds into HH:mm:ss
private String formatTime(long millis) {
long seconds = (millis / 1000) % 60;
long minutes = (millis / (1000 * 60)) % 60;
long hours = (millis / (1000 * 60 * 60)) % 24;
return String.format("%02d:%02d:%02d", hours, minutes, seconds);
}

//
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

public long getDailyWorkedMillis() {
LocalDate today = LocalDate.now();
long saved = dailyWorkedTime.getOrDefault(today, 0L);
if (clockInTime > 0) {
long currentSession = System.currentTimeMillis() - clockInTime - totalBreakMillis;
return saved + currentSession;
} else {
return saved; // No active session, return total worked time today
}

}

public long getWeeklyWorkedMillis() {
// Placeholder for weekly calculation
LocalDate today = LocalDate.now();
LocalDate startOfWeek = today.with(java.time.DayOfWeek.MONDAY); // Monday as start of week

return dailyWorkedTime.entrySet().stream()
.filter(entry -> !entry.getKey().isBefore(startOfWeek))
.mapToLong(Map.Entry::getValue)
.sum(); // Add today's worked time
}

public long getMonthlyWorkedMillis() {
// Placeholder for monthly calculation
LocalDate today = LocalDate.now();

return dailyWorkedTime.entrySet().stream()
.filter(entry -> entry.getKey().getMonth().equals(today.getMonth()) &&
                entry.getKey().getYear() == today.getYear())
.mapToLong(Map.Entry::getValue)
.sum(); // Add today's worked time
}   
}