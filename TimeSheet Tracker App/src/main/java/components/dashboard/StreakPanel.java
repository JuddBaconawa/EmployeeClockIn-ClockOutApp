// Package
package components.dashboard;

// |----------------------------------------|
// |-----------IMPORTS----------------------|
// |----------------------------------------|

// awt imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

// time imports
import java.time.DayOfWeek;
import java.time.LocalDate;

// sql imports
import java.sql.Connection;
import java.util.Map;

// swing imports
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dao.TimelogDAO;

// StreakPanel class
public class StreakPanel extends JPanel {


    // StreakPanel constructor
    public StreakPanel(Connection conn, int userId) {

        // Set layout and styling
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Title label
        JLabel titleLabel = new JLabel("Activity Streak");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(60, 60, 60));
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));

        // load real dealy work totals for the same 52-week period shown by the grid
        TimelogDAO timelogDAO = new TimelogDAO(conn);
        LocalDate today = LocalDate.now();
        LocalDate firstSunday = today.minusWeeks(52).with(DayOfWeek.SUNDAY);

        //  dailyHours map used to get the dailyWorkedHours
        Map<LocalDate, Double> dailyHours = timelogDAO.getDailyWorkedHours(userId, firstSunday, today);

        // Add components to the panel
        add(titleLabel, BorderLayout.NORTH);
        add(new StreakGridPanel(dailyHours), BorderLayout.CENTER);

    }
  
}
