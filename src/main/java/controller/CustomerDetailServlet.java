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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインチェック
		LoginUserChecker.checkLoginUser(request, response);
		// リクエストパラメータの取得
		String cstmID = request.getParameter("cstmID");
		int customerID = Integer.parseInt(cstmID);
		System.out.println("customerID=" + customerID);
		// 顧客情報と遷移先URLの取得
		CustomerLogic logic = new CustomerLogic();
		String url = logic.executesearchCustomerByID(request, customerID);
		System.out.println("url=" + url);
		// フォワード
		RequestDispatcher rd = request.getRequestDispatcher(url);
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
