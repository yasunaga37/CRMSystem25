package logic;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.dao.InquiryDAO;
import model.entity.Inquiry;

public class InquiryLogic {
	
	public void getInquiryListByCustomerID(HttpServletRequest request, int customerID) {
		InquiryDAO dao = new InquiryDAO();
		List<Inquiry> list = null;
		try {
			list = dao.selectInquiryByCustomerID(customerID);
			System.out.println(list.size());
			request.setAttribute("inquiry_list", list);
		} catch (SQLException e) {
			System.out.println("InquiryLogic#getInquiryByCustomerID 問合せ情報の取得に失敗しました。");
			e.printStackTrace();
		}
	}

}
