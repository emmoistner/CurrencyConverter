/**
 * @Author Matt Mandery and Ethan Moistner
 */
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

/**
 * This Class paints the main GUI for the program, and offers the user to be
 * able to view history, make conversions and delete the history
 */
public class paintGUI extends Client {
	// creates componenets for JFrame

	static CurrencyConversion newConversion = new CurrencyConversion(); // calls
																		// CurrencyConversion
																		// object

	@SuppressWarnings("static-access")
	static String[] currencies = newConversion.arrayListToArray(); // adds
																	// arraylist
																	// from
																	// newConversion
																	// to string
																	// array
																	// currencies
	static FileManipulator inFile = new FileManipulator();// calls
															// FileManipulator
															// object
	static History hist = new History(); // calls History object

	static String fileMenu = "File"; // sets string
	static String historyMenu = "History"; // sets string
	static String guiDrawMenu = "GUI";// sets string
	static String currMenu = "Currency";// sets string

	static JFrame guiMenu = new JFrame();// sets JFrame

	static JMenu file = new JMenu(fileMenu);// sets JMenu
	static JMenuItem quit = new JMenuItem("Quit");// sets JMenuItem

	static JMenu history = new JMenu(historyMenu);// sets JMenu
	static JMenuItem sorHistory = new JMenuItem("Show History");// sets
																// JMenuItem
	static JMenuItem delHistory = new JMenuItem("Delete Current History");// sets
																			// JMenuItem

	static JMenuBar bar = new JMenuBar();// creates JMenuBar

	static Label inputMessage = new Label(
			"Select the Currency you want to convert:");// creates a label
	static Label outputMessage = new Label(
			"Select the Currency you want to convert to:");// creates a label
	static Label writtenBy = new Label(
			"Program written by Matt, Ethan, and Jinze");// creates a label
	static Label currencyAmount = new Label(
			"Please enter how much of the Currency you want to convert:"); // creates
																			// a
																			// label
	static Label outputAppearsHere = new Label("Your Conversion:");

	static JComboBox input = new JComboBox();// creates a JComboBox
	static JComboBox output = new JComboBox();// creates a JComboBox
	static JButton convert = new JButton("Convert");// creates a JButton

	static JTextField inputAmount = new JTextField(22);// creates a JTextField

	static JTextField historyText = new JTextField(25);// creates a JTextField
	static JTextField outputText = new JTextField(22);// creates a JTextField

	static JMenuItem add = new JMenuItem("Add Currency");// creates a JMenuItem
	static JMenuItem del = new JMenuItem("Delete Currency");// creates a
															// JMenuItem
	static JMenuItem edit = new JMenuItem("Edit Currency");// creates a
															// JMenuItem

