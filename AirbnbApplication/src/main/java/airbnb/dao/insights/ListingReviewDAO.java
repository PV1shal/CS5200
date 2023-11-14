package airbnb.dao.insights;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import airbnb.dao.ConnectionManager;
import airbnb.model.insights.ListingReview;

/**
 * The `ListingReviewDAO` class is responsible for handling data access related to listing reviews on Airbnb.
 * It provides methods to interact with the database, such as retrieving the top listings based on certain criteria.
 * This class follows the Singleton pattern to ensure a single instance is used throughout the application.
 * It uses a `ConnectionManager` for managing database connections.
 *
 * @author ambikakabra
 */
public class ListingReviewDAO {

	protected ConnectionManager connectionManager;

    private static ListingReviewDAO instance = null;

    /**
     * Constructs a new instance of the `ListingReviewDAO` class.
     * It initializes the `ConnectionManager` to manage database connections.
     */
    protected ListingReviewDAO() {
        connectionManager = new ConnectionManager();
    }

    /**
     * Gets the singleton instance of the `ListingReviewDAO`.
     *
     * @return The singleton instance of the `ListingReviewDAO`.
     */
    public static ListingReviewDAO getInstance() {
        if(instance == null) {
            instance = new ListingReviewDAO();
        }
        return instance;
    }
    
    /**
     * Retrieves the top listing reviews based on criteria "Top 10 hosts with over 100 listings who have the highest average 
     * ReviewScoreRating and the number of listings they own" from the database.
     *
     * @return A list of `ListingReview` objects representing the top listings.
     * @throws SQLException If a SQL exception occurs during the database operation.
     */
	public List<ListingReview> getTopListings() throws SQLException {
        List<ListingReview> listingReviews = new ArrayList<>();
        String insertListingAdditionalInfo =
					"SELECT LISTING_REVIEW.HostId, Hosts.HostName, AVG_REVIEW_SCORE, CNT AS NUMBER_OF_LISTINGS"+
							" FROM "+
					"(SELECT "+
					    "Listings.HostId, "+
					    " AVG(CASE WHEN Reviews.ReviewScoresRating != -1 THEN Reviews.ReviewScoresRating END) AS AVG_REVIEW_SCORE "+
					"FROM Listings "+
					"INNER JOIN "+
					    "Reviews ON Listings.ListingId = Reviews.ListingId "+
					"GROUP BY "+ 
					    "Listings.HostId) AS LISTING_REVIEW "+
					"INNER JOIN "+
					"(SELECT "+
					   " Listings.HostId, COUNT(*) AS CNT "+
					"FROM "+
					    "Listings "+
					"GROUP BY "+
					    "Listings.HostId "+
					"HAVING "+
					    "CNT > 100) AS LISTING_COUNT ON LISTING_REVIEW.HostId = LISTING_COUNT.HostId "+
					"INNER JOIN "+
					"Hosts ON LISTING_REVIEW.HostId = Hosts.HostId "+
					"ORDER BY "+
					"AVG_REVIEW_SCORE DESC "
					+"LIMIT 10;";

        
        Connection connection = null;
        PreparedStatement statement = null;

        try {
        	 connection = connectionManager.getConnection();
        	 statement = connection.prepareStatement(insertListingAdditionalInfo);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ListingReview listingReview = new ListingReview();
                listingReview.setHostId(resultSet.getString("HostId"));
                listingReview.setAvgReviewScore(resultSet.getDouble("AVG_REVIEW_SCORE"));
                listingReview.setNumberOfListings(resultSet.getInt("NUMBER_OF_LISTINGS"));
                listingReview.setHostName(resultSet.getString("HostName"));
                listingReviews.add(listingReview);
            }
            
            return listingReviews;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
            	statement.close();
            }
        }    
    }
}
