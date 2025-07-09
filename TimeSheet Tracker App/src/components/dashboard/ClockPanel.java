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


    public ClockPanel(Dashboard dashboard) {
        this.dashboard = dashboard;

        setLayout(new BorderLayout(10, 10));
        setPreferredSize(new Dimension(300, 150));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createTitledBorder("Time Tracking"));

        JButton clockInButton = new JButton("Clock In");
        JButton clockOutButton = new JButton("Clock Out");
        JButton breakButton = new JButton("Start Break");

        statusLabel = new JLabel("Status: Off the clock");

        clockInButton.addActionListener(e -> {
            clockInTime = System.currentTimeMillis();
            statusLabel.setText("Status: Clocked In");
            dashboard.updateStatus("in");
        });

        clockOutButton.addActionListener(e -> {
            long workedMillis = System.currentTimeMillis() - clockInTime - totalBreakMillis;
            statusLabel.setText("Worked: " + workedMillis / 1000 + "s");
            clockInTime = 0;
            totalBreakMillis = 0;
            breakStartTime = 0;
            onBreak = false;
            dashboard.updateStatus("out");
        });

        breakButton.addActionListener(e -> {
            if (onBreak = !onBreak) {
                // Starting break
                breakStartTime = System.currentTimeMillis();
                totalBreakMillis += breakStartTime - clockInTime;
                breakButton.setText("End Break");
                statusLabel.setText("On Break");
                dashboard.updateStatus("break");
            } else {
                // Ending break
                long breakEndTime = System.currentTimeMillis();
                totalBreakMillis += breakEndTime - breakStartTime;
                onBreak = false;
                breakButton.setText("Start Break");
                statusLabel.setText("Clocked In");
                dashboard.updateStatus("in");
            }
            
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(clockInButton);
        buttonPanel.add(clockOutButton);
        buttonPanel.add(breakButton);

        add(buttonPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }

    private String formatTime(long millis) {
        long seconds = (millis / 1000) % 60;
        long minutes = (millis / (1000 * 60)) % 60;
        long hours = (millis / (1000 * 60 * 60)) % 24;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}

