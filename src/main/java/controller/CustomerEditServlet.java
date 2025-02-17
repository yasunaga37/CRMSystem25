package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.AreaLogic;
import logic.CustomerLogic;
import logic.UserLogic;
import util.LoginUserChecker;

/**
 * Servlet implementation class CustomerEditServlet
 */
@WebServlet("/customer_edit")
public class CustomerEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインチェック
		LoginUserChecker.checkLoginUser(request, response);
		// actionパラメータの取得
		String action = request.getParameter("action");
		String url = null;
		if ("goto_edit".equals(action)) {
			// 顧客情報を取得
			CustomerLogic clogic = new CustomerLogic();
			clogic.executesearchCustomerByID(request);
			// 地区情報を取得
			AreaLogic alogic = new AreaLogic();
			alogic.executeSelectAllArea(request);
			// ユーザー情報を取得
			UserLogic ulogic = new UserLogic();
			ulogic.executeSelectAllUsers(request);
			// 遷移先として顧客編集ページを設定
			url = "WEB-INF/view/customer_edit.jsp";
		} else if ("execute_edit".equals(action)) {
			
		} else {

		}{

		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
