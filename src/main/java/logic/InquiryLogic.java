package logic;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	
	/**
	 * お問合せIDから該当する問合せを取得する
	 * @param inquiry_id
	 * @return　Inquiry inquiry
	 */
	public Inquiry getInquiryByID (int inquiry_id) {		
		InquiryDAO dao = new InquiryDAO();
		Inquiry inquiry = null;
		try {
			inquiry = dao.selectInquiryByInquiryID(inquiry_id);
		} catch (SQLException e) {
			System.out.println("InquiryLogic#getInquiryByID() 問合せ情報の取得に失敗しました。");
			e.printStackTrace();
		}
		return inquiry;
	}
	
	/**
	 * お問合せ番号の「最大値 + 1」を取得しリクエストスコープに格納する
	 * @param request
	 */
	public void getMaxInquiryID(HttpServletRequest request) {
		InquiryDAO dao = new InquiryDAO();
		try {
			int max = dao.getMaxID() + 1;
			request.setAttribute("max_id", max);
		} catch (SQLException e) {
			System.out.println("InquiryLogic#InquiryLogic() MAX IDの取得に失敗しました。");
			e.printStackTrace();
		}
	}
	public Inquiry newInquiry(HttpServletRequest request) {
		String status = request.getParameter("status");
		String strDatetime1 = request.getParameter("inquiry_datetime");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(strDatetime1, formatter);

        Timestamp timestamp = Timestamp.valueOf(dateTime);
		System.out.println(status + "  " + timestamp);
		return null;		
	}

}
