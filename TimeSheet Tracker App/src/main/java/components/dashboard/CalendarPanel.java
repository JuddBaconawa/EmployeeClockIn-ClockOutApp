package components.dashboard;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.HashMap;

public class CalendarPanel extends JPanel {

    private final int WEEKS = 6;
    private final int DAYS_IN_WEEK = 7;
    private final int BOX_SIZE = 35;
    

    // Simulated time log data: LocalDate -> hours worked
    private final HashMap<LocalDate, Double> workLog;

    public CalendarPanel() {
        setPreferredSize(new Dimension(BOX_SIZE * DAYS_IN_WEEK + 40, BOX_SIZE * (WEEKS + 1)));
        setOpaque(false);

        workLog = new HashMap<>();

        // Simulated data for demonstration
        LocalDate today = LocalDate.now();
        LocalDate start = today.withDayOfMonth(1);

        for (int i = 0; i < 31; i++) {
            LocalDate day = start.plusDays(i);
            int dow = day.getDayOfWeek().getValue(); // 1 = Monday, 7 = Sunday
            if (dow >= 1 && dow <= 5) { // Mon–Fri only
                double simulatedHours = Math.random() < 0.2 ? 0 : 6 + Math.random() * 4; // Some absent, some overtime
                workLog.put(day, simulatedHours);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        LocalDate today = LocalDate.now();
        LocalDate firstOfMonth = today.withDayOfMonth(1);
        int startDayOfWeek = firstOfMonth.getDayOfWeek().getValue() % 7; // Sunday = 0

        // Draw day labels (S M T W T F S)
        String[] days = {"S", "M", "T", "W", "TH", "F", "S"};
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            int x = i * BOX_SIZE + 10;
            g2.drawString(days[i], x + 10, 20);
        }

        // Draw boxes
        LocalDate current = firstOfMonth.minusDays(startDayOfWeek);
        int boxYStart = 30;

        for (int week = 0; week < WEEKS; week++) {
            for (int day = 0; day < DAYS_IN_WEEK; day++) {
                int x = day * BOX_SIZE + 10;
                int y = week * BOX_SIZE + boxYStart;

                Color fill = new Color(230, 230, 230); // Default: weekend gray
                int weekday = current.getDayOfWeek().getValue(); // Mon = 1, Sun = 7

                if (current.getMonth() == today.getMonth()) {
                    if (weekday >= 1 && weekday <= 5) { // Only weekdays
                        double hours = workLog.getOrDefault(current, -1.0);
                        if (hours > 8.0) {
                            fill = new Color(100, 181, 246); // 🔵 Light Blue for overtime
                        } else if (hours == 8.0) {
                            fill = new Color(76, 175, 80); // 🟩 Green
                        } else if (hours > 0) {
                            fill = new Color(255, 235, 59); // 🟨 Yellow
                        } else {
                            fill = new Color(244, 67, 54); // 🟥 Red (absent)
                        }
                    }
                }

                // Fill and border
                g2.setColor(fill);
                g2.fillRect(x, y, BOX_SIZE, BOX_SIZE);
                g2.setColor(Color.DARK_GRAY);
                g2.drawRect(x, y, BOX_SIZE, BOX_SIZE);

                current = current.plusDays(1);
            }
        }
    }
}
