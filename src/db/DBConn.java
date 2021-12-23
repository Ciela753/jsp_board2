package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@db.codingmonkey.co.kr:1521:xe", "JAVABOARD", "JAVABOARD");
			System.out.println("성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
//		
		return connection;
	}
}
