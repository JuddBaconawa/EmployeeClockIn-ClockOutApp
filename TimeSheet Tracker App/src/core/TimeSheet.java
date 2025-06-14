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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;


import auth.LoginForm;
import models.User;
import pages.Home;
import pages.Profile;
import pages.Timelog;

import components.MenuPanel;
import components.TopInfoPanel;






// TimeSheet class
public class TimeSheet extends JFrame{

	private JPanel displayPanel;

	public void initialize(User user) {
		
		/*********************** Frame Setup *********************************/

		setTitle("Time Sheet Home");
		setUndecorated(true);						// Takes out the TimeSheet title bar
		setSize(1200, 801);						// Set the size of the frame	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setLayout(null);

		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));	// Rounded corners 10 radius


		/*********************** Display Panel Setup *************************/
		displayPanel = new JPanel(new java.awt.CardLayout());
		displayPanel.setBounds(200, 120, 1000, 681);	
		displayPanel.setBackground(new Color(240, 235, 216)); 

		// Add views to the display panel
		displayPanel.add(new Home(), "Home");			
		displayPanel.add(new Dashboard(), "Dashboard");	
		displayPanel.add(new Profile(), "Profile");	
		displayPanel.add(new Timelog(), "Time Log");	

		/************************ External Panels Add **************************/
		MenuPanel menuPanel = new MenuPanel(this, user);
		menuPanel.setBounds(0, 0, 200, 801);

		TopInfoPanel topInfoPanel = new TopInfoPanel(user);
		topInfoPanel.setBounds(200, 0, 1000, 120);	// Top info panel size is 1000 x 120
		
		/*********************** Add Panel to Frame **************************/
		// Add menu bar to the frame, no container is needed as it is being added to the main frame
		add(menuPanel);															//Menu bar on the left side  
		add(topInfoPanel);												// Add top info panel to the frame		
		add(displayPanel);												// Add display panel to the frame		
		
		setVisible(true);												// Set the frame to be visible
	}

	public JPanel getDisplayPanel() {
		return displayPanel;
	}

	public static void main(String[] args) {
		// Create a saple user Instance to test
			User user = new User();

			// Initialize the TimeSheet UI
			TimeSheet timesheet = new TimeSheet();
			timesheet.initialize(user);
	}


}
