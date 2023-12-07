package airbnb.model;

import airbnb.model.Listings.PropertyType;

public class ListingFilter {
	private String listingName;
	private PropertyType propertyType;
	private String amenities;
	private String street;
	private String neighborhoodCleansed;
	private String city;
	
	public ListingFilter(String listingName, PropertyType propertyType, String amenities, String street, String neighborhoodCleansed, String city) {
		this.listingName = listingName;
		this.propertyType = propertyType;
		this.amenities = amenities;
		this.street = street;
		this.neighborhoodCleansed = neighborhoodCleansed;
		this.city = city;
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
	@Override
	public String toString() {
		return "ListingFilter [listingName=" + listingName + ",propertType=" + propertyType + ", amenities=" + amenities + ", street=" + street
				+ ", neighborhoodCleansed=" + neighborhoodCleansed + ", city=" + city + "]";
	}
	
}
