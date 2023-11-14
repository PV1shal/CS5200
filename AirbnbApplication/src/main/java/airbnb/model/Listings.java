package airbnb.model;

public class Listings {
	protected int listingId;
	protected Hosts host;
	protected String listingUrl;
	protected String name;
	protected String xlPhotoUrl;
	protected PropertyType propertyType;
	
	public enum PropertyType {
		CAVE("CAVE"), EARTH_HOUSE("EARTH HOUSE"), ENTIRE_FLOOR("ENTIRE FLOOR"), TIPI("TIPI"), TRAIN("TRAIN"), TREEHOUSE("TREEHOUSE"), APARTMENT("APARTMENT"), HOUSE("HOUSE"), BED_BREAKFAST("BED & BREAKFAST"), 
		HERITAGE_HOTEL("HERITAGE HOTEL (INDIA)"), HOTEL("HOTEL"), HUT("HUT"), VACATION_HOME("VACATION HOME"), CONDOMINIUM("CONDOMINIUM"), BOAT("BOAT"), VILLA("VILLA"), CASTLE("CASTLE"), IGLOO("IGLOO"), 
		IN_LAW("IN-LAW"), ISLAND("ISLAND"), LIGHTHOUSE("LIGHTHOUSE"), VAN("VAN"), YURT("YURT"), TOWNHOUSE("TOWNHOUSE"), LOFT("LOFT"), CABIN("CABIN"), NATURE_LODGE("NATURE LODGE"), PARKING_SPACE("PARKING SPACE"), 
		PENSION("PENSION (KOREA)"), CAR("CAR"), BOUTIQUE_HOTEL("BOUTIQUE HOTEL"), BUNGALOW("BUNGALOW"), CAMPER_RV("CAMPER/RV"), PLANE("PLANE"), RYOKAN("RYOKAN (JAPAN)"), TENT("TENT"), TIMESHARE("TIMESHARE"), 
		CASA_PARTICULAR("CASA PARTICULAR"), HOSTEL("HOSTEL"), DORM("DORM"), GUESTHOUSE("GUESTHOUSE"), GUEST_SUITE("GUEST SUITE"), SERVICED_APARTMENT("SERVICED APARTMENT"), CHALET("CHALET"), OTHER("OTHER");
		
		private String displayName;
		
		PropertyType(String displayName) {
			this.displayName = displayName;
		}
		
	    public static PropertyType fromString(String inputString) {
	        for (PropertyType propertyType : values()) {
	            if (propertyType.displayName.equalsIgnoreCase(inputString)) {
	                return propertyType;
	            }
	        }
	        throw new IllegalArgumentException("No enum constant with displayName: " + inputString);
	    }
	}
	
	// This constructor can be used for reading records from MySQL, where we have all fields,
	// including the ListingtId.
	public Listings(int listingId, Hosts host, String listingUrl, String name, String xlPhotoUrl, PropertyType propertyType) {
		this.listingId = listingId;
		this.host = host;
		this.listingUrl = listingUrl;
		this.name = name;
		this.xlPhotoUrl = xlPhotoUrl;
		this.propertyType = propertyType;
	}

	// This constructor can be used for reading records from MySQL, where we only have the ListingId,
	// such as a foreign key reference to ListingId.
	// Given ListingId, we can fetch the full Listing record.
	public Listings(int listingId) {
		this.listingId = listingId;
	}


	// This constructor can be used for creating new listings, where the ListingId may not be
	// assigned yet since it is auto-generated by MySQL.
	public Listings(Hosts host, String listingUrl, String name, String xlPhotoUrl, PropertyType propertyType) {
		this.host = host;
		this.listingUrl = listingUrl;
		this.name = name;
		this.xlPhotoUrl = xlPhotoUrl;
		this.propertyType = propertyType;
	}

	public int getListingId() {
		return this.listingId;
	}

	public void setListingId(int listingId) {
		this.listingId = listingId;
	}

	public Hosts getHost() {
		return this.host;
	}

	public void setHost(Hosts host) {
		this.host = host;
	}

	public String getListingUrl() {
		return this.listingUrl;
	}

	public void setListingUrl(String listingUrl) {
		this.listingUrl = listingUrl;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getXlPhotoUrl() {
		return this.xlPhotoUrl;
	}

	public void setXlPhotoUrl(String xlPhotoUrl) {
		this.xlPhotoUrl = xlPhotoUrl;
	}

	public PropertyType getPropertyType() {
		return this.propertyType;
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}
	
}