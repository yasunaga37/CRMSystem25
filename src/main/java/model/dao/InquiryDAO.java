package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.entity.InquiryBean;

public class InquiryDAO {

	/**
	 * 顧客IDを元に問合せ一覧を取得する
	 * @param customerID 顧客ID
	 * @return List<Inquiry> 当該顧客に関する問合せ一覧
	 * @throws SQLException
	 */
	public List<InquiryBean> selectInquiryByCustomerID(int customerID) throws SQLException {
		List<InquiryBean> list = new ArrayList<InquiryBean>();
		String sql = "SELECT"
				+ "  ti.inquiry_id, "
				+ "  ti.inquiry_datetime, "
				+ "  mc.customer_name, "
				+ "  ms.status_name, "
				+ "  mu.user_name, "
				+ "  ti.inquiry_contents, "
				+ "  ti.reply_contents, "
				+ "  ti.delete_flg, "
				+ "  ti.update_datetime "
				+ "FROM"
				+ "  t_inquiry ti "
				+ "  INNER JOIN m_status ms "
				+ "    ON ti.status_code = ms.status_code "
				+ "  INNER JOIN m_customer mc "
				+ "    ON ti.customer_id = mc.customer_id "
				+ "  INNER JOIN m_user mu "
				+ "    ON mc.user_id = mu.user_id "
				+ "WHERE "
				+ "  mc.customer_id = ?";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, customerID);
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				InquiryBean inquiry = new InquiryBean();
				inquiry.setId(res.getInt("inquiry_id"));
				inquiry.setInquiryDatetime(res.getTimestamp("inquiry_datetime"));
				inquiry.setCustomer_name(res.getString("customer_name"));
				inquiry.setStatus_name(res.getString("status_name"));
				inquiry.setUser_name(res.getString("user_name"));
				inquiry.setInquiry_contents(res.getString("inquiry_contents"));
				inquiry.setReply_contents(res.getString("reply_contents"));
				inquiry.setDelete_flg(res.getInt("delete_flg"));
				inquiry.setUpdate_datetime(res.getTimestamp("update_datetime"));
				list.add(inquiry);
			}
		}
		return list;
	}

	/**
	 * 問合せIDを元に1件の問合せ情報を取得する
	 * @param inquiryID 問合せID
	 * @return Inquiry 当該IDに関する問合せ情報
	 * @throws SQLException
	 */
	public InquiryBean selectInquiryByInquiryID (int inquiryID) throws SQLException {
		InquiryBean inquiry = new InquiryBean();
		String sql = "SELECT"
				+ "  ti.inquiry_id, "
				+ "  ti.inquiry_datetime, "
				+ "  mc.customer_id, "
				+ "  mc.customer_name, "
				+ "  mc.contact_person_name, "
				+ "  ti.status_code, "
				+ "  ms.status_name, "
				+ "  mu.user_name, "
				+ "  ti.inquiry_contents, "
				+ "  ti.reply_contents, "
				+ "  ti.delete_flg, "
				+ "  ti.update_datetime "
				+ "FROM"
				+ "  t_inquiry ti "
				+ "  INNER JOIN m_status ms "
				+ "    ON ti.status_code = ms.status_code "
				+ "  INNER JOIN m_customer mc "
				+ "    ON ti.customer_id = mc.customer_id "
				+ "  INNER JOIN m_user mu "
				+ "    ON mc.user_id = mu.user_id "
				+ "WHERE "
				+ "  ti.inquiry_id = ?";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, inquiryID);
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				inquiry.setId(res.getInt("inquiry_id"));
				inquiry.setInquiryDatetime(res.getTimestamp("inquiry_datetime"));
				inquiry.setCustomer_id(res.getInt("customer_id"));
				inquiry.setCustomer_name(res.getString("customer_name"));
				inquiry.setContact_person_name(res.getString("contact_person_name"));
				inquiry.setStatus_code(res.getString("status_code"));
				inquiry.setStatus_name(res.getString("status_name"));
				inquiry.setUser_name(res.getString("user_name"));
				inquiry.setInquiry_contents(res.getString("inquiry_contents"));
				inquiry.setReply_contents(res.getString("reply_contents"));
				inquiry.setDelete_flg(res.getInt("delete_flg"));
				inquiry.setUpdate_datetime(res.getTimestamp("update_datetime"));
			}
		}
		return inquiry;
	}

