//packages


//imports
import javax.swing.JFrame;
import javax.swing.JPanel;


//GUI class
public class BaseGUI extends JFrame {

	protected JPanel panel;	//ensures that other class access within the same package + subclasses\\

	public BaseGUI(String title, int width, int height) {
		setTitle(title);
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		panel = new JPanel();
		panel.setLayout(null);
		add(panel);

	}


}
