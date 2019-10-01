package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBConector {
	
	public static Connection getConnection() throws SQLException {
		Properties p = new Properties();
		try {
			p.load(DBUtil.class.getClassLoader().getResourceAsStream("resource/db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Load properties failed!");
		}
		String user = p.getProperty("user");
		String url = p.getProperty("url");
		String pwd = p.getProperty("pwd");
		return DriverManager.getConnection(url,user,pwd);
	}
	public static void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Close connection failed");
		}
	}
}
