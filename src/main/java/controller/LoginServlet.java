package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.CustomerLogic;
import logic.UserLogic;
import util.LoginUserChecker;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/index.html")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginUserChecker.checkLoginUser(request, response);
		String url = null;
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		
		if ("home".equals(action) && session.getAttribute("loginUser") != null) {
			// 顧客一覧画面へ
			url = getCustomerListURL(request);
		} else if ("logout".equals(action) && session.getAttribute("loginUser") != null) {
			// セッション破棄してログアウト
			session.invalidate();
			request.setAttribute("logout", "ログアウトしました。");
			url = "WEB-INF/view/login.jsp";
		} else {
			// 初回アクセス時はログイン画面へ
			url = "WEB-INF/view/login.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログイン時のログイン認証
		String url = loginCheck(request);
//		System.out.println(url);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
	/**
	 * ログイン時のログイン認証を行う
	 * @param request
	 * @return 転移先URL
	 */
	private String loginCheck(HttpServletRequest request) {
		String url = null;
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
//		System.out.println("user_id: " +user_id);
		UserLogic ulogic = new UserLogic();
		boolean loginFLG = ulogic.execute(request, user_id, password);
		if (loginFLG) {
			url = getCustomerListURL(request);
		} else {
			request.setAttribute("login_error", "ログイン認証に失敗しました。");
			url = "WEB-INF/view/login.jsp";
		}
		return url;		
	}
	
	/**
	 * 顧客一覧画面URLを取得する
	 * @param request
	 * @return 顧客一覧画面URL
	 */
	private String getCustomerListURL(HttpServletRequest request) {
		CustomerLogic clogic = new CustomerLogic();		
		return clogic.executeSelectAllCustomers(request);	
	}

}
