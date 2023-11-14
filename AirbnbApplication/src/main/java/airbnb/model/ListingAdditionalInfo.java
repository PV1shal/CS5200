package airbnb.model;

public class ListingAdditionalInfo {
    protected String summary;
    protected String space;
    protected String neighborhoodOverview;
    protected String transit;
    protected String houseRules;
    protected String amenities;
    protected Listings listings;


    public ListingAdditionalInfo(Listings listings, String summary, String space, String neighborhoodOverview,
                                 String transit, String houseRules, String amenities) {
        this.summary = summary;
        this.space = space;
        this.neighborhoodOverview = neighborhoodOverview;
        this.transit = transit;
        this.houseRules = houseRules;
        this.amenities = amenities;
        this.listings = listings;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getNeighborhoodOverview() {
        return neighborhoodOverview;
    }

    public void setNeighborhoodOverview(String neighborhoodOverview) {
        this.neighborhoodOverview = neighborhoodOverview;
    }

    public String getTransit() {
        return transit;
    }

    public void setTransit(String transit) {
        this.transit = transit;
    }

    public String getHouseRules() {
        return houseRules;
    }

    public void setHouseRules(String houseRules) {
        this.houseRules = houseRules;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public Listings getListings() {
        return listings;
    }

    public void setListings(Listings listings) {
        this.listings = listings;
    }
}
