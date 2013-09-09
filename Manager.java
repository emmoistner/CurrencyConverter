/**
 * 
 * @author Matt Mandery
 *
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.*;


public class Manager extends User {
	int choice;
	private static String passInput;
	static Label info = new Label("Please enter the Admin password:"); // creates
																		// label
	static JFrame password = new JFrame("Currency Buddy"); // creates frame
	static Button submit = new Button("Submit"); // creates submit button
	static Button menu = new Button("Return to Main Menu"); // creates main menu
															// button
	static JPasswordField passwordField = new JPasswordField(200); // creates
																	// text
																	// field
	/*
	 * This Class paints the GUI and checks to make sure the password entered is correct.  If it is Client.manager() is called
	 */
	static Label error = new Label(
			"That is not the correct password, please try again"); 

	public void password() {

		password.setSize(400, 400);

		// closes if exit button is pressed
		password.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// makes background white
		password.setBackground(Color.white);

		// sets layout for frame
		password.getContentPane().setLayout(new GridLayout(4, 4));

		// create a submit button listener
		submit.addActionListener(new AddSubmitListener());

		// create a go back button listener
		menu.addActionListener(new MenuSubmitListener());

		// adds JButtons, JTextField and JLabel to JFrame
		password.add(info);
		password.add(passwordField);
		password.add(submit);
		password.add(menu);
		password.repaint();
		passwordField.setEchoChar('*');

		/*
		 * This method paints the Manager GUI
		 */
	}
	

	class AddSubmitListener implements ActionListener {

		@Override
    	//TO DO: fix it so that these methods don't run twice if jump to user and back. 
    		
			
    	    
    		
    	    public void actionPerformed(ActionEvent e) {
    	        String cmd = e.getActionCommand();

    	            char [] passInput = passwordField.getPassword();
    	            if (isPasswordCorrect(passInput)) {
    	                password.setVisible(false);
    	                Client.manager();
    	            } else {
    	                JOptionPane.showMessageDialog(frame,
    	                    "Invalid password. Try again.",
    	                    "Error Message",
    	                    JOptionPane.ERROR_MESSAGE);
    	            }

    	            //Zero out the possible password, for security.
    	            Arrays.fill(passInput, '0');

    	            passwordField.selectAll();
    			
    			
    		}
    		
	}
	
	

	private boolean isPasswordCorrect(char[] input) {
		boolean isCorrect = true;
		char[] correctPassword = { 'p', 'a', 's', 's', 'w', 'o', 'r', 'd' };

		if (input.length != correctPassword.length) {
			isCorrect = false;
		} else {
			isCorrect = Arrays.equals(input, correctPassword);
		}

		// Zero out the password.
		Arrays.fill(correctPassword, '0');

		return isCorrect;
		/*
		 * This method checks to make sure input from user is the correct password
		 */
	}

	static class MenuSubmitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			password.setVisible(false);
			User.frame.setVisible(true);

		}
		/*
		 * This accesses main menu GUI where conversions can be preformed. 
		 */

	}}

