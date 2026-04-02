package components.dashboard;

import javax.swing.*;
import java.awt.*;

public class TimeGraphPanel extends JPanel {

    private String[] modes = {"Daily", "Weekly", "Monthly"};
    private int currentMode = 0;

    private ClockPanel clockPanel;

    // Default assumptions
    private long totalMillis = 8 * 60 * 60 * 1000; // 8h in ms


    // Constructor - going to be changed into a exploding pie-donut chart
    public TimeGraphPanel(ClockPanel clockPanel) {
        this.clockPanel = clockPanel;

        setBorder(BorderFactory.createTitledBorder("Time Graph"));
        setPreferredSize(new Dimension(150, 150));
        setOpaque(false);

        // Optional: right-click or future button for cycling
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cycleMode();
            }
        });
    }

    public void cycleMode() {
        currentMode = (currentMode + 1) % modes.length;
        repaint();
    }

    private long getWorkedMillis() {
        switch (modes[currentMode]) {
            case "Daily":
                return clockPanel.getDailyWorkedMillis();
            case "Weekly":
                return clockPanel.getWeeklyWorkedMillis();
            case "Monthly":
                return clockPanel.getMonthlyWorkedMillis();
            default:
                return 0;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        long workedMillis = getWorkedMillis();
        long regularMillis = Math.min(workedMillis, totalMillis);
        long overtimeMillis = Math.max(0, workedMillis - totalMillis);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int size = Math.min(getWidth(), getHeight()) - 20;
        int x = (getWidth() - size) / 2;
        int y = (getHeight() - size) / 2;

        // Background Circle
        g2.setColor(new Color(230, 230, 230));
        g2.fillOval(x, y, size, size);

        // Green Arc: Regular hours
        int angleWorked = (int) ((regularMillis / (double) totalMillis) * 360);
        g2.setColor(new Color(76, 175, 80));
        g2.fillArc(x, y, size, size, 90, -angleWorked);

        // Red Arc: Overtime
        if (overtimeMillis > 0) {
            int angleOvertime = (int) ((overtimeMillis / (double) totalMillis) * 360);
            g2.setColor(new Color(244, 67, 54));
            g2.fillArc(x, y, size, size, 90 - angleWorked, -angleOvertime);
        }

        // Inner circle (Donut effect)
        int innerSize = size / 3;
        int innerX = x + (size - innerSize) / 2;
        int innerY = y + (size - innerSize) / 2;
        g2.setColor(getBackground());
        g2.fillOval(innerX, innerY, innerSize, innerSize);

        // Center text: total time
        String timeText = formatTime(workedMillis);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics fm = g2.getFontMetrics();
        int tx = getWidth() / 2 - fm.stringWidth(timeText) / 2;
        int ty = getHeight() / 2 + fm.getAscent() / 2 - 5;
        g2.setColor(Color.DARK_GRAY);
        g2.drawString(timeText, tx, ty);

        // Mode label under circle
        String modeText = modes[currentMode];
        g2.setFont(new Font("Arial", Font.BOLD, 25));
        FontMetrics mf = g2.getFontMetrics();
        int mx = getWidth() / 2 - mf.stringWidth(modeText) / 2;
        g2.drawString(modeText, mx, getHeight() - 10);
    }

    private String formatTime(long millis) {
        long seconds = (millis / 1000) % 60;
        long minutes = (millis / (1000 * 60)) % 60;
        long hours = (millis / (1000 * 60 * 60));
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
