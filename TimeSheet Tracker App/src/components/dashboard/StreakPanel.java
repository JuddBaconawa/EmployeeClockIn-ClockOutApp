package components.dashboard;

import javax.swing.*;
import java.awt.*;

public class StreakPanel extends JPanel {

    private String[] metrics = {
        "Avg Hours/Workday",
        "Avg Breaks Taken",
        "Work + Break Total"
    };

    private int currentMetric = 0;

    // Simulated data
    private double[] workHours = { 6.5, 6.5, 7.7 };
    private double[] breakHours = { 0.0, 1.2, 0.7 };  // relevant only for break/total
    private final double maxHours = 12.0;

    public StreakPanel() {
        setBorder(BorderFactory.createTitledBorder("Streak Overview"));
        setOpaque(false);
        setPreferredSize(new Dimension(800, 300));

        // left Panel: Graph
        JPanel graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawDonutGraph(g);
            }
        };

        graphPanel.setPreferredSize(new Dimension(400, 300));
        graphPanel.setOpaque(false);

        // Right Panel: Calendar
        JPanel calendarPanel = new JPanel(new BorderLayout());
        calendarPanel.setBorder(BorderFactory.createTitledBorder("Streak Calendar"));
        calendarPanel.setOpaque(false);
        calendarPanel.add(new CalendarPanel(), BorderLayout.EAST); 

        // Add to main layout
        add(graphPanel, BorderLayout.WEST);
        add(calendarPanel, BorderLayout.CENTER);

        // Click to cycle metrics
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                currentMetric = (currentMetric + 1) % metrics.length;
                repaint();
            }
        });
    }

    
    protected void drawDonutGraph(Graphics g) {
  

        double work = workHours[currentMetric];
        double breakTime = breakHours[currentMetric];
        double total = work + breakTime;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int size = 200;
        int x = (getWidth() - size) / 2;
        int y = 40;

        // Outer background ring (max)
        g2.setColor(new Color(220, 220, 220));
        g2.fillOval(x, y, size, size);

        // Outer arc = Total (Work + Break)
        int totalAngle = (int) ((total / maxHours) * 360);
        g2.setColor(new Color(33, 150, 243)); // Blue
        g2.fillArc(x, y, size, size, 90, -totalAngle);

        // Inner arc = Break Time
        int innerSize = (int) (size * 0.65);
        int innerX = x + (size - innerSize) / 2;
        int innerY = y + (size - innerSize) / 2;

        g2.setColor(new Color(255, 235, 59)); // Yellow
        int breakAngle = (int) ((breakTime / maxHours) * 360);
        g2.fillArc(innerX, innerY, innerSize, innerSize, 90, -breakAngle);

        // Center donut hole
        int centerSize = innerSize / 2;
        int centerX = x + (size - centerSize) / 2;
        int centerY = y + (size - centerSize) / 2;
        g2.setColor(getBackground());
        g2.fillOval(centerX, centerY, centerSize, centerSize);

        // Text
        g2.setFont(new Font("Arial", Font.BOLD, 18));
        String valueStr = String.format("%.2f h", total);
        FontMetrics fm = g2.getFontMetrics();
        int tx = getWidth() / 2 - fm.stringWidth(valueStr) / 2;
        int ty = y + size / 2 + fm.getAscent() / 2 - 5;
        g2.setColor(Color.DARK_GRAY);
        g2.drawString(valueStr, tx, ty);

        // Label under graph
        g2.setFont(new Font("Arial", Font.BOLD, 18));
        String label = metrics[currentMetric];
        FontMetrics lf = g2.getFontMetrics();
        int lx = getWidth() / 2 - lf.stringWidth(label) / 2;
        g2.drawString(label, lx, y + size + 30);

    }    
}