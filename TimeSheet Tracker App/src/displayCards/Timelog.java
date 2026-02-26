package displayCards;

// IMPORTS
import java.awt.Color;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import components.DisplayCard;
import dao.TimelogDAO;
import models.TimelogEntry;
import components.TitlePanel;
import components.dashboard.StatusIndicator;


import java.awt.BorderLayout;
import java.awt.Component;
import java.sql.Connection;


// TimeLog class
public class Timelog extends DisplayCard {
    
    private Connection conn;
    private StatusIndicator statusIndicator;
    private JLabel statusText;
    private JTable table;
    private TimelogDAO timelogDAO;
    private String[] columns = {"Project", "Clock In", "Clock Out", "Total Hours", "Date"};
    private TitlePanel titlePanel;
    private int userId;

    public Timelog(TimelogDAO dao, int userId) {

      super("Timelog");
      this.userId = userId;
      this.timelogDAO = dao;

      // timelog background and layout
    //   setBackground(new Color(0, 40, 0));
      setBackground(Color.GREEN);
      setLayout(new BorderLayout());

      // title Panel
      titlePanel = new TitlePanel("TimeLog", new StatusIndicator());
      titlePanel.setBackgroundColor(new Color(213, 180, 130));
      add(titlePanel, BorderLayout.NORTH);



      // create a panel for the timelog table
      JPanel tableContainer = new JPanel(new BorderLayout());
      tableContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
      tableContainer.setBackground(new Color(240, 235, 216));

      // table initialization
      table = new JTable();

      // set table model
      DefaultTableModel model = new DefaultTableModel(columns, 0);
      table.setModel(model);

  

  
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


    private void loadTimeLogData() {
        
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Timelog Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 680);
        frame.setLayout(new BorderLayout());

        TimelogDAO dao = new TimelogDAO(null); // temp
        frame.add(new Timelog(dao), BorderLayout.CENTER);

        frame.setVisible(true);
    }

}
