/*
 * Author Matt Mandery
 */
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BoundedRangeModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;


public class History extends JFrame{

	JFrame frame = new JFrame();
	JTextArea displayHistory = new JTextArea();
	ArrayList<String> histList = new ArrayList<String>();
	Button close = new Button("Close");
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	/*
	 * Object used to paint History Gui which ready History.txt and paints it to a JTextArea.
	 */
	
	public void History(){
		
		frame.getContentPane().setLayout(new BorderLayout());
		
		
		
		// Sets JFrame title
		frame.setTitle("History"); 
		// sets size of gui
		frame.setSize(500, 400);
		// closes if exit button is pressed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// makes background white
		frame.setBackground(Color.white);
		
		
		//add panel
		panel1.add(displayHistory);
		//add panel2
		panel2.add(close);
		
		
		
		frame.add(panel1, BorderLayout.WEST);
		frame.add(panel2, BorderLayout.SOUTH);
		
		setArrayList();
		
		frame.setVisible(true);
		
		
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.dispose();
			}
		});
		/*
		 * Method used to display History Gui
		 */
	}
	
	public ArrayList<String> fileExport() {
		
		try {
			FileWriter write = new FileWriter("History.txt");
			
			int i = 0;
			int counter = histList.toArray().length;
			while (i < counter) {
				write.append( histList.get(i) + "\n");
				i++;
			}
			write.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * This method exports ArrayList<String> to History.txt and returns histList
		 */
		return histList;
	}
	
	public ArrayList<String> fileImport() {
		
		// Counter for how many History references are in File
		/*
		 * First Scanner is for Finding how many History imputs are in the file
		 * being read in
		 */
		
		/*
		 * Second Scanner for taking values from the file and input the
		 * currencies from the file
		 */
		try {
			Scanner in = new Scanner(new File("History.txt"));
			
			// loops through and snags the currencies
			while(in.hasNextLine()) {
				String history = in.nextLine();
				histList.add(history);
				
			}
			in.close();
		} // CLOSE FILE
		catch (FileNotFoundException e) {
			System.out.println("File was not Found");
			e.printStackTrace();

		}
		/*
		 * This method imports History.txt and passes it into a ArrayList<String>
		 */
		return histList;
	}
	public String addHistory(String hist){
		histList.add(hist);
		fileExport();
		return hist;
		/*
		 * This method adds a String to the ArrayList<String> histList and exports the total list to History.txt
		 */
	}
	public ArrayList<String> deleteHistory(){
		histList.clear();
		fileExport();
		/*
		 * Completely erases ArrayList<String> and exports it to History.txt
		 */
		return histList;
	}
	public void setArrayList(){
		for (int i = 0; i < histList.toArray().length; i++) {
			displayHistory.append(histList.get(i) + "\n");
		}
		/*
		 * Retrieves the elements from ArrayList<String> (histList)
		 */
	}
	
}

