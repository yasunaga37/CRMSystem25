package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.dao.InquiryDAO;
import model.entity.Inquiry;

public class InquiryLogic {
	
	/**
	 * 該当顧客の問合せ一覧を取得して、リクエストスコープに格納する
	 * @param request
	 * @param customerID
	 */
	public void getInquiryListByCustomerID(HttpServletRequest request, int customerID) {
		InquiryDAO dao = new InquiryDAO();
		List<Inquiry> inquiryList_complete = null;
		try {
			inquiryList_complete = dao.selectInquiryByCustomerID(customerID);
//			System.out.println(list.size());
			List<Inquiry> inquiryListWithSummary = getSummaryOfContents(inquiryList_complete);			
			request.setAttribute("inquiry_list", inquiryListWithSummary);
		} catch (SQLException e) {
			System.out.println("InquiryLogic#getInquiryByCustomerID 問合せ情報の取得に失敗しました。");
			e.printStackTrace();
		}
	}
	
	/**
	 * 既存の問合せオブジェクトの内容を「概略」に置き換える
	 * @param list
	 * @return List<Inquiry>
	 */
	private List<Inquiry> getSummaryOfContents(List<Inquiry> list) {
		List<Inquiry> summarys = new ArrayList<Inquiry>();
		for (int i = 0; i < list.size(); i++) {
			String text;
			if (list.get(i).getInquiry_contents().length() >= 20) {
				text = list.get(i).getInquiry_contents().substring(0, 20) + ".....";
			} else {
				text = list.get(i).getInquiry_contents();
			}
			Inquiry inquiry = list.get(i);
			Inquiry summary = new Inquiry(inquiry, text);
			summarys.add(summary);
		}
		return summarys;
	}

}
