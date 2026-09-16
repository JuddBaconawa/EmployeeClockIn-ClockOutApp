// PACKAGE DECLARATION
package components.dashboard;

// IMPORTS 
import dao.TimelogDAO;

// awt imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

// SQL imports
import java.sql.Connection;

// SWING imports
import javax.swing.BorderFactory;
import javax.swing.JPanel;


// AveragePanel class to display average work hours and breaks in a donut graph format
public class AveragePanel extends JPanel{

    private final Connection conn;
    private final int userId;

    // donut graph metrics
    private String[] metrics = {
        "Avg Hours/Workday",
        "Avg Breaks Taken",
        "Work + Break Total"
    };

    // Current metric index set to 0
    private int currentMetric = 0;

    // Simulated data
    private double[] workHours = { 6.5, 6.5, 7.7 };
    private double[] breakHours = { 0.0, 1.2, 0.7 };  // relevant only for break/total
    private final double maxHours = 12.0;
    private JPanel graphPanel;

    // StreakPanel constructor
    public AveragePanel(Connection conn, int userId) {

        // Initialize the connection and userId
        this.conn = conn;
        this.userId = userId;


        // Set layout and properties for the AveragePanel
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Streak Overview"));
        setOpaque(false);
        setPreferredSize(new Dimension(800, 300));

        // left Panel: Graph
        graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawDonutGraph(g, this);
            }
        };

        // set graphPanel properties
        graphPanel.setPreferredSize(new Dimension(400, 300));
        graphPanel.setOpaque(false);

        add(graphPanel, BorderLayout.WEST);

        // Right Panel: Calendar
        JPanel calendarPanel = new JPanel(new BorderLayout());
        calendarPanel.setBorder(BorderFactory.createTitledBorder("Streak Calendar"));
        calendarPanel.setOpaque(false);
        calendarPanel.setPreferredSize(new Dimension(400, 300));
        calendarPanel.add(new CalendarPanel(), BorderLayout.CENTER);
        add(calendarPanel, BorderLayout.CENTER); 

        // Add to main layout
        add(graphPanel, BorderLayout.WEST);
        add(calendarPanel, BorderLayout.EAST);

        // Click to cycle metrics
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                currentMetric = (currentMetric + 1) % metrics.length;
                repaint();
            }
        });
    }

    // Method to draw a donut graph representing work hours and break time
    protected void drawDonutGraph(Graphics g, JPanel panel) {
  

        // Calculate the total work hours, break time, and overall total for the current metric
        double work = workHours[currentMetric];
        double breakTime = breakHours[currentMetric];
        double total = work + breakTime;

        // Cast Graphics to Graphics2D for better rendering control
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set the size and position for the donut graph
        int size = 200;
        int x = (panel.getWidth() - size) / 2;
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
        int tx = panel.getWidth() / 2 - fm.stringWidth(valueStr) / 2;
        int ty = y + size / 2 + fm.getAscent() / 2 - 5;
        g2.setColor(Color.DARK_GRAY);
        g2.drawString(valueStr, tx, ty);

        // Label under graph
        g2.setFont(new Font("Arial", Font.BOLD, 18));
        String label = metrics[currentMetric];
        FontMetrics lf = g2.getFontMetrics();
        int lx = panel.getWidth() / 2 - lf.stringWidth(label) / 2;
        g2.drawString(label, lx, y + size + 30);

    }

}
