package displayCards;

// IMPORTS
import java.awt.Color;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import components.DisplayCard;
import dao.TimelogDAO;
import models.TimelogEntry;


import java.awt.BorderLayout;
import java.awt.Component;


// TimeLog class
public class Timelog extends DisplayCard {
    
    private JTable table;
    private TimelogDAO timelogDAO;
    private String[] columns = {"Project", "Clock In", "Clock Out", "Total Hours", "Date"};

    public Timelog(TimelogDAO dao) {
        
      super("Timelog");
      this.timelogDAO = dao;

      setBackground(new Color(0, 40, 0));
      setBackground(Color.GREEN);
      setLayout(new BorderLayout());

      // create a panel for the timelog table
      JPanel tableContainer = new JPanel(new BorderLayout());
      tableContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
      tableContainer.setBackground(new Color(240, 235, 216));


  

  
      // Custom renderer for coloring text
      table.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
          @Override
          public Component getTableCellRendererComponent(JTable table, Object value,
                  boolean isSelected, boolean hasFocus, int row, int column) {
              Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
              String type = value.toString();
              if (type.equalsIgnoreCase("Login")) {
                  c.setForeground(Color.GREEN.darker());
              } else if (type.equalsIgnoreCase("Logout")) {
                  c.setForeground(Color.RED);
              } else if (type.equalsIgnoreCase("Break")) {
                  c.setForeground(Color.ORANGE);
              } else {
                  c.setForeground(Color.BLACK);
              }
              return c;
          }
      });
  
      JScrollPane scrollPane = new JScrollPane(table);
    //   scrollPane.setBounds(50, 150, 400, 200);
      
      add(scrollPane, BorderLayout.CENTER);
    } 

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;
        g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                             java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 32));
        g2d.setColor(java.awt.Color.LIGHT_GRAY);
        g2d.drawString("Timelog", 30, 60);
    }
  
      public static void main(String[] args) {
        JFrame frame = new JFrame("Timelog Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 680);
        frame.add(new Timelog());
        frame.setVisible(true);
    }
}
