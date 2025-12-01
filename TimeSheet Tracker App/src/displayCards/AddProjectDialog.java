package displayCards;

import javax.swing.JDialog;

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
    
  
}
