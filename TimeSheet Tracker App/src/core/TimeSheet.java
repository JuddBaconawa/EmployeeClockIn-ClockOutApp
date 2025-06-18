package core;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

import auth.LoginForm;
import components.MenuPanel;
import components.TopInfoPanel;
import models.User;
import pages.Dashboard;
import pages.Home;
import pages.Profile;
import pages.Timelog;

public class TimeSheet extends JFrame {

    private JPanel displayPanel;
    private TopInfoPanel topInfoPanel;
    private MenuPanel menuPanel;

    public TimeSheet(User user) {
        setupFrame();

        // Main layout: left menu, top info, and content panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // ========== Menu ==========
        menuPanel = new MenuPanel(this, user);
        mainPanel.add(menuPanel, BorderLayout.WEST);

        // ========== Top Info ==========
        topInfoPanel = new TopInfoPanel(user);
        mainPanel.add(topInfoPanel, BorderLayout.NORTH);

        // ========== Display Panel ==========
        displayPanel = new JPanel(new CardLayout());
        displayPanel.setBackground(new Color(240, 235, 216));
        displayPanel.add(new Home(), "Home");
        displayPanel.add(new Dashboard(), "Dashboard");
        displayPanel.add(new Timelog(), "Timelog");
        displayPanel.add(new Profile(), "Profile");

        mainPanel.add(displayPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private void setupFrame() {
        setTitle("Time Sheet Home");
        setUndecorated(true);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        // Rounded corners
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            User user = new User(); // Replace with proper initialization
            new TimeSheet(user);
        });
    }
}
