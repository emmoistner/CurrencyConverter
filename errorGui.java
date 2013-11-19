import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Displays a JFrame when a user tries to input a string instead of Double value
 * in the convert method
 * 
 * @author Ethan Moistner
 * 
 */
public class errorGui {
	JFrame error = new JFrame(); // creates frame
	JButton okayButton = new JButton("Okay");
	Label instructions = new Label(
			"Please Input a valid amount of currency. Example 12.31:");

	public void errorGui() {

		error.add(instructions);

		error.setSize(400, 200);

		// closes if exit button is pressed
		error.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// makes background white
		error.setBackground(Color.WHITE);

		// sets layout for frame
		error.getContentPane().setLayout(new GridBagLayout());

		instructions.setSize(50, 50);
		okayButton.setSize(50, 50);

		okayButton.setLocation(100, 100);

		error.add(instructions);
		error.add(okayButton);
		error.setVisible(true);
		error.setLocation(300, 300);
		error.setTitle("Input Error");
		okayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error.setVisible(false);
				paintGUI.guiMenu.repaint();
				paintGUI.guiMenu.setVisible(true);
			}
		});

		return;

	}

}
