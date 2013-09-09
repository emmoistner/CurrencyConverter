
/**
 * 
 * 
 * @author Ethan Moistner
 *Class was designed to build a Currency containing three basic details
 *
 */

public class Currency {
	private String Name;
	private Double Ratio;
	private String CurrencyCode;
	//private String[] Countries; //Unused currently
	
	/**
	 * Class Constructor specifying Name, ratio, and code.
	 * @param Name Name of the Currency
	 * @param Ratio Exchange rate of the Currency compared to the US Dollar
	 * @param CurrencyCode Three letter Code that refers to the Currency
	 */
	public Currency(String Name, Double Ratio, String CurrencyCode){	
		this.Name = Name;
		this.Ratio = Ratio;
		this.CurrencyCode = CurrencyCode;
		
	}
	
	/**
	 * Getter for Ratio
	 * @return the ratio of the specified Currency
	 */
	
	public Double getRatio(){
		return Ratio;
	}
	/**
	 * Setter for Ratio
	 * @param newRatio New Ratio for the specified Currency
	 */
	public void setRatio(Double newRatio){
		Ratio = newRatio;
	}
	/**
	 * Getter for Name of the specified Currency
	 * @return the name for the specified Currency
	 */
	public String getName(){
		return Name;
	}
	/**
	 * Setter for name of the specified Currency
	 * @param newName replaces the current name with NewName
	 */
	public void setName(String newName){
		Name = newName;
	}
	
	public String getCurrencyCode(){
		return CurrencyCode;
	}
	
	public void setCurrencyCode(String newCurrencyCode){
		CurrencyCode = newCurrencyCode;
	}
	//Currently Unused Getters and Setter 
	/*
	public String[] getCountriesOfUse(){
		return Countries;
	}
	
	public void setCourntriesOfUse(String[] newCountriesOfUse){
		Countries = newCountriesOfUse;
	}
	*/
	public String toString(){
		return Name;
	}
	
	

}
