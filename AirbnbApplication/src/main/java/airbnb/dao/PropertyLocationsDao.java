package airbnb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import airbnb.model.*;

public class PropertyLocationsDao {
	protected ConnectionManager connectionManager;

	private static PropertyLocationsDao instance = null;
	protected PropertyLocationsDao() {
		connectionManager = new ConnectionManager();
	}
	public static PropertyLocationsDao getInstance() {
		if(instance == null) {
			instance = new PropertyLocationsDao();
		}
		return instance;
	}
	
	public PropertyLocations create(PropertyLocations propertyLocation) throws SQLException {
        String insertPropertyLocation = "INSERT INTO PropertyLocations(ListingId,Street,NeighborhoodCleansed,City,State,Zipcode,Country) " +
                "VALUES(?,?,?,?,?,?,?)";

       Connection connection = null;
       PreparedStatement insertStmt = null;
       try {
           connection = connectionManager.getConnection();
           insertStmt = connection.prepareStatement(insertPropertyLocation);

           insertStmt.setInt(1, propertyLocation.getListing().getListingId());
           insertStmt.setString(2, propertyLocation.getStreet());
           insertStmt.setString(3, propertyLocation.getNeighborhoodCleansed());
           insertStmt.setString(4, propertyLocation.getCity());
           insertStmt.setString(5, propertyLocation.getState());
           insertStmt.setString(6, propertyLocation.getZipcode());
           insertStmt.setString(7, propertyLocation.getCountry());
           insertStmt.executeUpdate();
           return propertyLocation;
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
    
    public PropertyLocations getPropertyLocationByListingId(int listingId) throws SQLException {
    	String selectPropertyLocation = "SELECT ListingId,Street,NeighborhoodCleansed,City,State,Zipcode,Country " +
				"FROM PropertyLocations " +
				"WHERE ListingId=?;";
    	
    	Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPropertyLocation);
			selectStmt.setInt(1, listingId);
			
			results = selectStmt.executeQuery();
			ListingsDao listingDao = ListingsDao.getInstance();
			if(results.next()) {
				int resultListingId = results.getInt("ListingId");
				Listings listing = listingDao.getListingById(resultListingId);
				String street = results.getString("Street");
				String neighborhoodCleansed = results.getString("NeighborhoodCleansed");
				String city = results.getString("City");
				String state = results.getString("State");
				String zipcode = results.getString("Zipcode");
				String country = results.getString("Country");
				
				PropertyLocations propertyLocation = new PropertyLocations(listing, street, neighborhoodCleansed, 
						city, state, zipcode, country);
				return propertyLocation;
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
    
    public List<PropertyLocations> getPropertyLocationsByCountry(String country) throws SQLException {
    	String selectPropertyLocation = "SELECT ListingId,Street,NeighborhoodCleansed,City,State,Zipcode,Country " +
				"FROM PropertyLocations " +
				"WHERE Country=? " +
				"LIMIT 10; ";
    	List<PropertyLocations> propertyLocations = new ArrayList<PropertyLocations>();
    	
    	Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPropertyLocation);
			selectStmt.setString(1, country);		
			results = selectStmt.executeQuery();
			ListingsDao listingDao = ListingsDao.getInstance();
			while(results.next()) {
				int listingId = results.getInt("ListingId");
				Listings listing = listingDao.getListingById(listingId);
				String street = results.getString("Street");
				String neighborhoodCleansed = results.getString("NeighborhoodCleansed");
				String city = results.getString("City");
				String state = results.getString("State");
				String zipcode = results.getString("Zipcode");
				String resultCountry = results.getString("Country");

				
				PropertyLocations propertyLocation = new PropertyLocations(listing, street, neighborhoodCleansed, 
						city, state, zipcode, resultCountry);
				propertyLocations.add(propertyLocation);
				
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
		return propertyLocations;
    }
    
    public PropertyLocations delete(PropertyLocations propertyLocation) throws SQLException {
	
		String deletePropertyLocation = "DELETE FROM PropertyLocations WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePropertyLocation);
			deleteStmt.setInt(1, propertyLocation.getListing().getListingId());
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
