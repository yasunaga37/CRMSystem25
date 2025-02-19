package logic;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dao.UserDAO;
import model.entity.UserBean;

public class UserLogic {

	/**
	 * UserDAO経由で営業担当者のuserIDとpasswordを照合してログインチェックを行う
	 * @param request
	 * @param user_id
	 * @param password
	 * @return boolean
	 */
	public boolean execute(HttpServletRequest request, String user_id, String password) {
		UserDAO dao = new UserDAO();
		boolean result = false;
		try {
			UserBean loginUser = dao.loginCheck(user_id, password);
			if (loginUser != null) {	
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", loginUser);
				result = true;
			}
		} catch (SQLException e) {
			System.out.println("ログイン認証に失敗しました。");
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	/**
	 * UserDAO経由でログイン可能な営業部員リストを取得し、リクエストスコープに保存する
	 */
	public void executeSelectAllUsers(HttpServletRequest request) {
		UserDAO dao = new UserDAO();
		try {
			List<UserBean> user_list = dao.selectAllUsers();
			request.setAttribute("user_list", user_list);
		} catch (SQLException e) {
			System.out.println("ログイン可能な営業部員リストの取得に失敗しました。");
			e.printStackTrace();
		}
	}
	
	public boolean userChrck (HttpServletRequest request) {
		boolean userFLG = false;
		HttpSession session = request.getSession();
		UserBean loginUser = (UserBean) session.getAttribute("loginUser");
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
//		System.out.println(user_id + "  " + password);
//		System.out.println(loginUser.getUser_id() + "  " + loginUser.getPassword());		
		if (user_id.equals(loginUser.getUser_id()) && password.equals(loginUser.getPassword())) {
			userFLG = true;
		}
		return userFLG;		
	}

}
