package daoImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionDB {
	
	private Connection con;
	
	public ConnectionDB() {
		
	//get DB properties 
		
		Properties props=new Properties();
		try {
			props.load(new FileInputStream("hospital.properties"));
			
			String user=props.getProperty("user");
			String password=props.getProperty("password");
			String dburl=props.getProperty("dburl");
			
			con=DriverManager.getConnection(dburl,user,password);
			System.out.println("connection successful to "+dburl);
		} catch (IOException  | SQLException e) {
			System.out.println("file hospital.properties not found");
			e.printStackTrace();
		}
		

	}
	
	
	public Connection getCon() {
		return this.con;
	}
	
	
	public void close() throws SQLException {
		this.con.close();
	}


}
