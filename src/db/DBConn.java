package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.eclipse.jdt.internal.compiler.batch.Main;

public class DBConn {
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JSPBOARD", "JSPBOARD");
			System.out.println("성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
//		
		return connection;
		
	}
	
	public static void main(String[] args) {
		getConnection();
	}
}
