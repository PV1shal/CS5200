package airbnb.model;

public class Reviews {
    protected int listingId;
    protected int numberOfReviews;
    protected int reviewScoresAccuracy;
    protected int reviewScoresRating;
    protected int reviewScoresCleanliness;
    protected int reviewScoresCheckin;
    protected int reviewScoresCommunication;
    protected int reviewScoresLocation;
    protected int reviewScoresValue;
    protected float reviewsPerMonth;
    protected Listings listings;

    public Reviews(Listings listings, int numberOfReviews, int reviewScoresAccuracy, int reviewScoresRating,
                   int reviewScoresCleanliness, int reviewScoresCheckin, int reviewScoresCommunication,
                   int reviewScoresLocation, int reviewScoresValue, float reviewsPerMonth) {
        this.numberOfReviews = numberOfReviews;
        this.reviewScoresAccuracy = reviewScoresAccuracy;
        this.reviewScoresRating = reviewScoresRating;
        this.reviewScoresCleanliness = reviewScoresCleanliness;
        this.reviewScoresCheckin = reviewScoresCheckin;
        this.reviewScoresCommunication = reviewScoresCommunication;
        this.reviewScoresLocation = reviewScoresLocation;
        this.reviewScoresValue = reviewScoresValue;
        this.reviewsPerMonth = reviewsPerMonth;
        this.listings = listings;
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    public int getReviewScoresAccuracy() {
        return reviewScoresAccuracy;
    }

    public int getReviewScoresRating() {
        return reviewScoresRating;
    }

    public int getReviewScoresCleanliness() {
        return reviewScoresCleanliness;
    }

    public int getReviewScoresCheckin() {
        return reviewScoresCheckin;
    }

    public int getReviewScoresCommunication() {
        return reviewScoresCommunication;
    }

    public int getReviewScoresLocation() {
        return reviewScoresLocation;
    }

    public int getReviewScoresValue() {
        return reviewScoresValue;
    }

    public float getReviewsPerMonth() {
        return reviewsPerMonth;
    }

    public Listings getListings() {
        return listings;
    }

    public void setNumberOfReviews(int numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    public void setReviewScoresAccuracy(int reviewScoresAccuracy) {
        this.reviewScoresAccuracy = reviewScoresAccuracy;
    }

    public void setReviewScoresRating(int reviewScoresRating) {
        this.reviewScoresRating = reviewScoresRating;
    }

    public void setReviewScoresCleanliness(int reviewScoresCleanliness) {
        this.reviewScoresCleanliness = reviewScoresCleanliness;
    }

    public void setReviewScoresCheckin(int reviewScoresCheckin) {
        this.reviewScoresCheckin = reviewScoresCheckin;
    }

    public void setReviewScoresCommunication(int reviewScoresCommunication) {
        this.reviewScoresCommunication = reviewScoresCommunication;
    }

    public void setReviewScoresLocation(int reviewScoresLocation) {
        this.reviewScoresLocation = reviewScoresLocation;
    }

    public void setReviewScoresValue(int reviewScoresValue) {
        this.reviewScoresValue = reviewScoresValue;
    }

    public void setReviewsPerMonth(float reviewsPerMonth) {
        this.reviewsPerMonth = reviewsPerMonth;
    }

    public void setListings(Listings listings) {
        this.listings = listings;
    }

	@Override
	public String toString() {
		return "Reviews [listingId=" + listingId + ", numberOfReviews=" + numberOfReviews + ", reviewScoresAccuracy="
				+ reviewScoresAccuracy + ", reviewScoresRating=" + reviewScoresRating + ", reviewScoresCleanliness="
				+ reviewScoresCleanliness + ", reviewScoresCheckin=" + reviewScoresCheckin
				+ ", reviewScoresCommunication=" + reviewScoresCommunication + ", reviewScoresLocation="
				+ reviewScoresLocation + ", reviewScoresValue=" + reviewScoresValue + ", reviewsPerMonth="
				+ reviewsPerMonth + ", listings=" + listings + "]";
	}
}
