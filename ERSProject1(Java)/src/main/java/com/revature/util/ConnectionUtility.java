package com.revature.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtility {
	private Properties prop = new Properties();	
	private static ConnectionUtility connUtil = new ConnectionUtility();
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private ConnectionUtility() {
		super();
		try {
			InputStream dbProps = ConnectionUtility.class.getClassLoader().getResourceAsStream("database.properties");
			prop.load(dbProps);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("pass"));
		
	}
	
	public static ConnectionUtility getConnectionUtil() {
		return connUtil;
	}
}
