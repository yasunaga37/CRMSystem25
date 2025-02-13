package util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.UserBean;

public class LoginUserChecker {
	
	public static void checkLoginUser (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBean loginUser = (UserBean) session.getAttribute("loginUser");
		if (loginUser == null) {
			System.out.println("ログイン中のユーザー確認が出来ませんでした。");
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/login.jsp");
			rd.forward(request, response);
		}
	}

}
