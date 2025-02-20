package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.AreaLogic;
import logic.CustomerLogic;
import logic.UserLogic;
import model.dao.CustomerDAO;
import model.entity.CustomerBean;

/**
 * Servlet implementation class CustomerInsertServlet
 */
@WebServlet("/customer_insert")
public class CustomerInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * ナビゲーションバーの「顧客登録」から呼び出され、顧客登録画面へ遷移する
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
//		System.out.println(action);
		String url = gotoInsertPage(request);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * 顧客登録画面から呼び出される
	 * RequestParameterからCustomerを生成し、DAO経由で登録する
	 * 登録後は、新規顧客のcustomer_idを取得して、新規顧客情報を取得し
	 * 顧客詳細画面へ遷移する
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String url = null;
		if ("execute_insert".equals(action)) {
			//		System.out.println("CustomerInsertServlet#doPost");goto_insert_page
			CustomerLogic logic = new CustomerLogic();
			CustomerBean customer = logic.createCustomerByRequestParameter(request);
			CustomerDAO dao = new CustomerDAO();
			try {
				int count = dao.insertCustomer(customer);
				if (count > 0) {
					System.out.println("新規顧客登録に成功しました。");
					// 新規顧客の情報を取得して顧客情報詳細画面へ
					int max_id = dao.getMaxCustomerID();
					CustomerBean newCustomer = dao.searchCustomerByID(max_id);
					request.setAttribute("customer", newCustomer);
					url = "WEB-INF/view/customer_detail.jsp";
				} else {
					System.out.println("新規顧客登録に失敗しました。");
				}
			} catch (SQLException e) {
				System.out.println("新規顧客登録に失敗しました。");
				e.printStackTrace();
			}
		} else if ("goto_list".equals(action)) {
			
		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
	/**
	 * 地区リスト、ユーザーリストを取得して、遷移先を顧客登録画面に設定する
	 * @param request
	 * @return 遷移先URL
	 */
	private String gotoInsertPage(HttpServletRequest request) {
		// 地区リストを取得
		AreaLogic alogic = new AreaLogic();
		alogic.executeSelectAllArea(request);
		// ユーザーリストを取得
		UserLogic ulogic = new UserLogic();
		ulogic.executeSelectAllUsers(request);
		// AutoIncrementにより生成されるcustomer_idを取得する
		CustomerDAO dao = new CustomerDAO();
		int customer_id = dao.getMaxCustomerID() + 1;
		request.setAttribute("customer_id", customer_id);
		return "WEB-INF/view/customer_insert.jsp";
	}

}
