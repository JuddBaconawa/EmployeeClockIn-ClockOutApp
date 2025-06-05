// IMPORTS
import java.awt.Color;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

// TimeLog class
public class Timelog extends DisplayCard {
    public Timelog() {
      super("Timelog");
      setBackground(new Color(0, 40, 0));
      setLayout(null);
  
      // Example data
      String[] columns = {"Type", "Time"};
      Object[][] data = {
          {"Login", "08:00 AM"},
          {"Break", "12:00 PM"},
          {"Logout", "05:00 PM"},
          {"Login", "06:00 PM"},
          {"Break", "07:00 PM"},
          {"Logout", "08:00 PM"}
      };
  
      JTable table = new JTable(data, columns);
      table.setRowHeight(30);
  
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
      scrollPane.setBounds(50, 50, 400, 200);
      add(scrollPane);
      } 
  
      public static void main(String[] args) {
        JFrame frame = new JFrame("Timelog Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 680);
        frame.add(new Timelog());
        frame.setVisible(true);
    }
}
