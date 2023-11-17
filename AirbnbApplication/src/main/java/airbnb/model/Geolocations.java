package airbnb.model;

public class Geolocations {
	protected Listings listing;
	protected float latitude;
	protected float longitude;
	
	public Geolocations(Listings listing, float latitude, float longitude) {
		this.listing = listing;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Listings getListing() {
		return this.listing;
	}

	public void setListing(Listings listing) {
		this.listing = listing;
	}

	public float getLatitude() {
		return this.latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return this.longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
}