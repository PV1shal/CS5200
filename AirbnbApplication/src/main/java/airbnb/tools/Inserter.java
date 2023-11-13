package airbnb.tools;
import airbnb.dao.ListingAdditionalInfoDao;
import airbnb.dao.ListingAvailabilitiesDao;
import airbnb.dao.ListingCapacitiesDao;
import airbnb.dao.ReviewsDao;
import airbnb.model.ListingAdditionalInfo;
import airbnb.model.ListingAvailabilities;
import airbnb.model.ListingCapacities;
import airbnb.model.Reviews;

import java.sql.SQLException;

import static airbnb.model.ListingCapacities.BedType.AIRBED;

/**
 * Entry point of the application
 * main() runner
 */
public class Inserter {

	public static void main(String[] args) throws SQLException {
		System.out.println("hello! Welcome to airbnb insights");
		ReviewsDao reviewsDao = ReviewsDao.getInstance();
		ListingCapacitiesDao listingCapacitiesDao = ListingCapacitiesDao.getInstance();
		ListingAvailabilitiesDao listingAvailabilitiesDao = ListingAvailabilitiesDao.getInstance();
		ListingAdditionalInfoDao listingAdditionalInfoDao = ListingAdditionalInfoDao.getInstance();

		Reviews reviews = reviewsDao.getReviewByListingId(1078);
		System.out.format("Reading reviews for listingId 1078:\n " +
						"NumberOfReviews: %d" +
						"ReviewScoresAccuracy: %d\n" +
						"ReviewScoresRating: %d\n" +
						"ReviewScoresCleanliness: %d\n" +
						"ReviewScoresCheckin: %d\n" +
						"ReviewScoresCommunication: %d\n" +
						"ReviewScoresLocation: %d\n" +
						"ReviewScoresValue: %d\n" +
						"ReviewsPerMonth: %.4f\n",
				reviews.getNumberOfReviews(),
				reviews.getReviewScoresAccuracy(),
				reviews.getReviewScoresRating(),
				reviews.getReviewScoresCleanliness(),
				reviews.getReviewScoresCheckin(),
				reviews.getReviewScoresCommunication(),
				reviews.getReviewScoresLocation(),
				reviews.getReviewScoresValue(),
				reviews.getReviewsPerMonth());

		ListingAvailabilities listingAvailabilities = listingAvailabilitiesDao.getListingAvailabilitiesByListingId(5682);
		System.out.format("Reading listingAvailabilities with listingId 5682:\n hasAvailability='%s',\n availability30=%d,\n availability60=%d,\n availability90=%d,\n availability365=%d\n",
				listingAvailabilities.getHasAvailability(),
				listingAvailabilities.getAvailability30(),
				listingAvailabilities.getAvailibility60(),
				listingAvailabilities.getAvailibility90(),
				listingAvailabilities.getAvailibility365());

		ListingAvailabilities listingAvailabilitiesUpdated = listingAvailabilitiesDao.updateAvailability365(listingAvailabilities, 200);
		System.out.format("Updating listingAvailabilities with listingId 5682 with updated Availability365:\n hasAvailability='%s',\n availability30=%d,\n availability60=%d,\n availability90=%d,\n availability365=%d\n",
				listingAvailabilitiesUpdated.getHasAvailability(),
				listingAvailabilitiesUpdated.getAvailability30(),
				listingAvailabilitiesUpdated.getAvailibility60(),
				listingAvailabilitiesUpdated.getAvailibility90(),
				listingAvailabilitiesUpdated.getAvailibility365());

		ListingAdditionalInfo listingAdditionalInfo = listingAdditionalInfoDao.getListingAdditionalInfoByListingId(22573);
		System.out.format("Reading listingAdditionalInfo with listingId 22573:\n Summary='%s'\n, Space='%s',\n " +
						"NeighborhoodOverview='%s',\n Transit='%s',\n HouseRules='%s',\n Amenities='%s'\n",
				listingAdditionalInfo.getSummary(),
				listingAdditionalInfo.getSpace(),
				listingAdditionalInfo.getNeighborhoodOverview(),
				listingAdditionalInfo.getTransit(),
				listingAdditionalInfo.getHouseRules(),
				listingAdditionalInfo.getAmenities());

		ListingCapacities listingCapacities = listingCapacitiesDao.getListingCapacitiesByListingId(1078);
		System.out.format("Reading listingCapacities with listingId 1078:\n bedType='%s',\n roomType='%s',\n accommodates=%d,\n  bathrooms=%.2f,\n bedrooms=%d,\n beds=%d\n",
				listingCapacities.getBedtype().toString(),
				listingCapacities.getRoomType().toString(),
				listingCapacities.getAccommodates(),
				listingCapacities.getBathrooms(),
				listingCapacities.getBedrooms(),
				listingCapacities.getBeds());

		reviewsDao.delete(reviews);
		listingAvailabilitiesDao.delete(listingAvailabilitiesUpdated);
		listingAdditionalInfoDao.delete(listingAdditionalInfo);
		listingCapacitiesDao.delete(listingCapacities);
	}
}
