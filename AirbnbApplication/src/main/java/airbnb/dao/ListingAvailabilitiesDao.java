package airbnb.dao;

import airbnb.model.Hosts;
import airbnb.model.ListingAvailabilities;
import airbnb.model.Listings;

import java.sql.*;
import java.util.Date;

public class ListingAvailabilitiesDao {

    protected ConnectionManager connectionManager;

    private static ListingAvailabilitiesDao instance = null;

    protected ListingAvailabilitiesDao() {
        connectionManager = new ConnectionManager();
    }

    public static ListingAvailabilitiesDao getInstance() {
        if (instance == null) {
            instance = new ListingAvailabilitiesDao();
        }
        return instance;
    }

    public ListingAvailabilities create(ListingAvailabilities listingAvailabilities) throws SQLException {
        String insertListingAvailabilities =
                "INSERT INTO ListingAvailabilities(HasAvailability, Availability30, Availibility60," +
                        "Availibility90, Availibility365, ListingId)" +
                "VALUES(?,?,?,?,?,?);";

        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertListingAvailabilities);

            insertStmt.setString(1, listingAvailabilities.getHasAvailability());
            insertStmt.setInt(2, listingAvailabilities.getAvailability30());
            insertStmt.setInt(3, listingAvailabilities.getAvailibility90());
            insertStmt.setInt(4, listingAvailabilities.getAvailibility365());
            insertStmt.setInt(5, listingAvailabilities.getListings().getListingId());
            insertStmt.executeUpdate();

            return listingAvailabilities;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
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

    public ListingAvailabilities getListingAvailabilitiesByListingId(int listingsId)throws SQLException {
        String selectHost = "SELECT * FROM ListingAvailabilities WHERE ListingId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectHost);
            selectStmt.setInt(1, listingsId);


            results = selectStmt.executeQuery();

            ListingsDao listingsDao = ListingsDao.getInstance();
            Listings listing = listingsDao.getListingById(listingsId);

            if(results.next()) {
                String hasAvailability = results.getString("HasAvailability");
                int availability30 = results.getInt("Availability30");
                int availability60 = results.getInt("Availability60");
                int availability90 = results.getInt("Availability90");
                int availability365 = results.getInt("Availability365");

                ListingAvailabilities listingAvailabilities = new ListingAvailabilities(listing, hasAvailability, availability30, availability60, availability90, availability365);

                return listingAvailabilities;
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

    public ListingAvailabilities updateAvailability365(ListingAvailabilities listingAvailabilities, int newAvailability365) throws SQLException {
        String updateAvailability365 = "UPDATE ListingAvailabilities SET Availability365=? WHERE ListingId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateAvailability365);
            updateStmt.setInt(1, newAvailability365);
            updateStmt.setInt(2, listingAvailabilities.getListingId());
            updateStmt.executeUpdate();
            listingAvailabilities.setAvailibility365(newAvailability365);
            return listingAvailabilities;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(updateStmt != null) {
                updateStmt.close();
            }
        }
    }

    public ListingAvailabilities delete(ListingAvailabilities listingAvailabilities) throws SQLException {
        String deleteListingAvailabilities = "DELETE FROM ListingAvailabilities WHERE ListingId=?;";

        Connection connection = null;
        PreparedStatement deleteStmt = null;

        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteListingAvailabilities);
            deleteStmt.setInt(1, listingAvailabilities.getListings().getListingId());
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
