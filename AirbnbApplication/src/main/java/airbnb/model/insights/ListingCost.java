package airbnb.model.insights;

/**
 * The `ListingCost` class represents the cost information for a listing.
 * It includes details such as the listing neighborhood, country, and cost.
 *
 * @author ambikakabra
 */
public class ListingCost {
	  private String listingNeighborhood;
	    private String listingCountry;
	    private float listingCost;
	    
	    // constructors
		public ListingCost(String listingNeighborhood, String listingCountry, float listingCost) {
			this.listingNeighborhood = listingNeighborhood;
			this.listingCountry = listingCountry;
			this.listingCost = listingCost;
		}

		public ListingCost() {
		}

		//getters, and setters
		public String getListingNeighborhood() {
			return listingNeighborhood;
		}

		public void setListingNeighborhood(String listingNeighborhood) {
			this.listingNeighborhood = listingNeighborhood;
		}

		public String getListingCountry() {
			return listingCountry;
		}

		public void setListingCountry(String listingCountry) {
			this.listingCountry = listingCountry;
		}

		public float getListingCost() {
			return listingCost;
		}

		public void setListingCost(float listingCost) {
			this.listingCost = listingCost;
		}
		
		/**
	     * Returns a string representation of the `ListingCost` object.
	     *
	     * @return A string representation of the `ListingCost` object.
	     */
		@Override
	    public String toString() {
	        return "CityEarnings{" +
	               "ListingNeighborhood='" + listingNeighborhood + '\'' +
	               ", ListingCountry='" + listingCountry + '\'' +
	               ", ListingCost=" + listingCost +
	               '}';
	    }
}
