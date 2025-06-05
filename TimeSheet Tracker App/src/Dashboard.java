import javax.swing.*;
import java.awt.*;

public class Dashboard extends JPanel {
    private double regularHours;   // e.g., 32.5
    private double overtimeHours;  // e.g., 7.5
    private double maxHours;       // e.g., 40

    public Dashboard(double regularHours, double overtimeHours, double maxHours) {
        this.regularHours = regularHours;
        this.overtimeHours = overtimeHours;
        this.maxHours = maxHours;
        setPreferredSize(new Dimension(400, 300));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int size = 200;
        int x = (getWidth() - size) / 2;
        int y = (getHeight() - size) / 2;

        // Draw background circle
        g2.setColor(Color.LIGHT_GRAY);
        g2.setStroke(new BasicStroke(20));
        g2.drawArc(x, y, size, size, 0, 360);

        // Draw regular hours arc
        g2.setColor(new Color(100, 180, 255));
        int regularAngle = (int) (360 * (regularHours / maxHours));
        g2.drawArc(x, y, size, size, 90, -regularAngle);

        // Draw overtime hours arc (on top)
        g2.setColor(new Color(255, 120, 120));
        int overtimeAngle = (int) (360 * (overtimeHours / maxHours));
        g2.drawArc(x, y, size, size, 90 - regularAngle, -overtimeAngle);

        // Draw labels
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.drawString("Regular: " + regularHours + "h", x + 30, y + size + 30);
        g2.drawString("Overtime: " + overtimeHours + "h", x + 30, y + size + 50);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dashboard Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        Dashboard dashboardPanel = new Dashboard(32.5, 7.5, 40);
        frame.add(dashboardPanel);

        frame.pack();
        frame.setVisible(true);
    }
}
