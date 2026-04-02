package components.dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;


import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StreakPanel extends JPanel {

    public StreakPanel() {
      setLayout(new BorderLayout());
      setBackground(Color.WHITE);
      setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

      JLabel titleLabel = new JLabel("Activity Streak");
      titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
      titleLabel.setForeground(new Color(60, 60, 60));
      titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
      titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));

      add(titleLabel, BorderLayout.NORTH);
      add(new StreakGridPanel(), BorderLayout.CENTER);
    }
  
}
