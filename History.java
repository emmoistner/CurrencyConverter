/**
 * @Author Matt Mandery and Jinze Li
 */
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Object used to paint History Gui which ready History.txt and paints it to a
 * JTextArea.
 */
@SuppressWarnings("serial")
public class History extends JFrame {

	JFrame frame = new JFrame(); // creates new Frame
	JTextArea displayHistory = new JTextArea(); // Creates JTextArea
	ArrayList<String> histList = new ArrayList<String>(); // creates
															// ArrayList<String>
	Button close = new Button("Close"); // creates button
	JPanel panel1 = new JPanel(); // creates panel1
	JPanel panel2 = new JPanel();// creates panel2

	/**
	 * Method used to display History Gui
	 */
	public void History() {
		// sets layout
		frame.getContentPane().setLayout(new BorderLayout());
		// Sets JFrame title
		frame.setTitle("History");
		// sets size of gui
		frame.setSize(500, 400);
		// closes if exit button is pressed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// makes background white
		frame.setBackground(Color.white);

		// add panel
		panel1.add(displayHistory);
		// add panel2
		panel2.add(close);

		// adds panels to frame
		frame.add(panel1, BorderLayout.WEST);
		frame.add(panel2, BorderLayout.SOUTH);
		// sets teh array list
		setArrayList();
		// displays frame
		frame.setVisible(true);

		// adds action listener to close the GUI
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.dispose();
			}
		});

	}

	/**
	 * This method exports ArrayList<String> to History.txt and returns histList
	 */
	public ArrayList<String> fileExport() {

		try {
			FileWriter write = new FileWriter("History.txt");

			int i = 0;
			int counter = histList.toArray().length;
			while (i < counter) { // while i is less then array length, write to
									// History.txt
				write.append(histList.get(i) + "\n");
				i++;
			}
			write.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return histList;
	}

	/**
	 * This method imports History.txt and passes it into a ArrayList<String>
	 */
	public ArrayList<String> fileImport() {

		try {
			Scanner in = new Scanner(new File("History.txt"));

			// loops through and snags the currencies
			while (in.hasNextLine()) { // while there are elements in
										// History.txt keep reading
				String history = in.nextLine();
				histList.add(history);

			}
			in.close();
		} // CLOSE FILE
		catch (FileNotFoundException e) {
			System.out.println("File was not Found");
			e.printStackTrace();

		}

		return histList;
	}

	/**
	 * This method adds a String to the ArrayList<String> histList and exports
	 * the total list to History.txt
	 */
	public String addHistory(String hist) {
		histList.add(hist); // adds string to ArrayList<String>
		fileExport();// exports file
		return hist;

	}

	/**
	 * Completely erases ArrayList<String> and exports it to History.txt
	 */
	public ArrayList<String> deleteHistory() {
		histList.clear();// clears ArrayList<String>
		fileExport();

		return histList;
	}

	/**
	 * Retrieves the elements from ArrayList<String> (histList)
	 */
	public void setArrayList() {
		for (int i = 0; i < histList.toArray().length; i++) { // adds elements
																// to histList
			displayHistory.append(histList.get(i) + "\n");
		}

	}

}
