package airbnb.dao.insights;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import airbnb.dao.ConnectionManager;
import airbnb.model.insights.AmenitiesCount;
import airbnb.model.insights.AveragePrice;
import airbnb.model.insights.AvgAccommodatesByYear;
import airbnb.model.insights.ListingCost;
import airbnb.model.insights.RoomTypeCount;
import airbnb.model.insights.WirelessInternet;

/**
 * The `ListingInsightDAO` class is responsible for handling data access related to various insights about Airbnb listings.
 * It provides methods to interact with the database to retrieve insights such as listings with Wireless Internet, expensive
 * listings in neighborhoods, top amenities count, average price of listings, and room type count in a specific city.
 * This class follows the Singleton pattern to ensure a single instance is used throughout the application.
 * It uses a `ConnectionManager` for managing database connections.
 *
 * @author ambikakabra
 */
public class ListingInsightDAO {
	protected ConnectionManager connectionManager;

    private static ListingInsightDAO instance = null;

    /**
     * Constructs a new instance of the `ListingInsightDao` class.
     * It initializes the `ConnectionManager` to manage database connections.
     */
    protected ListingInsightDAO() {
        connectionManager = new ConnectionManager();
    }

    /**
     * Gets the singleton instance of the `ListingInsightDao`.
     *
     * @return The singleton instance of the `ListingInsightDao`.
     */
    public static ListingInsightDAO getInstance() {
        if(instance == null) {
            instance = new ListingInsightDAO();
        }
        return instance;
    }
    
