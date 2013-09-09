	import java.awt.BorderLayout;
	import java.awt.GridBagConstraints;
	import java.awt.GridBagLayout;
	import java.awt.Insets;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.ItemEvent;
	import java.awt.event.ItemListener;

	import javax.swing.JButton;
	import javax.swing.JComboBox;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditGui extends JFrame {
	
		CurrencyConversion newConversion = new CurrencyConversion();
		FileManipulator File = new FileManipulator();
		String [] currencies = newConversion.arrayListToArray();
		
		//frame
		JFrame frame;
		//panel
		JPanel panel1;
		//button
		JButton save, goBack;
		//label
		JLabel labelname, labelRate, labelcode;
		//textfield
		JTextField namefield, ratefield, codefield;
		//combobox
		JComboBox ratebox = new JComboBox();
		//string
		String name, rate, code;
		
		public void EditGui()
		{
			File.fileImport();
			ratebox.removeAllItems();
			newConversion.addFromArrayList(File.getHMC(), File.getNames(),
					File.getRatios(), File.getCodes());

			currencies = newConversion.arrayListToArray();
			//label
		    labelname = new JLabel("Name");
		    labelRate = new JLabel("Exchange Rate");
		    labelcode = new JLabel("Currency Code");
		   
		    //set text fields width
		    namefield = new JTextField(20);
		    ratefield = new JTextField(20);
		    codefield = new JTextField(20);
		    //button
		    save = new JButton("save");
			
			goBack = new JButton("Go Back");
			
			//box
			ratebox.equals(currencies);
			ratebox.removeAllItems();
			for (String n : currencies) {
				ratebox.addItem(n);
				System.out.print(n + " ");
			}
			
			
			ratebox = new JComboBox(currencies);
			
			MyItemListener actionListener = new MyItemListener();
			ratebox.addItemListener(actionListener);
			save.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Double newRatio = 0.0;
					String selected = (String) ratebox.getSelectedItem();
					Currency selectedItem = newConversion.search(selected);
					selectedItem.setName(namefield.getText());
					newRatio = Double.parseDouble(ratefield.getText());
					selectedItem.setRatio(newRatio);
					selectedItem.setCurrencyCode(codefield.getText());
					File.fileExport(File.getHMC(), File.getCurrentDate(), newConversion);
					
					
				}
			}
			);
			goBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
					paintGUI.guiMenu.repaint();
				}
			}	); //Not Finished
				
			
		    
		    //panel
		    panel1 = new JPanel();
			panel1.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			
			//position for the components
			//labels
			c.insets = new Insets(20,20,20,20);
			c.gridx = 0;
			c.gridy = 1;
			panel1.add(labelname,c);
			c.gridx = 0;
			c.gridy = 2;
			panel1.add(labelRate,c);
			c.gridx = 0;
			c.gridy = 3;
			panel1.add(labelcode,c);
			c.gridx = 1;
			c.gridy = 1;
			panel1.add( namefield,c);
			c.gridx = 1;
			c.gridy = 2;
			panel1.add(ratefield,c);
			c.gridx = 1;
			c.gridy = 3;
			panel1.add(codefield,c);
			
	       
			c.gridx = 2;
			c.gridy = 3;
			panel1.add(save,c);
			c.gridx = 4;
			c.gridy = 3;
			panel1.add(goBack,c);
			
			
			c.gridx = 3;
			c.gridy = 1;
			panel1.add(ratebox,c);
			
			frame = new JFrame();
			frame.setVisible(true);
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		    frame.setVisible(true);
			frame.add(panel1);
			 
			frame.pack();
		}
		class MyItemListener implements ItemListener {
		    // This method is called only if a new item has been selected.
		    public void itemStateChanged(ItemEvent evt) {
		        JComboBox ratebox = (JComboBox)evt.getSource();
		        name = (String) ratebox.getSelectedItem();
		        //rate will present the selected currency's conversion rate as a string. Convert double to string
		        String selectedItem = (String) ratebox.getSelectedItem();
		        rate = String.valueOf(newConversion.search(selectedItem).getRatio());
		        code = newConversion.search(selectedItem).getCurrencyCode();
		        
		        // Get the affected item
		        Object item = evt.getItem();

		        if (evt.getStateChange() == ItemEvent.SELECTED) {
		        	
		        	
		           namefield.setText(name);
		           ratefield.setText(rate);
		           codefield.setText(code);
		           
		        } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
		            // Item is no longer selected
		        }
		    }
		}	
	
}