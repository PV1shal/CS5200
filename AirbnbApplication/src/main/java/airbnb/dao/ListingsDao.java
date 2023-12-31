package airbnb.dao;

import airbnb.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ListingsDao {
	protected ConnectionManager connectionManager;

	private static ListingsDao instance = null;
	protected ListingsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ListingsDao getInstance() {
		if(instance == null) {
			instance = new ListingsDao();
		}
		return instance;
	}

	/**
	 *  Create a Listings instance
	 */
	public Listings create(Listings listing) throws SQLException {
		String insertListing =
			"INSERT INTO Listings(HostId,ListingUrl,Name,XlPhotoUrl,PropertyType) " +
			"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();

			insertStmt = connection.prepareStatement(insertListing,
				Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setInt(1, listing.getHost().getHostId());
			
			insertStmt.setString(2, listing.getListingUrl());
			
			insertStmt.setString(3, listing.getName());
			
			insertStmt.setString(4, listing.getXlPhotoUrl());
			
			insertStmt.setString(5, listing.getPropertyType().name());

			insertStmt.executeUpdate();
			

			resultKey = insertStmt.getGeneratedKeys();
			int listingId = -1;
			if(resultKey.next()) {
				listingId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			listing.setListingId(listingId);
			return listing;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}

	/**
	 * Delete the Listings instance.
	 * This runs a DELETE statement.
	 */
	public Listings delete(Listings listing) throws SQLException {
		String deleteListing = "DELETE FROM Listings WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteListing);
			deleteStmt.setInt(1, listing.getListingId());
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

	/**
	 * Get the Listings record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Listings instance.
	 */
	public Listings getListingById(int listingId) throws SQLException {
		String selectListing =
			"SELECT ListingId,HostId,ListingUrl,Name,XlPhotoUrl,PropertyType " +
			"FROM Listings " +
			"WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectListing);
			selectStmt.setInt(1, listingId);
			results = selectStmt.executeQuery();
			HostsDao hostDao = HostsDao.getInstance();
			if(results.next()) {
				int resultListingId = results.getInt("ListingId");
				int hostId = results.getInt("HostId");
				String listingUrl = results.getString("ListingUrl");
				String name = results.getString("Name");
				String xlPhotoUrl = results.getString("XlPhotoUrl");
				String propertyString = results.getString("PropertyType");
				Listings.PropertyType propertyType = null;
				if(propertyString != null) {
					propertyType = Listings.PropertyType.valueOf(
							Listings.PropertyType.fromString(propertyString).toString());
				}
				
				Hosts host = hostDao.getHostsByHostId(hostId);
				Listings listing = new Listings(resultListingId, host, listingUrl, name, xlPhotoUrl, propertyType);
				
				return listing;
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

	/**
	 * Get the all the Listings for a host.
	 */
	public List<Listings> getListingsForHost(Hosts host) throws SQLException {
		List<Listings> listings = new ArrayList<Listings>();
		String selectListings =
			"SELECT ListingId,HostId,ListingUrl,Name,XlPhotoUrl,PropertyType " +
			"FROM Listings " +
			"WHERE HostId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectListings);
			selectStmt.setInt(1, host.getHostId());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultListingId = results.getInt("ListingId");
				String listingUrl = results.getString("ListingUrl");
				String name = results.getString("Name");
				String xlPhotoUrl = results.getString("XlPhotoUrl");
				Listings.PropertyType propertyType = Listings.PropertyType.valueOf(
						results.getString("PropertyType"));

				Listings listing = new Listings(resultListingId, host, listingUrl, name, xlPhotoUrl, propertyType);

				listings.add(listing);
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
		return listings;
	}
	
	public List<Listings> getAllListings() throws SQLException {
		List<Listings> listings = new ArrayList<Listings>();
		String selectListings =
			"SELECT * " +
			"FROM Listings " +
			"LIMIT 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectListings);
			results = selectStmt.executeQuery();
			HostsDao hostDao = HostsDao.getInstance();
			while(results.next()) {
				int listingId = results.getInt("ListingId");
				int hostId = results.getInt("HostId");
				Hosts host = hostDao.getHostsByHostId(hostId);
				String listingUrl = results.getString("ListingUrl");
				String name = results.getString("Name");
				String xlPhotoUrl = results.getString("XlPhotoUrl");
				Listings.PropertyType propertyType = Listings.PropertyType.fromString(results.getString("PropertyType"));

				Listings listing = new Listings(listingId, host, listingUrl, name, xlPhotoUrl, propertyType);

				listings.add(listing);
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
		return listings;
	}
	
	public List<ListingFilter> getAllListingWithOtherTablesInfo()throws SQLException {
    	List<ListingFilter> listingFilterInstancesList = new ArrayList<>();
        String sql = "SELECT\n"
        		+ "    L.Name AS ListingName,\n"
        		+ "	   L.PropertyType AS PropertyType,\n"
        		+ "    PL.Street AS Street,\n"
        		+ "    PL.NeighborhoodCleansed AS NeighborhoodCleansed,\n"
        		+ "    PL.City AS City,\n"
        		+ "    LAI.Amenities,\n"
        		+ "    LP.Price,\n"
        		+ "    LP.SecurityDeposit,\n"
        		+ "    LP.CleaningFee,\n"
        		+ "    LR.CancellationPolicy,\n"
        		+ "    LR.GuestsIncluded,\n"
        		+ "    LR.MinimumNights,\n"
        		+ "    LR.MaximumNights,\n"
        		+ "    LC.BedType,\n"
        		+ "    LC.RoomType,\n"
        		+ "    LC.Accommodates,\n"
        		+ "    LC.Bathrooms,\n"
        		+ "    LC.Bedrooms,\n"
        		+ "    LC.Beds\n"
        		+ "FROM\n"
        		+ "    Listings L\n"
        		+ "JOIN\n"
        		+ "    PropertyLocations PL ON L.ListingId = PL.ListingId\n"
        		+ "JOIN\n"
        		+ "    ListingAdditionalInfo LAI ON L.ListingId = LAI.ListingId\n"
        		+ "JOIN\n"
        		+ "    ListingPricings LP ON L.ListingId = LP.ListingId\n"
        		+ "JOIN\n"
        		+ "    ListingRestrictions LR ON L.ListingId = LR.ListingId\n"
        		+ "JOIN\n"
        		+ "    ListingCapacities LC ON L.ListingId = LC.ListingId\n"
        		+ "";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(sql);
    
            results = selectStmt.executeQuery();
          
            while(results.next()) {
				String listingName = results.getString("ListingName");
				Listings.PropertyType propertyType = null;
				if (results.getString("PropertyType") != null) {
					propertyType = Listings.PropertyType.fromString(results.getString("PropertyType"));
				}
				String street = results.getString("Street");
				String neighborhoodCleansed = results.getString("NeighborhoodCleansed");
				String city = results.getString("City");
		        String amenities = results.getString("Amenities");
		        int price = results.getInt("Price");
		        int securityDeposit = results.getInt("SecurityDeposit");
		        int cleaningFee = results.getInt("CleaningFee");
		        String cancellationPolicy  = results.getString("CancellationPolicy");
		        int guestsIncluded = results.getInt("GuestsIncluded");
		        int minimumNights = results.getInt("MinimumNights");
		        int maximumNights = results.getInt("MaximumNights");
		        String bedType  = results.getString("BedType");
		        String roomType  = results.getString("RoomType");
		        int accommodates = results.getInt("Accommodates");
		        float bathrooms = results.getFloat("Bathrooms");
		        int bedrooms = results.getInt("Bedrooms");
		        int beds = results.getInt("Beds");
			       
		        ListingFilter listingFilterInstance = new ListingFilter(listingName, propertyType, amenities, street, neighborhoodCleansed, 
						city, price, securityDeposit, cleaningFee, cancellationPolicy, guestsIncluded, minimumNights, 
						maximumNights,bedType,roomType,accommodates,bathrooms,bedrooms,beds);
		        
		        listingFilterInstancesList.add(listingFilterInstance);
			}
            
            return listingFilterInstancesList;
            
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
    }
	
}