    /**
     * Retrieves a list of listings with Wireless Internet among their amenities and high cleanliness scores.
     *
     * @return A list of `WirelessInternet` objects representing the listings.
     * @throws SQLException If a SQL exception occurs during the database operation.
     */
    public List<WirelessInternet> getListingsWithWirelessInternet() throws SQLException {
        List<WirelessInternet> wirelessInternetList = new ArrayList<>();
        String sql = "SELECT L.Name, PL.City, PL.Country " +
                     "FROM Listings L " +
                     "JOIN PropertyLocations PL ON L.ListingId = PL.ListingId " +
                     "JOIN ListingAdditionalInfo LA ON L.ListingId = LA.ListingId " +
                     "JOIN Reviews R ON L.ListingId = R.ListingId " +
                     "WHERE LA.Amenities LIKE '%Wireless Internet' " +
                     "AND R.ReviewScoresCleanliness = 10 " +
                     "AND R.ReviewScoresCheckin = 10 " +
                     "AND R.ReviewScoresLocation = 10 " +
                     "AND R.ReviewScoresCommunication = 10;";
        
        Connection connection = null;
        PreparedStatement statement = null;

        try {
        	 connection = connectionManager.getConnection();
        	 statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	WirelessInternet wirelessInternet = new WirelessInternet();
            	wirelessInternet.setName(resultSet.getString("Name"));
            	wirelessInternet.setCity(resultSet.getString("City"));
            	wirelessInternet.setCountry(resultSet.getString("Country"));
            	wirelessInternetList.add(wirelessInternet);
            }
            
            return wirelessInternetList;

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
     * Retrieves a list of expensive listings in specific neighborhoods.
     *
     * @return A list of `ListingCost` objects representing the listings.
     * @throws SQLException If a SQL exception occurs during the database operation.
     */
    public List<ListingCost> getExpensiveListingsInNeighborhood() throws SQLException {
        List<ListingCost> listingCostList = new ArrayList<>();
        String sql = "SELECT LOCATION.NeighborhoodCleansed AS LISTING_NEIGHBORHOOD, "
        		+ "LOCATION.Country AS LISTING_COUNTRY, AVG(LISTING.COST) AS LISTING_COST "
        		+ "FROM "
        		+ "(SELECT ListingId, "
        		+ "(CASE WHEN Price != -1 THEN Price END + "
        		+ "CASE WHEN SecurityDeposit != -1 THEN SecurityDeposit END + "
        		+ "CASE WHEN CleaningFee != -1 THEN CleaningFee END) AS COST "
        		+ "FROM ListingPricings) AS LISTING "
        		+ "INNER JOIN "
        		+ "(SELECT Listings.ListingId, NeighborhoodCleansed, Country "
        		+ "FROM Listings INNER JOIN PropertyLocations ON Listings.ListingId = "
        		+ "PropertyLocations.ListingId "
        		+ "WHERE Listings.PropertyType = \"APARTMENT\") AS LOCATION "
        		+ "ON LISTING.ListingId = LOCATION.ListingId "
        		+ "GROUP BY LOCATION.NeighborhoodCleansed, LOCATION.Country "
        		+ "ORDER BY LISTING_COST DESC LIMIT 10;";
       

        Connection connection = null;
        PreparedStatement statement = null;

        try {
        	 connection = connectionManager.getConnection();
        	 statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	ListingCost listingCost = new ListingCost();
            	listingCost.setListingNeighborhood(resultSet.getString("LISTING_NEIGHBORHOOD"));
            	listingCost.setListingCountry(resultSet.getString("LISTING_COUNTRY"));
            	listingCost.setListingCost(resultSet.getFloat("LISTING_COST"));
             
            	listingCostList.add(listingCost);
            }
            
            return listingCostList;

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
     * Retrieves a list of top amenities and their counts.
     *
     * @return A list of `AmenitiesCount` objects representing the top amenities.
     * @throws SQLException If a SQL exception occurs during the database operation.
     */
    public List<AmenitiesCount> getTopAmenitiesCount() throws SQLException {
        List<AmenitiesCount> amenitiesCountList = new ArrayList<>();
        String sql = "SELECT SUBSTRING_INDEX(SUBSTRING_INDEX(LA.Amenities, ',', n.n), ',', -1) AS Amenity, " +
                     "COUNT(*) AS AmenityCount " +
                     "FROM ListingAdditionalInfo LA " +
                     "CROSS JOIN (" +
                     "  SELECT a.N + b.N * 10 + 1 n " +
                     "  FROM (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION " +
                     "        SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION " +
                     "        SELECT 9) a " +
                     "  JOIN (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION " +
                     "        SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION " +
                     "        SELECT 9) b " +
                     ") n " +
                     "WHERE n.n <= 1 + (LENGTH(LA.Amenities) - LENGTH(REPLACE(LA.Amenities, ',', ''))) " +
                     "GROUP BY Amenity " +
                     "ORDER BY AmenityCount DESC " +
                     "LIMIT 10;";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
        	 connection = connectionManager.getConnection();
        	 statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	 AmenitiesCount amenitiesCount = new AmenitiesCount();
                 amenitiesCount.setAmenity(resultSet.getString("Amenity"));
                 amenitiesCount.setAmenityCount(resultSet.getInt("AmenityCount"));
                 amenitiesCountList.add(amenitiesCount);
            }
            
            return amenitiesCountList;

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
     * Retrieves the average price of listings with a certain number of reviews.
     *
     * @return An `AveragePrice` object representing the average price.
     * @throws SQLException If a SQL exception occurs during the database operation.
     */
    public AveragePrice getAveragePrice() throws SQLException {
    	AveragePrice avgPrice = new AveragePrice();
        String sql = "SELECT AVG(CASE WHEN Price != -1 THEN Price END) AS AvgPrice " +
                     "FROM ListingPricings " +
                     "WHERE ListingId IN ( " +
                     "  SELECT ListingId " +
                     "  FROM Reviews " +
                     "  WHERE NumberOfReviews > 5 " +
                     ");";

        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
       	 connection = connectionManager.getConnection();
       	 statement = connection.prepareStatement(sql);

           ResultSet resultSet = statement.executeQuery();

           if (resultSet.next()) {
               avgPrice.setAvgPrice(resultSet.getDouble("AvgPrice"));
           }
           
           return avgPrice;

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
     * Retrieves the count of a specific room type in a given city.
     *
     * @return A `RoomTypeCount` object representing the room type count.
     * @throws SQLException If a SQL exception occurs during the database operation.
     */
    public RoomTypeCount getRoomTypeCount() throws SQLException {
        RoomTypeCount roomTypeCount = new RoomTypeCount();
        String sql = "SELECT LC.RoomType, COUNT(*) AS RoomTypeCount " +
                     "FROM Listings L " +
                     "JOIN ListingCapacities LC ON L.ListingId = LC.ListingId " +
                     "JOIN PropertyLocations PL ON L.ListingId = PL.ListingId " +
                     "WHERE PL.City = 'Seattle' " +
                     "GROUP BY LC.RoomType " +
                     "ORDER BY RoomTypeCount DESC " +
                     "LIMIT 1;";

        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
       	 connection = connectionManager.getConnection();
       	 statement = connection.prepareStatement(sql);

           ResultSet resultSet = statement.executeQuery();

           if (resultSet.next()) {
               roomTypeCount.setRoomType(resultSet.getString("RoomType"));
               roomTypeCount.setRoomTypeCount(resultSet.getInt("RoomTypeCount"));
           }
           return roomTypeCount;

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
