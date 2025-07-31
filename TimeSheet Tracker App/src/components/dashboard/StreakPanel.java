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
import java.util.Locale;
import java.time.DayOfWeek;
// Simulated time log data: LocalDate -> hours worked
import java.time.LocalDate;

// Swing imports
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

// StreakPanel class
public class StreakPanel extends JPanel {

    // Declarations of objects
    private final int WEEKS = 52; // 1 year of weeks
    private final int DAYS_IN_WEEK = 7; // 7 days in a week
    private final int BOX_SIZE = 15; // size of each box in the grid
    private final int GAP = 3; // gap between boxes

    private final int LABEL_GAP = 20; // gap for the month labels
    private final int BOX_PADDING = 2; // padding for the left side
    private final int TOP_PADDING = 20; // padding for the top side
    private final int LEFT_PADDING = 30; // padding for the left side
    

    // Map to hold the log data
    private final HashMap<LocalDate, Integer> data;

    // StreakPanel constructor
    public StreakPanel() {
        this.data = generateMockData();
        int panelWidth = (BOX_SIZE + BOX_PADDING) * WEEKS + 60;
        int panelHeight = (BOX_SIZE + BOX_PADDING) * DAYS_IN_WEEK + 40;
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBackground(Color.WHITE);

    }


    // Method to initialize the panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid((Graphics2D) g);
    }

    private void drawGrid(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minusWeeks(WEEKS);
        LocalDate firstDay = startDate.minusDays(startDate.getDayOfWeek().getValue() % 7);

        int startX = 50; // LEFT margin
        int startY = 30; // Top Margin

        int col = 0; // Column index
        int lastMonth = -1; // Last month index to avoid duplicate labels
        LocalDate firstSunday = startDate.with(DayOfWeek.SUNDAY);

        int lastMonth = -1;

        for (int week = 0; week < WEEKS; week++) {
            for (int day = 0; day < DAYS_IN_WEEK; day++) {
                LocalDate date = firstDay.plusDays(week).plusDay(day);
                int value = data.getOrDefault(date, 0);

                int x = LEFT_PADDING + week * (BOX_SIZE + GAP);
                int y = TOP_PADDING + day * (BOX_SIZE + GAP);


                g.setColor(getColorForHours(value));
                g.fillRect(x, y, BOX_SIZE, BOX_SIZE);
            }

            // Draw month labels
            
        }


    }

    // Method to get the color based on hours worked
    private Color getColorForHours(double hours) {
        if (hours == 0) return new Color(235, 237, 240);
        if (hours < 4) return new Color(198, 228, 139);
        if (hours < 6) return new Color(123, 201, 111);
        if (hours < 8) return new Color(35, 154, 59);
        return new Color(25, 97, 39); 
    }

    // Method to generate mock data for the last 27 weeks
    private HashMap<LocalDate, Integer> generateMockData() {
        HashMap<LocalDate, Integer> map = new HashMap<>();
        LocalDate today = LocalDate.now();
        for (int i = 0; i < WEEKS * DAYS_IN_WEEK; i++) {
            LocalDate date = today.minusDays(i);
            map.put(date, (int) Math.random() * 7);  // random hours
        }

        return map;
    }


}