	/**
	 * Paints the paintGUI GUI and sets action listeners for the paintGui class
	 */
	@SuppressWarnings("static-access")
	static void paintGui(String name, final JMenu manager) {
		outputText.setEditable(false);
		inFile.fileImport();// calls FileManipulator object
		newConversion.addFromArrayList(inFile.getHMC(), inFile.getNames(),
				inFile.getRatios(), inFile.getCodes());// calls
														// CurrencyConverter
														// object and passes in
														// necessary data

		currencies = newConversion.arrayListToArray();// populates array list
														// from object
														// newConversion

		input.equals(currencies);// sets JComboBox equal to array
		output.equals(currencies);// sets JComboBox equal to array

		// sets layout
		setLayout();
		input.removeAllItems();
		output.removeAllItems();
		for (String n : currencies) { // adds strings to JComboBoxes
			input.addItem(n);
			output.addItem(n);
		}

		guiMenu.setTitle(name); // Sets JFrame title

		// sets size of gui
		guiMenu.setSize(610, 600);

		// closes if exit button is pressed
		guiMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// makes background white
		guiMenu.setBackground(Color.gray);

		// adds buttons to JMenu
		file.add(quit);
		// adds History
		history.add(sorHistory);
		history.add(delHistory);
		// adds JMenuBar
		guiMenu.setJMenuBar(bar);
		guiMenu.getContentPane();

		// adds buttons to menu
		bar.add(file);
		bar.add(history);

		// paints guiMenu
		guiMenu.add(input);
		guiMenu.add(output);
		guiMenu.add(convert);
		guiMenu.add(inputAmount);
		guiMenu.add(outputText);
		guiMenu.add(inputMessage);
		guiMenu.add(outputMessage);
		guiMenu.add(currencyAmount);
		guiMenu.add(writtenBy);
		guiMenu.add(outputAppearsHere);

		// adds Manager button JMenu
		if (manager.getText().equals("")) {
			bar.setVisible(true);

		} else {
			manager.add(add);
			manager.add(del);
			manager.add(edit);
			bar.add(manager);
			bar.setVisible(true);

		}
		// paints gui
		guiMenu.setVisible(true);

		// adds action listeners. If convert button is pressed, takes in the two
		// currencies and amount wanted to convert and
		// calls newConversion object to do the conversion.
		convert.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String getInput;
				String getOutput;

				getInput = (input.getSelectedItem().toString());
				getOutput = (output.getSelectedItem().toString());
				try {

					double getInputAmount = Double.parseDouble(inputAmount
							.getText());

					double result = newConversion.convert(getInputAmount,
							getInput, getOutput);
					newConversion.formatDecimal(result);
					String resultString = Double.toString(result);
					String outputResult = (getInput + "  to " + getOutput
							+ ": " + resultString);
					outputText.setText(outputResult);

					hist.addHistory(outputResult);

					guiMenu.repaint();
					guiMenu.setVisible(true);

				} catch (Exception e1) {
					errorGui error = new errorGui();
					error.errorGui();

				}

			}
		});
		// action listener to add a currency. calls addGui class.
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				addGui add = new addGui();
				add.paintAddGui();

			}
		});
		// action listener for delete a currency. calls deleteGui class
		del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteGui delete = new deleteGui();
				delete.deleteGui();

			}
		});
		// action listener for editing a currency. calls EditGui class
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditGui edit = new EditGui();
				edit.EditGui();
			}
		});
		// action listener for quiting the program
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// action listener for deleting a history.
		delHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hist.deleteHistory();
			}
		});
		// actions listener to show history. Imports History.txt
		sorHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				History hist = new History();
				hist.fileImport();
				hist.History();
			}
		});

	}

	/**
	 * This method sets the layout for paintGui GUI
	 */
	static void setLayout() {
		// sets layout for gui
		GridBagLayout gbl = new GridBagLayout();
		final GridBagConstraints c = new GridBagConstraints();

		// Set layout on container
		guiMenu.setLayout(gbl);

		c.fill = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 1;
		c.gridy = 1;
		gbl.setConstraints(inputMessage, c); // sets Label inputMessage

		c.gridx = 1;
		c.gridy = 2;
		gbl.setConstraints(input, c); // sets JComboBox input

		c.fill = GridBagConstraints.LINE_START;
		c.gridx = 1;
		c.gridy = 3;
		gbl.setConstraints(outputMessage, c);// sets label outputMessage

		c.gridx = 1;
		c.gridy = 4;
		gbl.setConstraints(output, c); // sets JComboBox output

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 6;
		gbl.setConstraints(inputAmount, c);// sets JTextField inputAmount

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 6;
		gbl.setConstraints(currencyAmount, c); // sets Label currencyAmount
												// "Please enter amount"

		c.gridx = 2;
		c.gridy = 4;
		gbl.setConstraints(convert, c); // sets JButton convert

		c.gridx = 2;
		c.gridy = 7;
		gbl.setConstraints(outputText, c); // sets JTextField outputText

		c.gridx = 1;
		c.gridy = 7;
		gbl.setConstraints(outputAppearsHere, c); // sets Label
													// outputAppearsHere

		c.gridx = 1;
		c.gridy = 12;
		gbl.setConstraints(writtenBy, c); // sets Label writtenBy

		c.gridx = 4;
		c.gridy = 1;
		c.gridwidth = 3;
		c.gridheight = 10;
		gbl.setConstraints(historyText, c); // sets Label writtenBy

	}

}
