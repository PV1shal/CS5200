package airbnb.model.insights;

/**
 * The `AmenitiesCount` class represents the count of a specific amenity in listings.
 * It includes information about the amenity name and the count of listings with that amenity.
 *
 * @author ambikakabra
 */
public class AmenitiesCount {
	private String amenity;
    private int amenityCount;

    // constructors, getters, and setters
    public AmenitiesCount() {}
    
    public AmenitiesCount(String amenity, int amenityCount) {
		super();
		this.amenity = amenity;
		this.amenityCount = amenityCount;
	}

	public String getAmenity() {
		return amenity;
	}

	public void setAmenity(String amenity) {
		this.amenity = amenity;
	}

	public int getAmenityCount() {
		return amenityCount;
	}

	public void setAmenityCount(int amenityCount) {
		this.amenityCount = amenityCount;
	}

	 /**
     * Returns a string representation of the `AmenitiesCount` object.
     *
     * @return A string representation of the `AmenitiesCount` object.
     */
	@Override
    public String toString() {
        return "AmenitiesCount{" +
               "amenity='" + amenity + '\'' +
               ", amenityCount=" + amenityCount +
               '}';
    }

}
