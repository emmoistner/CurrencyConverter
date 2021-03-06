/**
 * 
 * @author Matt Mandery and Ethan Moistner
 *
 */
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * This class paints the GUI for the add a currency option in the program.
 * 
 */
@SuppressWarnings("serial")
public class addGui extends JFrame {
	// adds all the components for the JFrame
	CurrencyConversion newConversion = new CurrencyConversion();
	FileManipulator File = new FileManipulator(); // calls file manipulator
	static JFrame frame = new JFrame("Add a Currency"); // creates JFrame
	static JTextField name = new JTextField(200); // creates text field
	static JTextField ratio = new JTextField(200); // creates text field
	static JTextField code = new JTextField(200); // creates text field
	static JLabel nameInfo = new JLabel("Please enter the Name of the Currency"); // creates
																					// new
																					// Label

	static JLabel ratioInfo = new JLabel(
			"Please enter the Ratio of the Currency");// creates new Label
	static JLabel codeInfo = new JLabel("Please enter the code of the Currency");// creates
																					// new
																					// Label
	static JButton submit = new JButton("Save Currency");// creates new Label
	static JButton goBack = new JButton("Return to Manager Menu");// creates new
																	// Label

	/**
	 * This method imports currency list, paints the GUI and writes on
	 * Currency.txt when a currency is added.
	 */
	void paintAddGui() {
		File.fileImport();// imports Currency.txt
		newConversion.addFromArrayList(File.getHMC(), File.getNames(),
				File.getRatios(), File.getCodes());// calls CurrencyConversion
													// and calls
													// addFromArrayList

		// creates JFrame size
		frame.setSize(800, 600);// sets JFrame size

		// closes if exit button is pressed
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// makes background white
		frame.setBackground(Color.gray);

		// sets layout for frame
		frame.getContentPane().setLayout(new GridLayout(3, 4));
		// adds components to JFrame
		frame.add(nameInfo);
		frame.add(ratioInfo);
		frame.add(codeInfo);
		frame.add(name);
		frame.add(ratio);
		frame.add(code);
		frame.add(submit);
		frame.add(goBack);
		// set frame visible
		frame.setVisible(true);

		// adds action listeners
		submit.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				String newName = "Errored Currency";
				double newRatio = 0.00;
				String newCode = "???";
				newName = name.getText();
				try {
					newRatio = Double.parseDouble(ratio.getText());

				} catch (Exception e1) {
					addErrorGui error = new addErrorGui();
					error.errorGui();
					return;
				}

				newCode = code.getText();
				System.out.println(newName + " " + newRatio + " " + newCode);

				newConversion.addCurrency(newName, newRatio, newCode);
				frame.dispose();
				name.setText("");
				ratio.setText("");
				code.setText("");
				newConversion.sort();
				File.fileExport(File.getHMC() + 1, File.getCurrentDate(),
						newConversion);
				paintGUI.currencies = newConversion.arrayListToArray();
				paintGUI.guiMenu.repaint();
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
				frame.setVisible(false);
				paintGUI.guiMenu.repaint();

			}
		});

	}
}
