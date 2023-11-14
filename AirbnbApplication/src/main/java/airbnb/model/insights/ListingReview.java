package airbnb.model.insights;
/**
 * The `ListingReview` class represents insights related to a host's listing reviews on Airbnb.
 * It includes information such as the host ID, average review score, number of listings,
 * host name
 *
 * @author ambikakabra
 */
public class ListingReview {
	/**
     * The unique identifier for the host.
     */
    private String hostId;

    /**
     * The average review score for the host's listings.
     */
    private double avgReviewScore;

    /**
     * The total number of listings the host has.
     */
    private int numberOfListings;

    /**
     * The name of the host.
     */
    private String hostName;

    /**
     * Gets the host ID.
     *
     * @return The host ID.
     */
    public String getHostId() {
        return hostId;
    }

    /**
     * Sets the host ID.
     *
     * @param hostId The host ID to set.
     */
    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    /**
     * Gets the average review score.
     *
     * @return The average review score.
     */
    public double getAvgReviewScore() {
        return avgReviewScore;
    }

    /**
     * Sets the average review score.
     *
     * @param avgReviewScore The average review score to set.
     */
    public void setAvgReviewScore(double avgReviewScore) {
        this.avgReviewScore = avgReviewScore;
    }

    /**
     * Gets the number of listings.
     *
     * @return The number of listings.
     */
    public int getNumberOfListings() {
        return numberOfListings;
    }

    /**
     * Sets the number of listings.
     *
     * @param numberOfListings The number of listings to set.
     */
    public void setNumberOfListings(int numberOfListings) {
        this.numberOfListings = numberOfListings;
    }

    /**
     * Gets the host name.
     *
     * @return The host name.
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Sets the host name.
     *
     * @param hostName The host name to set.
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
    
    /**
     * Returns a string representation of the `ListingReview` object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "ListingReview [hostId=" + hostId + ", avgReviewScore=" + avgReviewScore
                + ", numberOfListings=" + numberOfListings + ", hostName=" + hostName
                + "]";
    }
}
