package projo;
import java.sql.*;
public class DBconnect {
	final String driver="com.mysql.cj.jdbc.Driver";
	final String DBpath="jdbc:mysql://localhost:3306/bookdemo";

	static Connection con;


	public void connect() throws Exception,SQLException
	{

		Class.forName(driver);
		System.out.println("Establshing connection with Database......");
		con=DriverManager.getConnection(DBpath,"root","root");
		System.out.println("connection success");
	}
	public void closeconnection() throws Exception,SQLException 
	{
con.close();
}
public Connection getConnection() 
	 {
		return con;
	}
}


