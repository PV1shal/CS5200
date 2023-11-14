package airbnb.model;

public class ListingAvailabilities {

    protected int listingId;
    protected Listings listing;
    protected String hasAvailability;
    protected int availability30;
    protected int availibility60;
    protected int availibility90;
    protected int availibility365;
    protected Listings listings;

    public ListingAvailabilities(Listings listing, String hasAvailability, int availability30, int availibility60,
                   int availibility90, int availibility365) {
        this.listing = listing;
        this.hasAvailability = hasAvailability;
        this.availability30 = availability30;
        this.availibility60 = availibility60;
        this.availibility90 = availibility90;
        this.availibility365 = availibility365;
        this.listing = listing;
    }


    public int getListingId() {
        return listingId;
    }

    public String getHasAvailability() {
        return hasAvailability;
    }

    public int getAvailability30() {
        return availability30;
    }

    public int getAvailibility60() {
        return availibility60;
    }

    public int getAvailibility90() {
        return availibility90;
    }

    public int getAvailibility365() {
        return availibility365;
    }

    public Listings getListings() {
        return listing;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public void setHasAvailability(String hasAvailability) {
        this.hasAvailability = hasAvailability;
    }

    public void setAvailability30(int availability30) {
        this.availability30 = availability30;
    }

    public void setAvailibility60(int availibility60) {
        this.availibility60 = availibility60;
    }

    public void setAvailibility90(int availibility90) {
        this.availibility90 = availibility90;
    }

    public void setAvailibility365(int availibility365) {
        this.availibility365 = availibility365;
    }

    public void setListing(Listings listing) {
        this.listing = listing;
    }
}
