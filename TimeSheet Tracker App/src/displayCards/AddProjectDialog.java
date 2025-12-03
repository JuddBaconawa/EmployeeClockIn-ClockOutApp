// PACKAGE DECLARATION
package displayCards;

// IMPORTS
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.util.concurrent.Flow;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


// Dialog for adding a new project
public class AddProjectDialog extends JDialog{

    // Input fields
    private JTextField projectNameField;
    private JTextArea projectDescriptionArea;
    private JTextField hoursField;
    private JTextField maxHoursField;
    private JTextField deadlineField;

    // Confirmation flag
    private boolean confirmed = false;

    public AddProjectDialog(JFrame parent) {
        super(parent, "Add New Project", true);
        
        // Dialog properties
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize layout
        initLayout();
        pack();
        setLocationRelativeTo(parent);
    }

    private void initFields() {
        // Text fields for project details
        projectNameField = new JTextField(20);
        maxHoursField = new JTextField(10);
        deadlineField = new JTextField(10);

        // Text area for project description
        projectDescriptionArea = new JTextArea(4, 20);
        projectDescriptionArea.setLineWrap(true);
        projectDescriptionArea.setWrapStyleWord(true);
    }

    //=========== Layout Initialization ===========//
    private void initLayout() {
        // Implementation of layout initialization
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

         GridBagConstraints gbc = new GridBagConstraints();
         gbc.insets = new Insets(5, 5, 5, 5);
         gbc.fill = GridBagConstraints.HORIZONTAL;

         // Row counter
         int row = 0;
         
         // Project name
         addRow(formPanel, gbc, row++, "Project Name: ", projectNameField);
         // Max Hours
         addRow(formPanel, gbc, row++, "Max Hours: ", maxHoursField);
         // DeadLine
         addRow(formPanel, gbc, row++, "Deadline: ", deadlineField);
         // Project Description
         addRow(formPanel, gbc, row++, "Project Description: ", projectDescriptionArea);

         // BUTTONS - declared here for layout and text added 
         JPanel buttonPanel = new JPanel();
         JButton confirmButton = new JButton("Confirm");
         JButton cancelButton = new JButton("Cancel");

         // Adding buttons to the button Panel
         buttonPanel.add(confirmButton);
         buttonPanel.add(cancelButton);

         // Adding form and button panels to dialog
         add(formPanel, BorderLayout.CENTER);
         add(buttonPanel, BorderLayout.SOUTH);

         confirmButton.addActionListener(e -> onConfirm());
         cancelButton.addActionListener(e -> dispose());

    }

    // JTextArea Row Addition Helper Method
    private void addRow(JPanel panel, GridBagConstraints gbc, int row, String labelText, JTextField field) {
        // Implementation of adding a row with label and text field
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.gridy = row;
        panel.add(field, gbc);
        
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int row, String labelText, JTextField area) {
        // Implementation of adding row with label and text area
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.gridy = row;

        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setPreferredSize(new Dimension(250, 80));
        panel.add(scrollPane, gbc);
    }
  
}
