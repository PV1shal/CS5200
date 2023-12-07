package airbnb.dao.insights;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import airbnb.dao.ConnectionManager;
import airbnb.model.insights.AvgAccommodatesByYear;
import airbnb.model.insights.CityEarnings;
import airbnb.model.insights.ListingReview;

/**
 * The `HostInsightDAO` class is responsible for handling data access related to host insights on Airbnb.
 * It provides methods to interact with the database, such as retrieving the top listings based on certain criteria.
 * This class follows the Singleton pattern to ensure a single instance is used throughout the application.
 * It uses a `ConnectionManager` for managing database connections.
 *
 * @author ambikakabra
 */
public class HostInsightDAO {

	protected ConnectionManager connectionManager;

    private static HostInsightDAO instance = null;

    /**
     * Constructs a new instance of the `HostInsightDAO` class.
     * It initializes the `ConnectionManager` to manage database connections.
     */
    protected HostInsightDAO() {
        connectionManager = new ConnectionManager();
    }

    /**
     * Gets the singleton instance of the `HostInsightDAO`.
     *
     * @return The singleton instance of the `HostInsightDAO`.
     */
    public static HostInsightDAO getInstance() {
        if(instance == null) {
            instance = new HostInsightDAO();
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
    
	/**
     * Retrieves a list of top earning cities based on the number of listings and total earnings.
     *
     * @return A list of `CityEarnings` objects representing the top earning cities.
     * @throws SQLException If a SQL exception occurs during the database operation.
     */
    public List<CityEarnings> getTopEarningCities() throws SQLException {
        List<CityEarnings> cityEarningsList = new ArrayList<>();
        String sql = "SELECT PL.City, PL.Country, COUNT(L.ListingId) AS ListingCount, " +
                     "(SELECT SUM(CASE WHEN Price != -1 THEN Price END + " +
                     "CASE WHEN SecurityDeposit != -1 THEN SecurityDeposit END + " +
                     "CASE WHEN CleaningFee != -1 THEN CleaningFee END ) " +
                     "FROM ListingPricings " +
                     "WHERE ListingId IN (SELECT ListingId FROM Listings WHERE HostId IN " +
                     "(SELECT HostId FROM Hosts WHERE HostResponseTime = 'within an hour'))) AS TotalEarnings " +
                     "FROM PropertyLocations PL " +
                     "JOIN Listings L ON PL.ListingId = L.ListingId " +
                     "WHERE L.HostId IN (SELECT HostId FROM Hosts WHERE HostResponseTime = 'within an hour') " +
                     "GROUP BY PL.City, PL.Country " +
                     "ORDER BY TotalEarnings DESC, ListingCount DESC " +
                     "LIMIT 10;";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
        	 connection = connectionManager.getConnection();
        	 statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	CityEarnings cityEarnings = new CityEarnings();
                cityEarnings.setCity(resultSet.getString("City"));
                cityEarnings.setCountry(resultSet.getString("Country"));
                cityEarnings.setListingCount(resultSet.getInt("ListingCount"));
                cityEarnings.setTotalEarnings(resultSet.getDouble("TotalEarnings"));
                cityEarningsList.add(cityEarnings);
            }
            
            return cityEarningsList;

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
    
    /**
     * Retrieves a list of average accommodates for listings by year.
     *
     * @return A list of `AvgAccommodatesByYear` objects representing the average accommodates by year.
     * @throws SQLException If a SQL exception occurs during the database operation.
     */
    public List<AvgAccommodatesByYear> getAvgAccommodatesByYear() throws SQLException {
        List<AvgAccommodatesByYear> avgAccommodatesByYearList = new ArrayList<>();
        String sql = "SELECT YEAR(H.HostSince) AS Year, " +
                     "AVG(CASE WHEN C.Accommodates != -1 THEN C.Accommodates END) AS AverageAccommodates " +
                     "FROM Hosts H " +
                     "JOIN Listings L ON H.HostId = L.HostId " +
                     "JOIN ListingCapacities C ON L.ListingId = C.ListingId " +
                     "WHERE YEAR(H.HostSince) IS NOT NULL " +
                     "GROUP BY Year " +
                     "ORDER BY Year;";

        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
          	 connection = connectionManager.getConnection();
          	 statement = connection.prepareStatement(sql);

              ResultSet resultSet = statement.executeQuery();

              while (resultSet.next()) {
                  AvgAccommodatesByYear avgAccommodatesByYear = new AvgAccommodatesByYear();
                  avgAccommodatesByYear.setYear(resultSet.getInt("Year"));
                  avgAccommodatesByYear.setAverageAccommodates(resultSet.getDouble("AverageAccommodates"));
                  avgAccommodatesByYearList.add(avgAccommodatesByYear);
              }
              
              return avgAccommodatesByYearList;

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
