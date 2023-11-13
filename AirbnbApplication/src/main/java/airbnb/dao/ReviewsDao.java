package airbnb.dao;

import airbnb.model.Listings;
import airbnb.model.Reviews;

import java.sql.*;

public class ReviewsDao {
    protected ConnectionManager connectionManager;

    private static ReviewsDao instance = null;

    protected ReviewsDao() {
        connectionManager = new ConnectionManager();
    }

    public static ReviewsDao getInstance() {
        if(instance == null) {
            instance = new ReviewsDao();
        }
        return instance;
    }

    public Reviews create(Reviews reviews) throws SQLException {
        String insertReviews =
                "INSERT INTO REVIEWS(NumberOfReviews, ReviewScoresRating, " +
                        "ReviewScoresAccuracy, ReviewScoresCleanliness, ReviewScoresCheckin, ReviewScoresCommunication, " +
                        "ReviewScoresLocation, ReviewScoresValue, ReviewsPerMonth, ListingId)" +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultSet = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt.setInt(1, reviews.getNumberOfReviews());
            insertStmt.setInt(2, reviews.getReviewScoresRating());
            insertStmt.setInt(3, reviews.getReviewScoresAccuracy());
            insertStmt.setInt(4, reviews.getReviewScoresCleanliness());
            insertStmt.setInt(5, reviews.getReviewScoresCheckin());
            insertStmt.setInt(6, reviews.getReviewScoresCommunication());
            insertStmt.setInt(7, reviews.getReviewScoresLocation());
            insertStmt.setInt(8, reviews.getReviewScoresValue());
            insertStmt.setFloat(9, reviews.getReviewsPerMonth());
            insertStmt.setInt(10, reviews.getListings().getListingId());
            insertStmt.executeUpdate();
            return reviews;
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
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    public Reviews getReviewByListingId(int listingId) throws SQLException {
        String selectReviews =
                "SELECT ListingId, NumberOfReviews, ReviewScoresRating, " +
                        "ReviewScoresAccuracy, ReviewScoresCleanliness, ReviewScoresCheckin, ReviewScoresCommunication, " +
                        "ReviewScoresLocation, ReviewScoresValue, ReviewsPerMonth " +
                "FROM Reviews " +
                "WHERE ListingId=?;";

        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReviews);
            selectStmt.setInt(1, listingId);
            results = selectStmt.executeQuery();
            ListingsDao listingsDao = ListingsDao.getInstance();
            Listings listing = listingsDao.getListingById(listingId);

            if(results.next()) {
                int numberOfReviews = results.getInt("NumberOfReviews");
                int reviewScoresRating = results.getInt("ReviewScoresRating");
                int reviewScoresAccuracy = results.getInt("ReviewScoresAccuracy");
                int reviewScoresCleanliness = results.getInt("ReviewScoresCleanliness");
                int reviewScoresCheckin = results.getInt("ReviewScoresCheckin");
                int reviewScoresCommunication = results.getInt("ReviewScoresCommunication");
                int reviewScoresLocation = results.getInt("ReviewScoresLocation");
                int reviewScoresValue = results.getInt("ReviewScoresValue");
                float reviewsPerMonth = results.getFloat("ReviewsPerMonth");

                return new Reviews( listing, numberOfReviews, reviewScoresRating, reviewScoresAccuracy,
                        reviewScoresCleanliness, reviewScoresCheckin, reviewScoresCommunication, reviewScoresLocation,
                        reviewScoresValue, reviewsPerMonth);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return null;
    }

    public Reviews delete(Reviews reviews) throws SQLException {
        String deleteReviews = "DELETE FROM REVIEWS WHERE ListingId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try{
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteReviews);
            deleteStmt.setInt(1, reviews.getListings().getListingId());
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
