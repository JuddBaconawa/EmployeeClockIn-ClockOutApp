package components.dashboard;

import javax.swing.*;
import java.awt.*;

public class StatusIndicator extends JPanel {

    private Color statusColor = Color.RED;  // Default to "logged out"

    public StatusIndicator() {
        setPreferredSize(new Dimension(25, 27));
    }

    public void setStatus(String status) {
        switch (status.toLowerCase()) {
            case "in":
                statusColor = Color.GREEN;
                break;
            case "break":
                statusColor = Color.YELLOW;
                break;
            case "out":
            default:
                statusColor = Color.RED;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int size = Math.min(getWidth(), getHeight());
        int x = (getWidth() - size) / 2 - 1;
        int y = (getHeight() - size) / 2 - 1;

        g.setColor(statusColor);
        g.fillOval(x, y, size, size);
    }
}

