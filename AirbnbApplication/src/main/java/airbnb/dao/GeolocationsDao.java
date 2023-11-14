package airbnb.dao;

import airbnb.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeolocationsDao {
	protected ConnectionManager connectionManager;

	private static GeolocationsDao instance = null;
	protected GeolocationsDao() {
		connectionManager = new ConnectionManager();
	}
	public static GeolocationsDao getInstance() {
		if(instance == null) {
			instance = new GeolocationsDao();
		}
		return instance;
	}
	
	public Geolocations create(Geolocations geolocation) throws SQLException {
        String insertGeolocation = "INSERT INTO Geolocations(ListingId,Latitude,Longitude) " +
                "VALUES(?,?,?)";

       Connection connection = null;
       PreparedStatement insertStmt = null;
       try {
           connection = connectionManager.getConnection();
           insertStmt = connection.prepareStatement(insertGeolocation);

           insertStmt.setInt(1, geolocation.getListing().getListingId());
           insertStmt.setFloat(2, geolocation.getLatitude());
           insertStmt.setFloat(3, geolocation.getLongitude());
           return geolocation;
       } catch (SQLException e) {
           e.printStackTrace();
           throw e;
       } finally {
           if(connection != null) {
               connection.close();
           }
           if(insertStmt != null) {
               insertStmt.close();
           }
       }
   }
    
    public Geolocations getGeolocationByListingId(int listingId) throws SQLException {
    	String selectGeolocation = "SELECT ListingId,Latitude,Longitude " +
				"FROM Geolocations " +
				"WHERE ListingId=?;";
    	
    	Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGeolocation);
			selectStmt.setInt(1, listingId);
			
			results = selectStmt.executeQuery();
			ListingsDao listingDao = ListingsDao.getInstance();
			if(results.next()) {
				int resultListingId = results.getInt("ListingId");
				Listings listing = listingDao.getListingById(resultListingId);
				float latitude = results.getFloat("Latitude");
				float longitude = results.getFloat("Longitude");
		
				Geolocations geolocation = new Geolocations(listing, latitude, longitude);
				return geolocation;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
    }
    
    public Geolocations delete(Geolocations geolocation) throws SQLException {
	
		String deleteGeolocation = "DELETE FROM Geolocations WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteGeolocation);
			deleteStmt.setInt(1, geolocation.getListing().getListingId());
			deleteStmt.executeUpdate();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
    }
	
}