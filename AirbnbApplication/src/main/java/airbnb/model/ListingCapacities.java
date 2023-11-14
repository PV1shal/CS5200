package airbnb.model;

public class ListingCapacities {

    public enum BedType {
        REAL_BED("REAL BED"),
        PULL_OUT_SOFA("PULL-OUT SOFA"),
        FUTON("FUTON"),
        AIRBED("AIRBED"),
        COUCH("COUCH");

        private String value;

        BedType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public static BedType fromValue(String value) {
            for (BedType type : BedType.values()) {
                if (type.getValue().equalsIgnoreCase(value)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("No enum constant for value " + value);
        }
    }

    public enum RoomType {
        ENTIRE_HOME_APT("ENTIRE HOME/APT"),
        PRIVATE_ROOM("PRIVATE ROOM"),
        SHARED_ROOM("SHARED ROOM");

        private String value;

        RoomType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public static RoomType fromValue(String value) {
            for (RoomType type : RoomType.values()) {
                if (type.getValue().equalsIgnoreCase(value)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("No enum constant for value " + value);
        }
    }

    protected BedType bedtype;
    protected RoomType roomType;
    protected int beds;
    protected int bedrooms;
    protected float bathrooms;
    protected int accommodates;
    protected Listings listings;
    protected int listingId;

    public BedType getBedtype() {
        return bedtype;
    }

    public void setBedtype(BedType bedtype) {
        this.bedtype = bedtype;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
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

    public Listings getListings() {
        return listings;
    }

    public void setListings(Listings listings) {
        this.listings = listings;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public int getListingId() {
        return listingId;
    }

    public ListingCapacities(Listings listings, BedType bedtype, RoomType roomType, int beds, int bedrooms, float bathrooms, int accommodates) {
        this.bedtype = bedtype;
        this.roomType = roomType;
        this.beds = beds;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.accommodates = accommodates;
        this.listings = listings;
    }



}
