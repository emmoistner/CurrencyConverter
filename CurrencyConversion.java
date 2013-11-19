import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Class was built to Create a list of Currencies via methods convert send
 * results of conversions to Client and Manager GUI's
 * 
 * @author Ethan Moistner
 * 
 * 
 * 
 */

public class CurrencyConversion {

	public static ArrayList<Currency> CurrencyList;
	private Double Conversion;

	public CurrencyConversion() {
		CurrencyList = new ArrayList<Currency>();
		Conversion = 0.0;

	}

	/**
	 * Searches for specified currencies within CurrencyList and checks their
	 * ratios compared to each other. If the first currency is less than second,
	 * the conversion ratio equals one divided by the first currency's ratio. It
	 * then Multiplies the Input amount which get's in terms of US Dollar. It
	 * then multiplies that amount by the second currency's ratio to get the
	 * final converted amount.
	 * 
	 * If the first currency's ratio is larger than the second it takes the
	 * input amount and divides it by the first currency's ratio and multiplies
	 * that amount by the second currencies ratio
	 * 
	 * If the first currency is the US dollar the second currency's ratio
	 * multiplies the input amount.
	 * 
	 * If the same currency is chosen to convert from and to the input amount is
	 * just returned.
	 * 
	 * @param InputAmount
	 *            Amount of that is to be converted
	 * @param currency1
	 *            the "From" currency
	 * @param currency2
	 *            the "To" currency
	 * @return the value that is found by calculating the input and ratios
	 */
	public Double convert(Double InputAmount, String currency1, String currency2) {

		Double ConversionRatio = 0.0;
		if (search(currency1).getRatio() < search(currency2).getRatio()) {
			ConversionRatio = (1 / search(currency1).getRatio());
			Conversion = InputAmount * ConversionRatio;
			Conversion = Conversion * search(currency2).getRatio();
		}

		if (search(currency1).getRatio() > search(currency2).getRatio()) {
			ConversionRatio = (search(currency1).getRatio());
			Conversion = InputAmount / ConversionRatio;
			Conversion = Conversion * search(currency2).getRatio();

		}
		if (search(currency1).getRatio() == search("US Dollar").getRatio()) {
			ConversionRatio = (search(currency2).getRatio());
			Conversion = InputAmount * ConversionRatio;
		}
		if (search(currency1).getRatio() == search(currency2).getRatio()) {
			Conversion = InputAmount;
		}
		Conversion = formatDecimal(Conversion);
		return Conversion;

	}

	/**
	 * Formats a double into a Currency Value
	 * 
	 * @param dbl
	 *            amount to be formatted
	 * @return the formatted double to second decimal place
	 */
	public double formatDecimal(Double dbl) {
		DecimalFormat twoDecimal = new DecimalFormat("#.##");
		return Double.valueOf(twoDecimal.format(dbl));
	}

	/**
	 * Converts the standard MMDDYYYY input from file in a easy to read format
	 * 
	 * @param date
	 *            MMDDYYYY from file input, For Example, 03032012
	 * @return Standard date. For Example, March 03, 2012
	 */
	public String dateToString(String date) {
		char[] charArray = new char[date.length()];
		String[] Date = new String[3];
		date.getChars(0, date.length(), charArray, 0);
		if (charArray[0] > '0' && charArray[1] == '0') {
			Date[0] = "October ";
		}
		if (charArray[0] > '0' && charArray[1] == '1') {
			Date[0] = "November ";
		}
		if (charArray[0] > '0' && charArray[1] == '2') {
			Date[0] = "December ";
		}
		if (charArray[0] == '0' && charArray[1] == '1') {
			Date[0] = "January ";
		}
		if (charArray[0] == '0' && charArray[1] == '2') {
			Date[0] = "Febuary ";
		}
		if (charArray[0] == '0' && charArray[1] == '3') {
			Date[0] = "March ";
		}
		if (charArray[0] == '0' && charArray[1] == '4') {
			Date[0] = "April ";
		}
		if (charArray[0] == '0' && charArray[1] == '5') {
			Date[0] = "May ";
		}
		if (charArray[0] == '0' && charArray[1] == '6') {
			Date[0] = "June ";
		}
		if (charArray[0] == '0' && charArray[1] == '7') {
			Date[0] = "July ";
		}
		if (charArray[0] == '0' && charArray[1] == '8') {
			Date[0] = "August ";
		}
		if (charArray[0] == '0' && charArray[1] == '9') {
			Date[0] = "September ";
		}
		String day = Character.toString(charArray[2]).concat(
				Character.toString(charArray[3]).concat(", "));
		Date[1] = day;
		String year = Character.toString(charArray[4]).concat(
				Character.toString(charArray[5]).concat(
						Character.toString(charArray[6]).concat(
								Character.toString(charArray[7]))));
		Date[2] = year;

		System.out.println();
		String finishedDate = Date[0].concat(day.concat(year));
		return finishedDate;
	}

