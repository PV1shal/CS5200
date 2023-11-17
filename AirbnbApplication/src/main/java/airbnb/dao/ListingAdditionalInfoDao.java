package airbnb.dao;

import airbnb.model.ListingAdditionalInfo;
import airbnb.model.Listings;

import java.sql.*;

public class ListingAdditionalInfoDao {

    protected ConnectionManager connectionManager;

    private static ListingAdditionalInfoDao instance = null;

    protected ListingAdditionalInfoDao() {
        connectionManager = new ConnectionManager();
    }

    public static ListingAdditionalInfoDao getInstance() {
        if(instance == null) {
            instance = new ListingAdditionalInfoDao();
        }
        return instance;
    }

    public ListingAdditionalInfo create(ListingAdditionalInfo listingAdditionalInfo) throws SQLException {
        String insertListingAdditionalInfo =
                "INSERT INTO ListingAdditionalInfo(Summary, Space, NeighborhoodOverview, Transit, HouseRules, Amenities, ListingId)" +
                "VALUES(?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertListingAdditionalInfo);
            insertStmt.setString(1, listingAdditionalInfo.getSummary());
            insertStmt.setString(2, listingAdditionalInfo.getSpace());
            insertStmt.setString(3, listingAdditionalInfo.getNeighborhoodOverview());
            insertStmt.setString(4, listingAdditionalInfo.getTransit());
            insertStmt.setString(5, listingAdditionalInfo.getHouseRules());
            insertStmt.setString(6, listingAdditionalInfo.getAmenities());
            insertStmt.setObject(7, listingAdditionalInfo.getListings());
            insertStmt.executeUpdate();

            return listingAdditionalInfo;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }

    public ListingAdditionalInfo getListingAdditionalInfoByListingId(int listingsId)throws SQLException {
        String sql = "SELECT * FROM ListingAdditionalInfo WHERE ListingId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(sql);
            selectStmt.setInt(1, listingsId);


            results = selectStmt.executeQuery();

            ListingsDao listingsDao = ListingsDao.getInstance();
            Listings listing = listingsDao.getListingById(listingsId);

            if(results.next()) {
                String summary= results.getString("Summary");
                String space = results.getString("Space");
                String NeighborhoodOverview = results.getString("NeighborhoodOverview");
                String transit = results.getString("Transit");
                String houseRules = results.getString("HouseRules");
                String amenities = results.getString("Amenities");

                ListingAdditionalInfo ListingAdditionalInfo = new ListingAdditionalInfo(listing, summary, space, NeighborhoodOverview, transit, houseRules, amenities);

                return ListingAdditionalInfo;
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

    public ListingAdditionalInfo delete(ListingAdditionalInfo listingAdditionalInfo) throws SQLException {
        String deleteListingAdditionalInfo = "DELETE FROM ListingAdditionalInfo WHERE ListingId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;

        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteListingAdditionalInfo);
            deleteStmt.setInt(1, listingAdditionalInfo.getListings().getListingId());
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
