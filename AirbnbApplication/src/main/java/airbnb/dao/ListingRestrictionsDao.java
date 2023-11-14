package airbnb.dao;

import airbnb.model.*;
import airbnb.model.ListingRestrictions.CancellationPolicy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListingRestrictionsDao {
	
	protected ConnectionManager connectionManager;

    private static ListingRestrictionsDao instance = null;

    protected ListingRestrictionsDao() {
        connectionManager = new ConnectionManager();
    }

    public static ListingRestrictionsDao getInstance() {
        if (instance == null) {
            instance = new ListingRestrictionsDao();
        }
        return instance;
    }
    
    
    public ListingRestrictions create(ListingRestrictions listingRestriction) throws SQLException {
        String insertListingRestriction = "INSERT INTO ListingRestrictions (ListingId, CancellationPolicy, GuestsIncluded, MinimumNights, MaximumNights ) " +
                "VALUES(?, ?, ?, ?, ?)";

       Connection connection = null;
       PreparedStatement insertStmt = null;
       try {
           connection = connectionManager.getConnection();
           insertStmt = connection.prepareStatement(insertListingRestriction);

           insertStmt.setInt(1, listingRestriction.getListing().getListingId());
           insertStmt.setString(2, listingRestriction.getCancellationPolicy().getValue());
           insertStmt.setInt(3, listingRestriction.getGuestsInclued());
           insertStmt.setInt(4, listingRestriction.getMinimumNights());
           insertStmt.setInt(5, listingRestriction.getMaximumNights());
           
         
           insertStmt.executeUpdate();
           return listingRestriction;
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
    
    
    public ListingRestrictions getListingRestrictionsByListingId(int listingId) throws SQLException {
    	String selectListingRestriction = "SELECT ListingId, CancellationPolicy, GuestsIncluded, MinimumNights, MaximumNights "
				+ "FROM ListingRestrictions "
				+ "WHERE ListingId = ? ;";
    	
    	Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectListingRestriction);
			selectStmt.setInt(1, listingId);
			
			results = selectStmt.executeQuery();
			ListingsDao listingDao = ListingsDao.getInstance();
			
			if(results.next()) {
				int retrievedLisitingId = results.getInt("ListingId");
				
		        ListingRestrictions.CancellationPolicy cancellationPolicy  = ListingRestrictions.CancellationPolicy.fromValue(results.getString("CancellationPolicy"));
		        int guestsIncluded = results.getInt("GuestsIncluded");
		        int minimumNights =  results.getInt("MinimumNights");
		        
		        int maximumNights = results.getInt("MaximumNights");
		      
		        Listings listing = listingDao.getListingById(retrievedLisitingId);
		 

		        ListingRestrictions listingRestriction = new ListingRestrictions (listing, cancellationPolicy,guestsIncluded,
		    			minimumNights, maximumNights);
				return listingRestriction;
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
    
    public List<ListingRestrictions> getListingRestrictionsByMinimumNights(int minimumNights, int offset) throws SQLException {
    	String selectListingRestriction = "SELECT ListingId, CancellationPolicy, GuestsIncluded, MinimumNights, MaximumNights "
				+ "FROM ListingRestrictions "
				+ "WHERE MinimumNights = ? "
				+ "LIMIT 20 "
				+ "OFFSET ?;";
    	List<ListingRestrictions>  listingRestrictions = new ArrayList<ListingRestrictions>();
    	
    	Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectListingRestriction);
			selectStmt.setInt(1, minimumNights);
			selectStmt.setInt(2, offset);
			
			results = selectStmt.executeQuery();
			ListingsDao listingDao = ListingsDao.getInstance();
			
			while(results.next()) {
				int retrievedLisitingId = results.getInt("ListingId");
				
		        ListingRestrictions.CancellationPolicy cancellationPolicy  = ListingRestrictions.CancellationPolicy.fromValue(results.getString("CancellationPolicy"));
		        int guestsIncluded = results.getInt("GuestsIncluded");
		      
		        int maximumNights = results.getInt("MaximumNights");
		      
		        Listings listing = listingDao.getListingById(retrievedLisitingId);
		 

		        ListingRestrictions listingRestriction = new ListingRestrictions (listing, cancellationPolicy,guestsIncluded,
		    			minimumNights, maximumNights);
				listingRestrictions.add(listingRestriction);
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
		return listingRestrictions;
    }
    
    

    public  ListingRestrictions delete( ListingRestrictions  listingRestriction) throws SQLException {
	
		String deleteListingRestriction = "DELETE FROM ListingRestrictions WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteListingRestriction);
			deleteStmt.setInt(1,  listingRestriction.getListing().getListingId());
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

