package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.CustomerLogic;
import logic.UserLogic;
import util.LoginUserChecker;

/**
 * Servlet implementation class CustomerDeleteServlet
 */
@WebServlet("/customer_delete")
public class CustomerDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerDeleteServlet() {
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
//		System.out.println("CustomerDeleteServlet#doPost");
		// ログインチェック
		LoginUserChecker.checkLoginUser(request, response);
		// パラメータの取得
		String action = request.getParameter("action");
		String url = null;
		if ("goto_delete".equals(action)) {
			// 顧客情報の取得して顧客情報削除画面へ
			CustomerLogic logic = new CustomerLogic();
			logic.searchCustomerByID(request);
			url = "WEB-INF/view/customer_delete.jsp";
		} else if ("execute_delete".equals(action)) {
			url = delete(request);
		} else if ("goto_detail".equals(action))  {
			// 顧客情報の取得して顧客情報画面へ
			CustomerLogic logic = new CustomerLogic();
			logic.searchCustomerByID(request);
			url = "WEB-INF/view/customer_detail.jsp";
		} else if ("goto_list".equals(action)) {
			// 顧客一覧画面へ
			CustomerLogic logic = new CustomerLogic();		
			url = logic.executeSelectAllCustomers(request);	
		} else {
			// do nothing
		}
		// フォワード
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);		
	}
	
	/**
	 * 該当顧客のdelete_flgを「1」に更新し、論理削除を行う
	 * @param request
	 * @return 遷移先URL
	 */
	private String delete (HttpServletRequest request) {
		String url = null;
		UserLogic ulogic = new UserLogic();
		boolean userFLG = ulogic.userChrck(request);
//		System.out.println(userFLG);
		if (userFLG) {
			CustomerLogic clogic = new CustomerLogic();
			int count = clogic.deleteCustomer(request);
			if (count <= 0) {
				System.out.println("CustomerDeleteServlet#delete 削除に失敗しました。");
				request.setAttribute("delete_execute", "以下の顧客情報を削除出来ませんでした。");
			} else {
				request.setAttribute("delete_execute", "以下の顧客情報を削除しました。");
			}
			clogic.searchCustomerByID(request);
			url = "WEB-INF/view/customer_delete.jsp";	
		} else {
			// 顧客情報の取得して顧客情報削除画面へ
			request.setAttribute("delete_error", "ユーザーIDとパスワードが一致しません。");			
			CustomerLogic clogic = new CustomerLogic();
			clogic.searchCustomerByID(request);
			url = "WEB-INF/view/customer_delete.jsp";
		}		
		return url;		
	}

}
