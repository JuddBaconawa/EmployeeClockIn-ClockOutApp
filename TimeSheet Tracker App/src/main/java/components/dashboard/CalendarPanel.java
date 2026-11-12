// CalendarPanel.java
package components.dashboard;

// IMPORTS
// AWT Imports
import java.awt.*;
import java.time.LocalDate;

// Util Imports
import java.util.Map;

// Swing Imports
import javax.swing.*;

// CalendarPanel class
public class CalendarPanel extends JPanel {

    // Declarations of objects
    private final int WEEKS = 6;
    private final int DAYS_IN_WEEK = 7;
    private final int BOX_SIZE = 23;
    

    // Daily worked hours loaded from the database: date => hours worked
    private final Map<LocalDate, Double> workLog;

    // Constructor for CalendarPanel
    public CalendarPanel(Map<LocalDate, Double> workLog) {

        // set to this.workLog
        this.workLog = workLog;

        // Set layout and styling
        setPreferredSize(new Dimension(BOX_SIZE * DAYS_IN_WEEK + 40, BOX_SIZE * (WEEKS + 1)));
        setOpaque(false);


    }

    // Method to set work log data
    @Override
    protected void paintComponent(Graphics g) {
        
        // Call the superclass's paintComponent method to ensure rendering
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Enable anti-aliasing for smoother graphics
        
        LocalDate today = LocalDate.now();
        LocalDate firstOfMonth = today.withDayOfMonth(1);
        int startDayOfWeek = firstOfMonth.getDayOfWeek().getValue() % 7; // Sunday = 0

        // Draw day labels (S M T W T F S)
        String[] days = {"S", "M", "T", "W", "TH", "F", "S"};
        g2.setFont(new Font("Arial", Font.BOLD, 15));

        // Get font metrics for centering the day labels
        FontMetrics dayMetrics = g2.getFontMetrics();

        // Draw the day labels at the top of the calendar
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            int x = i * BOX_SIZE + 10;

            //  
            int textX = x + (BOX_SIZE - dayMetrics.stringWidth(days[i])) / 2;

            // Draw the day label centered above the box
            g2.drawString(days[i], textX, 20);
        }

        
        // Draw boxes
        LocalDate current = firstOfMonth.minusDays(startDayOfWeek);
        int boxYStart = 30;

        // Draw the grid of boxes for the month
        for (int week = 0; week < WEEKS; week++) {
            for (int day = 0; day < DAYS_IN_WEEK; day++) {
                int x = day * BOX_SIZE + 10;
                int y = week * BOX_SIZE + boxYStart;

                Color fill = new Color(230, 230, 230); // Default: weekend gray

                // Check if the current day is a weekend (Saturday or Sunday)
                if (current.getMonth() == today.getMonth() 
                    
                            // Check if the current day is a weekday (Monday to Friday)
                            && current.getYear() == today.getYear()
                            && workLog.containsKey(current)) {

                        // Get the hours worked for the current day from the work log
                        double hours = workLog.get(current);


                        // Determine the fill color based on hours worked
                        if (hours > 8.0) {
                            fill = new Color(100, 181, 246); // 🔵 Light Blue for overtime
                        } else if (hours == 8.0) {
                            fill = new Color(76, 175, 80); // 🟩 Green
                        } else if (hours > 0) {
                            fill = new Color(255, 235, 59); // 🟨 Yellow
                        }
                    }
            

                // Fill and border
                g2.setColor(fill);
                g2.fillRect(x, y, BOX_SIZE, BOX_SIZE);
                g2.setColor(Color.DARK_GRAY);
                g2.drawRect(x, y, BOX_SIZE, BOX_SIZE);

                // draw day number
                current = current.plusDays(1);
            }
        }
    }
}