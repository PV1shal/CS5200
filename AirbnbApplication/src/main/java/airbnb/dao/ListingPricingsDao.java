package airbnb.dao;

import airbnb.model.*;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListingPricingsDao {
	
	protected ConnectionManager connectionManager;

    private static ListingPricingsDao instance = null;

    protected ListingPricingsDao() {
        connectionManager = new ConnectionManager();
    }

    public static ListingPricingsDao getInstance() {
        if (instance == null) {
            instance = new ListingPricingsDao();
        }
        return instance;
    }
    
    public ListingPricings create(ListingPricings listingPricing) throws SQLException {
        String insertListingPricing = "INSERT INTO ListingPricings(ListingId, Price, WeeklyPrice, MonthlyPrice, SecurityDeposit, CleaningFee) " +
                "VALUES(?, ?, ?, ?, ?, ?)";

       Connection connection = null;
       PreparedStatement insertStmt = null;
       try {
           connection = connectionManager.getConnection();
           insertStmt = connection.prepareStatement(insertListingPricing);

           insertStmt.setInt(1, listingPricing.getListing().getListingId());
           insertStmt.setInt(2, listingPricing.getPrice());
           insertStmt.setInt(3, listingPricing.getWeeklyPrice());
           insertStmt.setInt(4, listingPricing.getMonthlyPrice());
           insertStmt.setInt(5, listingPricing.getSecurityDeposit());
           insertStmt.setInt(6, listingPricing.getCleaningFee());
         
           insertStmt.executeUpdate();
           return listingPricing;
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
    
    public ListingPricings getListingPricingsByListingId(int listingId) throws SQLException {
    	String selectListingPricing = "SELECT ListingId, Price, WeeklyPrice, MonthlyPrice, SecurityDeposit, CleaningFee "
				+ "FROM ListingPricings "
				+ "WHERE ListingId = ? ;";
    	
    	Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectListingPricing);
			selectStmt.setInt(1, listingId);
			
			results = selectStmt.executeQuery();
			ListingsDao listingDao = ListingsDao.getInstance();
			if(results.next()) {
				int retrievedLisitingId = results.getInt("ListingId");
				
		        int price = results.getInt("Price");
		        int weeklyPrice = results.getInt("WeeklyPrice");
		        int monthlyPrice =  results.getInt("MonthlyPrice");
		        
		        int securityDeposit = results.getInt("SecurityDeposit");
		        int cleaningFee = results.getInt("CleaningFee");
		        
		        Listings listing = listingDao.getListingById(retrievedLisitingId);
		 

		        ListingPricings listingPricing = new ListingPricings (listing, price, weeklyPrice, monthlyPrice, securityDeposit, cleaningFee);
				return listingPricing;
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
    
    public List<ListingPricings> getListingPricingsByPrice(int price, int offset) throws SQLException {
    	String selectListingPricing = "SELECT ListingId, Price, WeeklyPrice, MonthlyPrice, SecurityDeposit, CleaningFee "
				+ "FROM ListingPricings "
				+ "WHERE Price = ? "
				+ "LIMIT 20 "
				+"OFFSET ?;";
    	List<ListingPricings> listingPricings = new ArrayList<ListingPricings>();
    	
    	Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectListingPricing);
			selectStmt.setInt(1, price);
			selectStmt.setInt(2, offset);
			
			results = selectStmt.executeQuery();
			ListingsDao listingDao = ListingsDao.getInstance();
			while(results.next()) {
				int retrievedLisitingId = results.getInt("ListingId");
				
		       
		        int weeklyPrice = results.getInt("WeeklyPrice");
		        int monthlyPrice =  results.getInt("MonthlyPrice");
		        
		        int securityDeposit = results.getInt("SecurityDeposit");
		        int cleaningFee = results.getInt("CleaningFee");
		        
		        Listings listing = listingDao.getListingById(retrievedLisitingId);
		 

		        ListingPricings listingPricing = new ListingPricings (listing, price, weeklyPrice, monthlyPrice, securityDeposit, cleaningFee);
				listingPricings.add(listingPricing);
				
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
		return listingPricings;
    }
    
    public ListingPricings delete(ListingPricings listingPricing) throws SQLException {
	
		String deleteListingPricing = "DELETE FROM ListingPricings WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteListingPricing);
			deleteStmt.setInt(1, listingPricing.getListing().getListingId());
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