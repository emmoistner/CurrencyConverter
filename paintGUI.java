/*
 * Author Matt Mandery
 */
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class paintGUI extends Client {
	// creates componenets for JFrame

	static CurrencyConversion newConversion = new CurrencyConversion();

	static String[] currencies = newConversion.arrayListToArray();
	static FileManipulator inFile = new FileManipulator();
	static History hist = new History();

	static String fileMenu = "File";
	static String historyMenu = "History";
	static String guiDrawMenu = "GUI";
	static String currMenu = "Currency";

	static JFrame guiMenu = new JFrame();

	static JMenu file = new JMenu(fileMenu);
	static JMenuItem quit = new JMenuItem("Quit");

	static JMenu history = new JMenu(historyMenu);
	static JMenuItem sorHistory = new JMenuItem("Show History");
	static JMenuItem delHistory = new JMenuItem("Delete Current History");

	
	static JMenuBar bar = new JMenuBar();

	static Label inputMessage = new Label(
			"Select the Currency you want to convert:");
	static Label outputMessage = new Label(
			"Select the Currency you want to convert to:");
	static Label writtenBy = new Label(
			"Program written by Matt, Ethan, and Jinze");
	static Label currencyAmount = new Label(
			"Please enter how much of the Currency you want to convert:");
	static Label outputAppearsHere = new Label("Your Conversion:");

	static JComboBox input = new JComboBox();
	static JComboBox output = new JComboBox();
	static JButton convert = new JButton("Convert");

	static JTextField inputAmount = new JTextField(22);

	static JTextField historyText = new JTextField(25);
	static JTextField outputText = new JTextField(22);

	static JMenuItem add = new JMenuItem("Add Currency");
	static JMenuItem del = new JMenuItem("Delete Currency");
	static JMenuItem edit = new JMenuItem("Edit Currency");
	/*
	 * This Class paints the main GUI for the program, and offers the user to be able to view history, 
	 * make conversions and delete the history
	 */

	static void paintGui(String name, final JMenu manager) {
		outputText.setEditable(false);
		inFile.fileImport();
		newConversion.addFromArrayList(inFile.getHMC(), inFile.getNames(),
				inFile.getRatios(), inFile.getCodes());

		currencies = newConversion.arrayListToArray();

		input.equals(currencies);
		output.equals(currencies);

		// sets layout
		setLayout();
		input.removeAllItems();
		output.removeAllItems();
		for (String n : currencies) {
			input.addItem(n);
			output.addItem(n);
			System.out.print(n + " ");
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
		
		history.add(sorHistory);
		history.add(delHistory);
		
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
		// guiMenu.add(historyText);

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
		guiMenu.setVisible(true);

		// adds action listeners

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
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				addGui add = new addGui();
				add.paintAddGui();

			}
		});
		del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteGui delete = new deleteGui();
				delete.deleteGui();

			}
		});
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditGui edit = new EditGui();
				edit.EditGui();
			}
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		delHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hist.deleteHistory();
			}
		});

		sorHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				History hist = new History();
				hist.fileImport();
				hist.History();
			}
		});
		/*
		 * Paints the paintGUI GUI and sets action listeners for the GUI
		 */
	}

	// sets layout, DEBUG
	static void setLayout() {
		// sets layout for manager

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

		return;
/*
 * This method sets the layout for paintGui GUI 
 */
	}

}
