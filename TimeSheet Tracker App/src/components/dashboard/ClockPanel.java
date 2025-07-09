package components.dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import displayCards.Dashboard;

public class ClockPanel extends JPanel {
    private JLabel statusLabel;
    private long clockInTime = 0;
    private boolean onBreak = false;
    private Dashboard dashboard;
    private long breakStartTime = 0;
    private long totalBreakMillis = 0;
    private JButton clockInButton;
    private JButton clockOutButton;
    private JButton breakButton;


    public ClockPanel(Dashboard dashboard) {
        this.dashboard = dashboard;

        setLayout(new BorderLayout(10, 10));
        setPreferredSize(new Dimension(300, 150));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createTitledBorder("Time Tracking"));

        clockInButton = new JButton("Clock In");
        clockOutButton = new JButton("Clock Out");
        breakButton = new JButton("Start Break");

        statusLabel = new JLabel("Status: Off the clock");

        clockInButton.addActionListener(e -> {
            clockInTime = System.currentTimeMillis();
            statusLabel.setText("Status: Clocked In");
            dashboard.updateStatus("in");
            updateButtonState("in");
        });

        clockOutButton.addActionListener(e -> {
            long workedMillis = System.currentTimeMillis() - clockInTime - totalBreakMillis;
            statusLabel.setText("Worked: " + workedMillis / 1000 + "s");
            clockInTime = 0;
            totalBreakMillis = 0;
            breakStartTime = 0;
            onBreak = false;
            dashboard.updateStatus("out");
            updateButtonState("out");
        });

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

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(clockInButton);
        buttonPanel.add(clockOutButton);
        buttonPanel.add(breakButton);

        add(buttonPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        updateButtonState("out"); // Initial state
    }

    private String formatTime(long millis) {
        long seconds = (millis / 1000) % 60;
        long minutes = (millis / (1000 * 60)) % 60;
        long hours = (millis / (1000 * 60 * 60)) % 24;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

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
}