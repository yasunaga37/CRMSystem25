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
	 */
	public void executeSelectAllCustomers (HttpServletRequest request) {
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
	}
	

}
