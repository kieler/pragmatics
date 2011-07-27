/**
 * Java Electronic Tool Integration - jETI
 * Copyright (C) 2004-2011 Chair for Programming Systems, TU Dortmund
 *
 * This file is part of jETI.
 *
 * jETI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * jETI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with jETI. If not, see <http://www.gnu.org/licenses/>.
 */
package de.unido.ls5.eti.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The database: Connect to hsqldb
 * Use this as singletone : Database db = Database.getInstance()
 * 
 * @author David Karla
 */
public class Database {
	
    protected static Database instance = null;
	
	private Connection connection;

	private Database() {

		try {
			this.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Database getInstance(){
        if (instance == null) {
            instance = new Database();
        }
        return instance;
	}


	/*
	 * Establish connection through jdbc driver
	 */
	public void connect() throws Exception {
		try {
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			try {
				this.connection = DriverManager.getConnection("jdbc:hsqldb:database/jetidb","sa","");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	/**
	 * Returns the result of a SQL query
	 * @param query
	 * @return query result
	 */
	public ResultSet getQueryResult (String query){
			Statement st;
			ResultSet rs;
			try {
				st = this.connection.createStatement();
				rs = st.executeQuery(query);
				return rs;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
	
	/**
	 * Update datasets with this function
	 * @param query sql statement
	 */
	public void update (String query){
		Statement s;
		try {
			s = this.connection.createStatement();
			s.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Execute a sql script which is stored in a text file
	 * (see: ./config/sql -> create the tables)
	 * 
	 * @param filename
	 */
	public void runSQLScriptFromFile(String filename) {
		String query = "";
		String line;
		// load the text file
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					new FileInputStream(filename)));
			while (null != (line = in.readLine())) {
				query = query + line;
			}
			in.close();
		} catch (FileNotFoundException ex) {
			System.err.println("File " + filename + " not found.");
		} catch (Exception ex) {
			System.out.println(ex);
		}
		// run the query
		// System.out.println (query);
		Statement s = null;
		try {
			s = this.getConnection().createStatement();

		} catch (SQLException e) {
			System.err.println("Error creating database statement.");
		}
		try {
			s.execute(query);
			s.close();
			this.connection.commit();
		} catch (SQLException se) {
			System.err.println("Error executing query " + filename + ".");
			se.printStackTrace();
		}
	}

	/**
	 * Check if a table already exists
	 * This is used when the server is started:
	 * if tables do not exist -> create them)
	 * @param tableName
	 * @return
	 */
	public boolean tableExists(String tableName) {
		    String query = "select count(*) from  " + tableName;
		    Statement stmt = null;
		    try {
		      stmt = this.connection.createStatement();
		      stmt.executeQuery(query);
		      return true;
		    }
		    catch (Exception e ) {
		      // table does not exist or some other problem
		      //e.printStackTrace();
		      return false;
		    }
	}

	public void disconnect() {
		try {
			this.connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	};

}