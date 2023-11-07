package airbnb.dao;

import airbnb.model.*;
import airbnb.dao.ConnectionManager;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * HostsDao is a singleton class responsible for managing database operations for Host entities.
 */
public class HostsDao{
	
	
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static HostsDao instance = null;
	protected HostsDao() {
		connectionManager = new ConnectionManager();
	}
	public static HostsDao getInstance() {
		if(instance == null) {
			instance = new HostsDao();
		}
		return instance;
	}
	
	
	// here is create function for Host
	// insert - load local csv to this object
	
	public Hosts create(Hosts host) throws SQLException {
		String insertHost = "INSERT INTO Hosts(HostId,HostUrl,HostName, HostSince, HostResponseTime, HostResponseRate, HostTotalListingCount, HostVerification) "
				+ "  VALUES(?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
		
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertHost);
		
			
			insertStmt.setInt(1, host.getHostId());
			insertStmt.setString(2, host.getHostUrl());
			insertStmt.setString(3, host.getHostName());
			insertStmt.setTimestamp(4, new Timestamp(host.getHostSince().getTime()));
			insertStmt.setString(5, host.getHostResponseTime());
			insertStmt.setInt(6, host.getHostResponseRate());
			insertStmt.setInt(7, host.getHostTotalListingCount());
			insertStmt.setString(8, host.getHostVerification());
				

			insertStmt.executeUpdate();
		
			return host;
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
	
	
	public Hosts getHostsByHostId(int hostId)throws SQLException {
		String selectHost = "SELECT HostId, HostUrl, HostName, HostSince, HostResponseTime, HostResponseRate, HostTotalListingCount, HostVerification"
				+ " FROM Hosts "
				+ "WHERE HostId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHost);
			selectStmt.setInt(1, hostId);
		
			
			results = selectStmt.executeQuery();
		
			
			if(results.next()) {
				int retrievedHostId = results.getInt("HostId");
		        String hostUrl = results.getString("HostUrl");
		        String hostName = results.getString("HostName");
		        Date hostSince =  new Date(results.getTimestamp("HostSince").getTime());
		        String hostResponseTime = results.getString("HostResponseTime");
		        int hostResponseRate = results.getInt("HostResponseRate");
		        int hostTotalListingCount = results.getInt("HostTotalListingCount");
		        String hostVerification = results.getString("HostVerification");

		        Hosts host = new Hosts(retrievedHostId, hostUrl, hostName, hostSince, hostResponseTime, hostResponseRate, hostTotalListingCount, hostVerification);
		        
				return host;
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
	
	
	public List<Hosts> getHostsByHostName(String hostName)throws SQLException {
	
		List<Hosts> hosts = new ArrayList<Hosts>();
		
		String selectHost = "SELECT HostId, HostUrl, HostName, HostSince, HostResponseTime, HostResponseRate, HostTotalListingCount, HostVerification"
				+ " FROM Hosts "
				+ "WHERE HostName= ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHost);
			selectStmt.setString(1, hostName);
		
			
			results = selectStmt.executeQuery();
		
			
			while(results.next()) {
				int retrievedHostId = results.getInt("HostId");
		        String hostUrl = results.getString("HostUrl");
		       
		        Date hostSince =  new Date(results.getTimestamp("HostSince").getTime());
		        String hostResponseTime = results.getString("HostResponseTime");
		        int hostResponseRate = results.getInt("HostResponseRate");
		        int hostTotalListingCount = results.getInt("HostTotalListingCount");
		        String hostVerification = results.getString("HostVerification");

		        Hosts host = new Hosts(retrievedHostId, hostUrl, hostName, hostSince, hostResponseTime, hostResponseRate, hostTotalListingCount, hostVerification);
		        
				hosts.add(host);
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
		return hosts;
	}
	
	
	
	
	public Hosts delete(Hosts host) throws SQLException {
		
		String deleteHost = "DELETE FROM Hosts WHERE HostId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteHost);
			deleteStmt.setInt(1, host.getHostId());
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