	// USED FOR TESTING ONLY
	public void printConvert() {
		formatDecimal(Conversion);
		System.out.println(Conversion);
	}

	/**
	 * Getter for specified currency
	 * 
	 * @param currency
	 * @return specified currency
	 */
	public Currency getCurrency(int currency) {

		return CurrencyList.get(currency);
	}

	/**
	 * Getter for CurrencyList where currencies are stored
	 * 
	 * @return
	 */
	public ArrayList<Currency> getCurrencyList() {
		return CurrencyList;

	}

	/**
	 * Deletes a Currency from the ArrayList.
	 * 
	 * @param currency
	 *            Removes the currency with the Name specified
	 */
	public void deleteCurrency(String currency) {
		CurrencyList.remove(search(currency));
	}

	public void addFromArrayList(int HMC, ArrayList<String> names,
			ArrayList<Double> ratios, ArrayList<String> codes) {
		for (int i = 0; i < HMC; i++) {

			addCurrency(names.get(i), ratios.get(i), codes.get(i));
		}
	}

	/**
	 * Adds a specified currency to the ArrayList.
	 * 
	 * @param name
	 *            Name of the currency
	 * @param ratio
	 *            Exchanged rate compared to the US Dollar
	 * @param code
	 *            Three Letter code for the currency
	 */
	public void addCurrency(String name, Double ratio, String code) {
		Currency currency = new Currency(name, ratio, code);
		CurrencyList.add(currency);
	}

	/**
	 * Sorts and Searches the CurrencyList for Specified currency using binary
	 * search and insertion sort. Insertion sort using a Currency and a
	 * comparator.
	 * 
	 * @param c1
	 *            currency that is be searched for
	 * @return the currency that was searched for
	 */
	public Currency search(String c1) {
		sort();
		ArrayList<String> Currencies = new ArrayList<String>();
		int Counter = 0;
		for (Currency n : CurrencyList) {
			Currencies.add(n.getName());
			Counter++;
		}

		int index = Collections.binarySearch(Currencies, c1);

		return CurrencyList.get(index);

	}

	/**
	 * Takes CurrencyList and creates a String Array with the names of each
	 * currency for Combo box in GUI
	 * 
	 * @return String array of currency names
	 */
	public static String[] arrayListToArray() {
		String[] currencyArray = new String[CurrencyList.toArray().length];
		int counter = 0;
		for (Currency n : CurrencyList) {
			currencyArray[counter] = n.getName();
			counter++;
		}
		return currencyArray;

	}

	// Comparator used to compare currencies
	private static Comparator<Currency> COMPARATOR = new Comparator<Currency>() {
		/**
		 * Compares strings to find out which string is alphabetically comes
		 * first
		 * 
		 * @param c1
		 *            First currency that was specified
		 * @param c2
		 *            Second currency that was specified
		 * @return integer for comparator
		 */
		public int compare(Currency c1, Currency c2) {
			int result = c1.getName().compareToIgnoreCase(c2.getName());
			if (result == 0) {
				result = 0;
			} else if (result > 0) {
				result = 1;
			} else if (result < 0) {
				result = -1;
			}
			return result;
		}

		/**
		 * Compares strings to find out which string is longer, if they are same
		 * length than compares characters
		 * 
		 * @param c1
		 *            first currency
		 * @param c2
		 *            second currency
		 * @return returns int for comparator
		 */
		@SuppressWarnings("unused")
		public int compareLength(Currency c1, Currency c2) {

			if (c1.getName().compareTo(c2.getName()) == 0)
				return 0;
			else if (c1.getName().length() < c2.getName().length())
				return 1;
			else if (c1.getName().length() > c2.getName().length())
				return -1;
			else
				return c1.getName().compareToIgnoreCase(c2.getName());

		}
	};

	/**
	 * Sort method using insertion sort
	 */
	public void sort() {
		Collections.sort(CurrencyList, COMPARATOR);

	}

}
