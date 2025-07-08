package components.dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClockPanel extends JPanel {
    private JLabel statusLabel;
    private long clockInTime = 0;
    private boolean onBreak = false;

    public ClockPanel() {
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
        });

        clockOutButton.addActionListener(e -> {
            long workedMillis = System.currentTimeMillis() - clockInTime;
            statusLabel.setText("Worked: " + workedMillis / 1000 + "s");
            clockInTime = 0;
        });

        breakButton.addActionListener(e -> {
            onBreak = !onBreak;
            breakButton.setText(onBreak ? "End Break" : "Start Break");
            statusLabel.setText(onBreak ? "On Break" : "Clocked In");
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(clockInButton);
        buttonPanel.add(clockOutButton);
        buttonPanel.add(breakButton);

        add(buttonPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }
}

