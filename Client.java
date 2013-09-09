/**
 * 
 * @author Matt Mandery
 *
 */
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.text.StyledDocument;

public class Client extends User{
	static String clientName = "Currency Buddy: Client";
	static String managerName = "Currency Buddy: Manager";
	static JMenu currency = new JMenu("Administrator");
	/*
	 * This class passes needed information into paintGui class.  If a client, A blank JMenu is passed in.  Similarly, 
	 * if manager, a Administrator JMenu is passed in. 
	 */
	
	static void client(){
		//creates blank value to pass
		JMenu blank = new JMenu("");
		//passes to paintGui()
		paintGUI.paintGui(clientName, blank);
		/*
		 * Passes a blank JMenu into paintGui() in the paintGui class. 
		 */
	}
	static void manager(){
		paintGUI.paintGui(managerName, currency);
		/*
		 * Passes an Administrator into paintGui() in the paintGui class. 
		 */
	}

}