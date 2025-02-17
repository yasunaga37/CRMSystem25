package logic;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.dao.CustomerDAO;
import model.entity.CustomerBean;

public class CustomerLogic {
	
	/**
	 * CustomerDOA経由で顧客情報一覧を取得し、requestスコープに格納する
	 * @param request
	 * 	@return List<CustomerBean> list
	 */
	public String executeSelectAllCustomers (HttpServletRequest request) {
		CustomerDAO dao = new CustomerDAO();
		List<CustomerBean> list = null;
		try {
			list = dao.selectAllCustomers();
//			System.out.println("list.size=" + list.size());
			request.setAttribute("customer_list", list);
		} catch (SQLException e) {
			System.out.println("顧客一覧リストの取得に失敗しました。");
			e.printStackTrace();
		}
		return "WEB-INF/view/customer_list.jsp";
	}
	
	/**
	 * CustomerDOA経由で顧客詳細情報を取得する
	 * @param id 顧客ID
	 * @return 遷移先URL(顧客詳細画面)
	 */
	public void searchCustomerByID (HttpServletRequest request) {		
		// リクエストパラメータの取得
		String customer_id = request.getParameter("customer_id");
		int id = Integer.parseInt(customer_id);
		CustomerDAO dao = new CustomerDAO();
		CustomerBean customer = null;
		try {
			customer = dao.searchCustomerByID(id);
			request.setAttribute("customer", customer);
		} catch (SQLException e) {
			System.out.println("該当顧客の情報取得に失敗しました。");
			e.printStackTrace();
		}
	}
	
	/**
	 * CustomerDOA経由で顧客詳細情報を更新する
	 * @param request
	 * @param customer
	 * @return int 更新したレコード件数
	 */
	public int updateCustomer (HttpServletRequest request, CustomerBean customer) {
		CustomerDAO dao = new CustomerDAO();
		int count = 0;
		try {
			count = dao.update(customer);
			if (count <= 0) {
				System.out.println("該当顧客の情報更新に失敗しました。0件更新");
			}
		} catch (SQLException e) {
			System.out.println("該当顧客の情報更新に失敗しました。");
			e.printStackTrace();
		}
		return count;
	}

}
