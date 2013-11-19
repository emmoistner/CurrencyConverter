/**
 * @Author Matt Mandery and Ethan Moistner
 */
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 * Class called to delete currencies from the currency list
 */
public class deleteGui {

	CurrencyConversion newConversion = new CurrencyConversion(); // calls
																	// CurrencyConversion
																	// object
	FileManipulator File = new FileManipulator();// calls FileManipulator object
	USerror us = new USerror(); // calls USerror object
	JFrame delete = new JFrame(); // creates frame
	@SuppressWarnings("static-access")
	String[] currencies = newConversion.arrayListToArray();// creates a string
															// array and places
															// newConversion.arrayListToArray()
															// inside
	JComboBox list = new JComboBox();// creates a JComboBox
	JButton deleteButton = new JButton("Delete"); // Creates a JButton
	Label instructions = new Label(
			"Please select the currency you wish to delete from the list:"); // Creates
																				// a
																				// Label
	JButton goBack = new JButton("Go Back"); // Creates a go back button

	/**
	 * Method used to paint the gui, and add action listeners to delete a
	 * currency from the list, or to go back to the previous menu.
	 */
	@SuppressWarnings("static-access")
	public void deleteGui() {
		File.fileImport();// calls fileImport()
		list.removeAllItems();// removes all items from list
		newConversion.addFromArrayList(File.getHMC(), File.getNames(),
				File.getRatios(), File.getCodes()); // converts in newConversion
													// object

		currencies = newConversion.arrayListToArray(); // places array list into
														// String array
														// currencies
		list.equals(currencies);// makes list equal currencies
		list.removeAllItems();// removes all items from list
		for (String n : currencies) { // adds all strings from currencies into
										// the array list
			list.addItem(n);
		}

		delete.add(instructions); // adds a label to Frame

		delete.setSize(850, 400);// sets size for Frame

		// closes if exit button is pressed
		delete.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// makes background white
		delete.setBackground(Color.white);

		// sets layout for frame
		delete.getContentPane().setLayout(new GridBagLayout());
		// sets layout for the frame
		instructions.setSize(50, 50);
		deleteButton.setSize(50, 50);
		list.setSize(50, 50);
		goBack.setSize(50, 50);

		list.setLocation(50, 100);
		goBack.setLocation(100, 100);
		// adds components to Frame
		delete.add(instructions);
		delete.add(list);
		delete.add(deleteButton);
		delete.add(goBack);
		delete.setVisible(true);
		// adds action listener that deletes the array from the list
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String result = (String) list.getSelectedItem();
				if (result.equalsIgnoreCase("US Dollar")) {
					us.errorGui();
					return;
				} else
					newConversion.deleteCurrency(result);

				delete.dispose();
				newConversion.sort();
				File.fileExport(File.getHMC() - 1, File.getCurrentDate(),
						newConversion);
				paintGUI.guiMenu.repaint();
				paintGUI.currencies = newConversion.arrayListToArray();
				paintGUI.input.removeAllItems();
				paintGUI.output.removeAllItems();
				for (String str : paintGUI.currencies) {
					paintGUI.input.addItem(str);
					paintGUI.output.addItem(str);

				}
			}
		});
		// adds a action listener that setsVisible false
		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete.setVisible(false);
				paintGUI.guiMenu.repaint();
			}
		});

	}

}
