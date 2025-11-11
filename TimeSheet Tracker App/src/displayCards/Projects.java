package displayCards;

import components.DisplayCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;

public class Projects extends DisplayCard {

    private JPanel projectsGrid;

    public Projects() {
        super("Projects"); // DisplayCard title

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
        projectsGrid.setLayout(new GridLayout(0, 2, 20, 20)); // 2 columns, dynamic rows
        projectsGrid.setBackground(new Color(240, 240, 240));
        projectsGrid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

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

    // Add projects dynamically
    public void setProjects(List<Project> projectList) {
        projectsGrid.removeAll();
        for (Project p : projectList) {
            projectsGrid.add(createProjectCard(p));
        }
        projectsGrid.revalidate();
        projectsGrid.repaint();
    }

    // Create each project card
private JPanel createProjectCard(Project p) {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.setPreferredSize(new Dimension(300, 200)); // taller to fit timelog
    panel.setBackground(Color.WHITE);
    panel.setBorder(BorderFactory.createLineBorder(new Color(62, 92, 118), 2));

    // --- Project name at top ---
    JLabel nameLabel = new JLabel(p.name);
    nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
    nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
    panel.add(nameLabel, BorderLayout.NORTH);

    // --- Timelog panel in center ---
    JPanel timeLogPanel = new JPanel();
    timeLogPanel.setLayout(new GridLayout(0, 1, 0, 2)); // 1 column, dynamic rows
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

    panel.add(timeLogPanel, BorderLayout.CENTER);

    // --- Total hours at bottom ---
    JLabel progressLabel = new JLabel(p.hoursLogged + " / " + p.maxHours + " hrs total");
    progressLabel.setFont(new Font("Arial", Font.PLAIN, 16));
    progressLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
    panel.add(progressLabel, BorderLayout.SOUTH);

    return panel;
}


    // Open a timelog table for the project
    private void openTimeLogTable(Project project) {
        JFrame tableFrame = new JFrame(project.name + " Timelog");
        tableFrame.setSize(500, 400);

        String[] columns = {"Date", "Hours", "% of Project"};
        Object[][] data = new Object[project.timeEntries.size()][3];

        for (int i = 0; i < project.timeEntries.size(); i++) {
            Project.TimeEntry entry = project.timeEntries.get(i);
            data[i][0] = entry.date;
            data[i][1] = entry.hours;
            data[i][2] = String.format("%.0f%%", (entry.hours / (double) project.maxHours) * 100);
        }

        JTable table = new JTable(data, columns);
        JScrollPane scroll = new JScrollPane(table);
        tableFrame.add(scroll);
        tableFrame.setVisible(true);
    }

    // Minimal Project class with time entries
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
            public String date; // e.g., "2025-11-10"
            public double hours;
            public TimeEntry(String date, double hours) {
                this.date = date;
                this.hours = hours;
            }
        }
    }

    // Test main
    public static void main(String[] args) {
        JFrame frame = new JFrame("Projects Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);

        Projects projectsCard = new Projects();
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

        // Example projects
        List<Project> projectList = new ArrayList<>();
        projectList.add(new Project("Alpha", 10, 40, alphaEntries));
        projectList.add(new Project("Beta", 14, 50, betaEntries));

        projectsCard.setProjects(projectList);

        frame.setVisible(true);
    }
}
