package airbnb.model;

public class PropertyLocations {
	protected Listings listing;
	protected String street;
	protected String neighborhoodCleansed;
	protected String city;
	protected String State;
	protected String zipcode;
	protected String country;
	
	
	public PropertyLocations(Listings listing, String street, String neighborhoodCleansed, String city, String state,
			String zipcode, String country) {
		this.listing = listing;
		this.street = street;
		this.neighborhoodCleansed = neighborhoodCleansed;
		this.city = city;
		this.State = state;
		this.zipcode = zipcode;
		this.country = country;
	}


	public Listings getListing() {
		return this.listing;
	}


	public void setListing(Listings listing) {
		this.listing = listing;
	}


	public String getStreet() {
		return this.street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getNeighborhoodCleansed() {
		return this.neighborhoodCleansed;
	}


	public void setNeighborhoodCleansed(String neighborhoodCleansed) {
		this.neighborhoodCleansed = neighborhoodCleansed;
	}


	public String getCity() {
		return this.city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return this.State;
	}


	public void setState(String state) {
		this.State = state;
	}


	public String getZipcode() {
		return this.zipcode;
	}


	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}


	public String getCountry() {
		return this.country;
	}


	public void setCountry(String country) {
		this.country = country;
	}
	
	
}