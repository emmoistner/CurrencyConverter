import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CurrencyConversionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddCurrency() {
		CurrencyConversion newInstance = new CurrencyConversion();
		newInstance.addCurrency("US Dollar", 1.0, "USD");
		String actualName = newInstance.getCurrency(0).getName();
		Double actualRatio = newInstance.getCurrency(0).getRatio();
		String actualCode = newInstance.getCurrency(0).getCurrencyCode();
		String expectedName = "US Dollar";
		Double expectedRatio = 1.0;
		String expectedCode = "USD";
		assertEquals(actualName, expectedName);
		assertEquals(actualRatio, expectedRatio);
		assertEquals(actualCode, expectedCode);
	}

	@Test
	public void testConvert() {
		CurrencyConversion newInstance = new CurrencyConversion();
		newInstance.addCurrency("US Dollar", 1.0, "USD");
		newInstance.addCurrency("Euro", 0.756264, "EUR");
		Double actual = newInstance.convert(50.00, "US Dollar", "Euro");
		Double expected = 37.8132;
		assertEquals(actual, expected);
	}

	@Test
	public void testFormatDecimal() {
		CurrencyConversion newInstance = new CurrencyConversion();
		Double actual = .3334213;
		actual = newInstance.formatDecimal(actual);
		Double expected = .33;
		assertEquals(actual, expected);

	}

	@Test
	public void testDateToString() {
		CurrencyConversion newInstance = new CurrencyConversion();
		String date = "12042012";
		String actual = newInstance.dateToString(date);
		String expected = "December 04, 2012";
		assertEquals(actual, expected);

	}

	@Test
	public void testSearch() {
		CurrencyConversion newInstance = new CurrencyConversion();
		newInstance.addCurrency("US Dollar", 1.0, "USD");
		newInstance.addCurrency("Euro", 0.756264, "EUR");
		newInstance.addCurrency("Indian Rupee", 51.1591, "INR");
		Currency expected = newInstance.getCurrency(2);
		Currency actual = newInstance.search("Indian Rupee");

		assertEquals(actual, expected);
	}

	@Test
	public void testDeleteCurrency() {
		CurrencyConversion newInstance = new CurrencyConversion();
		newInstance.addCurrency("Indian Rupee", 51.1591, "INR");
		Boolean isPresent = true;
		newInstance.deleteCurrency("Indian Rupee");
		ArrayList<Currency> emptyArrayList = new ArrayList<Currency>();
		if (newInstance.getCurrencyList().equals(emptyArrayList)) {
			isPresent = true;
		} else {
			isPresent = false;
		}

		Boolean actual = isPresent;
		Boolean expected = true;
		assertEquals(actual, expected);

	}

}
