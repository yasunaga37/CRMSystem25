package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.CustomerLogic;
import logic.InquiryLogic;
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
		// 顧客IDの取得
		int id = Integer.parseInt(request.getParameter("customer_id"));	
		// 顧客詳細画面URLを取得する
		String url = gotoCustomerDetail(request, id);
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
	
	/**
	 * 顧客詳細画面へ遷移する
	 * @param request
	 * @param customer_id
	 * @return
	 */
	private String gotoCustomerDetail(HttpServletRequest request, int customer_id) {
		// 顧客情報の取得
		CustomerLogic clogic = new CustomerLogic();
		clogic.setCustomerToRequestScope(request, customer_id);
		// 問合せ情報の取得
		InquiryLogic ilogic = new InquiryLogic();
		ilogic.getInquiryListByCustomerID(request, customer_id);
		return "WEB-INF/view/customer_detail.jsp";
	}

}
