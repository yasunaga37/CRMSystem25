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
import model.entity.CustomerBean;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインチェック
		LoginUserChecker.checkLoginUser(request, response);
		// 顧客IDの取得
		int id = Integer.parseInt(request.getParameter("customer_id"));	
		// actionパラメータの取得
		String action = request.getParameter("action");
		String url = null;
		if ("goto_edit".equals(action)) {
			// 遷移先として顧客編集ページを設定
			url = gotoEditPage(request, id);
		} else if ("execute_edit".equals(action)) {
			// 顧客情報を編集し、レコードを更新する
			CustomerBean customer = setUpadateCustomerData(request);
			url = update(request, customer);
		} else if ("goto_detail".equals(action)) {
			// 顧客情報画面へ戻る
			url = "customer_detail";
		} else {
			// do nothing 
		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * 顧客情報、地区リスト、ユーザーリストを取得する
	 * @param request
	 * @return 遷移先URL
	 */
	private String gotoEditPage(HttpServletRequest request, int customer_id) {
		// 顧客情報を取得
		CustomerLogic clogic = new CustomerLogic();
		clogic.setCustomerToRequestScope(request, customer_id);
		// 地区リストを取得
		AreaLogic alogic = new AreaLogic();
		alogic.executeSelectAllArea(request);
		// ユーザーリストを取得
		UserLogic ulogic = new UserLogic();
		ulogic.executeSelectAllUsers(request);
		return "WEB-INF/view/customer_edit.jsp";
	}
	
	/**
	 * 既存の顧客レコードを更新し、顧客情報詳細ページへ遷移する
	 * @param request
	 * @param customer
	 * @return 遷移先URL
	 */
	private String update(HttpServletRequest request, CustomerBean customer) {
		CustomerLogic logic = new CustomerLogic();
		int count = logic.updateCustomer(customer);
		if (count <= 0) {
			System.out.println("CustomerEditServlet#update 更新に失敗しました。");
			request.setAttribute("update_failed", "顧客情報を更新出来ませんでした。");
		} else {
			request.setAttribute("update_success", "顧客情報を更新しました。");
		}
		return "customer_detail?customer_id=" + customer.getCustomer_id();		
	} 
	
	/**
	 * 顧客編集ページから送信されてデータを元にCustomerBeanを設定する
	 * @param request
	 * @return CustomerBean
	 */
	private CustomerBean setUpadateCustomerData(HttpServletRequest request) {		
		int id = Integer.parseInt(request.getParameter("customer_id"));
		CustomerLogic logic = new CustomerLogic();
		CustomerBean customer = logic.createCustomerByRequestParameter(request);
		customer.setCustomer_id(id);
		return customer;		
	}

}
