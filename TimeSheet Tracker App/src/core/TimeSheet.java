package core;
// Packages

// IMPORTS
import components.MenuPanel;

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
import models.User;
import pages.Dashboard;
import pages.Home;
import pages.Profile;
import pages.Timelog;
import components.TopInfoPanel;


import javax.swing.border.EmptyBorder;





// TimeSheet class
public class TimeSheet extends JFrame{

	private JPanel displayPanel;

	public void initialize(User user) {
		
		/*********************** Frame Setup *********************************/

		setTitle("Time Sheet Home");
		setUndecorated(true);						// Takes out the TimeSheet title bar
		setSize(1200, 800);						// Set the size of the frame	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setLayout(null);

		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));	// Rounded corners 10 radius

		/********************* Main Panel Settings ***********************/
		JPanel mainPanel = new JPanel(null);
		mainPanel.setBounds(0, 0, 1200, 800);			//panel size is 1200 x 800
		mainPanel.setBackground(new Color(240, 235, 216));


		/*********************** Menu Bar Panel *********************************/
		JPanel menuPanel = new MenuPanel(this, user);
		menuPanel.setBounds(0, 0, 200, getHeight()); // Example bounds, adjust as needed
		menuPanel.setBackground(new Color(13, 19, 33)); // Example color, adjust as needed
		mainPanel.add(menuPanel);
		// Use the custom MenuPanel instead of TopInfoPanel for the menu



		/********************* Top Info Panel Settings ******************************/
		// Used to add sections into the main panel
		JPanel topInfoPanel = new TopInfoPanel(user);
		topInfoPanel.setBounds(200, 0, 1000, 120); // Example bounds, adjust as needed
		topInfoPanel.setBackground(new Color(62, 92, 118)); // Example color, adjust as needed
		topInfoPanel.setBorder(new MatteBorder(0, 0, 1, 0, new Color(220, 220, 220))); // Example border, adjust as needed
		mainPanel.add(topInfoPanel); // Add top info panel to the main panel

		/******************************* (Views) Display Panel ***********************************/
		displayPanel = new JPanel(new java.awt.CardLayout());
		displayPanel.setBounds(200, 120, 1000, 680);
		displayPanel.setBackground(new Color(240, 235, 216));
		displayPanel.add(new Home(), "Home");
		displayPanel.add(new Dashboard(), "Dashboard");
		displayPanel.add(new Profile(), "Profile");
		displayPanel.add(new Timelog(), "Time Log");
		
		/*********************** Add Panel to Frame **************************/
		mainPanel.add(displayPanel);					// Add display panel to the main panel
		// add(displayPanel);												// Add display panel to the frame		
		add(mainPanel);														// Add main panel to the frame					
		// add(menuPanel);														// Add menu panel to the frame
	}

	public static void main(String[] args) {
		// Create a saple user Instance to test
			User user = new User();

			// Initialize the TimeSheet UI
			TimeSheet timesheet = new TimeSheet();
			timesheet.initialize(user);
	}


}
