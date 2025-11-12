package displayCards;

import components.DisplayCard;
import models.User;
import util.LoadProjectsFromDB;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Projects extends DisplayCard {

    private JPanel projectsGrid;
    private Connection conn;
    private User user;

    public Projects(Connection conn, User user) {
        super("Projects");
        this.conn = conn;
        this.user = user;

        setBackground(new Color(240, 235, 216));
        setLayout(new BorderLayout());

        // === Title Panel ===
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        titlePanel.setBackground(new Color(62, 92, 118));
        JLabel titleLabel = new JLabel("Projects");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // === Projects Grid Panel ===
        projectsGrid = new JPanel();
        // [CHANGED] Keep GridLayout, same as before
        projectsGrid.setLayout(new GridLayout(0, 2, 20, 20)); // 2 columns, dynamic rows
        projectsGrid.setBackground(new Color(240, 240, 240));
        projectsGrid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // [CHANGED] Wrap grid in scroll pane
        JScrollPane scrollPane = new JScrollPane(projectsGrid);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

        // === Footer Panel ===
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        footerPanel.setBackground(new Color(180, 180, 180));
        JButton addProjectBtn = new JButton("+ Add Project");
        footerPanel.add(addProjectBtn);
        add(footerPanel, BorderLayout.SOUTH);
    }

    // load projects from the utility class
    public void loadProjectsFromDB() {
        LoadProjectsFromDB loader = new LoadProjectsFromDB(conn);
        List<Project> projectList = loader.loadProjects();
        setProjects(projectList);
    }

    // Add projects dynamically
    public void setProjects(List<Project> projectList) {
        projectsGrid.removeAll();
        for (Project p : projectList) {
            // [CHANGED] call new card creation method
            projectsGrid.add(createProjectCard(p));
        }
        projectsGrid.revalidate();
        projectsGrid.repaint();
    }

    // Create each project card
    private JPanel createProjectCard(Project p) {
        JPanel panel = new JPanel(new BorderLayout());
        // [CHANGED] taller card to fit timelog
        panel.setPreferredSize(new Dimension(300, 200));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(new Color(62, 92, 118), 2));

        // --- Top: Project name ---
        JLabel nameLabel = new JLabel(p.name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        panel.add(nameLabel, BorderLayout.NORTH);

        // --- Center: Timelog ---
        JPanel timeLogPanel = new JPanel();
        // [CHANGED] Use GridLayout for vertical entries
        timeLogPanel.setLayout(new GridLayout(0, 1, 0, 2));
        timeLogPanel.setBackground(Color.WHITE);
        timeLogPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        for (Project.TimeEntry entry : p.timeEntries) {
            double percent = (entry.hours / (double) p.maxHours) * 100;
            JLabel entryLabel = new JLabel(
                entry.date + " : " + entry.hours + " hrs (" + String.format("%.0f%%", percent) + ")"
            );
            entryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            timeLogPanel.add(entryLabel);
        }

        // [ADDED] Make timelog scrollable if too many entries
        JScrollPane timeLogScroll = new JScrollPane(timeLogPanel);
        timeLogScroll.setBorder(null);
        timeLogScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        timeLogScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(timeLogScroll, BorderLayout.CENTER);

        // --- Bottom: Total hours ---
        JLabel progressLabel = new JLabel(p.hoursLogged + " / " + p.maxHours + " hrs total");
        progressLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        progressLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        panel.add(progressLabel, BorderLayout.SOUTH);

        return panel;
    }

    // Minimal Project class with timelog
    public static class Project {
        public String name;
        public int hoursLogged;
        public int maxHours;
        public List<TimeEntry> timeEntries;

        public Project(String name, int hoursLogged, int maxHours, List<TimeEntry> timeEntries) {
            this.name = name;
            this.hoursLogged = hoursLogged;
            this.maxHours = maxHours;
            this.timeEntries = timeEntries;
        }

        public static class TimeEntry {
            public String date;
            public double hours;
            public TimeEntry(String date, double hours) {
                this.date = date;
                this.hours = hours;
            }
        }
    }

    // [REMOVED] main method — no longer needed for dashboard integration

    public static void main(String[] args) {
        JFrame frame = new JFrame("Projects Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);

        Projects projectsCard = new Projects(null, null);
        frame.add(projectsCard);

        // Example time entries
        List<Project.TimeEntry> alphaEntries = new ArrayList<>();
        alphaEntries.add(new Project.TimeEntry("2025-11-10", 5));
        alphaEntries.add(new Project.TimeEntry("2025-11-11", 3));
        alphaEntries.add(new Project.TimeEntry("2025-11-12", 4));
        alphaEntries.add(new Project.TimeEntry("2025-11-13", 8));

        List<Project.TimeEntry> betaEntries = new ArrayList<>();
        betaEntries.add(new Project.TimeEntry("2025-11-10", 8));
        betaEntries.add(new Project.TimeEntry("2025-11-11", 6));

        List<Project.TimeEntry> timeproject = new ArrayList<>();
        alphaEntries.add(new Project.TimeEntry("2025-11-10", 5));
        alphaEntries.add(new Project.TimeEntry("2025-11-11", 3));
        alphaEntries.add(new Project.TimeEntry("2025-11-12", 4));
        alphaEntries.add(new Project.TimeEntry("2025-11-13", 8));

        List<Project.TimeEntry> marketingProject = new ArrayList<>();
        betaEntries.add(new Project.TimeEntry("2025-11-10", 8));
        betaEntries.add(new Project.TimeEntry("2025-11-11", 6));

        // Example projects
        List<Project> projectList = new ArrayList<>();
        projectList.add(new Project("Alpha", 10, 40, alphaEntries));
        projectList.add(new Project("Beta", 14, 50, betaEntries));projectList.add(new Project("Time", 10, 40, timeproject));
        projectList.add(new Project("Marketing", 14, 50, marketingProject));

        projectsCard.setProjects(projectList);

        frame.setVisible(true);
    }
}