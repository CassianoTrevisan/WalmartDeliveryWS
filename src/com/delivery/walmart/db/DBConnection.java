package com.delivery.walmart.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;

public class DBConnection {
	
	private String userName = "root";
	private String password = "1234";
	private String dbms = "mysql";
	private String serverName = "localhost";
	private String portNumber = "3306";
	private String db_schema = "walmart_logistica";
	private static Connection conn = null;
	
	public static void main(String[] args){
		try {
			Connection conn = new DBConnection().getConnection();
			
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {// java seven stuff ;)
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public Connection getConnection() throws SQLException, ClassNotFoundException {

	   
	    Properties connectionProps = new Properties();
	    connectionProps.put("user", this.userName);
	    connectionProps.put("password", this.password);

	    
	    Class.forName("com.mysql.jdbc.Driver");
	    
        conn = DriverManager.getConnection(
                   "jdbc:" + this.dbms + "://" +
                   this.serverName +
                   ":" + this.portNumber + "/" + this.db_schema,
                   connectionProps);

	    System.out.println("Connected to database");
	    return conn;
	}
}
