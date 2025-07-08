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

    public TimeStatsPanel() {
        setLayout(new BorderLayout(10, 10));
        setPreferredSize(new Dimension(300, 150));
        setBackground(new Color(255, 255, 255));
        setBorder(BorderFactory.createTitledBorder("Time Stats"));

        // Top: Toggle Button
        JButton toggleButton = new JButton("Switch View");
        toggleButton.addActionListener(e -> cycleMode());

        modeLabel = new JLabel("Mode: " + modes[currentMode]);
        regularHoursLabel = new JLabel("Regular Hours: 0");
        overtimeLabel = new JLabel("Overtime: 0");
        remainingHoursLabel = new JLabel("Remaining: 0");

        JPanel center = new JPanel(new GridLayout(3, 1));
        center.add(regularHoursLabel);
        center.add(overtimeLabel);
        center.add(remainingHoursLabel);

        add(toggleButton, BorderLayout.NORTH);
        add(modeLabel, BorderLayout.WEST);
        add(center, BorderLayout.CENTER);

    }

    private void cycleMode() {
      currentMode = (currentMode + 1) % modes.length;
      modeLabel.setText("Mode: " + modes[currentMode]);
      // TODO: Update stats for the selected mode
    }
  
}
