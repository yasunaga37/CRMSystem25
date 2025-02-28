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
import logic.StatusLogic;
import model.entity.Inquiry;
import util.LoginUserChecker;

/**
 * Servlet implementation class InquiryInsertServlet
 */
@WebServlet("/inquiry_insert")
public class InquiryInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインチェック
		LoginUserChecker.checkLoginUser(request, response);
		// 顧客IDの取得
		int customer_id = Integer.parseInt(request.getParameter("customer_id"));
		// 新規お問合せ登録画面へ遷移する
		String url = gotoInquiryInsert(request, customer_id);
		// ディスパッチ
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		int customer_id = Integer.parseInt(request.getParameter("customer_id"));
		String url = null;
		if ("goto_customer_detail".equals(action)) {
			//顧客詳細画面へ戻る
			url = gotoCustomerDetail(request, customer_id);
		} else if ("inquiry_insert_execute".equals(action)) {
			url = executeInsert(request);
		}
		// ディスパッチ
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
	/**
	 * 顧客詳細画面の「新規お問合せ登録」押下時に呼び出される
	 * 新規お問合せ登録画面へ遷移する
	 * @param request
	 * @param customer_id
	 * @return 新規お問合せ登録画面URL
	 */
	private String gotoInquiryInsert(HttpServletRequest request, int customer_id) {
		// ステータスリストを取得し、リクエストスコープに格納する
		StatusLogic slogic = new StatusLogic();
		slogic.getStatusList(request);
		// 顧客情報を取得し、リクエストスコープに格納する
		CustomerLogic clogic = new CustomerLogic();
		clogic.setCustomerToRequestScope(request, customer_id);
		// 問い合わせ番号の最大値を取得し、リクエストスコープに格納する
		InquiryLogic iLogic = new InquiryLogic();
		iLogic.getMaxInquiryID(request);
		return "WEB-INF/view/inquiry_insert.jsp";
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
	
	private String executeInsert(HttpServletRequest request) {
//		System.out.println("AAAAAAAAA");
		InquiryLogic logic = new InquiryLogic();
		Inquiry inquiry = logic.newInquiry(request);
		return null;		
	}

}
