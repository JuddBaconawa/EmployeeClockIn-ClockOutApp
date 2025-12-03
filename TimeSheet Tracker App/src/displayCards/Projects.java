// Projects.java
package displayCards;

// Imports for project management
import components.DisplayCard;
import models.User;
import util.LoadProjectsFromDB;
import components.TitlePanel;
import components.dashboard.StatusIndicator;

// Imports for GUI and utilities
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Swing imports
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

// SQL and time imports
import java.sql.Connection;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Main Projects Display Card
public class Projects extends DisplayCard {

    //declarations
    private JPanel projectsGrid;
    private JLabel monthLabel;
    private Connection conn;
    private User user;

    // list of all projects loaded
    private List<Project> allProjects = new ArrayList<>();
    private YearMonth currentMonth = YearMonth.now(); // Tracks which month is being shown
    private StatusIndicator statusIndicator;

    public Projects(Connection conn, User user) {
    super("Projects");
    this.conn = conn;
    this.user = user;

    // Main Layout
    setBackground(new Color(240, 235, 216));
    setLayout(new BorderLayout());

    //statusIndicator
    statusIndicator = new StatusIndicator();

    // === Title Panel ===
    TitlePanel titlePanel = new TitlePanel("Projects", statusIndicator);
    
    // titlePanel.setLayout(new BorderLayout());
    titlePanel.setPreferredSize( new Dimension(0, 60));

    // === Navigation Panel ===
    JPanel navPanel = new JPanel(new BorderLayout());
    navPanel.setBackground(new Color(62, 92, 118));
    navPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

    // Left: Title
    JLabel navLabel = new JLabel("Navigation");
    navLabel.setFont(new Font("Arial", Font.BOLD, 32));
    navLabel.setForeground(Color.WHITE);
    navPanel.add(navLabel, BorderLayout.EAST);  // hid the "Navigation" text

    // Right: Month Navigation
    JPanel monthlyNavPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
    monthlyNavPanel.setOpaque(false);

    // months buttons and label
    JButton prevBtn = new JButton("◀ Prev");
    JButton nextBtn = new JButton("Next ▶");
    monthLabel = new JLabel(currentMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy")));
    monthLabel.setFont(new Font("Arial", Font.BOLD, 18));
    monthLabel.setForeground(Color.WHITE);

    prevBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            changeMonth(-1);
        }
    });

    nextBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            changeMonth(1);
        }
    });

    monthlyNavPanel.add(prevBtn);
    monthlyNavPanel.add(monthLabel);
    monthlyNavPanel.add(nextBtn);

    // add monthlyNavPane to navPanel
    navPanel.add(monthlyNavPanel, BorderLayout.EAST);

    

    // === Top Panel Wrapper ===
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
    topPanel.add(titlePanel);
    topPanel.add(navPanel);
    

    // === Projects Grid Panel ===
    projectsGrid = new JPanel();
    projectsGrid.setLayout(new GridLayout(0, 2, 20, 20));
    projectsGrid.setBackground(new Color(240, 240, 240));
    projectsGrid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    // scroll panel for projects grid
    JScrollPane scrollPane = new JScrollPane(projectsGrid);
    scrollPane.setBorder(null);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    

    // === Footer Panel ===
    JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
    footerPanel.setBackground(new Color(180, 180, 180));
    JButton addProjectBtn = new JButton("+ Add Project");


    // old new project action
    addProjectBtn.addActionListener(e -> {
        Project newProject = new Project(
            "New Project",
            0,
            40,
            new ArrayList<>()
        );
        allProjects.add(newProject);
        refreshProjectsView();

    });

    // add button to Footer
    footerPanel.add(addProjectBtn);
    

    // added panels to main layout
    add(topPanel, BorderLayout.NORTH);
    add(scrollPane, BorderLayout.CENTER);
    add(footerPanel, BorderLayout.SOUTH);
}


    // Change viewed month
    private void changeMonth(int offset) {
        currentMonth = currentMonth.plusMonths(offset);
        monthLabel.setText(currentMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy")));
        refreshProjectsView();
    }

    // load projects from the utility class
    public void loadProjectsFromDB() {
        LoadProjectsFromDB loader = new LoadProjectsFromDB(conn);
        List<Project> projectList = loader.loadProjectsForUser(user);
        setProjects(projectList);
    }

    // Add projects dynamically
    public void setProjects(List<Project> projectList) {
        this.allProjects = projectList;
        refreshProjectsView();
    }

    // Rebuild grid based on currentMonth
    private void refreshProjectsView() {
        projectsGrid.removeAll();
        for (Project p : allProjects) {
            projectsGrid.add(createProjectCard(p));
        }
        projectsGrid.revalidate();
        projectsGrid.repaint();
    }

    // Create each project card (filtered by current month)
    private JPanel createProjectCard(Project p) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(300, 200));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(new Color(62, 92, 118), 2));

        // Project Name
        JLabel nameLabel = new JLabel(p.name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        panel.add(nameLabel, BorderLayout.NORTH);

        // === Filter entries by current month ===
        JPanel timeLogPanel = new JPanel(new GridLayout(0, 1, 0, 2));
        timeLogPanel.setBackground(Color.WHITE);
        timeLogPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        // date Formatter (if needed)
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Project.TimeEntry entry : p.timeEntries) {
            LocalDate date = LocalDate.parse(entry.date, fmt);
            if (YearMonth.from(date).equals(currentMonth)) {
                double percent = (entry.hours / (double) p.maxHours) * 100;
                JLabel entryLabel = new JLabel(
                        entry.date + " : " + entry.hours + " hrs (" + String.format("%.0f%%", percent) + ")"
                );
                // set font for entry
                entryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                // add entry to panel
                timeLogPanel.add(entryLabel);
            }
        }

        // Scroll pane for time log entries
        JScrollPane timeLogScroll = new JScrollPane(timeLogPanel);
        timeLogScroll.setBorder(null);
        timeLogScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        timeLogScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(timeLogScroll, BorderLayout.CENTER);

        // Progress Summary
        JLabel progressLabel = new JLabel(p.hoursLogged + " / " + p.maxHours + " hrs total");
        progressLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        progressLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        panel.add(progressLabel, BorderLayout.SOUTH);

        // return the panel when complete/done
        return panel;
    }

    // Minimal Project class
    public static class Project {
        public String name;
        public int hoursLogged;
        public int maxHours;
        public List<TimeEntry> timeEntries;

        // Project Constructor
        public Project(String name, int hoursLogged, int maxHours, List<TimeEntry> timeEntries) {
            this.name = name;
            this.hoursLogged = hoursLogged;
            this.maxHours = maxHours;
            this.timeEntries = timeEntries;
        }

        // Time Entry inner class
        public static class TimeEntry {
            public String date;
            public double hours;

            // TimeEntry Constructor
            public TimeEntry(String date, double hours) {
                this.date = date;
                this.hours = hours;
            }
        }
    }

    // === Example Runner ===
    public static void main(String[] args) {
        JFrame frame = new JFrame("Projects Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);

        Projects projectsCard = new Projects(null, null);
        frame.add(projectsCard);

        // === Example Data ===

        // November Projects
        List<Project.TimeEntry> alphaEntries = new ArrayList<>();
        alphaEntries.add(new Project.TimeEntry("2025-11-10", 5));
        alphaEntries.add(new Project.TimeEntry("2025-11-11", 3));
        alphaEntries.add(new Project.TimeEntry("2025-11-12", 4));
        alphaEntries.add(new Project.TimeEntry("2025-11-13", 8));

        List<Project.TimeEntry> betaEntries = new ArrayList<>();
        betaEntries.add(new Project.TimeEntry("2025-11-09", 8));
        betaEntries.add(new Project.TimeEntry("2025-11-14", 6));

        // January Project
        List<Project.TimeEntry> timeProjectEntries = new ArrayList<>();
        timeProjectEntries.add(new Project.TimeEntry("2025-01-05", 6));
        timeProjectEntries.add(new Project.TimeEntry("2025-01-08", 4));
        timeProjectEntries.add(new Project.TimeEntry("2025-01-09", 5));

        // March Project
        List<Project.TimeEntry> marketingEntries = new ArrayList<>();
        marketingEntries.add(new Project.TimeEntry("2025-03-02", 8));
        marketingEntries.add(new Project.TimeEntry("2025-03-04", 7));
        marketingEntries.add(new Project.TimeEntry("2025-03-05", 5));

        // June Project
        List<Project.TimeEntry> devOpsEntries = new ArrayList<>();
        devOpsEntries.add(new Project.TimeEntry("2025-06-10", 5));
        devOpsEntries.add(new Project.TimeEntry("2025-06-15", 6));
        devOpsEntries.add(new Project.TimeEntry("2025-06-20", 4));

        // August Project
        List<Project.TimeEntry> researchEntries = new ArrayList<>();
        researchEntries.add(new Project.TimeEntry("2025-08-03", 8));
        researchEntries.add(new Project.TimeEntry("2025-08-04", 6));
        researchEntries.add(new Project.TimeEntry("2025-08-10", 5));

        // October Project
        List<Project.TimeEntry> designEntries = new ArrayList<>();
        designEntries.add(new Project.TimeEntry("2025-10-01", 4));
        designEntries.add(new Project.TimeEntry("2025-10-03", 6));
        designEntries.add(new Project.TimeEntry("2025-10-05", 7));

        // === Projects List ===
        List<Project> projectList = new ArrayList<>();
        projectList.add(new Project("Alpha Account", 20, 40, alphaEntries));
        projectList.add(new Project("The Hilton - Marketing", 14, 50, betaEntries));
        projectList.add(new Project("TimeZone - Main POS", 15, 40, timeProjectEntries));
        projectList.add(new Project("CVS POS Software", 20, 50, marketingEntries));
        projectList.add(new Project("DevOps", 15, 40, devOpsEntries));
        projectList.add(new Project("Research & Design", 17, 45, researchEntries));
        projectList.add(new Project("Walmart app - UI Design", 18, 50, designEntries));

        // Set projects to the card
        projectsCard.setProjects(projectList);

        // Show frame
        frame.setVisible(true);
    }
}
