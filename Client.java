/**
 * 
 * @author Matt Mandery
 *
 */
import javax.swing.JMenu;
/**
 * This class passes needed information into paintGui class.  If a client, A blank JMenu is passed in.  Similarly, 
 * if manager, a Administrator JMenu is passed in. 
 */
public class Client extends User{
	static String clientName = "Currency Buddy: Client";//creates string
	static String managerName = "Currency Buddy: Manager";//creates string
	static JMenu currency = new JMenu("Administrator");// creates JMenu
	
	/**
	 * Passes a blank JMenu into paintGui() in the paintGui class. 
	 */
	static void client(){
		//creates blank value to pass
		JMenu blank = new JMenu("");
		//passes to paintGui()
		paintGUI.paintGui(clientName, blank);
		
	}
	/**
	 * Passes an Administrator into paintGui() in the paintGui class. 
	 */
	static void manager(){
		paintGUI.paintGui(managerName, currency);
		
	}

}