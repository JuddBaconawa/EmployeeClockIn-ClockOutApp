package core;
// Packages

// IMPORTS
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.Box;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import auth.LoginForm;
import components.TopInfoPanel;
import components.MenuPanel;
import models.User;
import pages.Dashboard;
import pages.Home;
import pages.Profile;
import pages.Timelog;

import javax.swing.border.EmptyBorder;



// TimeSheet class
public class TimeSheet extends JFrame{

	private JPanel displayPanel;
	private MenuPanel menuPanel;
	private TopInfoPanel topInfoPanel;


	public void initialize(User user) {

		setupFrame();
		
		/*********************** Frame Setup *********************************/
		JPanel mainPanel = new JPanel(new BorderLayout());

		/*********************** Main Panel Settings *************************/
		menuPanel = new MenuPanel(this, user);
		mainPanel.add(menuPanel, BorderLayout.WEST);


		/*********************** Top Info Panel ******************************/
		topInfoPanel = new TopInfoPanel(this, user);
		topInfoPanel.setPreferredSize(new Dimension(0, 50));
		topInfoPanel.setBackground(new Color(0, 0, 0, 0));
		mainPanel.add(topInfoPanel, BorderLayout.NORTH);

		/*********************** Display Panel *******************************/
		

		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBounds(0, 0, getWidth(), getHeight());
		add(mainPanel);
		menuPanel = new MenuPanel(this, user);
		mainPanel.add(menuPanel, BorderLayout.WEST);
		mainPanel.add(menuPanel, BorderLayout.WEST);
		


		/*********************** (Side) Menu Bar/Panel *******************************/
		

		

		/******************** (Side) Menu Bar/Panel Settings ***********************/

		
		
	}

	private void setupFrame() {
		setTitle("Time Sheet Home");
		setUndecorated(true);						// Takes out the TimeSheet title bar
		setSize(1200, 801);						// Set the size of the frame	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setLayout(null);

		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));	

	}

	public static void main(String[] args) {
		// Create a saple user Instance to test
			User user = new User();

			// Initialize the TimeSheet UI
			TimeSheet timesheet = new TimeSheet();
			timesheet.initialize(user);
	}


}
