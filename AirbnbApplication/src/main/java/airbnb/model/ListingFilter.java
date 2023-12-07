package airbnb.model;

import airbnb.model.Listings.PropertyType;

public class ListingFilter {
	private String listingName;
	private PropertyType propertyType;
	private String amenities;
	private String street;
	private String neighborhoodCleansed;
	private String city;
	private int price;
	private int securityDeposit;
	private int cleaningFee;
	private String cancellationPolicy;
	private int guestsIncluded;
	private int minimumNights;
	private int maximumNights;
	private String bedtype;
	private String roomType;
	private int beds;
	private int bedrooms;
	private float bathrooms;
	private int accommodates;
	
	public ListingFilter(String listingName, PropertyType propertyType, String amenities, String street, String neighborhoodCleansed, String city, int price, int securityDeposit, int cleaningFee, String cancellationPolicy, int guestsIncluded, int minimumNights, int maximumNights, String bedType, String roomType, int accommodates, float bathrooms, int bedrooms, int beds) {
		this.listingName = listingName;
		this.propertyType = propertyType;
		this.amenities = amenities;
		this.street = street;
		this.neighborhoodCleansed = neighborhoodCleansed;
		this.city = city;
		this.price = price;
		this.securityDeposit = securityDeposit;
		this.cleaningFee = cleaningFee;
		this.cancellationPolicy = cancellationPolicy;
		this.guestsIncluded = guestsIncluded;
		this.minimumNights = minimumNights;
		this.maximumNights = maximumNights;
		this.bedtype = bedType;
		this.roomType = roomType;
		this.beds = beds;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.accommodates = accommodates;
		
	}
	public String getListingName() {
		return listingName;
	}
	public void setListingName(String listingName) {
		this.listingName = listingName;
	}
	public PropertyType getPropertyType() {
		return this.propertyType;
	}
	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}
	public String getAmenities() {
		return amenities;
	}
	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNeighborhoodCleansed() {
		return neighborhoodCleansed;
	}
	public void setNeighborhoodCleansed(String neighborhoodCleansed) {
		this.neighborhoodCleansed = neighborhoodCleansed;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSecurityDeposit() {
		return securityDeposit;
	}
	public void setSecurityDeposit(int securityDeposit) {
		this.securityDeposit = securityDeposit;
	}
	public int getCleaningFee() {
		return cleaningFee;
	}
	public void setCleaningFee(int cleaningFee) {
		this.cleaningFee = cleaningFee;
	}
	public String getCancellationPolicy() {
		return cancellationPolicy;
	}
	public void setCancellationPolicy(String cancellationPolicy) {
		this.cancellationPolicy = cancellationPolicy;
	}
	public int getGuestsIncluded() {
		return guestsIncluded;
	}
	public void setGuestsIncluded(int guestsIncluded) {
		this.guestsIncluded = guestsIncluded;
	}
	public int getMinimumNights() {
		return minimumNights;
	}
	public void setMinimumNights(int minimumNights) {
		this.minimumNights = minimumNights;
	}
	public int getMaximumNights() {
		return maximumNights;
	}
	public void setMaximumNights(int maximumNights) {
		this.maximumNights = maximumNights;
	}
	public String getBedtype() {
		return bedtype;
	}
	public void setBedtype(String bedtype) {
		this.bedtype = bedtype;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public int getBeds() {
		return beds;
	}
	public void setBeds(int beds) {
		this.beds = beds;
	}
	public int getBedrooms() {
		return bedrooms;
	}
	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}
	public float getBathrooms() {
		return bathrooms;
	}
	public void setBathrooms(float bathrooms) {
		this.bathrooms = bathrooms;
	}
	public int getAccommodates() {
		return accommodates;
	}
	public void setAccommodates(int accommodates) {
		this.accommodates = accommodates;
	}
	@Override
	public String toString() {
		return "ListingFilter [listingName=" + listingName + ", propertyType=" + propertyType + ", amenities="
				+ amenities + ", street=" + street + ", neighborhoodCleansed=" + neighborhoodCleansed + ", city=" + city
				+ ", price=" + price + ", securityDeposit=" + securityDeposit + ", cleaningFee=" + cleaningFee
				+ ", cancellationPolicy=" + cancellationPolicy + ", guestsIncluded=" + guestsIncluded
				+ ", minimumNights=" + minimumNights + ", maximumNights=" + maximumNights + ", bedtype=" + bedtype
				+ ", roomType=" + roomType + ", beds=" + beds + ", bedrooms=" + bedrooms + ", bathrooms=" + bathrooms
				+ ", accommodates=" + accommodates + "]";
	}
	
}
