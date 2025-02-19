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
			splitName(request, customer);
			request.setAttribute("customer", customer);
		} catch (SQLException e) {
			System.out.println("該当顧客の情報取得に失敗しました。");
			e.printStackTrace();
		}
	}
	
	/**
	 * CustomerDOA経由で顧客詳細情報を更新する
	 * @param customer
	 * @return int 更新したレコード件数
	 */
	public int updateCustomer (CustomerBean customer) {
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
	
	/**
	 * リクエストパラメータから顧客IDを取得し、該当情報を論理削除する
	 * @param request
	 * @return 削除レコード数
	 */
	public int deleteCustomer (HttpServletRequest request) {
		int customer_id = Integer.parseInt(request.getParameter("customer_id"));
		CustomerDAO dao = new CustomerDAO();
		int count = 0;
		try {
			count = dao.deleteCustomerByID(customer_id);
			if (count <= 0) {
				System.out.println("該当顧客の情報削除に失敗しました。0件削除");
			}
		} catch (SQLException e) {
			System.out.println("該当顧客の情報削除に失敗しました。");
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 第2引数の取引先担当者名を姓と名に分解する
	 * @param request
	 * @param customer CustomerBean 
	 */
	private void splitName(HttpServletRequest request, CustomerBean customer) {
		String[] fullName = customer.getContact_person_name().split("　");
		request.setAttribute("last_name", fullName[0]);
		request.setAttribute("first_name", fullName[1]);
		String[] fullNameKana = customer.getContact_person_name_kana().split("　");
		request.setAttribute("last_name_kana", fullNameKana[0]);
		request.setAttribute("first_name_kana", fullNameKana[1]);
	}

}
