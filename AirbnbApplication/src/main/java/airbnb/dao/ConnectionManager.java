package airbnb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Use ConnectionManager to connect to your database instance.
 */
public class ConnectionManager {

	// User to connect to your database instance. By default, this is "root2".
	private final String user = "root";
	// Password for the user.
	private final String password = "";
	// URI to your database server. If running on the same machine, then this is "localhost".
	private final String hostName = "localhost";
	// Port to your database server. By default, this is 3307.
	private final int port= 3306;
	// Name of the MySQL schema that contains your tables.
	private final String schema = "AllAirbnb";
	// Default timezone for MySQL server.
	private final String timezone = "UTC";

	/** Get the connection to the database instance. */
	public Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			Properties connectionProperties = new Properties();
			connectionProperties.put("user", this.user);
			connectionProperties.put("password", this.password);
			connectionProperties.put("serverTimezone", this.timezone);
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new SQLException(e);
			}
			connection = DriverManager.getConnection(
					//Note: for MySql 8, add "&allowPublicKeyRetrieval=true" to the query string
			    "jdbc:mysql://" + this.hostName + ":" + this.port + "/" + this.schema + "?useSSL=false&allowPublicKeyRetrieval=true",
			    connectionProperties);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return connection;
	}

	/** Close the connection to the database instance. */
	public void closeConnection(Connection connection) throws SQLException {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
