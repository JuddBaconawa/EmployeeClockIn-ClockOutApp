// PACKAGES
package components.dashboard;

// IMPORTS
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.util.HashMap;

// Simulated time log data: LocalDate -> hours worked
import java.time.LocalDate;

// Swing imports
import javax.swing.BorderFactory;
import javax.swing.JPanel;

// StreakPanel class
public class StreakPanel extends JPanel {

    // Declarations of objects
    private final int WEEKS = 27;
    private final int DAYS_IN_WEEK = 7;
    private final int BOX_SIZE = 15;
    private final int GAP = 3;
    private final int LEFT_PADDING = 40;
    private final int TOP_PADDING = 20;
    

    private final HashMap<LocalDate, Double> logData = new HashMap<>();

    public StreakPanel() {
        setPreferredSize(new Dimension(WEEKS * (BOX_SIZE + 2), DAYS_IN_WEEK * (BOX_SIZE + 2)));
        setBackground(Color.WHITE);

        generateMockData();
    }

    private void generateMockData() {
        LocalDate today = LocalDate.now();
        for (int i = 0; i < WEEKS * DAYS_IN_WEEK; i++) {
            LocalDate date = today.minusDays(i);
            logData.put(date, Math.random() * 10);  // random hours
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        LocalDate today = LocalDate.now();
        int x = 0;

        for (int week = 0; week < WEEKS; week++) {
            int y = 0;
            for (int day = 0; day < DAYS_IN_WEEK; day++) {
                LocalDate date = today.minusDays(week * 7 + day);
                double hours = logData.getOrDefault(date, 0.0);

                g2.setColor(getColorForHours(hours));
                g2.fillRect(x, y, BOX_SIZE, BOX_SIZE);

                y += BOX_SIZE + 2;
            }

            x+= BOX_SIZE + 2;
        }
    }

    private Color getColorForHours(double hours) {
        if (hours == 0) return new Color(235, 237, 240);
        if (hours < 2) return new Color(198, 228, 139);
        if (hours < 4) return new Color(123, 201, 111);
        if (hours < 6) return new Color(35, 154, 59);
        return new Color(25, 97, 39); 
    }


}