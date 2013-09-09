import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class FileManipulator {
	private ArrayList<String> nameList;
	private ArrayList<Double> ratioList;
	private ArrayList<String> codeList;
	private String date;
	private int HMC;

	public FileManipulator() {
		setDate("0000000");
		HMC = 0;
		nameList = new ArrayList<String>();
		ratioList = new ArrayList<Double>();
		codeList = new ArrayList<String>();

	}

	public String getCurrentDate() {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyy");
		String dateNow = "0000000";
		dateNow = formatter.format(currentDate.getTime());
		return dateNow;

	}

	public void fileExport(int hmc, String date, CurrencyConversion currency) {
		try {
			FileWriter write = new FileWriter("Currency.txt");
			write.write(date + System.getProperty( "line.separator" ));
			int i = 0;
			while (i < hmc) {
				write.write(currency.getCurrency(i).getName()
						+ System.getProperty("line.separator"));
				write.write(String.valueOf(currency.getCurrency(i).getRatio())
						+ System.getProperty("line.separator"));
				write.write(currency.getCurrency(i).getCurrencyCode()
						+ System.getProperty("line.separator"));
				i++;
			}
			write.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void fileImport() {
		String Name = "name"; // Used in Scanning file
		Double Ratio = 1.0; // Used in Scanning file
		String Code = "code"; // Used in Scanning file
		// String date = "00000000"; // Used in Scanning file
		// Counter for how many Currencies are in File
		/*
		 * First Scanner is for Finding how many currencies are in the file
		 * being read in
		 */
		try {
			Scanner CCA = new Scanner(new File("Currency.txt"));
			CCA.nextLine(); // Eat up the date
			while (CCA.hasNextLine()) {
				CCA.nextLine();
				HMC++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("File was not Found");
			e.printStackTrace();

		}
		/*
		 * Second Scanner for taking values from the file and input the
		 * currencies from the file
		 */
		try {

			Scanner in = new Scanner(new File("Currency.txt"));
			// BufferedReader inCurrency = new BufferedReader(in);

			// Grabs the Date from the top of the file
			setDate(in.nextLine());
			// .dateToString(date);

			// loops through and snags the currencies
			// String ratioIn = ""; //UNUSED
			for (int i = 0; i < HMC / 3; i++) {

				Name = in.nextLine();
				System.out.println(Name); // Print out to make sure all
				nameList.add(Name); // currencies values made it in
				Ratio = in.nextDouble();
				System.out.println(Ratio);
				ratioList.add(Ratio);
				in.nextLine();
				Code = in.nextLine();
				System.out.println(Code);
				codeList.add(Code);

				// k.addCurrency(Name, Ratio, Code);
			}

			// k.Print(); // print to make sure currencies are located
			// the arrayList
			in.close();
		} // CLOSE FILE
		catch (FileNotFoundException e) {
			System.out.println("File was not Found");
			e.printStackTrace();

		}
	}

	public ArrayList<String> getNames() {
		return nameList;

	}

	public ArrayList<Double> getRatios() {
		return ratioList;

	}

	public ArrayList<String> getCodes() {
		return codeList;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public int getHMC() {
		return HMC / 3;

	}
}