// IMPORTS
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;


import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import models.User;


public class TopInfoPanel extends JPanel {
    public JLabel hourseLabel;
    public JLabel timeLabel;
    public JLabel dateLabel;

    public TopInfoPanel(User user) {
        setLayout(new GridLayout(1, 6, 15, 50));
        setBorder(BorderFactory.createEmptyBorder(40, 10, 20, 10));
        setBackground(new Color(62, 92, 118));

        add(createUserPanel(user));
        add(createHoursPanel());
        add(createTimePanel());
        add(createDatePanel());
    }

    private JPanel createUserPanel(User user) {
        JPanel userPanel = createStylePanel(250);
        JLabel usernameLabel = new JLabel("User: " + user.username);
        JLabel roleLabel = new JLabel("Role: " + user.role);
        usernameLabel.setBounds(17, 10, 200, 20);
        roleLabel.setBounds(10, 30, 200, 20);
        userPanel.setLayout(null);
        userPanel.add(usernameLabel);
        userPanel.add(roleLabel);
        return userPanel;

    }

    private JPanel createHoursPanel() {
        JPanel 
    }

    private JPanel createStylePanel(int width) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, 55));
        panel.setBackground(new Color(240, 235, 216));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createBevelBorder(1, Color.BLACK, Color.GRAY),
            BorderFactory.createEmptyBorder(10, 5, 10, 5)  
        ));
        return panel;
    }

}
