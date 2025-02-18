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
		// actionパラメータの取得
		String action = request.getParameter("action");
		String url = null;
		if ("goto_edit".equals(action)) {
			// 遷移先として顧客編集ページを設定
			url = gotoEditPage(request);
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
	private String gotoEditPage(HttpServletRequest request) {
		// 顧客情報を取得
		CustomerLogic clogic = new CustomerLogic();
		clogic.searchCustomerByID(request);
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
		int count = logic.updateCustomer(request, customer);
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
		String area = request.getParameter("area");
		String name = request.getParameter("customer_name");
		String name_kana = request.getParameter("customer_name_kana");
		String postal_code = request.getParameter("postal_code");
		String adress = request.getParameter("adress");
		
		// 姓と名を全角スペースで連結させてフルネームを生成(漢字)
		String contact_person_lname = request.getParameter("contact_person_lname");
		String contact_person_fname = request.getParameter("contact_person_fname");
		String fullName = contact_person_lname + "　" + contact_person_fname;
		String contact_person_name = fullName;	
		// 姓と名を全角スペースで連結させてフルネームを生成(カナ)
		String contact_person_lname_kana = request.getParameter("contact_person_lname_kana");
		String contact_person_fname_kana = request.getParameter("contact_person_fname_kana");
		String fullName_kana = contact_person_lname_kana + "　" + contact_person_fname_kana;
		String contact_person_name_kana = fullName_kana;	
		
		String contact_person_tel = request.getParameter("contact_person_tel");
		String user = request.getParameter("user");
//		System.out.print(id + "  " + area + "  " + name + "  " + name_kana + "  " + postal_code + "  " + contact_person_name + "  ");
//		System.out.println(contact_person_name_kana + "  " + contact_person_tel + "  " + user);
		CustomerBean bean = new CustomerBean
				(id, name, name_kana, postal_code, adress, area, contact_person_name, contact_person_name_kana, contact_person_tel, user);
		return bean;		
	}

}
