import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author Ethan Moistner Built to test Currency and CurrencyConversion Classes
 * 
 */
public class CurrencyConversionTester {

	public static void main(String[] args) throws IOException {
		CurrencyConversion NewConversion = new CurrencyConversion();
		FileManipulator inFile = new FileManipulator();
		inFile.fileImport();
		NewConversion.addFromArrayList(inFile.getHMC(), inFile.getNames(), inFile.getRatios(), inFile.getCodes());
	
		
		/*String Name = "name"; // Used in Scanning file
		Double Ratio = 1.0; // Used in Scanning file
		String Code = "code"; // Used in Scanning file
		String date = "00000000"; // Used in Scanning file
		int HMC = 0; // Counter for how many Currencies are in File
		
		/*
		 * First Scanner is for Finding how many currencies are in the file
		 * being read in
		 *
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
		 *
		try {
			Scanner in = new Scanner(new File("Currency.txt"));
			// BufferedReader inCurrency = new BufferedReader(in);

			// Grabs the Date from the top of the file
			date = in.nextLine();
			NewConversion.dateToString(date);

			// loops through and snags the currencies
			//String ratioIn = ""; //UNUSED
			for (int i = 0; i < HMC/3; i++) {

				Name = in.nextLine();
				System.out.println(Name); // Print out to make sure all
											// currencies values made it in
				Ratio = in.nextDouble();
				System.out.println(Ratio);
				in.nextLine();
				Code = in.nextLine();
				System.out.println(Code);
				NewConversion.addCurrency(Name, Ratio, Code);
			}

			NewConversion.Print(); // print to make sure currencies are located
									// the arrayList
			in.close();
		} // CLOSE FILE
		catch (FileNotFoundException e) {
			System.out.println("File was not Found");
			e.printStackTrace();

		}
		*/
		// YESS MOAR SCANNERS
		// For Inputting extra Currency Make sure add is working and sort
		// working correctly
		Scanner name = new Scanner(System.in);
		System.out.print("What's the name of the currency is being created? ");
		String j = "Name";
		try {
			j = name.nextLine();
		} catch (InputMismatchException e1) {
			System.out.println("There was error on input");
			System.exit(0);
		}
		// Scanner for Ratio of Keyboard fed Currency
		Scanner ratio = new Scanner(System.in);
		System.out.print("What's the ratio of the currency is being created? ");
		Double i = 0.0;
		try {
			i = ratio.nextDouble();
		} catch (InputMismatchException e1) {
			System.out
					.println("That was not the right input. Program Closing.");
			System.exit(0);
		}
		// Scanner for Currency Code of Keyboard fed Currency
		Scanner code = new Scanner(System.in);
		System.out
				.print("What's the Currency Code of the currency is being created? ");
		String a = "code";
		try {
			a = code.next();

		} catch (InputMismatchException e1) {
			System.out
					.println("That was not the right input. Program Closing.");
			System.exit(0);
		}
		// Scanner for Keyboard Fed Input Amount(HOW MOUCH DO YOU WANT
		// CONVERTED!?)
		Scanner inputAmount = new Scanner(System.in);
		System.out.print("How much is to be converted ");
		Double iA = 0.0;
		try {
			iA = inputAmount.nextDouble();
		} catch (InputMismatchException e1) {
			System.out
					.println("That was not the right input. Program Closing.");
			System.exit(0);
		}
		// Scanner FOR the "FROM" Currency
		Scanner C1 = new Scanner(System.in);
		System.out.print("Initial Currency? ");
		String Currency1 = "";
		try {
			Currency1 = C1.nextLine();
		} catch (InputMismatchException e1) {
			System.out
					.println("That was not the right input. Program Closing.");
			System.exit(0);
		}
		// Scanner for the "TO" Currency
		Scanner C2 = new Scanner(System.in);
		System.out.print("Convert to? ");
		String Currency2 = "";
		try {
			Currency2 = C2.nextLine();
		} catch (InputMismatchException e1) {
			System.out
					.println("That was not the right input. Program Closing.");
			System.exit(0);
		}
		NewConversion.addCurrency(j, i, a);// KEYBOARD fed Currency // Was put
											// in CurrencyList
		NewConversion.convert(iA, Currency1, Currency2); // Convert method!
		NewConversion.printConvert(); // make sure everything was correct
		NewConversion.sort(); // Sort
		inFile.fileExport(inFile.getHMC(), inFile.getCurrentDate(), NewConversion);
	}

}
