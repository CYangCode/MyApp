package com.tc.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * ���ݿ⹤����
 * 
 */
public class DButil {

	private static Connection connection;

	// ˽�й��캯��
	private DButil() {
		Properties pro = new Properties();
		String driver = null;
		String url = null;
		String username = null;
		String password = null;
		try {
			InputStream is = DButil.class.getClassLoader().getResourceAsStream(
					"DB.properties");
			// System.out.println(is.toString());
			pro.load(is);
			driver = pro.getProperty("driver");
			url = pro.getProperty("url");
			username = pro.getProperty("username");
			password = pro.getProperty("password");
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * �ر����ݿ�
	 * 
	 * @param rs
	 * @param st
	 * @param conn
	 */
	public static void close(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rs, PreparedStatement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void close(PreparedStatement ps, Connection conn) {
		try {
			ps.close();
			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized static Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				new DButil();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	// public static void main(String[] args) {
	// System.out.println(DButil.getConnection());
	// System.out.println(DButil.getConnection());
	// }
}
