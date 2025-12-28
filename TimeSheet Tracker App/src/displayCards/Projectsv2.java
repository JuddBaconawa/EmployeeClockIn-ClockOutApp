// // PACKAGES
// package displayCards;

// // IMPORTS
// import components.DisplayCard;

// import javax.swing.*;
// import java.awt.*;
// import java.util.LinkedHashMap;
// import java.util.Map;

// public class Projectsv2 extends DisplayCard {

//     public Projectsv2() {
//         super("Projects");
//         setBackground(new Color(62, 92, 118));
//         setLayout(new BorderLayout());

//         // ===== Title Panel =====
//         JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//         titlePanel.setBackground(new Color(62, 92, 118));
//         JLabel titleLabel = new JLabel("Projects2");
//         titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
//         titleLabel.setForeground(Color.LIGHT_GRAY);
//         titlePanel.add(titleLabel);
//         add(titlePanel, BorderLayout.NORTH);

//         // ===== Content Panel (Scrollable) =====
//         JPanel contentPanel = new JPanel();
//         contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
//         contentPanel.setBackground(new Color(245, 245, 245));

//         // Add progress sections
//         contentPanel.add(createProgressSection("Daily Progress", 1, 7));
//         contentPanel.add(createProgressSection("Weekly Progress", 2, 4));
//         contentPanel.add(createProgressSection("Monthly Progress", 3, 4));

//         // ===== Radial Chart and Legend Panel =====
//         LinkedHashMap<String, Double> chartData = new LinkedHashMap<>();
//         chartData.put("Project A", 0.35);
//         chartData.put("Project B", 0.25);
//         chartData.put("Project C", 0.15);
//         chartData.put("Project D", 0.25);

//         Color[] colors = {
//             new Color(160, 196, 255),
//             new Color(189, 178, 255),
//             new Color(255, 198, 255),
//             new Color(255, 255, 160)
//         };

//         JPanel chartAndLegendPanel = new JPanel();
//         chartAndLegendPanel.setLayout(new BorderLayout());
//         chartAndLegendPanel.setBackground(Color.WHITE);
//         chartAndLegendPanel.setBorder(BorderFactory.createTitledBorder("Time Distribution"));

//         // Chart
//         RadialBarChartPanel radialChart = new RadialBarChartPanel(chartData, colors);
//         chartAndLegendPanel.add(radialChart, BorderLayout.CENTER);

//         // Legend
//         JPanel legendPanel = new JPanel();
//         legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS));
//         legendPanel.setBackground(Color.WHITE);
//         legendPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

//         int i = 0;
//         for (String projectName : chartData.keySet()) {
//             JPanel legendItem = new JPanel(new FlowLayout(FlowLayout.LEFT));
//             legendItem.setBackground(Color.WHITE);

//             JLabel colorBox = new JLabel("■");
//             colorBox.setForeground(colors[i % colors.length]);
//             colorBox.setFont(new Font("Arial", Font.BOLD, 16));

//             JLabel label = new JLabel(projectName);
//             label.setFont(new Font("Arial", Font.PLAIN, 14));

//             legendItem.add(colorBox);
//             legendItem.add(Box.createHorizontalStrut(5));
//             legendItem.add(label);
//             legendPanel.add(legendItem);
//             i++;
//         }

//         chartAndLegendPanel.add(legendPanel, BorderLayout.EAST);

//         contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
//         contentPanel.add(chartAndLegendPanel);

//         // ===== Scroll Wrapper =====
//         JScrollPane scrollPane = new JScrollPane(contentPanel);
//         scrollPane.setBorder(null);
//         scrollPane.getVerticalScrollBar().setUnitIncrement(16);
//         add(scrollPane, BorderLayout.CENTER);

//         // ===== Footer =====
//         JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//         footerPanel.setBackground(new Color(200, 200, 200));
//         footerPanel.setPreferredSize(new Dimension(0, 40));
//         footerPanel.add(new JLabel("Footer"));
//         add(footerPanel, BorderLayout.SOUTH);
//     }

//     // === Creates a titled progress grid ===
//     private JPanel createProgressSection(String title, int rows, int cols) {
//         JPanel sectionPanel = new JPanel(new BorderLayout());
//         sectionPanel.setBackground(new Color(245, 245, 245));
//         sectionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

//         JLabel sectionTitle = new JLabel(title);
//         sectionTitle.setFont(new Font("Arial", Font.BOLD, 20));
//         sectionPanel.add(sectionTitle, BorderLayout.NORTH);

//         JPanel grid = new JPanel(new GridLayout(rows, cols, 10, 10));
//         grid.setBackground(Color.WHITE);

//         for (int i = 0; i < rows * cols; i++) {
//             JPanel square = new JPanel();
//             square.setPreferredSize(new Dimension(50, 50));
//             square.setBackground(new Color(100 + (i * 10) % 155, 180, 220));
//             square.setToolTipText("Progress " + (i + 1));
//             grid.add(square);
//         }

//         sectionPanel.add(grid, BorderLayout.CENTER);
//         return sectionPanel;
//     }

//     // === Custom radial chart panel ===
//     static class RadialBarChartPanel extends JPanel {
//         private final Map<String, Double> data;
//         private final Color[] colors;

//         public RadialBarChartPanel(Map<String, Double> data, Color[] colors) {
//             this.data = data;
//             this.colors = colors;
//             setPreferredSize(new Dimension(300, 300));
//             setBackground(Color.WHITE);
//         }

//         @Override
//         protected void paintComponent(Graphics g) {
//             super.paintComponent(g);
//             Graphics2D g2 = (Graphics2D) g;

//             int centerX = getWidth() / 2;
//             int centerY = getHeight() / 2;
//             int radius = 100;

//             g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

//             int i = 0;
//             int arcWidth = 15;
//             int gap = 5;

//             for (Map.Entry<String, Double> entry : data.entrySet()) {
//                 double percent = entry.getValue();
//                 int angle = (int) (360 * percent);

//                 int currentRadius = radius - (i * (arcWidth + gap));
//                 int offset = radius - currentRadius;

//                 g2.setStroke(new BasicStroke(arcWidth));
//                 g2.setColor(colors[i % colors.length]);
//                 g2.drawArc(centerX - currentRadius, centerY - currentRadius,
//                            2 * currentRadius, 2 * currentRadius,
//                            90, -angle); // Counter-clockwise
//                 i++;
//             }
//         }
//     }

//     // === Main method ===
//     public static void main(String[] args) {
//         Projects projects = new Projects(null, null);
//         JFrame frame = new JFrame("Projects Example");
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.add(projects);
//         frame.setSize(1200, 680);
//         frame.setVisible(true);
//     }
// }
