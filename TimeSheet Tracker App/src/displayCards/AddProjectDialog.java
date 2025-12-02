package displayCards;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class AddProjectDialog extends JDialog{

    private JTextField projectNameField;
    private JTextArea projectDescriptionArea;
    private JTextField hoursField;
    private JTextField maxHoursField;
    private JTextField deadlineField;

    private boolean confirmed = false;

    public AddProjectDialog(JFrame parent) {
        super(parent, "Add New Project", true);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        initLayout();
        pack();
        setLocationRelativeTo(parent);
    }

    //=========== Layout Initialization ===========//
    private void initLayout() {
        // Implementation of layout initialization
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

         GridBagConstraints gbc = new GridBagConstraints();
         gbc.insets = new Insets(5, 5, 5, 5);
         gbc.fill = GridBagConstraints.HORIZONTAL;

         int row = 0;

         // Project name
         addRow(formPanel, gbc, row++, "Project Name:", projectNameField);

         // Max Hours
         addRow(formPanel, gbc, row++, "Max Hours: ", maxHoursField);

         // DeadLine
         addRow(formPanel, gbc, row++, "Deadline: ", deadlineField);

         // Project Description
         addRow(formPanel, gbc, row++, "Project Description: ", projectDescriptionArea);

         // BUTTONS
         JPanel buttonPanel = new JPanel();
         JButton confirmButton = new JButton("Confirm");
         JButton cancelButton = new JButton("Cancel");
         buttonPanel.add(confirmButton);
         buttonPanel.add(cancelButton);

         add(formPanel, BorderLayout.CENTER);

         confirmButton.addActionListener(this::onConfirm);
         cancelButton.addActionListener(e -> dispose());

    }
  
}
