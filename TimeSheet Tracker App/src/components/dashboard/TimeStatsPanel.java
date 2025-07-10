package components.dashboard;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

public class TimeStatsPanel extends JPanel {

    private String[] modes = {"Daily", "Weekly", "Monthly"};
    private int currentMode = 0;

    private JLabel modeLabel;
    private JLabel regularHoursLabel;
    private JLabel overtimeLabel;
    private JLabel remainingHoursLabel;
    private ClockPanel clockPanel;
    

    public TimeStatsPanel(ClockPanel clockPanel) {
        this.clockPanel = clockPanel;  

        // Initialize the panel
        setLayout(new BorderLayout(15, 10));
        setPreferredSize(new Dimension(300, 150));
        setBackground(new Color(255, 255, 255));
        // setOpaque(false);
        setBorder(BorderFactory.createTitledBorder("Time Stats"));

        // Top: Toggle Button
        JButton toggleButton = new JButton("Switch View");
        toggleButton.addActionListener(e -> cycleMode());

        modeLabel = new JLabel("Mode: " + modes[currentMode]);
        regularHoursLabel = new JLabel("Regular Hours: 00:00:00");
        overtimeLabel = new JLabel("Overtime: 00:00:00");
        remainingHoursLabel = new JLabel("Remaining: 00:00:00");

        JPanel center = new JPanel(new GridLayout(3, 1));
        center.add(regularHoursLabel);
        center.add(overtimeLabel);
        center.add(remainingHoursLabel);

        add(toggleButton, BorderLayout.NORTH);
        add(modeLabel, BorderLayout.WEST);
        add(center, BorderLayout.CENTER);

        updateStats();

    }

    private void cycleMode() {
      currentMode = (currentMode + 1) % modes.length;
      modeLabel.setText("Mode: " + modes[currentMode]);
      updateStats();
      // TODO: Update stats for the selected mode
    }

    private void updateStats() {
      long millis = 0;
      switch (modes[currentMode]) {
        case "Daily":
          millis = clockPanel.getDailyWorkedMillis();
          break;
        case "Weekly":
          millis = clockPanel.getWeeklyWorkedMillis();
          break;
        case "Monthly":
          millis = clockPanel.getMonthlyWorkedMillis();
          break;
      }

      long regularMillis = Math.min(millis, 8 * 60 * 60 * 1000); // 8 hours
      long overtimeMillis = Math.max(0, millis - regularMillis);
      long remainingMillis = Math.max(0, (8 * 60 * 60 * 1000) - millis);

      regularHoursLabel.setText("Regular Hours: " + formatTime(regularMillis));
      overtimeLabel.setText("Overtime: " + formatTime(overtimeMillis));
      remainingHoursLabel.setText("Remaining: " + formatTime(remainingMillis));

    }

    private String formatTime(long millis) {
      long seconds = (millis / 1000) % 60;
      long minutes = (millis / (1000 * 60)) % 60;
      long hours = (millis / (1000 * 60 * 60));
      return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

  
}
