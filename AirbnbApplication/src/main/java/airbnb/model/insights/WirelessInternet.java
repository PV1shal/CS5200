package airbnb.model.insights;

/**
 * The `WirelessInternet` class represents information about listings with wireless internet.
 * It includes details such as the name of the listing, city, and country.
 *
 * @author ambikakabra
 */
public class WirelessInternet {
	private String name;
    private String city;
    private String country;

    //constructors
    public WirelessInternet() {}
    
    public WirelessInternet(String name, String city, String country) {
		super();
		this.name = name;
		this.city = city;
		this.country = country;
	}

    //getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	/**
     * Returns a string representation of the `WirelessInternet` object.
     *
     * @return A string representation of the `WirelessInternet` object.
     */
	@Override
    public String toString() {
        return "WirelessInternet{" +
               "name='" + name + '\'' +
               ", city='" + city + '\'' +
               ", country='" + country + '\'' +
               '}';
    }
}
