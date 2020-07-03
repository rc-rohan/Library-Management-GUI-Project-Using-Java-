package Lib_management;

import java.sql.*;
/**
 * @author rohan
 *--> while closing any JFrame make sure that the db-connections are closed from everywhere
 */
public class db_connection {
	
	
	Connection con;
	Statement stmt;

	public db_connection()
	{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lib_management","root","1898");
			stmt =con.createStatement();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	

}
