package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.UserBean;

public class UserDAO {
	
	/**
	 * 営業担当者のuserIDとpasswordを照合してログインチェックを行う
	 * @param userID
	 * @param password
	 * @return UserBean ログインユーザー
	 * @throws SQLException
	 */
	public UserBean loginCheck(String user_id, String password) throws SQLException {
		UserBean loginUser = null;
		String sql = "SELECT * FROM m_user WHERE user_id = ? AND password = ?";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, user_id);
			pstmt.setString(2, password);
			ResultSet res = pstmt.executeQuery();
			if (res.next()) {
				loginUser = new UserBean();
				loginUser.setUser_id(user_id);
				loginUser.setPassword(res.getString("password"));
				loginUser.setUser_name(res.getString("user_name"));
				loginUser.setUpdate_datetime(res.getTimestamp("update_datetime"));
			}
		} 
		return loginUser;		
	}
	
	/**
	 * ログイン可能な営業担当者リストを取得する
	 * @return 営業担当者リスト
	 * @throws SQLException
	 */
	public List<UserBean> selectAllUsers() throws SQLException{
		List<UserBean> userList = new ArrayList<UserBean>();
		String sql = "SELECT * FROM m_user";
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement()) {
			ResultSet res = stmt.executeQuery(sql);
			while (res.next()) {
				UserBean user = new UserBean();
				user.setUser_id(res.getString("user_id"));
				user.setPassword(res.getString("password"));
				user.setUser_name(res.getString("user_name"));
				user.setUpdate_datetime(res.getTimestamp("update_datetime"));
				userList.add(user);
			}
		} 
		return userList;
	}

}
