import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.util.*;

public class deleteGui {

	CurrencyConversion newConversion = new CurrencyConversion();
	FileManipulator File = new FileManipulator();
	USerror us = new USerror();
	JFrame delete = new JFrame(); // creates frame
	String[] currencies = newConversion.arrayListToArray();
	JComboBox list = new JComboBox();
	JButton deleteButton = new JButton("Delete");
	Label instructions = new Label(
			"Please select the currency you wish to delete from the list:");
	JButton goBack = new JButton("Go Back");

	public void deleteGui() {
		File.fileImport();
		list.removeAllItems();
		newConversion.addFromArrayList(File.getHMC(), File.getNames(),
				File.getRatios(), File.getCodes());

		currencies = newConversion.arrayListToArray();
		list.equals(currencies);
		list.removeAllItems();
		for (String n : currencies) {
			list.addItem(n);
			System.out.print(n + " ");
		}

		delete.add(instructions);

		delete.setSize(650, 300);

		// closes if exit button is pressed
		delete.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// makes background white
		delete.setBackground(Color.white);

		// sets layout for frame
		delete.getContentPane().setLayout(new GridBagLayout());

		instructions.setSize(50, 50);
		deleteButton.setSize(50, 50);
		list.setSize(50, 50);
		goBack.setSize(50, 50);

		list.setLocation(50, 100);
		goBack.setLocation(100, 100);

		delete.add(instructions);
		delete.add(list);
		delete.add(deleteButton);
		delete.add(goBack);
		delete.setVisible(true);

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String result = (String) list.getSelectedItem();
				if(result.equalsIgnoreCase("US Dollar")){
					us.errorGui();
					return;
				}
				else newConversion.deleteCurrency(result);
				
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
		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete.setVisible(false);
				paintGUI.guiMenu.repaint();
			}
		});

		return;

	}

} 
