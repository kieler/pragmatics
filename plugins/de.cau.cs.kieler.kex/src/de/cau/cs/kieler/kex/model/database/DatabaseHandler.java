package de.cau.cs.kieler.kex.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {

	private final String driver = "org.postgresql.Driver";

	private final String host = "localhost"; // !!! anpassen !!!

	private final String port = "5432"; // !!! anpassen !!!

	private final String database = "postgres"; // !!! anpassen !!!

	private final String user = "postgres"; // !!! anpassen !!!

	private final String password = "postgres"; // !!! anpassen !!!

	private Connection connection = null;

	/**
	 * @return Url-string for postgreSQL-database connection
	 */
	private String getUrl() {
		// PostgreSQL takes one of the following url-forms:
		// ================================================
		// jdbc:postgresql:database
		// jdbc:postgresql://host/database
		// jdbc:postgresql://host:port/database

		return ("jdbc:postgresql:"
				+ (host != null ? ("//" + host)
						+ (port != null ? ":" + port : "") + "/" : "") + database);
	}

	/**
	 * loading the JDBC driver
	 */
	private void loadJdbcDriver() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}

		System.out.println("driver loaded");
	}

	/**
	 * opening the connection
	 */
	private void openConnection() {
		try {
			connection = DriverManager.getConnection(getUrl(), user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}

		System.out.println("connection opened");
	}

	/**
	 * close the connection
	 */
	private void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}

		System.out.println("\nconnection closed");
	}

	/**
	 * loads examples of database
	 */
	public void loadExample() {
		loadJdbcDriver();
		openConnection();
		try {
			Statement statement = connection.createStatement();

			ResultSet resultSet = statement
					.executeQuery("select * from example");

			while (resultSet.next()) {
				System.out.println(resultSet.getString("title"));
			}

			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		closeConnection();
	}

}
