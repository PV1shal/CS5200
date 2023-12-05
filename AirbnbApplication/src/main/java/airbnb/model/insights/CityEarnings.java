package airbnb.model.insights;

/**
 * The `CityEarnings` class represents the earnings information for a city.
 * It includes details such as the city name, country, listing count, and total earnings.
 *
 * @author ambikakabra
 */
public class CityEarnings {
	private String city;
    private String country;
    private int listingCount;
    private double totalEarnings;

    //constructors
    public CityEarnings() {}

	public CityEarnings(String city, String country, int listingCount, double totalEarnings) {
		super();
		this.city = city;
		this.country = country;
		this.listingCount = listingCount;
		this.totalEarnings = totalEarnings;
	}

	//getters and setters
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getListingCount() {
		return listingCount;
	}

	public void setListingCount(int listingCount) {
		this.listingCount = listingCount;
	}

	public double getTotalEarnings() {
		return totalEarnings;
	}

	public void setTotalEarnings(double totalEarnings) {
		this.totalEarnings = totalEarnings;
	}
	
	/**
     * Returns a string representation of the `CityEarnings` object.
     *
     * @return A string representation of the `CityEarnings` object.
     */
	@Override
    public String toString() {
        return "CityEarnings{" +
               "city='" + city + '\'' +
               ", country='" + country + '\'' +
               ", listingCount=" + listingCount +
               ", totalEarnings=" + totalEarnings +
               '}';
    }
  
}