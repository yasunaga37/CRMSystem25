package logic;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.dao.InquiryDAO;
import model.entity.InquiryBean;

public class InquiryLogic {

	/**
	 * 該当顧客の問合せ一覧を取得して、リクエストスコープに格納する
	 * @param request
	 * @param customerID
	 */
	public void getInquiryListByCustomerID(HttpServletRequest request, int customerID) {
		InquiryDAO dao = new InquiryDAO();
		List<InquiryBean> inquiryList_complete = null;
		try {
			inquiryList_complete = dao.selectInquiryByCustomerID(customerID);
			//			System.out.println(list.size());
			List<InquiryBean> inquiryListWithSummary = getSummaryOfContents(inquiryList_complete);
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
	private List<InquiryBean> getSummaryOfContents(List<InquiryBean> list) {
		List<InquiryBean> summarys = new ArrayList<InquiryBean>();
		for (int i = 0; i < list.size(); i++) {
			String text;
			if (list.get(i).getInquiry_contents().length() >= 20) {
				text = list.get(i).getInquiry_contents().substring(0, 20) + ".....";
			} else {
				text = list.get(i).getInquiry_contents();
			}
			InquiryBean inquiry = list.get(i);
			InquiryBean summary = new InquiryBean(inquiry, text);
			summarys.add(summary);
		}
		return summarys;
	}

	/**
	 * お問合せIDから該当する問合せを取得する
	 * @param inquiry_id
	 * @return　Inquiry inquiry
	 */
	public InquiryBean getInquiryByID(int inquiry_id) {
		InquiryDAO dao = new InquiryDAO();
		InquiryBean inquiry = null;
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

	/**
	 * リクエストパラメータから新規問合せオブジェクトを生成する
	 * @param request
	 * @return inquiry
	 */
	public InquiryBean newInquiry(HttpServletRequest request) {
		String status = request.getParameter("status");
		Timestamp inquiry_datetime = stringToTimestamp(request, "inquiry_datetime");
		int customer_id = Integer.parseInt(request.getParameter("customer_id"));
		String inquiry_contents = request.getParameter("inquiry_contents");
		String reply_contents = request.getParameter("reply_contents");
		//		System.out.println(status + "  " + inquiry_datetime + "  " + customer_id);
		//		System.out.println(inquiry_contents + "  " + reply_contents);		
		InquiryBean inquiry = new InquiryBean(customer_id, inquiry_datetime, inquiry_contents, reply_contents, status);
		return inquiry;
	}

	/**
	 * 問合せオブジェクトを新規レコードとして挿入する
	 * @param inquiry
	 */
	public void insertNewInquiry(InquiryBean inquiry) {
		InquiryDAO dao = new InquiryDAO();
		try {
			int count = dao.insertInquiry(inquiry);
			System.out.println(count);
		} catch (SQLException e) {
			System.out.println("InquiryLogic#newInquiry() 新規問合せの挿入に失敗しました。");
			e.printStackTrace();
		}
	}

	/**
	 * お問合せ編集画面から送信されるリクエストパラメータを元に問合せ情報を取得し、
	 * 該当問合せ情報の更新処理を行う
	 * @param request
	 * @return
	 */
	public int editInquiry(HttpServletRequest request, int inquiry_id) {
		// 問合せ情報の取得		
		String status_code = request.getParameter("status");
		String inquiry_contents = request.getParameter("inquiry_contents");
		String reply_contents = request.getParameter("reply_contents");
//		System.out.println("タンタンタ～コピ   " + inquiry_id + " " + status_code + " " + inquiry_contents + " " + reply_contents);
		InquiryBean inquiry = new InquiryBean(inquiry_id, inquiry_contents, reply_contents, status_code);
		// 問合せ情報の更新処理
		InquiryDAO dao = new InquiryDAO();
		int count = 0;
		try {
			count = dao.updateInquiryByInquiryID(inquiry);
			if (count != 1) {
				System.out.println("InquiryLogic#editInquiry() 問合せ情報の更新に失敗しました-1。");
			}
		} catch (SQLException e) {
			System.out.println("InquiryLogic#editInquiry() 問合せ情報の更新に失敗しました-2。");
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 時刻文字列(yyyy-MM-dd'T'HH:mm:ss)をjava.sql.Timestamp型へ変換する
	 * @param request
	 * @param param 時刻文字列(yyyy-MM-dd'T'HH:mm:ss)
	 * @return timestamp
	 */
	private Timestamp stringToTimestamp(HttpServletRequest request, String param) {
		String strDatetime1 = request.getParameter(param) + ":00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(strDatetime1, formatter);
		Timestamp timestamp = Timestamp.valueOf(dateTime);
		return timestamp;
	}

}
