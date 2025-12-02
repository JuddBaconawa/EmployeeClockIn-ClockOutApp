package displayCards;

import java.awt.BorderLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
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

        

    }
  
}
