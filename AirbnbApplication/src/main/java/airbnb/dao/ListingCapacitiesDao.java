package airbnb.dao;

import airbnb.model.ListingCapacities;
import airbnb.model.Listings;

import java.sql.*;

public class ListingCapacitiesDao {

    protected ConnectionManager connectionManager;

    private static ListingCapacitiesDao instance = null;

    protected ListingCapacitiesDao() {
        connectionManager = new ConnectionManager();
    }

    public static ListingCapacitiesDao getInstance() {
        if (instance == null) {
            instance = new ListingCapacitiesDao();
        }
        return instance;
    }

    public ListingCapacities create(ListingCapacities listingCapacities) throws SQLException {
         String insertListingCapacities = "INSERT INTO ListingCapacities(BedType, RoomType, Accommodates, Bathrooms, Bedrooms, Beds, ListingId) " +
                 "VALUES(?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertListingCapacities);

            insertStmt.setString(1, listingCapacities.getBedtype().getValue());
            insertStmt.setString(2, listingCapacities.getRoomType().getValue());
            insertStmt.setInt(3, listingCapacities.getAccommodates());
            insertStmt.setFloat(4, listingCapacities.getBathrooms());
            insertStmt.setInt(5, listingCapacities.getBedrooms());
            insertStmt.setInt(6, listingCapacities.getBeds());
            insertStmt.setObject(7, listingCapacities.getListings());
            insertStmt.executeUpdate();
            return listingCapacities;
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

    public ListingCapacities getListingCapacitiesByListingId(int listingId) throws SQLException {
        String sql = "SELECT * FROM ListingCapacities WHERE listingId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(sql);
            selectStmt.setInt(1, listingId);
            results = selectStmt.executeQuery();

            ListingsDao listingsDao = ListingsDao.getInstance();
            Listings listing = listingsDao.getListingById(listingId);

            if(results.next()) {
                ListingCapacities.BedType bedType= ListingCapacities.BedType.fromValue(results.getString("BedType"));
                ListingCapacities.RoomType roomType = ListingCapacities.RoomType.fromValue(results.getString("RoomType"));
                int accommodates= results.getInt("Accommodates");
                int bathrooms = results.getInt("Bathrooms");
                int bedrooms= results.getInt("Bedrooms");
                int beds= results.getInt("Beds");

                ListingCapacities ListingCapacities = new ListingCapacities(listing, bedType, roomType,  accommodates, bathrooms, bedrooms, beds);

                return ListingCapacities;
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

    public ListingCapacities delete(ListingCapacities listingCapacities) throws SQLException {
        String deleteListingCapacities = "DELETE FROM ListingCapacities WHERE ListingId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try{
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteListingCapacities);
            deleteStmt.setInt(1, listingCapacities.getListings().getListingId());
            deleteStmt.executeUpdate();

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }
}
