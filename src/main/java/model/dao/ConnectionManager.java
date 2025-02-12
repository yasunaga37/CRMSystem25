package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	/**
	 * データベースURL
	 */
	private final static String URL = "jdbc:mysql://localhost:3306/customer_db25";

	/**
	 * ユーザ
	 */
	private final static String USER = "admin";

	/**
	 * パスポート
	 */
	private final static String PASSWORD = "admin";

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Connection getConnection() throws SQLException {

		// JDBCドライバの読み込み
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバが見つかりません。");
			e.printStackTrace();
		}
		return DriverManager.getConnection(URL, USER, PASSWORD);

	}
}
