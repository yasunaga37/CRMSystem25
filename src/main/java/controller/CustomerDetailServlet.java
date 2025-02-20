package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.CustomerLogic;
import util.LoginUserChecker;

/**
 * Servlet implementation class CustomerDetailServlet
 */
@WebServlet("/customer_detail")
public class CustomerDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * ログインチェックで問題がなければ、以下の処理を行う。(問題発生時はログインページへ差し戻し)
	 * 顧客IDを取得する
	 * 該当顧客情報を取得し顧客情報ページへ遷移する
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインチェック
		LoginUserChecker.checkLoginUser(request, response);
		// 顧客情報の取得
		CustomerLogic logic = new CustomerLogic();
		logic.setCustomerToRequestScope(request);
		// フォワード
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/customer_detail.jsp");
		rd.forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