//	/**
//	 * 問合せ情報を更新する
//	 * @param inquiry 問合せ情報
//	 * @return int 更新レコード数
//	 * @throws SQLException
//	 */
//	public int updateInquiryByInquiryID (Inquiry inquiry) throws SQLException {
//		int count = 0;
//		Timestamp now = new Timestamp(System.currentTimeMillis());
//		String sql = "UPDATE t_inquiry "
//					  + " SET "
//					  + " inquiry_contents = ?, reply_contents = ?, status_code = ?, update_datetime = ? "
//					  + " WHERE inquiry_id = ?";
//		try (Connection con = ConnectionManager.getConnection();
//				PreparedStatement pstmt = con.prepareStatement(sql)) {
//			pstmt.setString(1, inquiry.getInquiry_contents());
//			pstmt.setString(2, inquiry.getReply_contents());
//			pstmt.setString(3, inquiry.getStatus_code());
//			pstmt.setTimestamp(4, now);
//			pstmt.setInt(5, inquiry.getId());
//			count = pstmt.executeUpdate();
//		}
//		return count;
//	}
	
	/**
	 * 問合せ情報を更新する
	 * @param inquiry 問合せ情報
	 * @return int 更新レコード数
	 * @throws SQLException
	 */
	public int updateInquiryByInquiryID (InquiryBean inquiry) throws SQLException {
		int count = 0;
		String sql = "UPDATE t_inquiry "
					  + " SET "
					  + " inquiry_contents = ?, reply_contents = ?, status_code = ? "
					  + " WHERE inquiry_id = ?";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, inquiry.getInquiry_contents());
			pstmt.setString(2, inquiry.getReply_contents());
			pstmt.setString(3, inquiry.getStatus_code());
			pstmt.setInt(4, inquiry.getId());
			count = pstmt.executeUpdate();
		}
		return count;
	}
	
	/**
	 * 新規お問合せ登録
	 * @param inquiry
	 * @return 新規登録されたレコード数
	 * @throws SQLException
	 */
	public int insertInquiry (InquiryBean inquiry) throws SQLException {
		int count = 0;
		int customerID = inquiry.getCustomer_id();
		Timestamp inquiry_datetime = inquiry.getInquiry_datetime();
		String inquiryContents = inquiry.getInquiry_contents();
		String replyContents = inquiry.getReply_contents();
		String statusCode = inquiry.getStatus_code();
		String sql = "INSERT INTO t_inquiry (customer_id, inquiry_datetime, inquiry_contents, reply_contents, status_code) "
				+ "VALUES (?, ?, ?, ?, ?)";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1,customerID);
			pstmt.setTimestamp(2, inquiry_datetime);
			pstmt.setString(3, inquiryContents);
			pstmt.setString(4, replyContents);
			pstmt.setString(5, statusCode);
			count = pstmt.executeUpdate();
		}
		return count;
	}

	/**
	 * 問合せテーブルの問合せIDの最大値を取得する
	 * @return 問合せテーブルのレコード数
	 * @throws SQLException
	 */
	public int getMaxID() throws SQLException {
		int count = 0;
		String sql = "SELECT MAX(inquiry_id) AS cnt FROM t_inquiry";
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement()) {
			ResultSet res = stmt.executeQuery(sql);
			while (res.next()) {
				count = res.getInt("cnt");
			}
		}
//		System.out.println("count=" + count);
		return count;
	}

}
