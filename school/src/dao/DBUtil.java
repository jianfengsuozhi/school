package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	public static Connection getConnection() throws Exception{
		Connection conn;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"system", 
					"123456");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	public static void closeConnection(Connection conn) throws Exception{
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
   

}
