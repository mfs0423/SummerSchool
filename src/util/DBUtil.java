package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;


public class DBUtil {
	
	private static BasicDataSource dbs;
	
	static {
		Properties p = new Properties();
		try {
			p.load(DBUtil.class.getClassLoader().getResourceAsStream("resource/db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Load properties failed!");
		}
		String driver = p.getProperty("driver");
		String user = p.getProperty("user");
		String url = p.getProperty("url");
		String pwd = p.getProperty("pwd");
		String initSize = p.getProperty("initSize");
		String maxSize = p.getProperty("maxSize");
		
		dbs = new BasicDataSource();
		dbs.setDriverClassName(driver);
		dbs.setUrl(url);
		dbs.setUsername(user);
		dbs.setPassword(pwd);
		dbs.setInitialSize(Integer.valueOf(initSize));
		dbs.setMaxActive(Integer.valueOf(maxSize));
	}
	
	public static Connection getConnection() throws SQLException {
		return dbs.getConnection();
	}
	public static void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Close connection failed");
		}
	}
	public static void main(String[] args) 
			throws SQLException {
		Connection conn = 
			DBUtil.getConnection();
		System.out.println(conn);
		DBUtil.close(conn);
	}

}
