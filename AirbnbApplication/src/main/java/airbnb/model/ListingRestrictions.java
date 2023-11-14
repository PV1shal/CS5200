package airbnb.model;


public class ListingRestrictions {
	public enum CancellationPolicy {
		STRICT("STRICT"), 
		MODERATE("MODERATE"),
		FLEXIBLE("FLEXIBLE"),
		SUPER_STRICT_60("SUPER_STRICT_60"),
		SUPER_STRICT_60_NEW("SUPER_STRICT_60_NEW"), 
		SUPER_STRICT_30("SUPER_STRICT_30"), 
		SUPER_STRICT_30_NEW("SUPER_STRICT_30_NEW"),
		STRICT_NEW("STRICT_NEW"),
		FLEXIBLE_NEW("FLEXIBLE_NEW"), 
		MODERATE_NEW("MODERATE_NEW"), 
		LONG_TERM("LONG_TERM"), 
		NO_REFUNDS("NO_REFUNDS");

        private String value;

        CancellationPolicy(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public static CancellationPolicy fromValue(String value) {
            for (CancellationPolicy type : CancellationPolicy.values()) {
                if (type.getValue().equalsIgnoreCase(value)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("No enum constant for value " + value);
        }
    }
	 protected Listings listing;
	 protected CancellationPolicy cancellationPolicy;
	 protected int guestsInclued;
	 protected int minimumNights;
	 protected int maximumNights;
	 
	 
	public ListingRestrictions(Listings listings, CancellationPolicy cancellationPolicy, int guestsInclued,
			int minimumNights, int maximumNights) {
		super();
		this.listing = listings;
		this.cancellationPolicy = cancellationPolicy;
		this.guestsInclued = guestsInclued;
		this.minimumNights = minimumNights;
		this.maximumNights = maximumNights;
	}


	public Listings getListing() {
		return listing;
	}


	public void setListings(Listings listing) {
		this.listing = listing;
	}


	public CancellationPolicy getCancellationPolicy() {
		return cancellationPolicy;
	}


	public void setCancellationPolicy(CancellationPolicy cancellationPolicy) {
		this.cancellationPolicy = cancellationPolicy;
	}


	public int getGuestsInclued() {
		return guestsInclued;
	}


	public void setGuestsInclued(int guestsInclued) {
		this.guestsInclued = guestsInclued;
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
	
}