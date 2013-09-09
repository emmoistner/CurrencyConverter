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
import javax.swing.JTextField;
import java.awt.*;
import javax.swing.*; 



import java.applet.*;


public class User extends Main {
	int choice ;
	static JFrame frame = new JFrame("Currency Buddy");
	static Button user = new Button("User");
	static Button admin = new Button("Admin");
	static Label info = new Label("Please choose whether you are a Admin or a User");
	
	public void user(){
		//creates JFrame
		
		frame.setSize(575, 250);
		
		//closes if exit button is pressed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		//makes background white 
		frame.setBackground(Color.white);
		
		//sets layout for frame
		frame.getContentPane().setLayout(new GridLayout(2, 1));
		
		
		// Constructs the action listeners
	    ActionListener userListener = new AddUserListener();
		ActionListener adminListener = new AddAdminListener();
	
		admin.addActionListener(adminListener);
	    user.addActionListener(userListener);
	    
	    //sets sizes of buttons
	    user.setSize(150, 150);
	    admin.setSize(150, 150); 
	     
	    
	   
	    
	    //sets label in frame
	    info.setSize(50, 50);
	    info.setAlignment(Label.CENTER);
	    
	    // add the button to the layout
	    frame.add(user);
	    frame.add(admin);
	    frame.add(info);
	  
	    //sets buttons and makes it visible
	    
	    frame.setVisible(true);
	   
	   
	}
	   class AddAdminListener implements ActionListener { 
	    	@Override
	    	public void actionPerformed(ActionEvent e) {//jumps to Manager class
	    		// TODO Auto-generated method stub
	    	 	frame.setVisible(false);
	    	 	Manager.password.setVisible(true);
	    	 	return;  
	    	}
	    }
	  class AddUserListener implements ActionListener {

	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		frame.setVisible(false);
	    		Client.client();
	    		return;
	    	}
	    	
	    }
	 
	    
	}
	
